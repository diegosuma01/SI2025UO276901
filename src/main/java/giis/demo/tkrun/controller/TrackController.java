package giis.demo.tkrun.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.view.TrackView;
import giis.demo.util.SwingUtil;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.dtos.DTOPackage;
import giis.demo.tkrun.dtos.DTOTrack;
import giis.demo.tkrun.models.TrackModel;


public class TrackController {
    private TrackView view;
    private TrackModel model;
    private SwingMain main;
    private DTOTrack dto;

    public TrackController(TrackModel trackModel, TrackView trackView, SwingMain swingMain) {
        this.model = trackModel;
        this.view = trackView;
        this.main = swingMain;

        this.initView();
    }

    public void initView() {
        view.getFrame().setLocationRelativeTo(null);
        view.getFrame().setVisible(true);
    }

    public void initController() {
        view.getSearchButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getTrack()));        
        
    }

    private void getTrack() {
    // 1. Obtenemos el ID del paquete del campo de texto
    String packageId = view.getPackageIdField().getText();

    // 2. CONTROL DE EXCEPCIÓN 1: ¿El campo de texto está vacío?
    // Usamos .trim() para eliminar espacios en blanco al principio y al final.
    if (packageId == null || packageId.trim().isEmpty()) {
        // Mostramos un mensaje claro al usuario y no hacemos nada más.
        JOptionPane.showMessageDialog(view.getFrame(), "Please enter a package ID to track.", "Input Required", JOptionPane.WARNING_MESSAGE);
        return; // Salimos del método
    }

    // 3. Si hay texto, procedemos a buscar en la base de datos
    try {
        List<DTOTrack> trackingEvents = model.getPackagesTrack(packageId.trim());

        // 4. CONTROL DE EXCEPCIÓN 2: ¿La búsqueda no ha devuelto resultados?
        // Si la lista está vacía, significa que no se encontró ningún paquete con ese ID.
        if (trackingEvents.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "No tracking information found for package ID: " + packageId.trim() + ". Please check the ID and try again.", "Package Not Found", JOptionPane.ERROR_MESSAGE);
            // Limpiamos la tabla por si había una búsqueda anterior
            view.getPackageInfoTable().setModel(new javax.swing.table.DefaultTableModel());
            return;
        }

        // 5. Si encontramos resultados, los mostramos en la tabla
        TableModel tmodel = SwingUtil.getTableModelFromPojos(trackingEvents, new String[] { "packageId", "location", "status", "timestamp"});
        view.getPackageInfoTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getPackageInfoTable());

    } catch (Exception e) {
        // Captura de cualquier error inesperado durante la consulta a la base de datos
        JOptionPane.showMessageDialog(view.getFrame(), "An unexpected error occurred while searching: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
}
