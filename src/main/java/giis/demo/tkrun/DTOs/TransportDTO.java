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
        String sqlGetPackages = "SELECT DISTINCT p.package_id AS packageId, u1.name AS senderName, u2.name AS receiverName, p.citySender, p.addressSender AS adressSender, p.cityRec AS cityReceiver, p.addressRec AS adressReceiver, p.status FROM Packages p INNER JOIN Users u1 ON p.sender_id = u1.user_id INNER JOIN Users u2 ON p.receiver_id = u2.user_id INNER JOIN City c ON p.citySender = c.city INNER JOIN Routes r ON r.origin = c.city_id WHERE r.origin = ? AND p.status = 'REGISTERED'";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, lastSelectedKey);
    }

    // Método para validar que el paquete existe y está pendiente de envío
    private void validatePackage(String packageId) {
        String sql = "SELECT status FROM Packages WHERE package_id = ?";
        String status = db.executeQuerySingle(String.class, sql, packageId);
        if (status == null || !status.equals("REGISTERED")) {
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

    public List<String> getVehicles(String lastSelectedKey) {
        String query = "SELECT VEHICLE_ID FROM VEHICLES WHERE CURRENT_LOCATION = ? and type = 'T'";
    
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

    public Integer getVehicleCapacity(String vehicle){
        String query = "SELECT CAPACITY FROM VEHICLES WHERE VEHICLE_ID = ?";
        return db.executeQuerySingle(Integer.class, query, vehicle);
    }

    /*public List<PackageModel> getPackagesTransport(String origen, String destino){
        String sqlGetPackages = "select DISTINCT p.package_id AS packageId,  p.citySender as citySender, p.status as status from routes r inner join waypoints w on r.route_id = w.route_id " + 
                        " inner join city co on co.city_id = origin inner join city cd on cd.city_id = destination inner join Packages p on p.citySender = co.city and p.cityRec = cd.city inner join city cw on cw.city_id = w.city_id inner join city cl on cl.city_id = p.actual_location " + 
                        "where cl.city = ? and (cd.city = ? or cw.city = ?) AND P.STATUS = 'IN TRANSIT' order by package_id";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, origen, destino, destino);
    }*/

    public List<PackageModel> getPackagesTransport(String origen, String destino) {
        String sqlGetPackages = "SELECT DISTINCT p.package_id AS packageId, p.citySender AS citySender, p.status AS status " + 
                                "FROM routes r " + 
                                "INNER JOIN waypoints w ON r.route_id = w.route_id " + 
                                "INNER JOIN city co ON co.city_id = r.origin " + 
                                "INNER JOIN city cd ON cd.city_id = r.destination " + 
                                "INNER JOIN Packages p ON p.citySender = co.city AND p.cityRec = cd.city " + 
                                "INNER JOIN city cl ON cl.city_id = p.actual_location " + 
                                "LEFT JOIN city cw ON cw.city_id = w.city_id " + 
                                "WHERE cl.city = ? AND (cd.city = ? OR cw.city = ?) " + 
                                "AND p.status = 'IN TRANSIT' " + 
                                "ORDER BY p.package_id";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, origen, destino, destino);
    }
    
    
    

    public PackageModel getPackage(String packageId){
        String sqlGetPackage = "SELECT package_id packageId, cityRec cityReceiver FROM Packages WHERE package_id = ?";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackage, packageId).get(0);
    }

    public void updateVehicleMove(String location, String vehicle) {
        String query = "UPDATE VEHICLES SET CURRENT_LOCATION = ? WHERE vehicle_id = ?";
        db.executeUpdate(query, location, vehicle);
    }

    public void updatePackageMove(Integer location, String destino, String packageId) {
        String query = "";
        if (destino.equals("S")) {
            query = "UPDATE PACKAGES SET actual_location = ?, STATUS='READY FOR DELIVERY' WHERE PACKAGE_ID = ?";
        } else {
            query = "UPDATE PACKAGES SET actual_location = ? WHERE PACKAGE_ID = ?";
        }
        db.executeUpdate(query, location, packageId);

    }
    
    public Integer getCity(String city) {
        String query = "SELECT CITY_id FROM CITY WHERE CITY = ?";
        return db.executeQuerySingle(Integer.class, query, city);
    }

}
