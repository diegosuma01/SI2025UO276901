package giis.demo.tkrun.DTOs;

import java.util.List;

import giis.demo.tkrun.model.PackageModel;
import giis.demo.util.Database;

public class PickDTO {


    private Database db = new Database();

    public List<PackageModel> getPackagesOffice(String lastSelectedKey){
        String sqlGetPackages = "SELECT DISTINCT p.package_id AS packageId, p.name_sender AS senderName, p.name_rec AS receiverName, p.citySender, p.addressSender AS adressSender, p.cityRec AS cityReceiver, p.addressRec AS adressReceiver, p.status FROM Packages p  WHERE p.citySender = ? AND p.status = 'REGISTERED'";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, lastSelectedKey);
    }

    public void updatePackageStatus(String packageId){
        String sqlUpdatePackage = "UPDATE Packages SET status = 'IN TRANSIT' WHERE package_id = ?";
        db.executeUpdate(sqlUpdatePackage, packageId);
    }

    public void updateloction(String packageId, String location){
        String sql = "UPDATE Packages SET actual_location = (SELECT city_id FROM City WHERE city = ?) WHERE package_id = ?";
        db.executeUpdate(sql, location, packageId);
    }

}
