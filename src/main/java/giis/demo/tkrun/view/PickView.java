package giis.demo.tkrun.view;

import javax.swing.*;
import java.awt.*;

public class PickView {

    private JFrame frame;
    private JComboBox<String> comboCityRec;
    private JButton btnConfirmOffice;
    private JButton btnConfirmPickUp;
    private JTable tabPacakgesToDeliver;

    /**
     * Create the application.
     */
    public PickView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // Inicializar el JFrame
        frame = new JFrame();
        frame.setTitle("Pick Up Packages");
        frame.setBounds(0, 0, 1000, 600); // Ajustar el tamaño inicial de la ventana

        // Crear un panel con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacios entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir componentes horizontalmente

        // Label y ComboBox para seleccionar la oficina
        JLabel officeLabel = new JLabel("Select the Office:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(officeLabel, gbc);

        comboCityRec = new JComboBox<>(new String[]{"Gijón", "Valencia", "Madrid", "Sevilla"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(comboCityRec, gbc);

        // Botón para confirmar la oficina
        btnConfirmOffice = new JButton("CONFIRM OFFICE");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnConfirmOffice, gbc);

        // Label para la selección de paquetes
        JLabel packageLabel = new JLabel("Packages to Pick:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(packageLabel, gbc);

        // Crear la tabla para mostrar los paquetes y envolverla en un JScrollPane
        tabPacakgesToDeliver = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(tabPacakgesToDeliver);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 1.0; // Expandir el ancho de la tabla
        gbc.weighty = 1.0; // Expandir la altura de la tabla
        gbc.fill = GridBagConstraints.BOTH; // Ocupar todo el espacio disponible
        panel.add(tableScrollPane, gbc);

        // Botón para confirmar la recogida
        btnConfirmPickUp = new JButton("CONFIRM PICK UP");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Solo expandir horizontalmente
        gbc.ipady = 20; // Aumentar la altura del botón
        gbc.weightx = 0.5;
        panel.add(btnConfirmPickUp, gbc);

        // Hacer la ventana visible y ajustar tamaño automáticamente
        frame.setVisible(true);
    }

    // Métodos para acceder a los componentes desde el controlador
    public JFrame getFrame() {
        return this.frame;
    }

    public JComboBox<String> getComboCityRec() {
        return comboCityRec;
    }

    public JButton getBtnConfirmOffice() {
        return btnConfirmOffice;
    }

    public JButton getBtnConfirmPickUp() {
        return btnConfirmPickUp;
    }

    public JTable getTabPacakgesToPickUp() {
        return tabPacakgesToDeliver;
    }

    // Método para ajustar los datos de la tabla
    public void setPackagesTableData(Object[][] data, String[] columnNames) {
        tabPacakgesToDeliver.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    // Método para mostrar mensajes al usuario
    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }
}

