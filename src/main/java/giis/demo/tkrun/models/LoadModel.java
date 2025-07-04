package giis.demo.tkrun.models;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import giis.demo.util.Database;

public class LoadModel {

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

    public void updatePackageStatus(String packageId, String vehicleId, String location) {
		String sqlUpdatePackage = "UPDATE PACKAGES SET STATUS = 'DELIVERING' WHERE PACKAGE_ID = ?";
		db.executeUpdate(sqlUpdatePackage, packageId);
		String sqlUploadPackage = "INSERT INTO SHIPMENTS (PACKAGE_ID, VEHICLE_ID, PICKUP_DATE) VALUES (?,?,CURRENT_TIMESTAMP)";
		db.executeUpdate(sqlUploadPackage, packageId, vehicleId);
        String sqlPackageTracking = "INSERT INTO PACKAGE_TRACKING (PACKAGE_ID, LOCATION, STATUS, TIMESTAMP) VALUES (?, ?, 'DELIVERING', CURRENT_TIMESTAMP)";
        db.executeUpdate(sqlPackageTracking, packageId, location);
	}

    public void updatePackageStatusUnload(String packageId, String vehicleId, String location) {
		String sqlUpdatePackage = "UPDATE PACKAGES SET STATUS = 'READY FOR DELIVERY' WHERE PACKAGE_ID = ?";
		db.executeUpdate(sqlUpdatePackage, packageId);
		String sqlUploadPackage = "DELETE FROM SHIPMENTS WHERE PACKAGE_ID = ? AND VEHICLE_ID = ?";
		db.executeUpdate(sqlUploadPackage, packageId, vehicleId);
        String sqlPackageTracking = "INSERT INTO PACKAGE_TRACKING (PACKAGE_ID, LOCATION, STATUS, TIMESTAMP) VALUES (?, ?, 'READY FOR DELIVERY', CURRENT_TIMESTAMP)";
        db.executeUpdate(sqlPackageTracking, packageId, location);
	}
    
}
