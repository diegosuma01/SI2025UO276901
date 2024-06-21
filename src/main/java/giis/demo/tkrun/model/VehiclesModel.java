package giis.demo.tkrun.model;

public class VehiclesModel {

    private String vehicleId;
    private int capacity;
    private String currentLocation;

    public VehiclesModel() {
    }

    public VehiclesModel(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehiclesModel(String vehicleId, int capacity, String currentLocation) {
        this.vehicleId = vehicleId;
        this.capacity = capacity;
        this.currentLocation = currentLocation;

    }

    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    
}
