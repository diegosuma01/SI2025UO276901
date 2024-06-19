package giis.demo.tkrun.DTOs;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;

import giis.demo.tkrun.model.UsersModel;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class UserDTO {

	private static final String MSG_FILL_DATA = "Fill in all the data";
	private static final String MSG_VALID_PHONE = "The phone must be a number";
	private static final String MSG_VALID_EMAIL = "The email is not valid";

	private Database db=new Database();

    /* De uso general para validacion de objetos */
	private void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}

	private int validatePhoneNumber(String phone, String message) {
		try {
			return Integer.parseInt(phone);
		} catch (NumberFormatException e) {
			throw new ApplicationException(message);
		}
	}
	
	private void validateEmail(String email, String message) {
        // Expresión regular para validar un correo electrónico
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		// Compilar la expresión regular en un patrón
		Pattern pattern = Pattern.compile(regex);

		// Crear un objeto Matcher con la cadena de texto proporcionada
        Matcher matcher = pattern.matcher(email);

		if(!matcher.matches())
			throw new ApplicationException(message);
	
	}

    public void addSaveUser(String user_id, String name, String phone, String email) {
				// rellenar la base de datos con los datos del usuario
				validateNotNull(user_id, MSG_FILL_DATA);
				validateNotNull(name, MSG_FILL_DATA);
				validateNotNull(phone, MSG_FILL_DATA);
				validateNotNull(email, MSG_FILL_DATA);

				validatePhoneNumber(phone, MSG_VALID_PHONE);
				validateEmail(email, MSG_VALID_EMAIL);

				String sqlInsertUserSend = "INSERT INTO USERS (user_id, name, email, phone) VALUES (?,?,?,?)";
				db.executeUpdate(sqlInsertUserSend, user_id, name, email, phone); 
    }
    
}
