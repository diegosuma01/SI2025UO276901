package giis.demo.util;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import giis.demo.tkrun.controller.DeliverController;
import giis.demo.tkrun.controller.LoadController;
import giis.demo.tkrun.controller.PackageController;
import giis.demo.tkrun.controller.TransportController;
import giis.demo.tkrun.models.DeliverModel;
import giis.demo.tkrun.models.LoadModel;
import giis.demo.tkrun.models.PackageModel;
import giis.demo.tkrun.models.PickModel;
import giis.demo.tkrun.models.TrackModel;
import giis.demo.tkrun.models.TransportModel;
import giis.demo.tkrun.controller.PickController;
import giis.demo.tkrun.controller.TrackController;
import giis.demo.tkrun.view.DeliverView;
import giis.demo.tkrun.view.LoadView;
import giis.demo.tkrun.view.PackageView;
import giis.demo.tkrun.view.TransportView;
import giis.demo.tkrun.view.PickView;
import giis.demo.tkrun.view.TrackView;

public class SwingMain {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
            public void run() {
                try {
                    SwingMain window = new SwingMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(); //NOSONAR codigo autogenerado
                }
            }
        });
    }

    public SwingMain() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Main");
        frame.setBounds(100, 100, 600, 400); // Pantalla más grande
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre los botones

        JButton btnRegister = createButton("Register a package", e -> {
            PackageController controller = new PackageController(new PackageModel(), new PackageView(), SwingMain.this);
            controller.initController();
        });
        JButton btnPick = createButton("Pick Up a package", e -> {
            PickController controller = new PickController(new PickModel(), new PickView(), SwingMain.this, new TrackModel());
            controller.initController();
        });
        JButton btnTransport = createButton("Move a package", e -> {
            TransportController controller = new TransportController(new TransportModel(), new TransportView(), SwingMain.this);
            controller.initController();
        });
        JButton btnTrack = createButton("Track a package", e -> {
            TrackController controller = new TrackController(new TrackModel(), new TrackView(), SwingMain.this);
            controller.initController();
        });
        JButton btnDeliver = createButton("Deliver a package", e -> {
            DeliverController controller = new DeliverController(new DeliverModel(), new DeliverView(), SwingMain.this);
            controller.initController();
        });
        JButton btnLoad = createButton("Load a package", e -> {
            LoadController controller = new LoadController(new LoadModel(), new LoadView(), SwingMain.this, new PackageModel());
            controller.initController();
        });

        JButton btnInicializarBaseDeDatos = createButton("Inicializar Base de Datos en Blanco", e -> {
            Database db = new Database();
            db.createDatabase(false);
        });
        JButton btnCargarDatosIniciales = createButton("Cargar Datos Iniciales para Pruebas", e -> {
            Database db = new Database();
            db.createDatabase(false);
            db.loadDatabase();
        });

        // Colocamos los botones en 2 columnas
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.getContentPane().add(btnRegister, gbc);

        gbc.gridx = 1;
        frame.getContentPane().add(btnPick, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(btnTransport, gbc);

        gbc.gridx = 1;
        frame.getContentPane().add(btnTrack, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.getContentPane().add(btnDeliver, gbc);

        gbc.gridx = 1;
        frame.getContentPane().add(btnLoad, gbc);

        // Los botones de la base de datos ocupan toda la fila
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa las dos columnas
        frame.getContentPane().add(btnInicializarBaseDeDatos, gbc);

        gbc.gridy = 4;
        frame.getContentPane().add(btnCargarDatosIniciales, gbc);
    }

    // Método para crear botones con forma redondeada
    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180)); // Color azul
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        button.setPreferredSize(new Dimension(200, 40)); // Tamaño de los botones
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Borde interno para redondeado
        return button;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}

