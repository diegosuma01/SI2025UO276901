package ShippingCompany.src.main.java.giis.demo;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ShippingCompany.src.main.java.giis.demo.view.PackageView;
import ShippingCompany.src.main.java.giis.demo.DTOs.PackageDTO;
import ShippingCompany.src.main.java.giis.demo.controller.PackageController;

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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnRegister = new JButton("Register a package");
        btnRegister.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
                /**
                 * Creates a new instance of the PackageController class and initializes it with a PackageModel, PackageView, and the current SwingMain instance.
                 * 
                 * @param controller The PackageController instance to be created.
                 * @param model The PackageModel instance to be associated with the controller.
                 * @param view The PackageView instance to be associated with the controller.
                 */
                PackageController controller=new PackageController(new PackageDTO(), new PackageView(), SwingMain.this);
                controller.initController();
            }
        });
        btnRegister.setBounds(10, 36, 200, 23);
        frame.getContentPane().add(btnRegister);

    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}

