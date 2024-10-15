package giis.demo.tkrun.DTOs;

import java.util.ArrayList;
import java.util.List;

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

    
    
}
