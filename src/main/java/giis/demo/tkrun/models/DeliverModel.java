package giis.demo.tkrun.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.dtos.DTOPackage;
import giis.demo.util.Database;

public class DeliverModel {

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

    public List<DTOPackage> getPackages(String lastSelectedKey){
        String sqlGetPackages = "SELECT p.package_id as packageId, name_sender, name_rec, citySender, addressSender as adressSender, cityRec as cityReceiver, addressRec as adressReceiver, status FROM Packages p inner join shipments s on p.package_id = s.package_id where s.vehicle_id = ?";
        return db.executeQueryPojo(DTOPackage.class, sqlGetPackages, lastSelectedKey);
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

        // DeliverModel.java
        // DeliverModel.java

    public String updateFail(String packageId) {
        // Obtenemos la ubicación actual del paquete para el log de tracking.
        // Asumimos que la tabla Packages tiene una columna 'actual_location' que es un ID de ciudad,
        // o 'cityRec' si queremos registrar el evento en la ciudad de destino.
        String locationQuery = "SELECT cityRec FROM Packages WHERE package_id = ?";
        String location = db.executeQuerySingle(String.class, locationQuery, packageId);
        
        // Si no encontramos la ubicación, usamos un valor por defecto para no fallar.
        if (location == null) {
            location = "Unknown Location";
        }

        // 1. Verificamos el número de intentos actuales
        String countQuery = "SELECT COUNT(*) FROM Delivery_Attempts WHERE package_id = ?";
        int currentAttempts = db.executeQuerySingle(Integer.class, countQuery, packageId);

        if (currentAttempts >= 3) {
            // Si ya se ha llegado al límite, no hacemos nada más y devolvemos el mensaje.
            return "Maximum delivery attempts reached for this package.";
        }

        // 2. Insertamos el nuevo intento fallido en la tabla Delivery_Attempts
        int newAttemptNumber = currentAttempts + 1;
        String insertAttemptQuery = "INSERT INTO Delivery_Attempts (package_id, attempt_number, status, timestamp) VALUES (?, ?, 'Failed', CURRENT_TIMESTAMP)";
        db.executeUpdate(insertAttemptQuery, packageId, newAttemptNumber);
        
        // 3. Actualizamos el estado en la tabla principal de Paquetes
        String newStatus = "DELIVERY ATTEMPT FAILED";
        if (newAttemptNumber >= 3) {
            newStatus = "HELD AT OFFICE"; // Si este es el tercer intento, cambiamos el estado final
        }
        String updatePackageStatusQuery = "UPDATE Packages SET status = ? WHERE package_id = ?";
        db.executeUpdate(updatePackageStatusQuery, newStatus, packageId);
        
        // Insertamos el evento en la tabla de seguimiento para que sea visible.
        String trackStatusMessage = "Delivery Attempt #" + newAttemptNumber + " Failed";
        String insertTrackingQuery = "INSERT INTO Package_Tracking (package_id, location, status, timestamp) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        db.executeUpdate(insertTrackingQuery, packageId, location, trackStatusMessage);

        // 5. Devolvemos el mensaje de éxito para el controlador
        return "Delivery attempt #" + newAttemptNumber + " has been registered.";
    }
}



    


    
    

