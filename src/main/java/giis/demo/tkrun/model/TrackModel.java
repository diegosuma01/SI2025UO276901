package giis.demo.tkrun.model;

public class TrackModel {

    private String packageId;
    private String location;
    private String status;
    private String timestamp;


    public TrackModel() {
        }

    public TrackModel(String packageId) {
        this.packageId = packageId;
    }

    public TrackModel(String packageId, String location, String status, String timestamp) {
        this.packageId = packageId;
        this.location = location;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }   

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
}
