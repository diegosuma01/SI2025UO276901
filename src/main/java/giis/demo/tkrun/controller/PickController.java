package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.PickView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;

import java.util.List;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.DTOs.PickDTO;
import giis.demo.tkrun.DTOs.TrackDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.model.PickModel;
import giis.demo.tkrun.model.VehiclesModel;


public class PickController {

    private PickView view;
    private SwingMain main;
    private PickDTO dto;
    private PickModel model;
    private TrackDTO trackDTO;

    public PickController(PickDTO PickDTO, PickView PickView, SwingMain swingMain, TrackDTO trackDTO) {
        this.dto = PickDTO;
        this.view = PickView;
        this.main = swingMain;
        this.model = model;
        this.trackDTO = trackDTO;
        this.initView();
    }

    public void initController() {
        view.getBtnConfirmOffice()
				.addActionListener(e -> SwingUtil.exceptionWrapper(() -> getPackagesOffice()));

        view.getBtnConfirmPickUp()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getPackagesHome()));
      
    }

    public void initView() {
        view.getFrame().setVisible(true);
        view.getFrame().setLocationRelativeTo(null);

        /*List<VehiclesModel> listVehicles = dto.getVehicles();
        for (VehiclesModel vehicle : listVehicles) {
            view.getComboVehicles().addItem(vehicle.getVehicleId());
        }

        getRoutes(); */
    }

    private void getPackagesOffice(){
        String lastSelectedKey = view.getComboCityRec().getSelectedItem().toString();
        List<PackageModel> listPackages = dto.getPackagesOffice(lastSelectedKey);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] { "packageId", "senderName",
         "receiverName", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
        view.getTabPacakgesToPickUp().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTabPacakgesToPickUp());
        TableColumnModel columnModel = view.getTabPacakgesToPickUp().getColumnModel();
    }

    private void getPackagesHome(){
        String lastSelectedKey = SwingUtil.getSelectedKey(view.getTabPacakgesToPickUp());
        dto.updatePackageStatus(lastSelectedKey);
        String location = view.getComboCityRec().getSelectedItem().toString();
        trackDTO.updateTracking(String.valueOf(lastSelectedKey), location, "IN TRANSIT");
        dto.updateloction(lastSelectedKey, location);
        getPackagesOffice();
    }


    
}
