package giis.demo.tkrun.controller;

import java.util.List;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.DTOs.LoadDTO;
import giis.demo.tkrun.DTOs.PackageDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.view.LoadView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;

public class LoadController {
    private LoadView view;
    private LoadDTO dto;
    private SwingMain main;
    private PackageDTO packageDTO;

    public LoadController(LoadDTO loadDTO, LoadView loadView, SwingMain swingMain, PackageDTO packageDTO) {
        this.dto = loadDTO;
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
        return dto.getVehicles(lastSelectedKey);
    }

    public String getSelectedOrigin() {
        return (String) view.getOfficeComboBox().getSelectedItem();
    }



    private void getLoad() {
        String vehicle = view.getTruckComboBox().getSelectedItem().toString();
        String lastSelectedKey = SwingUtil.getSelectedKey(view.getPackagesAtOfficeTable());
        dto.updatePackageStatus(lastSelectedKey, vehicle, getSelectedOrigin()); 
        String origen = getSelectedOrigin();
        getPackagesOffice(origen);
        getPackagesVehicle();
    }

    private void getUnload() {
        String vehicle = view.getTruckComboBox().getSelectedItem().toString();
        String lastSelectedKey = SwingUtil.getSelectedKey(view.getPackagesAtTruckTable());
        dto.updatePackageStatusUnload(lastSelectedKey, vehicle, getSelectedOrigin()); 
        String origen = getSelectedOrigin();
        getPackagesOffice(origen);
        getPackagesVehicle();

    }

    private void getPackagesOffice(String office) {
        List<PackageModel> listPackages = packageDTO.getPackagesOffice(office);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] {
            "packageId", "name_sender", "name_rec", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
        view.getPackagesAtOfficeTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getPackagesAtOfficeTable());
        TableColumnModel columnModel = view.getPackagesAtOfficeTable().getColumnModel();
        }

        private void getPackagesVehicle() {
            if (view.getTruckComboBox() != null && view.getTruckComboBox().getSelectedItem() != null) {
                String vehicle = view.getTruckComboBox().getSelectedItem().toString();
                List<PackageModel> listPackages = packageDTO.getPackagesVehicle(vehicle);
                TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] {
                    "packageId", "name_sender", "name_rec", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
                view.getPackagesAtTruckTable().setModel(tmodel);
                SwingUtil.autoAdjustColumns(view.getPackagesAtTruckTable());
                TableColumnModel columnModel = view.getPackagesAtTruckTable().getColumnModel();
            }
            
            }

    
}
