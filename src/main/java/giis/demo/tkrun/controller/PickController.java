package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.PickView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.dtos.DTOPackage;
import giis.demo.tkrun.models.PickModel;
import giis.demo.tkrun.models.TrackModel;


public class PickController {

    private PickView view;
    private SwingMain main;
    private PickModel model;
    private TrackModel trackDTO;

    public PickController(PickModel PickModel, PickView PickView, SwingMain swingMain, TrackModel trackModel) {
        this.model = PickModel;
        this.view = PickView;
        this.main = swingMain;
        this.trackDTO = trackModel;
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
        List<DTOPackage> listPackages = model.getPackagesOffice(lastSelectedKey);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] { "packageId", "senderName",
         "receiverName", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
        view.getTabPacakgesToPickUp().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTabPacakgesToPickUp());
        TableColumnModel columnModel = view.getTabPacakgesToPickUp().getColumnModel();
    }

    private void getPackagesHome(){
    // 1. Obtenemos la clave de la fila seleccionada en la tabla
    String lastSelectedKey = SwingUtil.getSelectedKey(view.getTabPacakgesToPickUp());

    // 2. CONTROL DE EXCEPCIÓN: Verificamos si el usuario ha seleccionado algo
    // Si no ha seleccionado nada, lastSelectedKey será null.
    if (lastSelectedKey == null || lastSelectedKey.isEmpty()) {
        // Mostramos un mensaje de advertencia amigable y salimos del método.
        JOptionPane.showMessageDialog(view.getFrame(), "Please select a package from the table before confirming.", "No Package Selected", JOptionPane.WARNING_MESSAGE);
        return; // Detiene la ejecución para no causar un NullPointerException más adelante
    }

    // 3. Si la validación pasa, continuamos con la lógica de negocio
    // (Ahora sabemos que lastSelectedKey no es null)
    try {
        model.updatePackageStatus(lastSelectedKey);
        
        String location = view.getComboCityRec().getSelectedItem().toString();
        
        // Usamos la variable con el nombre correcto que inicializamos en el constructor
        this.trackDTO.updateTracking(String.valueOf(lastSelectedKey), location, "IN TRANSIT");
        
        model.updateloction(lastSelectedKey, location);
        
        // 4. Refrescamos la tabla para que el paquete recogido desaparezca de la lista
        JOptionPane.showMessageDialog(view.getFrame(), "Package " + lastSelectedKey + " confirmed as picked up.", "Success", JOptionPane.INFORMATION_MESSAGE);
        getPackagesOffice(); 

    } catch (Exception e) {
        // Captura cualquier otro error inesperado y muéstralo de forma controlada
        JOptionPane.showMessageDialog(view.getFrame(), "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    
}
