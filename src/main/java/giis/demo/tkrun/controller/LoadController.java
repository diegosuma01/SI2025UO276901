package giis.demo.tkrun.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.dtos.DTOPackage;
import giis.demo.tkrun.models.LoadModel;
import giis.demo.tkrun.models.PackageModel;
import giis.demo.tkrun.view.LoadView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;

public class LoadController {
    private LoadView view;
    private LoadModel model;
    private SwingMain main;
    private PackageModel packageDTO;

    public LoadController(LoadModel loadModel, LoadView loadView, SwingMain swingMain, PackageModel packageDTO) {
        this.model = loadModel;
        this.view = loadView;
        this.main = swingMain;
        this.packageDTO = packageDTO;

        this.initView();
    }

    public void initView() {
        view.getFrame().setLocationRelativeTo(null);
        view.getFrame().setVisible(true);
    }

    public void initController() {
        view.getLoadButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getLoad()));   
                
        view.getUnloadButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getUnload()));
        
        view.getOfficeComboBox()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getVehicles()));
        
        view.getTruckComboBox()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getPackagesVehicle()));
    }

    private void getVehicles() {
        String origen = getSelectedOrigin();
        updateVehicleComboBox(origen);
        getPackagesOffice(origen);
    }

    private void updateVehicleComboBox(String city) {
        List<String> vehicles = getVehiclesForCity(city);
        view.getTruckComboBox().removeAllItems();
        for (String vehicle : vehicles) {
            view.getTruckComboBox().addItem(vehicle);
        }
    }

    private List<String> getVehiclesForCity(String city) {
        String lastSelectedKey = this.getSelectedOrigin();
        return model.getVehicles(lastSelectedKey);
    }

    public String getSelectedOrigin() {
        return (String) view.getOfficeComboBox().getSelectedItem();
    }



    private void getLoad() {
    // 1. CONTROL DE EXCEPCIÓN: ¿Se ha seleccionado un camión?
    Object selectedVehicleItem = view.getTruckComboBox().getSelectedItem();
    if (selectedVehicleItem == null) {
        JOptionPane.showMessageDialog(view.getFrame(), "Please select a vehicle first.", "No Vehicle Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String vehicle = selectedVehicleItem.toString();

    // 2. CONTROL DE EXCEPCIÓN: ¿Se ha seleccionado un paquete de la tabla de la oficina?
    String lastSelectedKey = SwingUtil.getSelectedKey(view.getPackagesAtOfficeTable());
    if (lastSelectedKey == null || lastSelectedKey.isEmpty()) {
        JOptionPane.showMessageDialog(view.getFrame(), "Please select a package from the 'Packages at Office' table to load.", "No Package Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Si todo es correcto, ejecutamos la lógica de negocio
    try {
        model.updatePackageStatus(lastSelectedKey, vehicle, getSelectedOrigin()); 
        
        // 4. Refrescamos ambas tablas para ver el resultado del movimiento
        String origen = getSelectedOrigin();
        getPackagesOffice(origen);
        getPackagesVehicle();
        
        // 5. Damos feedback al usuario
        JOptionPane.showMessageDialog(view.getFrame(), "Package " + lastSelectedKey + " loaded onto vehicle " + vehicle + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        // Captura de cualquier error inesperado
        JOptionPane.showMessageDialog(view.getFrame(), "An error occurred while loading the package: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void getUnload() {
    // 1. CONTROL DE EXCEPCIÓN: ¿Se ha seleccionado un camión?
    Object selectedVehicleItem = view.getTruckComboBox().getSelectedItem();
    if (selectedVehicleItem == null) {
        JOptionPane.showMessageDialog(view.getFrame(), "Please select a vehicle first.", "No Vehicle Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String vehicle = selectedVehicleItem.toString();

    // 2. CONTROL DE EXCEPCIÓN: ¿Se ha seleccionado un paquete de la tabla del camión?
    String lastSelectedKey = SwingUtil.getSelectedKey(view.getPackagesAtTruckTable());
    if (lastSelectedKey == null || lastSelectedKey.isEmpty()) {
        JOptionPane.showMessageDialog(view.getFrame(), "Please select a package from the 'Packages in Truck' table to unload.", "No Package Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Si todo es correcto, ejecutamos la lógica de negocio
    try {
        model.updatePackageStatusUnload(lastSelectedKey, vehicle, getSelectedOrigin()); 
        
        // 4. Refrescamos ambas tablas para ver el resultado del movimiento
        String origen = getSelectedOrigin();
        getPackagesOffice(origen);
        getPackagesVehicle();

        // 5. Damos feedback al usuario
        JOptionPane.showMessageDialog(view.getFrame(), "Package " + lastSelectedKey + " unloaded from vehicle " + vehicle + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        // Captura de cualquier error inesperado
        JOptionPane.showMessageDialog(view.getFrame(), "An error occurred while unloading the package: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void getPackagesOffice(String office) {
        List<DTOPackage> listPackages = packageDTO.getPackagesOffice(office);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] {
            "packageId", "name_sender", "name_rec", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
        view.getPackagesAtOfficeTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getPackagesAtOfficeTable());
        TableColumnModel columnModel = view.getPackagesAtOfficeTable().getColumnModel();
        }

        private void getPackagesVehicle() {
            if (view.getTruckComboBox() != null && view.getTruckComboBox().getSelectedItem() != null) {
                String vehicle = view.getTruckComboBox().getSelectedItem().toString();
                List<DTOPackage> listPackages = packageDTO.getPackagesVehicle(vehicle);
                TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] {
                    "packageId", "name_sender", "name_rec", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
                view.getPackagesAtTruckTable().setModel(tmodel);
                SwingUtil.autoAdjustColumns(view.getPackagesAtTruckTable());
                TableColumnModel columnModel = view.getPackagesAtTruckTable().getColumnModel();
            }
            
            }

    
}
