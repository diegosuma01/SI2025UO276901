package giis.demo.tkrun.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import giis.demo.tkrun.model.TransportModel;

public class TransportView {

    private JFrame frame;

    private JComboBox<String> comboVehicles;
    private JTable tabRoutes;
    private JButton btnCheckPack;
    private JTable tabPackages;
    private JButton btnLoad;
    private JButton btnLoadAll;

    /**
     * Create the application.
     */
    public TransportView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Transport of packages");
        frame.setName("Transport");
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lblVehicles = new JLabel("Vehicles:");
        topPanel.add(lblVehicles, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        comboVehicles = new JComboBox<>();
        topPanel.add(comboVehicles, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lblRoutes = new JLabel("Routes:");
        topPanel.add(lblRoutes, gbc);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        tabRoutes = new JTable();
        tabRoutes.setName("tabRoutes");
        tabRoutes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabRoutes.setDefaultEditor(Object.class, null); // readonly

        JScrollPane scrollPaneRoutes = new JScrollPane(tabRoutes); // Encapsulate in JScrollPane
        frame.getContentPane().add(scrollPaneRoutes, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        btnCheckPack = new JButton("Check packages");
        bottomPanel.add(btnCheckPack, gbc);

        gbc.gridx = 1;
        btnLoad = new JButton("Load");
        bottomPanel.add(btnLoad, gbc);

        gbc.gridx = 2;
        btnLoadAll = new JButton("Load All");
        bottomPanel.add(btnLoadAll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tabPackages = new JTable();
        tabPackages.setName("tabPackages");
        tabPackages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabPackages.setDefaultEditor(Object.class, null); // readonly

        JScrollPane scrollPanePackages = new JScrollPane(tabPackages); // Encapsulate in JScrollPane
        bottomPanel.add(scrollPanePackages, gbc);

        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JComboBox<String> getComboVehicles() {
        return comboVehicles;
    }

    public JTable getTableRoutes() {
        return tabRoutes;
    }

    public JTable getTablePackages() {
        return tabPackages;
    }

    public JButton getBtnCheckPack() {
        return btnCheckPack;
    }

    public JButton getBtnLoad() {
        return btnLoad;
    }

    public JButton getBtnLoadAll() {
        return btnLoadAll;
    }

	public void updateShipmentsTable(List<TransportModel> shipments) {
		throw new UnsupportedOperationException("Unimplemented method 'updateShipmentsTable'");
	}
}
