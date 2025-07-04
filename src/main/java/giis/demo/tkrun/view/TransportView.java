package giis.demo.tkrun.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class TransportView {

    private JFrame frame;
    private JComboBox<String> comboOrigen;
    private JComboBox<String> comboDestino;
    private JComboBox<String> comboVehiculo;
    private JButton btnFiltrar;
    private JButton btnMover;
    private JLabel lblPaquetes;
    private JLabel lblVehiculo;
    private JTable tablaPaquetes;
    private JScrollPane scrollPane;

    public TransportView() {
        initialize();
    }

    private void initialize() {
        // Inicializar el JFrame
        frame = new JFrame();
        frame.setTitle("Move a Package");
        frame.setBounds(0, 0, 800, 600); // Tamaño ajustado
        frame.setLayout(new BorderLayout());

        // Panel principal con GridBagLayout
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Etiqueta y ComboBox para seleccionar el origen
        JLabel lblOrigen = new JLabel("Select the origin:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblOrigen, gbc);

        comboOrigen = new JComboBox<>(new String[]{
            "Gijón", "Valencia", "Madrid", "Sevilla", 
            "Burgos Warehouse", "Zaragoza Warehouse", "Valladolid Warehouse", 
            "Toledo Warehouse", "Cáceres Warehouse"
        });
        gbc.gridx = 1;
        panelPrincipal.add(comboOrigen, gbc);

        // Etiqueta y ComboBox para seleccionar el destino
        JLabel lblDestino = new JLabel("Select the destiny:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(lblDestino, gbc);

        comboDestino = new JComboBox<>(new String[]{
            "Gijón", "Valencia", "Madrid", "Sevilla", 
            "Burgos Warehouse", "Zaragoza Warehouse", "Valladolid Warehouse", 
            "Toledo Warehouse", "Cáceres Warehouse"
        });
        gbc.gridx = 1;
        panelPrincipal.add(comboDestino, gbc);

        // Botón para filtrar
        btnFiltrar = new JButton("Filter");
        gbc.gridx = 2;
        panelPrincipal.add(btnFiltrar, gbc);

        // Sección de selección de vehículos
        lblVehiculo = new JLabel("Vehicles in Gijón:");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        panelPrincipal.add(lblVehiculo, gbc);

        comboVehiculo = new JComboBox<>();
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(comboVehiculo, gbc);

        // Sección de paquetes
        lblPaquetes = new JLabel("Packages from Gijón to Madrid");
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        panelPrincipal.add(lblPaquetes, gbc);

        // Tabla para mostrar los paquetes
        String[] columnNames = {"packageId", "citySender", "status"};
        Object[][] data = {
            {"", "", ""},
            {"", "", ""},
        };
        tablaPaquetes = new JTable(data, columnNames);
        tablaPaquetes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Permite la selección múltiple
        scrollPane = new JScrollPane(tablaPaquetes);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 150));
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollPane, gbc);

        // Botón para mover los paquetes
        btnMover = new JButton("Move");
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(btnMover, gbc);

        // Agregar el panel principal al JFrame
        frame.add(panelPrincipal, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana
        frame.pack();
        frame.setVisible(true);
    }

    // Métodos para acceder a los componentes desde el controlador
    public JFrame getFrame() {
        return this.frame;
    }

    public String getSelectedOrigin() {
        return (String) comboOrigen.getSelectedItem();
    }

    public String getSelectedDestination() {
        return (String) comboDestino.getSelectedItem();
    }

    public String getSelectedVehicle() {
        return (String) comboVehiculo.getSelectedItem();
    }

    public JLabel getLblVehiculo(){
        return lblVehiculo;
    }

    public void setVehicleLabel(String city) {
        lblVehiculo.setText("Vehicles in " + city);
    }

    public JLabel getLblPackages(){
        return lblPaquetes;
    }

    public void setPackagesLabel(String origin, String destination) {
        lblPaquetes.setText("Packages from " + origin + " to " + destination);
    }

    public JButton getFilterButton() {
        return btnFiltrar;
    }

    public JButton getMoveButton() {
        return btnMover;
    }

    public JTable getTable() {
        return tablaPaquetes;
    }

    public JComboBox<String> getComboOrigen() {
        return this.comboOrigen;
    }

    public JComboBox<String> getComboDestino() {
        return this.comboDestino;
    }

    public JComboBox<String> getComboVehiculo() {
        return this.comboVehiculo;
    }
}
