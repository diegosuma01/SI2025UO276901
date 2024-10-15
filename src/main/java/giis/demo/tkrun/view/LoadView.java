package giis.demo.tkrun.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class LoadView {

    private JFrame frame;
    private JComboBox<String> officeComboBox;
    private JComboBox<String> truckComboBox;
    private JTable packagesAtOfficeTable;
    private JTable packagesAtTruckTable;
    private JButton loadButton;
    private JButton unloadButton;

    /**
     * Create the application.
     */
    public LoadView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Load Packages");
        frame.setBounds(0, 0, 1000, 700); // Ventana grande

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label y ComboBox para seleccionar la oficina
        JLabel officeLabel = new JLabel("Select the office:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        panel.add(officeLabel, gbc);

        officeComboBox = new JComboBox<>(new String[]{"Gijón", "Valencia", "Madrid", "Sevilla"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5; // Proporción de espacio igual al de truckComboBox
        panel.add(officeComboBox, gbc);

        // Label y ComboBox para seleccionar el camión
        JLabel truckLabel = new JLabel("Select the truck:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        panel.add(truckLabel, gbc);

        truckComboBox = new JComboBox<>();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5; // Proporción de espacio igual al de officeComboBox
        panel.add(truckComboBox, gbc);

        // Tabla de paquetes en la oficina y botón de LOAD
        packagesAtOfficeTable = new JTable();
        JScrollPane officeScrollPane = new JScrollPane(packagesAtOfficeTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(officeScrollPane, gbc);

        loadButton = new JButton("LOAD");
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loadButton, gbc);

        // Tabla de paquetes en el camión y botón de UNLOAD
        packagesAtTruckTable = new JTable();
        JScrollPane truckScrollPane = new JScrollPane(packagesAtTruckTable);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(truckScrollPane, gbc);

        unloadButton = new JButton("UNLOAD");
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(unloadButton, gbc);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    // Métodos para acceder a los componentes desde el controlador
    public JFrame getFrame() {
        return frame;
    }

    public JComboBox<String> getOfficeComboBox() {
        return officeComboBox;
    }

    public JComboBox<String> getTruckComboBox() {
        return truckComboBox;
    }

    public JTable getPackagesAtOfficeTable() {
        return packagesAtOfficeTable;
    }

    public JTable getPackagesAtTruckTable() {
        return packagesAtTruckTable;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getUnloadButton() {
        return unloadButton;
    }

    public void setPackagesAtOfficeTableData(Object[][] data, String[] columnNames) {
        packagesAtOfficeTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public void setPackagesAtTruckTableData(Object[][] data, String[] columnNames) {
        packagesAtTruckTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}

