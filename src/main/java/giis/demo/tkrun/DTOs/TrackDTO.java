package giis.demo.tkrun.DTOs;

import java.util.List;

import giis.demo.tkrun.model.TrackModel;
import giis.demo.util.Database;

public class TrackDTO {

    private Database db = new Database();

    public List<TrackModel> getPackagesTrack(String packageId){
        String sqlGetTrack = "SELECT t.package_id AS packageId, t.location, t.status, t.timestamp FROM Package_Tracking t WHERE t.package_id = ?";
        return db.executeQueryPojo(TrackModel.class, sqlGetTrack, packageId);
    }

    public void updateTracking(String packageId, String status, String location){
        String sql = "INSERT INTO Package_Tracking (package_id, location, status, timestamp) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        db.executeUpdate(sql, packageId, status, location);
    }
    
}
