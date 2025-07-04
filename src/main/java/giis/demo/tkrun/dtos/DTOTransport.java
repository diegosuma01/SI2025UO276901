package giis.demo.tkrun.dtos;

import java.time.LocalDate;
import java.util.List;

public class DTOTransport {
    
    private String packageId;
    private String routeId;
    private int routeId1;
    private String originCity;
    private String destinationCity;
    private double distance; // en kilómetros
    private int vehicleId;
    private LocalDate pickUpDate;
    private LocalDate deliveryDate;

    public DTOTransport() {
    }

    public DTOTransport(String routeId, String originCity, String destinationCity, double distance) {
        this.routeId = routeId;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.distance = distance;
    }

    public DTOTransport(String packageId, int routeId2, int vehicleId, LocalDate pickUpDate, LocalDate deliveryDate) {
        
        this.packageId = packageId;
        this.routeId1 = routeId2;
        this.vehicleId = vehicleId;
        this.pickUpDate = pickUpDate;
        this.deliveryDate = deliveryDate;
        
        }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    public void setRouteId1(int routeId1) {
        this.routeId1 = routeId1;
    }
    
    public int getRouteId1() {
        return routeId1;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    // Método para calcular la fecha estimada de entrega
    public LocalDate calculateDeliveryDate() {
        // Supongamos que por cada 200 km se suman 1 día
        int daysToAdd = (int) Math.ceil(distance / 200.0);
        return LocalDate.now().plusDays(daysToAdd);
    }
/* 
    public boolean createShipment(TransportModel shipment) {
        throw new UnsupportedOperationException("Unimplemented method 'createShipment'");
    }

    public int getRouteDistance(int routeId2) {
        throw new UnsupportedOperationException("Unimplemented method 'getRouteDistance'");
    }

    public int getVehicleIdByName(String selectedVehicle) {
        throw new UnsupportedOperationException("Unimplemented method 'getVehicleIdByName'");
    }

    public boolean updatePackageStatus(int packageId, String string) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePackageStatus'");
    }

    public List<TransportModel> getAllShipments() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllShipments'");
    } */
}



