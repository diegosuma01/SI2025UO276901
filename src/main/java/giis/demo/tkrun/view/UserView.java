package giis.demo.tkrun.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class UserView {

    private JFrame frame;

    private JTextField txtIdUser;
    private JTextField txtNameUser;
    private JTextField txtPhoneUser;
    private JTextField txtEmailUser;

    private JButton btnRegister;

    /**
     * Create the application.
     */
    public UserView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Register a User");
        frame.setName("Register User");
        frame.setBounds(0, 0, 600, 600);
        frame.getContentPane().setLayout(new GridLayout(22, 2, 10, 10));

        // Sender Information
        final JLabel lblSenderInfo = new JLabel("Information of the user:");
        frame.getContentPane().add(lblSenderInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblIdSender = new JLabel("ID:");
        frame.getContentPane().add(lblIdSender);
        txtIdUser = new JTextField();
        txtIdUser.setName("txtIdUser");
        frame.getContentPane().add(txtIdUser);
        txtIdUser.setColumns(10);
        final JLabel lblNameSender = new JLabel("Full name:");
        frame.getContentPane().add(lblNameSender);
        txtNameUser = new JTextField();
        txtNameUser.setName("txtNameUser");
        frame.getContentPane().add(txtNameUser);
        txtNameUser.setColumns(10);

        final JLabel lblPhoneSender = new JLabel("Phone:");
        frame.getContentPane().add(lblPhoneSender);
        txtPhoneUser = new JTextField();
        txtPhoneUser.setName("txtPhoneUser");
        frame.getContentPane().add(txtPhoneUser);
        txtPhoneUser.setColumns(10);

        final JLabel lblEmailSender = new JLabel("Email:");
        frame.getContentPane().add(lblEmailSender);
        txtEmailUser = new JTextField();
        txtEmailUser.setName("txtEmailUser");
        frame.getContentPane().add(txtEmailUser);
        txtEmailUser.setColumns(10);


        btnRegister = new JButton("Register User");
        frame.getContentPane().add(btnRegister);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public String getIdSender() {
        if (this.txtIdUser.getText().isEmpty())
            return null;
        else
            return this.txtIdUser.getText();
    }
    public void setIdSender(String id) {
        this.txtIdUser.setText(id);
    }


    public String getNameSender() {
        if (this.txtNameUser.getText().isEmpty())
            return null;
        else
            return this.txtNameUser.getText();
    }
    public void setNameSender(String name) {
        this.txtNameUser.setText(name);
    }


    public String getPhoneSender() {
        if (this.txtPhoneUser.getText().isEmpty())
            return null;
        else
            return this.txtPhoneUser.getText();
    }
    public void setPhoneSender(String phone) {
        this.txtPhoneUser.setText(phone);
    }


    public String getEmailSender() {
        if (this.txtEmailUser.getText().isEmpty())
            return null;
        else
            return this.txtEmailUser.getText();
    }
    public void setEmailSender(String email) {
        this.txtEmailUser.setText(email);
    }




    public JButton getbtnRegister() {
        return this.btnRegister;
    }
    public void setbtnRegister(JButton btnRegister) {
        this.btnRegister = btnRegister;
    }
}
