package giis.demo.tkrun.dtos;

import java.awt.List;
import java.util.ArrayList;

public class DTORoutes {

    private String routeId;
    private String originCity;
    private String destinationCity;
    private ArrayList<String> waypoints;
    private String waypointsTable;
    private int distance;


    public DTORoutes() {
    }

    public DTORoutes(String routeId) {
        this.routeId = routeId;
    }

    public DTORoutes(String routeId, String originCity, String destinationCity, ArrayList<String> waypoints, String waypointsTable) {
        this.routeId = routeId;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.waypoints = waypoints;
        this.waypointsTable = waypointsTable;
    }

    public String getRouteId() {
        return routeId;
    }
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getoriginCity() {
        return originCity;
    }
    public void setoriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getdestinationCity() {
        return destinationCity;
    }
    public void setdestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public ArrayList<String> getWaypoints() {
        return waypoints;
    }
    public void setWaypoints(ArrayList <String> waypoints) {
        this.waypoints = waypoints;
    }

    public String getWaypointsTable() {
        return waypointsTable;
    }
    public void setWaypointsTable(String waypointsTable) {
        this.waypointsTable = waypointsTable;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
}
