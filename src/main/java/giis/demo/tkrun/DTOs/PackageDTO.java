package giis.demo.tkrun.DTOs;

import java.util.List;

import javax.swing.JComboBox;

import giis.demo.tkrun.model.PackageModel;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class PackageDTO {

	private static final String MSG_FILL_DATA = "Fill in all the data";
	private static final String MSG_USER_NOT_EXIST = "The user does not exist";

	private Database db = new Database();

    /* De uso general para validacion de objetos */
	private void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
	
	
	public void addSendPackage(String directionSender, String directionRec,
			JComboBox<String> comboCitySender, JComboBox<String> comboCityRec, String width, String height,
			String length, String weight, String price,String nameSender, String emailSender, String phoneSender, 
			String nameRec, String emailRec, String phoneRec) {
				validateNotNull(directionSender, MSG_FILL_DATA);
				validateNotNull(directionRec, MSG_FILL_DATA);
				validateNotNull(width, MSG_FILL_DATA);
				validateNotNull(height, MSG_FILL_DATA);
				validateNotNull(length, MSG_FILL_DATA);
				validateNotNull(weight, MSG_FILL_DATA);
				validateNotNull(price, MSG_FILL_DATA);

				String sqlInsertPackage = "INSERT INTO PACKAGES (CITYSENDER, CITYREC," + 
						"ADDRESSSENDER, ADDRESSREC, WEIGHT, HEIGHT, LENGTH, DEPTH, STATUS, PRICE," +
						"NAME_SENDER, EMAIL_SENDER, PHONE_SENDER, NAME_REC, EMAIL_REC, PHONE_REC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				db.executeUpdate(sqlInsertPackage, comboCitySender.getSelectedItem(), comboCityRec.getSelectedItem(),
						directionSender, directionRec, weight, height, length, width, "REGISTERED", price, nameSender, emailSender, phoneSender, nameRec, emailRec, phoneRec);


	}

	public List<PackageModel> getPackagesOffice(String lastSelectedKey){
        String sqlGetPackages = "SELECT package_id as packageId, name_sender, name_rec, citySender, addressSender as adressSender, cityRec as cityReceiver, addressRec as adressReceiver, status FROM Packages INNER JOIN CITY C ON ACTUAL_LOCATION = CITY_ID WHERE C.CITY = ? and status = 'READY FOR DELIVERY'";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, lastSelectedKey);
    } 

	public List<PackageModel> getPackagesVehicle(String vehicle){
        String sqlGetPackages = "SELECT p.package_id as packageId, name_sender, name_rec, citySender, addressSender as adressSender, cityRec as cityReceiver, addressRec as adressReceiver, status FROM Packages p INNER JOIN shipments s on p.package_id = s.package_id WHERE status = 'DELIVERING' and s.vehicle_id = ?";
        return db.executeQueryPojo(PackageModel.class, sqlGetPackages, vehicle);
    }
	
	public void addTracking(String object) {
		validateNotNull(object, MSG_FILL_DATA);
		String Id_sql = "Select MAX(package_id) from Packages";
		Integer id = db.executeQuerySingle(Integer.class, Id_sql) + 1;
		String sqlPackageTracking = "INSERT INTO PACKAGE_TRACKING (PACKAGE_ID, LOCATION, STATUS, TIMESTAMP) VALUES (?, ?, 'REGISTERED', CURRENT_TIMESTAMP)";
		db.executeUpdate(sqlPackageTracking, id, object);
	}

	public Integer getRouteDistance(String citySender, String cityRec) {
		if (citySender.equals(cityRec)) {
			return 0;

		} else{
			String query = "SELECT DISTANCE FROM ROUTES inner join city c on c.city_id  = origin inner join city c2 on c2.city_id = destination WHERE c.city = ? AND c2.city = ?";
			return db.executeQuerySingle(Integer.class, query, citySender, cityRec);
		}
		
		
	}
    
}
