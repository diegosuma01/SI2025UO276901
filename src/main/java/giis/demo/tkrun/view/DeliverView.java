package giis.demo.tkrun.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class DeliverView {

    private JFrame frame;
    private JComboBox<String> officeComboBox;
    private JComboBox<String> vehicleComboBox;
    private JTable packagesTable;
    private JTextField nameField;
    private JTextField idField;
    private JButton doneButton;
    private JButton failedButton;

    /**
     * Create the application.
     */
    public DeliverView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Package Delivery");
        frame.setBounds(0, 0, 800, 600); // Tamaño ajustado

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
        panel.add(officeLabel, gbc);

        officeComboBox = new JComboBox<>(new String[]{"Gijón", "Valencia", "Madrid", "Sevilla"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(officeComboBox, gbc);

        // Label y ComboBox para seleccionar el vehículo
        JLabel vehicleLabel = new JLabel("Choose a vehicle:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(vehicleLabel, gbc);

        vehicleComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(vehicleComboBox, gbc);

        // Label para la selección de paquetes
        JLabel packageLabel = new JLabel("Select the package to deliver:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(packageLabel, gbc);

        // Crear la tabla para mostrar los paquetes y envolverla en un JScrollPane
        packagesTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(packagesTable);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Ocupar todo el ancho y altura
        panel.add(tableScrollPane, gbc);

        // Label y TextField para el nombre del destinatario
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameLabel, gbc);

        nameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(nameField, gbc);

        // Label y TextField para el ID del destinatario
        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(idLabel, gbc);

        idField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(idField, gbc);

        // Botones DONE y FAILED, ambos ocupan el mismo ancho
        doneButton = new JButton("DONE");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20; // Ajusta la altura de los botones para que sea la mitad de altos
        gbc.weightx = 0.5; // Peso igual para que ocupen el mismo ancho
        panel.add(doneButton, gbc);

        failedButton = new JButton("FAILED");
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5; // Peso igual para que ocupen el mismo ancho
        panel.add(failedButton, gbc);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    // Métodos para acceder a los componentes desde el controlador
    public JFrame getFrame() {
        return this.frame;
    }

    public JComboBox<String> getOfficeComboBox() {
        return officeComboBox;
    }

    public JComboBox<String> getVehicleComboBox() {
        return vehicleComboBox;
    }

    public JTable getPackagesTable() {
        return packagesTable;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getDoneButton() {
        return doneButton;
    }

    public JButton getFailedButton() {
        return failedButton;
    }

    public void setOfficeOptions(List<String> offices) {
        officeComboBox.removeAllItems();
        for (String office : offices) {
            officeComboBox.addItem(office);
        }
    }

    public void setVehicleOptions(List<String> vehicles) {
        vehicleComboBox.removeAllItems();
        for (String vehicle : vehicles) {
            vehicleComboBox.addItem(vehicle);
        }
    }

    public void setPackagesTableData(Object[][] data, String[] columnNames) {
        packagesTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

}


