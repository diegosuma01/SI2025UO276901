package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.DeliverView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.dtos.DTOPackage;
import giis.demo.tkrun.models.DeliverModel;

public class DeliverController {
    private DeliverView view;
    private DeliverModel model;
    private SwingMain main;

    public DeliverController(DeliverModel deliverModel, DeliverView deliverView, SwingMain swingMain) {
        this.model = deliverModel;
        this.view = deliverView;
        this.main = swingMain;
        this.initView();
    }

    public void initController() {
        view.getDoneButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> doneDeliver()));
        
        view.getOfficeComboBox()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getVehicles()));

        view.getVehicleComboBox()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getDeliveries()));

        view.getFailedButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> registerFail()));
    }
    
    public void initView() {
        view.getFrame().setLocationRelativeTo(null);
        view.getFrame().setVisible(true);
        getVehicles();
    }

    private void getVehicles() {
        String origen = getSelectedOrigin();
        updateVehicleComboBox(origen);
    }

    private void updateVehicleComboBox(String city) {
        List<String> vehicles = getVehiclesForCity(city);
        view.getVehicleComboBox().removeAllItems();
        for (String vehicle : vehicles) {
            view.getVehicleComboBox().addItem(vehicle);
        }
    }

    // Simula una consulta a la base de datos para obtener vehículos disponibles en una ciudad específica
    private List<String> getVehiclesForCity(String city) {
        String lastSelectedKey = this.getSelectedOrigin();
        return model.getVehicles(lastSelectedKey);
    }

    public String getSelectedOrigin() {
        return (String) view.getOfficeComboBox().getSelectedItem();
    }

    

    private void getDeliveries() {
        if (view.getVehicleComboBox() != null && view.getVehicleComboBox().getSelectedItem() != null) {
            String lastSelectedKey = view.getVehicleComboBox().getSelectedItem().toString();
            List<DTOPackage> listPackages = model.getPackages(lastSelectedKey);
            TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] {
                "packageId", "name_sender", "name_rec", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
            view.getPackagesTable().setModel(tmodel);
            SwingUtil.autoAdjustColumns(view.getPackagesTable());
            TableColumnModel columnModel = view.getPackagesTable().getColumnModel();
        }
    }

        private void doneDeliver() {
        // 1. CONTROL DE EXCEPCIÓN: ¿Se ha seleccionado un paquete?
        String selectedId = SwingUtil.getSelectedKey(view.getPackagesTable());
        if (selectedId == null || selectedId.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "Please select a package from the table.", "No Package Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // 2. CONTROL DE EXCEPCIÓN: ¿Los campos de texto están llenos?
        String name = view.getNameField().getText().trim();
        String id = view.getIdField().getText().trim();
    
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "Please enter the recipient's Name and ID to confirm delivery.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 3. CONTROL DE EXCEPCIÓN: ¿El formato del DNI es válido? (8 números y 1 letra)
        if (!id.matches("\\d{8}[A-Za-z]")) {
            JOptionPane.showMessageDialog(view.getFrame(), "The ID format is invalid. It must be 8 digits followed by a letter.", "Invalid ID Format", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // 4. Si todo es válido, procedemos con la lógica de negocio
        try {
            model.updatePackageStatus(selectedId);
            
            // Limpiamos los campos y refrescamos la tabla
            view.getNameField().setText("");
            view.getIdField().setText("");
            getDeliveries(); // Refresca la lista de paquetes en el vehículo

            JOptionPane.showMessageDialog(view.getFrame(), "Package " + selectedId + " marked as DELIVERED.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(), "An error occurred while confirming delivery: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }      
    }
        
        

        public void registerFail() {
        // 1. CONTROL DE EXCEPCIÓN: ¿Se ha seleccionado un paquete?
        String selectedId = SwingUtil.getSelectedKey(view.getPackagesTable());
        if (selectedId == null || selectedId.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "Please select a package from the table to register a failed attempt.", "No Package Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Lógica de negocio
        try {
            // Llamamos al método del modelo, que ahora devuelve un mensaje
            String resultMessage = model.updateFail(selectedId);
            
            // Mostramos el mensaje devuelto por el modelo
            if (resultMessage.startsWith("Maximum")) {
                JOptionPane.showMessageDialog(view.getFrame(), resultMessage, "Delivery Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), resultMessage, "Delivery Attempt Registered", JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Refrescamos la vista para que el paquete desaparezca o cambie de estado
            getDeliveries();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(), "An error occurred while registering the failed attempt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
}




