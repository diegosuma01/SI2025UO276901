package giis.demo.tkrun.DTOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.model.RoutesModel;
import giis.demo.tkrun.model.TransportModel;
import giis.demo.tkrun.model.VehiclesModel;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class TransportDTO {


    private static final String MSG_FILL_DATA = "Fill in all the data";
    private static final String MSG_INVALID_PACKAGE = "Invalid package";
    private static final String MSG_INVALID_ROUTE = "Invalid route";
    private static final String MSG_INVALID_VEHICLE = "Invalid vehicle";
    private static final String MSG_PACKAGE_ALREADY_SHIPPED = "Package already shipped";

    private Database db = new Database();

    public List<VehiclesModel> getVehicles() {
        String sqlGetVehicles = "Select vehicle_id vehicleId from vehicles";
        return db.executeQueryPojo(VehiclesModel.class, sqlGetVehicles);
    }

    public List<RoutesModel> getRoutes(){
        String sqlGetRoutes = "SELECT r.route_id AS routeId, (SELECT city FROM City WHERE city_id = r.origin) AS originCity, (SELECT city FROM City WHERE city_id = r.destination) AS destinationCity, (SELECT GROUP_CONCAT(w3.city, ', ') FROM waypoints AS w2 INNER JOIN City AS w3 ON w2.city_id = w3.city_id WHERE w2.route_id = r.route_id) AS waypointsTable FROM routes AS r";
        return db.executeQueryPojo(RoutesModel.class, sqlGetRoutes);
    }

    public List<PackageModel> getPackages(String lastSelectedKey){
        String sqlGetPackages = "SELECT DISTINCT p.package_id AS packageId, u1.name AS senderName, u2.name AS receiverName, p.citySender, p.addressSender AS adressSender, p.cityRec AS cityReceiver, p.addressRec AS adressReceiver, p.status FROM Packages p INNER JOIN Users u1 ON p.sender_id = u1.user_id INNER JOIN Users u2 ON p.receiver_id = u2.user_id INNER JOIN City c ON p.citySender = c.city INNER JOIN Routes r ON r.origin = c.city_id WHERE r.origin = ? AND p.status = 'PENDING'";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, lastSelectedKey);
    }

    // Método para validar que el paquete existe y está pendiente de envío
    private void validatePackage(String packageId) {
        String sql = "SELECT status FROM Packages WHERE package_id = ?";
        String status = db.executeQuerySingle(String.class, sql, packageId);
        if (status == null || !status.equals("PENDING")) {
            throw new ApplicationException(MSG_INVALID_PACKAGE);
        }
    }

    // Método para validar que la ruta existe y obtener su distancia
    private int validateRouteAndGetDistance(int routeId) {
        String sql = "SELECT distance FROM Routes WHERE route_id = ?";
        Integer distance = db.executeQuerySingle(Integer.class, sql, routeId);
        if (distance == null) {
            throw new ApplicationException(MSG_INVALID_ROUTE);
        }
        return distance;
    }

    // Método para validar que el vehículo existe y obtener su ID
    private int validateAndGetVehicleId(String selectedVehicle) {
        String sql = "SELECT vehicle_id FROM Vehicles WHERE name = ?";
        Integer vehicleId = db.executeQuerySingle(Integer.class, sql, selectedVehicle);
        if (vehicleId == null) {
            throw new ApplicationException(MSG_INVALID_VEHICLE);
        }
        return vehicleId;
    }

    // Método para calcular la fecha estimada de entrega
    private LocalDate calculateDeliveryDate(double distance) {
        // Supongamos que por cada 200 km se suman 1 día
        int daysToAdd = (int) Math.ceil(distance / 200.0);
        return LocalDate.now().plusDays(daysToAdd);
    }

    // Método para marcar un paquete como enviado (shipped)
    public void markPackageAsShipped(String packageId, int routeId, String selectedVehicle) {
        validatePackage(packageId);
        int vehicleId = Integer.parseInt(selectedVehicle);
        int distance = validateRouteAndGetDistance(routeId);
        LocalDate pickUpDate = LocalDate.now();
        LocalDate deliveryDate = calculateDeliveryDate(distance);

        String sqlUpdatePackage = "UPDATE Packages SET status = 'SHIPPED' WHERE package_id = ?";
        db.executeUpdate(sqlUpdatePackage, packageId);

        String sqlInsertShipment = "INSERT INTO Shipments (package_id, route_id, vehicle_id, pickup_date, delivery_date) VALUES (?, ?, ?, ?, ?)";
        db.executeUpdate(sqlInsertShipment, packageId, routeId, vehicleId, pickUpDate, deliveryDate);
    }

    // Método para obtener todos los envíos realizados
    public List<TransportModel> getAllShipments() {
        List<TransportModel> shipments = new ArrayList<>();
        String sql = "SELECT * FROM Shipments";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TransportModel shipment = new TransportModel();
                shipment.setPackageId(rs.getString("package_id"));
                shipment.setRouteId1(rs.getInt("route_id"));
                shipment.setVehicleId(rs.getInt("vehicle_id"));
                shipment.setPickUpDate(rs.getDate("pick_up_date").toLocalDate());
                shipment.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
                shipments.add(shipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    public int getRouteDistance(int routeId) {
        String sql = "SELECT distance FROM Routes WHERE route_id = ?";
        List<RoutesModel> distanceStr = db.executeQueryPojo(RoutesModel.class, sql, routeId);
        int distance = distanceStr.get(0).getDistance();
        return distance;
    }
    
    
    public void updatePackageStatus(String packageId, String status) {
        String sql = "UPDATE Packages SET status = ? WHERE package_id = ?";
        db.executeUpdate(sql, status, packageId);
    }
    
    

    public void createShipment(TransportModel shipment) {
        String sql = "INSERT INTO Shipments (package_id, route_id, vehicle_id, pick_up_date, delivery_date) " +
                     "VALUES (?, ?, ?, ?, ?)";
        LocalDate pickUpDate = shipment.getPickUpDate();
        LocalDate deliveryDate = shipment.getDeliveryDate();
        db.executeUpdate(sql, shipment.getPackageId(), shipment.getRouteId(), shipment.getVehicleId(),
                                java.sql.Date.valueOf(pickUpDate), java.sql.Date.valueOf(deliveryDate)); 
    }

}
