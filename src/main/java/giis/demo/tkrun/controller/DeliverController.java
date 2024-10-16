package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.DeliverView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.DTOs.DeliverDTO;
import giis.demo.tkrun.model.PackageModel;

public class DeliverController {
    private DeliverView view;
    private DeliverDTO dto;
    private SwingMain main;

    public DeliverController(DeliverDTO deliverDTO, DeliverView deliverView, SwingMain swingMain) {
        this.dto = deliverDTO;
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
        return dto.getVehicles(lastSelectedKey);
    }

    public String getSelectedOrigin() {
        return (String) view.getOfficeComboBox().getSelectedItem();
    }

    

    private void getDeliveries() {
        if (view.getVehicleComboBox() != null && view.getVehicleComboBox().getSelectedItem() != null) {
            String lastSelectedKey = view.getVehicleComboBox().getSelectedItem().toString();
            List<PackageModel> listPackages = dto.getPackages(lastSelectedKey);
            TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] {
                "packageId", "name_sender", "name_rec", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
            view.getPackagesTable().setModel(tmodel);
            SwingUtil.autoAdjustColumns(view.getPackagesTable());
            TableColumnModel columnModel = view.getPackagesTable().getColumnModel();
        }
        
        }

        private void doneDeliver() {
            // Check if there is a selected package in the table
            String selectedId = SwingUtil.getSelectedKey(view.getPackagesTable());
            if (selectedId == null || selectedId.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "No package selected. Please select a package from the list.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            // Check if the ID and Name fields are filled
            String id = view.getIdField().getText().trim();
            String name = view.getNameField().getText().trim();
        
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Please enter both ID and Name.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            // Proceed with updating the package status if validation passes
            dto.updatePackageStatus(selectedId);
            view.getIdField().setText("");
            view.getNameField().setText("");
            getDeliveries();         
        }
        
        

        public void registerFail() {
            String selectedId = SwingUtil.getSelectedKey(view.getPackagesTable());
            if (selectedId != null) {
                dto.updateFail(selectedId); // Llama al método que actualiza el intento
            } else {
                JOptionPane.showMessageDialog(null, "No package selected.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
}




