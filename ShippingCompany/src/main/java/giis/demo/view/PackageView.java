package ShippingCompany.src.main.java.giis.demo.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class PackageView {

    private JFrame frame;
    private JTextField txtNameSender;
    private JTextField txtPhoneSender;
    private JTextField txtEmailSender;
    private JTextField txtDirectionSender;
    private JComboBox<String> comboCitySender;

    private JTextField txtNameRec;
    private JTextField txtPhoneRec;
    private JTextField txtEmailRec;
    private JTextField txtDirectionRec;
    private JComboBox<String> comboCityRec;

    private JTextField txtWeight;
    private JComboBox<String> comboPackageSize;
    private JLabel lblPrice;
    private JButton btnSend;

    /**
     * Create the application.
     */
    public PackageView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Register a package");
        frame.setName("Register");
        frame.setBounds(0, 0, 600, 600);
        frame.getContentPane().setLayout(new GridLayout(20, 2, 10, 10));

        // Sender Information
        final JLabel lblSenderInfo = new JLabel("Sender Information:");
        frame.getContentPane().add(lblSenderInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblNameSender = new JLabel("Full name:");
        frame.getContentPane().add(lblNameSender);
        txtNameSender = new JTextField();
        txtNameSender.setName("txtNameSender");
        frame.getContentPane().add(txtNameSender);
        txtNameSender.setColumns(10);

        final JLabel lblPhoneSender = new JLabel("Phone:");
        frame.getContentPane().add(lblPhoneSender);
        txtPhoneSender = new JTextField();
        txtPhoneSender.setName("txtPhoneSender");
        frame.getContentPane().add(txtPhoneSender);
        txtPhoneSender.setColumns(10);

        final JLabel lblEmailSender = new JLabel("Email:");
        frame.getContentPane().add(lblEmailSender);
        txtEmailSender = new JTextField();
        txtEmailSender.setName("txtEmailSender");
        frame.getContentPane().add(txtEmailSender);
        txtEmailSender.setColumns(10);

        final JLabel lblDirectionSender = new JLabel("Direction:");
        frame.getContentPane().add(lblDirectionSender);
        txtDirectionSender = new JTextField();
        txtDirectionSender.setName("txtDirectionSender");
        frame.getContentPane().add(txtDirectionSender);
        txtDirectionSender.setColumns(10);

        final JLabel lblCitySender = new JLabel("City:");
        frame.getContentPane().add(lblCitySender);
        comboCitySender = new JComboBox<>(new String[]{"City1", "City2", "City3"});
        frame.getContentPane().add(comboCitySender);

        // Recipient Information
        final JLabel lblRecInfo = new JLabel("Recipient Information:");
        frame.getContentPane().add(lblRecInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblNameRec = new JLabel("Full name:");
        frame.getContentPane().add(lblNameRec);
        txtNameRec = new JTextField();
        txtNameRec.setName("txtNameRec");
        frame.getContentPane().add(txtNameRec);
        txtNameRec.setColumns(10);

        final JLabel lblPhoneRec = new JLabel("Phone:");
        frame.getContentPane().add(lblPhoneRec);
        txtPhoneRec = new JTextField();
        txtPhoneRec.setName("txtPhoneRec");
        frame.getContentPane().add(txtPhoneRec);
        txtPhoneRec.setColumns(10);

        final JLabel lblEmailRec = new JLabel("Email:");
        frame.getContentPane().add(lblEmailRec);
        txtEmailRec = new JTextField();
        txtEmailRec.setName("txtEmailRec");
        frame.getContentPane().add(txtEmailRec);
        txtEmailRec.setColumns(10);

        final JLabel lblDirectionRec = new JLabel("Direction:");
        frame.getContentPane().add(lblDirectionRec);
        txtDirectionRec = new JTextField();
        txtDirectionRec.setName("txtDirectionRec");
        frame.getContentPane().add(txtDirectionRec);
        txtDirectionRec.setColumns(10);

        final JLabel lblCityRec = new JLabel("City:");
        frame.getContentPane().add(lblCityRec);
        comboCityRec = new JComboBox<>(new String[]{"City1", "City2", "City3"});
        frame.getContentPane().add(comboCityRec);

        // Package Information
        final JLabel lblPackageInfo = new JLabel("Package Information:");
        frame.getContentPane().add(lblPackageInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblPackageSize = new JLabel("Package Size:");
        frame.getContentPane().add(lblPackageSize);
        comboPackageSize = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        frame.getContentPane().add(comboPackageSize);

        final JLabel lblWeight = new JLabel("Weight:");
        frame.getContentPane().add(lblWeight);
        txtWeight = new JTextField();
        txtWeight.setName("txtWeight");
        frame.getContentPane().add(txtWeight);
        txtWeight.setColumns(10);

        // Price and Send Button
        final JLabel lblPriceLabel = new JLabel("Price:");
        frame.getContentPane().add(lblPriceLabel);
        lblPrice = new JLabel("$0.00");
        frame.getContentPane().add(lblPrice);

        btnSend = new JButton("SEND");
        frame.getContentPane().add(btnSend);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public String getNameSender() {
        if (this.txtNameSender.getText().isEmpty())
            return null;
        else
            return this.txtNameSender.getText();
    }
    public void setNameSender(String name) {
        this.txtNameSender.setText(name);
    }

    public String getNameRec() {
        if (this.txtNameRec.getText().isEmpty())
            return null;
        else
            return this.txtNameRec.getText();
    }
    public void setNameRec(String name) {
        this.txtNameRec.setText(name);
    }

    public String getPhoneSender() {
        if (this.txtPhoneSender.getText().isEmpty())
            return null;
        else
            return this.txtPhoneSender.getText();
    }
    public void setPhoneSender(String phone) {
        this.txtPhoneSender.setText(phone);
    }

    public String getPhoneRec() {
        if (this.txtPhoneRec.getText().isEmpty())
            return null;
        else
            return this.txtPhoneRec.getText();
    }
    public void setPhoneRec(String phone) {
        this.txtPhoneRec.setText(phone);
    }

    public String getEmailSender() {
        if (this.txtEmailSender.getText().isEmpty())
            return null;
        else
            return this.txtEmailSender.getText();
    }
    public void setEmailSender(String email) {
        this.txtEmailSender.setText(email);
    }

    public String getEmailRec() {
        if (this.txtEmailRec.getText().isEmpty())
            return null;
        else
            return this.txtEmailRec.getText();
    }
    public void setEmailRec(String email) {
        this.txtEmailRec.setText(email);
    }

    public String getDirectionSender() {
        if (this.txtDirectionSender.getText().isEmpty())
            return null;
        else
            return this.txtDirectionSender.getText();
    }
    public void setDirectionSender(String direction) {
        this.txtDirectionSender.setText(direction);
    }

    public String getDirectionRec() {
        if (this.txtDirectionRec.getText().isEmpty())
            return null;
        else
            return this.txtDirectionRec.getText();
    }
    public void setDirectionRec(String direction) {
        this.txtDirectionRec.setText(direction);
    }

    public JComboBox<String> getComboCitySender() {
		return comboCitySender;
	}
	public void setComboCitySender(JComboBox<String> comboCity) {
		this.comboCitySender = comboCity;
	}

    public JComboBox<String> getComboCityRec() {
		return comboCityRec;
	}
	public void setComboCityRec(JComboBox<String> comboCity) {
		this.comboCityRec = comboCity;
	}

    public String getWeight() {
        if (this.txtWeight.getText().isEmpty())
            return null;
        else
            return this.txtWeight.getText();
    }
    public void setweight(String weight) {
        this.txtWeight.setText(weight);
    }

    public JComboBox<String> getComboPackageSize() {
		return comboPackageSize;
	}
	public void setComboPackageSize(JComboBox<String> comboPackageSize) {
		this.comboPackageSize = comboPackageSize;
	}

    public JButton getBtnSend() { return this.btnSend; }

}
