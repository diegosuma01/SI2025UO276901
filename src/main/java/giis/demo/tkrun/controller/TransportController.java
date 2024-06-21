package giis.demo.tkrun.controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.DTOs.TransportDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.model.RoutesModel;
import giis.demo.tkrun.model.VehiclesModel;
import giis.demo.tkrun.model.TransportModel;
import giis.demo.tkrun.view.TransportView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;
import javax.swing.JOptionPane;


public class TransportController {

    private TransportView view;
    private SwingMain main;
    private TransportDTO dto;
    private TransportModel model;

    public TransportController(TransportDTO transportDTO, TransportView transportView, SwingMain swingMain) {
        this.dto = transportDTO;
        this.view =transportView;
        this.main = swingMain;
        this.model = model;
        this.initView();
    }

    public void initController() {
        view.getBtnCheckPack()
				.addActionListener(e -> SwingUtil.exceptionWrapper(() -> getPackages()));
        view.getBtnLoad().addActionListener(e -> SwingUtil.exceptionWrapper(() -> loadPackage()));
    }

    public void initView() {
        view.getFrame().setVisible(true);

        List<VehiclesModel> listVehicles = dto.getVehicles();
        for (VehiclesModel vehicle : listVehicles) {
            view.getComboVehicles().addItem(vehicle.getVehicleId());
        }

        getRoutes();
    }

    private void getRoutes() {
        List<RoutesModel> listRoutes = dto.getRoutes();

        TableModel tmodel = SwingUtil.getTableModelFromPojos(listRoutes, new String[] { "routeId", "originCity", "destinationCity",
                "waypointsTable" });
        view.getTableRoutes().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableRoutes());
		TableColumnModel columnModel = view.getTableRoutes().getColumnModel();
    }

    private void getPackages(){
        String lastSelectedKey = SwingUtil.getSelectedKey(view.getTableRoutes());
        List<PackageModel> listPackages = dto.getPackages(lastSelectedKey);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] { "packageId", "senderName",
         "receiverName", "citySender", "adressSender", "cityReceiver", "adressReceiver", "status"});
        view.getTablePackages().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTablePackages());
        TableColumnModel columnModel = view.getTablePackages().getColumnModel();
    }

    private void updateShipmentsTable() {
        // Obtener y actualizar la tabla de envíos en la vista
        List<TransportModel> shipments = dto.getAllShipments();
        view.updateShipmentsTable(shipments);
    }

    private void loadPackage() {
        int selectedRowIndex = view.getTablePackages().getSelectedRow();
        if (selectedRowIndex == -1) {
            // No se ha seleccionado ningún paquete
            JOptionPane.showMessageDialog(null, "Please select a package to load.");
            return;
        }
    
        // Obtener el ID del paquete seleccionado
        String packageId = (String) view.getTablePackages().getValueAt(selectedRowIndex, 0); // Suponiendo que la primera columna es el packageId
    
        // Obtener el ID del vehículo seleccionado
        String selectedVehicle = (String) view.getComboVehicles().getSelectedItem();
        int vehicleId = Integer.parseInt(selectedVehicle);
    
        // Obtener la fecha de hoy como pick up date
        LocalDate pickUpDate = LocalDate.now();
    
        // Obtener la distancia de la ruta seleccionada y calcular delivery date
        int selectedRouteIndex = view.getTableRoutes().getSelectedRow();
        Object routeIdObj = view.getTableRoutes().getValueAt(selectedRouteIndex, 0);
        int routeId = Integer.parseInt(routeIdObj.toString()); // Asumiendo que el ID de la ruta es un entero
        int distance = dto.getRouteDistance(routeId);
    
        LocalDate deliveryDate = pickUpDate.plusDays(distance / 200 + 1); // Sumar días según la distancia
    
        // Cambiar el estado del paquete a "Shipped" en la base de datos
        dto.markPackageAsShipped(packageId, routeId, selectedVehicle);
        
        // Crear un nuevo envío (shipment)
        TransportModel shipment = new TransportModel(packageId, routeId, vehicleId, pickUpDate, deliveryDate);
    
        // Actualizar la vista de envíos (shipments)
        updateShipmentsTable();
        JOptionPane.showMessageDialog(null, "Package loaded successfully.");
    }
    

}
