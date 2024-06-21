package giis.demo.tkrun.DTOs;

import java.util.List;

import javax.swing.JComboBox;

import giis.demo.tkrun.model.UsersModel;
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

	private void validateId(String id, String message) {
		validateNotNull(id, MSG_FILL_DATA);
		String sqlIdExistSend = 
				"SELECT user_id userId FROM USERS WHERE user_ID = ?";

		List<UsersModel> listUsers = db.executeQueryPojo(UsersModel.class, sqlIdExistSend, id);
		if (listUsers.isEmpty()) {
			throw new ApplicationException(message);
		}
	}

	public double getRouteDistance(String originCity, String destinationCity) {
		// Si la ciudad de origen y destino son iguales, devolver 0
		if (originCity.equals(destinationCity)) {
			return 0.0;
		}
	
		// Consulta SQL para obtener la distancia entre el origen y el destino
		String sqlGetRouteDistance = 
			"SELECT COALESCE(distance, 0) AS distance " +
			"FROM Routes AS r " +
			"INNER JOIN City AS c1 ON r.origin = c1.city_id " +
			"INNER JOIN City AS c2 ON r.destination = c2.city_id " +
			"WHERE c1.city = ? AND c2.city = ?";
		
		// Ejecutar la consulta y obtener la distancia
		Double distance = db.executeQuerySingle(Double.class, sqlGetRouteDistance, originCity, destinationCity);
		
		// Devolver la distancia obtenida, o 0 si no se encontró ninguna ruta
		return distance;
	}
	
	
	
	public void addSendPackage(String idSender, String idRec, String directionSender, String directionRec,
			JComboBox<String> comboCitySender, JComboBox<String> comboCityRec, String width, String height,
			String length, String weight, String price) {
				validateNotNull(directionSender, MSG_FILL_DATA);
				validateNotNull(directionRec, MSG_FILL_DATA);
				validateNotNull(width, MSG_FILL_DATA);
				validateNotNull(height, MSG_FILL_DATA);
				validateNotNull(length, MSG_FILL_DATA);
				validateNotNull(weight, MSG_FILL_DATA);
				validateNotNull(price, MSG_FILL_DATA);
				validateId(idSender, MSG_USER_NOT_EXIST);
				validateId(idRec, MSG_USER_NOT_EXIST);

				String sqlInsertPackage = "INSERT INTO PACKAGES (SENDER_ID, RECEIVER_ID, CITYSENDER, CITYREC," + 
						"ADDRESSSENDER, ADDRESSREC, WEIGHT, HEIGHT, LENGTH, DEPTH, STATUS, PRICE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				db.executeUpdate(sqlInsertPackage, idSender, idRec, comboCitySender.getSelectedItem(), comboCityRec.getSelectedItem(),
						directionSender, directionRec, weight, height, length, width, "PENDING", price);


	}
    
}
