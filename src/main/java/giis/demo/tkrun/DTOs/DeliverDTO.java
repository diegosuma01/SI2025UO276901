package giis.demo.tkrun.DTOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.model.PackageModel;
import giis.demo.util.Database;

public class DeliverDTO {

     private Database db = new Database();


     public List<String> getVehicles(String lastSelectedKey) {
        String query = "SELECT VEHICLE_ID FROM VEHICLES WHERE CURRENT_LOCATION = ? and type = 'V'";
    
        // Obtener el resultado como List<Object[]> desde la base de datos
        List<Object[]> rawResults = db.executeQueryArray(query, lastSelectedKey);
    
        // Convertir los resultados a una lista de String
        List<String> vehicles = new ArrayList<>();
        for (Object[] row : rawResults) {
            if (row.length > 0 && row[0] != null) {
                vehicles.add(row[0].toString()); // Convertir el primer valor a String y añadirlo a la lista
            }
        }
        return vehicles;
    }

    public List<PackageModel> getPackages(String lastSelectedKey){
        String sqlGetPackages = "SELECT p.package_id as packageId, name_sender, name_rec, citySender, addressSender as adressSender, cityRec as cityReceiver, addressRec as adressReceiver, status FROM Packages p inner join shipments s on p.package_id = s.package_id where s.vehicle_id = ?";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, lastSelectedKey);
    } 

    public void updatePackageStatus(String id){
        String sqlUpdatePackage = "UPDATE PACKAGES SET STATUS = 'DELIVERED' WHERE PACKAGE_ID = ?";
        db.executeUpdate(sqlUpdatePackage, id);
        String sqlPackageTracking = "INSERT INTO PACKAGE_TRACKING (PACKAGE_ID, LOCATION, STATUS, TIMESTAMP) VALUES (?, 'DESTINATION', 'DELIVERED', CURRENT_TIMESTAMP)";
        db.executeUpdate(sqlPackageTracking, id);
        //ACTUALZIAR TABLA DONDE SALEN LOS PAQUETES PARA QUITAR EL QUE 
        String sqlUpdateShipment = "DELETE FROM SHIPMENTS WHERE PACKAGE_ID = ?";
        db.executeUpdate(sqlUpdateShipment, id);
    }
/* 
    public void updateFail(String packageId) throws SQLException {
        // SQL para verificar el número de intentos actuales
        String selectQuery = "SELECT MAX(attempt_number) as attempt_number FROM Delivery_Attempts WHERE package_id = ?";
        try (PreparedStatement selectStmt = db.getConnection().prepareStatement(selectQuery)) {
            selectStmt.setString(1, packageId); // Ajuste para usar String
            ResultSet rs = selectStmt.executeQuery();
    
            int currentAttempt = 0;
            if (rs.next()) {
                currentAttempt = rs.getInt("attempt_number"); // Obtiene el último número de intento
            }
    
            // Si el número de intentos es 3, lanzamos una excepción
            if (currentAttempt >= 3) {
                throw new SQLException("Maximum delivery attempts reached for package ID: " + packageId);
            }
    
            // Si no hay intentos previos, insertamos el primer intento
            if (currentAttempt == 0) {
                String insertQuery = "INSERT INTO Delivery_Attempts (package_id, attempt_number, status, timestamp) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
                db.executeUpdate(insertQuery, packageId, 1, "Failed");
            } else {
                // Si hay intentos previos, hacemos un nuevo insert incrementando el número de intentos
                String insertQuery = "INSERT INTO Delivery_Attempts (package_id, attempt_number, status, timestamp) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
                db.executeUpdate(insertQuery, packageId, currentAttempt + 1, "Failed");
            }
        }
    }*/

    public void updateFail(String packageId) {
        String selectQuery = "SELECT attempt_number FROM Delivery_Attempts WHERE package_id = ?";
        int currentAttempts = 0;

        try (PreparedStatement stmt = db.getConnection().prepareStatement(selectQuery)) {
            stmt.setString(1, packageId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    currentAttempts = rs.getInt("attempt_number");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving attempt number: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there's an error
        }

        // Check if the current attempts are less than 3
        if (currentAttempts < 3) {
            if (currentAttempts == 0) {
                // First attempt
                String insertQuery = "INSERT INTO Delivery_Attempts (package_id, attempt_number, status, timestamp) VALUES (?, ?, 'Failed', CURRENT_TIMESTAMP)";
                try (PreparedStatement stmt = db.getConnection().prepareStatement(insertQuery)) {
                    stmt.setString(1, packageId);
                    stmt.setInt(2, 1); // Set to first attempt
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "First try has been registered.", "Delivery Attempt", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error inserting first delivery attempt: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Update the attempt number for existing attempts
                String updateQuery = "UPDATE Delivery_Attempts SET attempt_number = attempt_number + 1, status = 'Failed', timestamp = CURRENT_TIMESTAMP WHERE package_id = ?";
                try (PreparedStatement stmt = db.getConnection().prepareStatement(updateQuery)) {
                    stmt.setString(1, packageId);
                    stmt.executeUpdate();
                    // Show a message depending on the attempt number
                    int newAttemptNumber = currentAttempts + 1; // Calculate new attempt number
                    if (newAttemptNumber == 2) {
                        JOptionPane.showMessageDialog(null, "Second try has been registered.", "Delivery Attempt", JOptionPane.INFORMATION_MESSAGE);
                    } else if (newAttemptNumber == 3) {
                        JOptionPane.showMessageDialog(null, "Third try has been registered.", "Delivery Attempt", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error updating delivery attempt: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Notify user if the maximum number of attempts has been reached
            JOptionPane.showMessageDialog(null, "Maximum number of delivery attempts reached for this package.", "Delivery Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}



    


    
    

