package giis.demo.tkrun.controller;

import giis.demo.tkrun.models.PackageModel;
import giis.demo.tkrun.view.PackageView;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.models.PackageModel;
import giis.demo.util.SwingUtil;
import java.util.List;
import javax.swing.JOptionPane;

public class PackageController {

    private PackageView view;
    private SwingMain main;
    private static PackageModel model;
    

    public PackageController(PackageModel packageModel, PackageView packageView, SwingMain swingMain) {
        this.model = packageModel;
        this.view = packageView;
        this.main = swingMain;
        this.initView();
    }

    public void initController() {
        view.getBtnSend().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
            if (view.validateFields()) {
                sendPackage();
                saveTracking();
            }
        }));
            
        view.getBtnSend().addActionListener(e -> SwingUtil.exceptionWrapper(() -> saveTracking()));
        view.getBtnPrice().addActionListener(e -> SwingUtil.exceptionWrapper(() -> calculatePrice()));
    }

    public void initView() {
        List<String> cities = model.getAllCities();

        view.setCityLists((cities));

        view.getFrame().setLocationRelativeTo(null);
        view.getFrame().setVisible(true);
	}

    public void sendPackage() {
		model.addSendPackage(view.getDirectionSender(), view.getDirectionRec(), view.getComboCitySender(), view.getComboCityRec(), view.getWidth(), view.getHeight(), view.getLength(), view.getWeight(), view.getPrice(), view.getIdSender(), view.getEmailSender(), view.getPhoneSender(), view.getIdRec(), view.getEmailRec(), view.getPhoneRec());
        JOptionPane.showMessageDialog(null, "Registration of the package has been completed");
    }

    public void saveTracking() {
        model.addTracking(view.getComboCitySender().getSelectedItem().toString());
    }

    private void calculatePrice() {
        try {
            // Obtenemos los valores de la vista
            String lengthText = view.getLength();
            String widthText =  view.getWidth();
            String heightText = view.getHeight();
            String weightText = view.getWeight();

            // ===== ¡AQUÍ ESTÁ LA SOLUCIÓN! =====
            // Comprobamos si alguno de los campos numéricos está vacío antes de continuar.
            // Usamos .trim() para asegurarnos de que no son solo espacios en blanco.
            if (lengthText == null || lengthText.trim().isEmpty() ||
                widthText == null || widthText.trim().isEmpty() ||
                heightText == null || heightText.trim().isEmpty() ||
                weightText == null || weightText.trim().isEmpty()) {
                
                // Si alguno está vacío, mostramos un error claro y salimos del método.
                JOptionPane.showMessageDialog(view.getFrame(), "Please fill all package dimension and weight fields to calculate the price.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Detiene la ejecución
            }
            
            // También validamos que las ciudades estén seleccionadas
            if (view.getComboCitySender().getSelectedIndex() <= 0 || view.getComboCityRec().getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(view.getFrame(), "Please select both origin and destination cities.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Si todos los campos tienen texto, intentamos convertirlos a número
            double length = Double.parseDouble(lengthText);
            double width = Double.parseDouble(widthText);
            double height = Double.parseDouble(heightText);
            double weight = Double.parseDouble(weightText);

            // Obtenemos la distancia (código de getDistance no cambia)
            double distance = getDistance();
            
            // Calculamos el precio (código de calculatePrice con parámetros no cambia)
            double price = calculatePrice(length, width, height, weight, distance);
            view.getLblPrice().setText(String.format("%.2f€", price));

        } catch (NumberFormatException ex) {
            // Este catch ahora solo se activará si el texto no es un número válido
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid input. Please enter valid numbers for dimensions and weight.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            // Este catch ahora solo se activará si getDistance() devuelve null (ruta no encontrada)
            JOptionPane.showMessageDialog(view.getFrame(), "A route could not be found between the selected cities.", "Route Not Found", JOptionPane.ERROR_MESSAGE);
            view.getLblPrice().setText("N/A");
        }
    }

    private double calculatePrice(double length, double width, double height, double weight, double distance) {
        double volume = length * width * height;
        double basePrice = 5.0;
        double price = basePrice + (volume * 0.001) + (weight * 1.5) + (distance * 0.04);
        return price;
    }

    private double getDistance(){
        String citySender = view.getComboCitySender().getSelectedItem().toString();
        String cityRec =  view.getComboCityRec().getSelectedItem().toString();
        Integer distance = model.getRouteDistance(citySender, cityRec);
        if (distance == null) {
            throw new NullPointerException("Route not found"); 
        }
        return distance;
    }
}
