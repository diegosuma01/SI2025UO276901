package giis.demo.tkrun.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.DTOs.TrackDTO;
import giis.demo.tkrun.DTOs.TransportDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.model.RoutesModel;
import giis.demo.tkrun.model.VehiclesModel;
import giis.demo.tkrun.model.TransportModel;
import giis.demo.tkrun.view.TransportView;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;
import javafx.event.ActionEvent;
import giis.demo.tkrun.model.PackageModel;

import javax.swing.JOptionPane;


public class TransportController {

    private TransportView view;
    private SwingMain main;
    private TransportDTO dto;
    private TransportModel model;
    private TrackDTO trackDTO = new TrackDTO();

    public TransportController(TransportDTO transportDTO, TransportView transportView, SwingMain swingMain) {
        this.dto = transportDTO;
        this.view =transportView;
        this.main = swingMain;
        this.model = model;
        this.initView();
    }

    public void initController() {
        view.getComboOrigen()
				.addActionListener(e -> SwingUtil.exceptionWrapper(() -> getVehicles()));
        view.getComboDestino()
				.addActionListener(e -> SwingUtil.exceptionWrapper(() -> updatePackagesLabel()));
        view.getFilterButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> filterPackages()));
        view.getMoveButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> movePackage()));
    }

    public void initView() {
        view.getFrame().setVisible(true);
        view.getFrame().setLocationRelativeTo(null);
        
    }

    private void getVehicles() {
        String origen = getSelectedOrigin();
        view.getLblVehiculo().setText("Vehicles in " + origen);
        updatePackagesLabel();
        updateVehicleComboBox(origen);
    }

    public String getSelectedOrigin() {
        return (String) view.getComboOrigen().getSelectedItem();
    }

    // Método para actualizar la etiqueta de paquetes
    private void updatePackagesLabel() {
        String origen = getSelectedOrigin();
        String destino = getSelectedDestination();
        view.getLblPackages().setText("Packages from " + origen + " to " + destino);
    }

    private void filterPackages() {
        String origen = getSelectedOrigin();
        String destino = getSelectedDestination();
        List<PackageModel> listPackages = dto.getPackagesTransport(origen, destino);
        
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] { 
            "packageId", "citySender",  "status"});
        view.getTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTable());
        TableColumnModel columnModel = view.getTable().getColumnModel();
    }

    public String getSelectedDestination() {
        return (String) view.getComboDestino().getSelectedItem();
    }

    public Integer getCapacity(String vehiculo) {
        return dto.getVehicleCapacity(vehiculo);
    }

    // Simulación de la obtención de vehículos desde la base de datos para la ciudad seleccionada
    private void updateVehicleComboBox(String city) {
        List<String> vehicles = getVehiclesForCity(city);
        view.getComboVehiculo().removeAllItems();
        for (String vehicle : vehicles) {
            view.getComboVehiculo().addItem(vehicle);
        }
    }

    // Simula una consulta a la base de datos para obtener vehículos disponibles en una ciudad específica
    private List<String> getVehiclesForCity(String city) {
        String lastSelectedKey = this.getSelectedOrigin();
        return dto.getVehicles(lastSelectedKey);
    }

    private void movePackage() {
        Integer city = dto.getCity(view.getComboDestino().getSelectedItem().toString());
        //dto.updateVehicleMove(view.getComboDestino().getSelectedItem().toString(), view.getComboVehiculo().getSelectedItem().toString());
        
        String selectedDestination = view.getComboDestino().getSelectedItem() != null ? view.getComboDestino().getSelectedItem().toString() : null;
        String selectedVehicle = view.getComboVehiculo().getSelectedItem() != null ? view.getComboVehiculo().getSelectedItem().toString() : null;

        // Check if the vehicle combo box is empty
        if (selectedVehicle == null || selectedVehicle.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "There are no trucks available for that shipment.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {

            int[] columnas = view.getTable().getSelectedRows();
            // Check if the vehicle combo box is empty
            if (columnas.length == 0) {
                JOptionPane.showMessageDialog(view.getFrame(), "You must select at least one.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else{
                // Proceed with updating the vehicle move
                dto.updateVehicleMove(view.getComboDestino().getSelectedItem().toString(), view.getComboVehiculo().getSelectedItem().toString());
                int[] selectedRows = view.getTable().getSelectedRows(); 
                for (int i: selectedRows) {
                    String id = (String) view.getTable().getValueAt(i, 0);
                PackageModel p = dto.getPackage(id);
                    if (view.getComboDestino().getSelectedItem().toString().equals(p.getCityReceiver())) {
                        dto.updatePackageMove(city, "S", id);
                        trackDTO.updateTracking(id, view.getComboDestino().getSelectedItem().toString(), "READY FOR DELIVERY");
                    } else{
                        dto.updatePackageMove(city, "N", id);
                        trackDTO.updateTracking(id, view.getComboDestino().getSelectedItem().toString(), "IN TRANSIT");
                    }
                }
                filterPackages();
                updateVehicleComboBox(view.getComboOrigen().getSelectedItem().toString());
            }
            
        }
            
        /*int[] selectedRows = view.getTable().getSelectedRows(); 
        for (int i: selectedRows) {
            String id = (String) view.getTable().getValueAt(i, 0);
           PackageModel p = dto.getPackage(id);
            if (view.getComboDestino().getSelectedItem().toString().equals(p.getCityReceiver())) {
                dto.updatePackageMove(city, "S", id);
                trackDTO.updateTracking(id, view.getComboDestino().getSelectedItem().toString(), "READY FOR DELIVERY");
            } else{
                dto.updatePackageMove(city, "N", id);
                trackDTO.updateTracking(id, view.getComboDestino().getSelectedItem().toString(), "IN TRANSIT");
            }
        }
        filterPackages();
        updateVehicleComboBox(view.getComboOrigen().getSelectedItem().toString());
*/
    }

    public void updateVehicleMove() {
        String selectedDestination = view.getComboDestino().getSelectedItem() != null ? view.getComboDestino().getSelectedItem().toString() : null;
        String selectedVehicle = view.getComboVehiculo().getSelectedItem() != null ? view.getComboVehiculo().getSelectedItem().toString() : null;
        // Check if the vehicle combo box is empty
        if (selectedVehicle == null || selectedVehicle.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "There are no trucks available for that shipment.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int[] selectedRows = view.getTable().getSelectedRows();
        // Check if the vehicle combo box is empty
        if (selectedRows == null) {
            JOptionPane.showMessageDialog(view.getFrame(), "You must select at least one.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else{
            // Proceed with updating the vehicle move
            dto.updateVehicleMove(selectedDestination, selectedVehicle);
        }
        //dto.updateVehicleMove(selectedDestination, selectedVehicle);

        
    }
    
    
}
