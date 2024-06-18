package giis.demo.tkrun.DTOs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;

import giis.demo.util.ApplicationException;

public class PackageDTO {

    /* De uso general para validacion de objetos */
	private void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
	private void validateCondition(boolean condition, String message) {
		if (!condition)
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

    public void addSendPackage(String nameSender, String nameRec, String phoneSender, String phoneRec,
            String emailSender, String emailRec, String directionSender, String directionRec,
            JComboBox<String> comboCitySender, JComboBox<String> comboCityRec, JComboBox<String> comboPackageSize,
            String weight) {
        
    }
    
}
