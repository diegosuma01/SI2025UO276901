package giis.demo.tkrun.view;

import javax.swing.JButton;
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

public class TrackView {

    private JFrame frame;
    private JTextField packageIdField;
    private JButton searchButton;
    private JTable packageInfoTable;
    private JTextField newAddressField;
    private JButton changeAddressButton;

    /**
     * Create the application.
     */
    public TrackView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Package Tracking");
        frame.setBounds(0, 0, 800, 600); // Tamaño ajustado

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label y campo para ingresar el ID del paquete
        JLabel packageIdLabel = new JLabel("Insert package ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(packageIdLabel, gbc);

        packageIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(packageIdField, gbc);

        searchButton = new JButton("Search");
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(searchButton, gbc);

        // Crear la tabla para mostrar la información del paquete
        packageInfoTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(packageInfoTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Ocupar todo el ancho y altura
        panel.add(tableScrollPane, gbc);
        /* 
        // Label para el cambio de dirección
        JLabel changeDirectionLabel = new JLabel("In case you want to change the direction, write it here:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(changeDirectionLabel, gbc);

        // Campo de texto para la nueva dirección
        newAddressField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(newAddressField, gbc);

        // Botón para confirmar el cambio de dirección
        changeAddressButton = new JButton("Change Address");
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(changeAddressButton, gbc);

        // Hacer visible la ventana
        frame.setVisible(true);

        // Acción al hacer clic en el botón "Change Address"
        changeAddressButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(frame, "Address has been changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE)
        );
        */
    }

    // Métodos para acceder a los componentes desde el controlador
    public JFrame getFrame() {
        return this.frame;
    }

    public JTextField getPackageIdField() {
        return packageIdField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getPackageInfoTable() {
        return packageInfoTable;
    }

    public JTextField getNewAddressField() {
        return newAddressField;
    }

    public JButton getChangeAddressButton() {
        return changeAddressButton;
    }

    public void setPackageInfoTableData(Object[][] data, String[] columnNames) {
        packageInfoTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

    public JTable getpackageInfoTable() {
        return packageInfoTable;
    }

    


}
