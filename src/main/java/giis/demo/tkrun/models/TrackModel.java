package giis.demo.tkrun.models;

import java.util.List;

import giis.demo.tkrun.dtos.DTOTrack;
import giis.demo.util.Database;

public class TrackModel {

    private Database db = new Database();

    public List<DTOTrack> getPackagesTrack(String packageId){
        String sqlGetTrack = "SELECT t.package_id AS packageId, t.location, t.status, t.timestamp FROM Package_Tracking t WHERE t.package_id = ?";
        return db.executeQueryPojo(DTOTrack.class, sqlGetTrack, packageId);
    }

    public void updateTracking(String packageId, String status, String location){
        String sql = "INSERT INTO Package_Tracking (package_id, location, status, timestamp) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        db.executeUpdate(sql, packageId, status, location);
    }
    
}
