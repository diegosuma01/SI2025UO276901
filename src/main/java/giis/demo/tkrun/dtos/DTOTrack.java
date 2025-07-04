package giis.demo.tkrun.dtos;

public class DTOTrack {

    private String packageId;
    private String location;
    private String status;
    private String timestamp;


    public DTOTrack() {
        }

    public DTOTrack(String packageId) {
        this.packageId = packageId;
    }

    public DTOTrack(String packageId, String location, String status, String timestamp) {
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
