package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.PackageView;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.DTOs.PackageDTO;
import giis.demo.util.SwingUtil;
import javafx.event.ActionEvent;

import javax.swing.JOptionPane;

public class PackageController {

    private PackageView view;
    private SwingMain main;
    private static PackageDTO dto;
    

    public PackageController(PackageDTO packageDTO, PackageView packageView, SwingMain swingMain) {
        this.dto = packageDTO;
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
        view.getFrame().setLocationRelativeTo(null);
        view.getFrame().setVisible(true);
	}

    public void sendPackage() {
		dto.addSendPackage(view.getDirectionSender(), view.getDirectionRec(), view.getComboCitySender(), view.getComboCityRec(), view.getWidth(), view.getHeight(), view.getLength(), view.getWeight(), view.getPrice(), view.getIdSender(), view.getEmailSender(), view.getPhoneSender(), view.getIdRec(), view.getEmailRec(), view.getPhoneRec());
        JOptionPane.showMessageDialog(null, "Registration of the package has been completed");
    }

    public void saveTracking() {
        dto.addTracking(view.getComboCitySender().getSelectedItem().toString());
    }

    private void calculatePrice() {
        try {
            String lengthText = view.getLength();
            String widthText =  view.getWidth();
            String heightText = view.getHeight();
            String weightText = view.getWeight();

            if (lengthText.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Length is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (widthText.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Width is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (heightText.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Height is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (weightText.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Weight is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (view.getComboCityRec().getSelectedItem() == null || view.getComboCityRec().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please, select the cities to calculate the price.",
                        "Ciudades no seleccionadas", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double length = Double.parseDouble(lengthText);
            double width = Double.parseDouble(widthText);
            double height = Double.parseDouble(heightText);
            double weight = Double.parseDouble(weightText);

            double price = calculatePrice(length, width, height, weight);
            view.getLblPrice().setText(String.format("%.2f€", price));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculatePrice(double length, double width, double height, double weight) {
        // Implement your pricing logic here. For example:
        double volume = length * width * height;
        double basePrice = 5.0;
        double distance = new Double(this.getDistance());
        double price = basePrice + (volume * 0.001) + (weight * 1.5) + (distance * 0.04);
    
        return price;
    }

    private double getDistance(){
        String citySender = view.getComboCitySender().getSelectedItem().toString();
        String cityRec =  view.getComboCityRec().getSelectedItem().toString();
        return dto.getRouteDistance(citySender, cityRec);
    }
}
