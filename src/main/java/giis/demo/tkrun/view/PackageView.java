package giis.demo.tkrun.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class PackageView {

    private JFrame frame;

    private JTextField txtIdSender;
    private JTextField txtphoneSender;
    private JTextField txtemailSender;

    private JTextField txtDirectionSender;
    private JComboBox<String> comboCitySender;

    private JTextField txtIdRec;
    private JTextField txtphoneRec;
    private JTextField txtemailRec;

    private JTextField txtDirectionRec;
    private JComboBox<String> comboCityRec;

    private JTextField txtWeight;
    private JTextField txtLength;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JLabel lblPrice;
    private JButton btnSend;
    private JButton btnPrice;
    private double distance;


    public PackageView() {
        initialize();
    }
        
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Register a package");
        frame.setName("Register");
        frame.setBounds(0, 0, 600, 600);
        frame.getContentPane().setLayout(new GridLayout(22, 2, 10, 10));

        // Sender Information
        final JLabel lblSenderInfo = new JLabel("Sender Information:");
        frame.getContentPane().add(lblSenderInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblIdSender = new JLabel("Name Sender:");
        frame.getContentPane().add(lblIdSender);
        txtIdSender = new JTextField();
        txtIdSender.setName("txtIdSender");
        frame.getContentPane().add(txtIdSender);
        txtIdSender.setColumns(10);

        final JLabel lblphonesender = new JLabel("Phone Sender:");
        frame.getContentPane().add(lblphonesender);
        txtphoneSender = new JTextField();
        txtphoneSender.setName("txtphoneSender");
        frame.getContentPane().add(txtphoneSender);
        txtphoneSender.setColumns(10);

        final JLabel lblemailsender = new JLabel("Email sender:");
        frame.getContentPane().add(lblemailsender);
        txtemailSender = new JTextField();
        txtIdSender.setName("txtemailSender");
        frame.getContentPane().add(txtemailSender);
        txtemailSender.setColumns(10);

    

        final JLabel lblCitySender = new JLabel("City:");
        frame.getContentPane().add(lblCitySender);
        //comboCitySender = new JComboBox<>(new String[]{"Gijón", "Valencia","Madrid", "Sevilla"});
        comboCitySender = new JComboBox<>();
        frame.getContentPane().add(comboCitySender);

        final JLabel lblDirectionSender = new JLabel("Address:");
        frame.getContentPane().add(lblDirectionSender);
        txtDirectionSender = new JTextField();
        txtDirectionSender.setName("txtDirectionSender");
        frame.getContentPane().add(txtDirectionSender);
        txtDirectionSender.setColumns(10);

        // Recipient Information
        final JLabel lblRecInfo = new JLabel("Recipient Information:");
        frame.getContentPane().add(lblRecInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblIdRec = new JLabel("Name Recipient:");
        frame.getContentPane().add(lblIdRec);
        txtIdRec = new JTextField();
        txtIdRec.setName("txtIdRec");
        frame.getContentPane().add(txtIdRec);
        txtIdRec.setColumns(10);

        final JLabel lblphonerec = new JLabel("Phone Recipient:");
        frame.getContentPane().add(lblphonerec);
        txtphoneRec = new JTextField();
        txtphoneRec.setName("txtphoneRec");
        frame.getContentPane().add(txtphoneRec);
        txtphoneRec.setColumns(10);

        final JLabel lblemailRec = new JLabel("Email Recipient:");
        frame.getContentPane().add(lblemailRec);
        txtemailRec = new JTextField();
        txtemailRec.setName("txtemailRec");
        frame.getContentPane().add(txtemailRec);
        txtemailRec.setColumns(10);

        
        final JLabel lblCityRec = new JLabel("City:");
        frame.getContentPane().add(lblCityRec);
        //comboCityRec = new JComboBox<>(new String[]{"Gijón", "Valencia","Madrid", "Sevilla"});
        comboCityRec = new JComboBox<>();
        frame.getContentPane().add(comboCityRec);

        final JLabel lblDirectionRec = new JLabel("Address:");
        frame.getContentPane().add(lblDirectionRec);
        txtDirectionRec = new JTextField();
        txtDirectionRec.setName("txtDirectionRec");
        frame.getContentPane().add(txtDirectionRec);
        txtDirectionRec.setColumns(10);

        // Package Information
        final JLabel lblPackageInfo = new JLabel("Package Information:");
        frame.getContentPane().add(lblPackageInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblLength = new JLabel("Length (cm):");
        frame.getContentPane().add(lblLength);
        txtLength = new JTextField();
        txtLength.setName("txtLength");
        frame.getContentPane().add(txtLength);
        txtLength.setColumns(10);

        final JLabel lblWidth = new JLabel("Width (cm):");
        frame.getContentPane().add(lblWidth);
        txtWidth = new JTextField();
        txtWidth.setName("txtWidth");
        frame.getContentPane().add(txtWidth);
        txtWidth.setColumns(10);

        final JLabel lblHeight = new JLabel("Height (cm):");
        frame.getContentPane().add(lblHeight);
        txtHeight = new JTextField();
        txtHeight.setName("txtHeight");
        frame.getContentPane().add(txtHeight);
        txtHeight.setColumns(10);

        final JLabel lblWeight = new JLabel("Weight (kg):");
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

        btnPrice = new JButton("CALCULATE PRICE");
        frame.getContentPane().add(btnPrice);

        btnSend = new JButton("SEND");
        frame.getContentPane().add(btnSend);


        frame.setVisible(true);
    }


    public boolean validateFields() {
        String idSender = getIdSender();
        String idRec = getIdRec();
        String phoneSender = getPhoneSender();
        String phoneRec = getPhoneRec();
        String emailSender = getEmailSender();
        String emailRec = getEmailRec();
        String directionSender = getDirectionSender();
        String directionRec = getDirectionRec();
        String weight = getWeight();
        String length = getLength();
        String width = getWidth();
        String height = getHeight();
    
        // Validate that all fields are filled
        if (idSender == null || idRec == null || phoneSender == null || phoneRec == null || 
            emailSender == null || emailRec == null || directionSender == null || 
            directionRec == null || weight == null || length == null || 
            width == null || height == null) {
            JOptionPane.showMessageDialog(getFrame(), "Please complete all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        // Validate phone number (must be exactly 9 digits)
        if (!phoneSender.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(getFrame(), "Sender's phone number must have exactly 9 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!phoneRec.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(getFrame(), "Recipient's phone number must have exactly 9 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        // Validate email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!emailSender.matches(emailRegex)) {
            JOptionPane.showMessageDialog(getFrame(), "Sender's email address is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!emailRec.matches(emailRegex)) {
            JOptionPane.showMessageDialog(getFrame(), "Recipient's email address is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        // Validate that weight and dimensions are valid numbers
        try {
            Double.parseDouble(weight);
            Double.parseDouble(length);
            Double.parseDouble(width);
            Double.parseDouble(height);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(getFrame(), "Please enter valid numeric values for weight and dimensions.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        // If all validations are correct
        return true;
    }
    

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setCityLists(List<String> cities) {
        // Limpiamos los combo boxes por si tuvieran datos previos
        comboCitySender.removeAllItems();
        comboCityRec.removeAllItems();
        
        // Añadimos un item por defecto para guiar al usuario
        comboCitySender.addItem("-- Select Origin City --");
        comboCityRec.addItem("-- Select Destination City --");
        
        // Poblamos ambos combo boxes con la lista de ciudades de la base de datos
        for (String city : cities) {
            comboCitySender.addItem(city);
            comboCityRec.addItem(city);
        }
    }

    

    public JFrame getFrame() {
        return this.frame;
    }

    public String getIdSender() {
        if (this.txtIdSender.getText().isEmpty())
            return null;
        else
            return this.txtIdSender.getText();
    }
    public void setIdSender(String id) {
        this.txtIdSender.setText(id);
    }

    public String getIdRec() {
        if (this.txtIdRec.getText().isEmpty())
            return null;
        else
            return this.txtIdRec.getText();
    }
    public void setIdRec(String id) {
        this.txtIdRec.setText(id);
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
    public void setWeight(String weight) {
        this.txtWeight.setText(weight);
    }

    public String getLength() {
        if (this.txtLength.getText().isEmpty())
            return null;
        else
            return this.txtLength.getText();
    }
    public void setLength(String length) {
        this.txtLength.setText(length);
    }

    public String getWidth() {
        if (this.txtWidth.getText().isEmpty())
            return null;
        else
            return this.txtWidth.getText();
    }
    public void setWidth(String width) {
        this.txtWidth.setText(width);
    }

    public String getHeight() {
        if (this.txtHeight.getText().isEmpty())
            return null;
        else
            return this.txtHeight.getText();
    }
    public void setHeight(String height) {
        this.txtHeight.setText(height);
    }

    public String getPrice() {
        return this.lblPrice.getText();
    }
    public void setPrice(String price) {
        this.lblPrice.setText(price);
    }

    public JButton getBtnSend() {
        return this.btnSend;
    }
    public void setBtnSend(JButton btnSend) {
        this.btnSend = btnSend;
    }

    public JButton getBtnPrice() {
        return this.btnPrice;
    }
    public void setBtnPrice(JButton btnPrice) {
        this.btnPrice = btnPrice;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public JTextField getTxtphoneSender() {
        return txtphoneSender;
    }

    public void setTxtphoneSender(JTextField txtphoneSender) {
        this.txtphoneSender = txtphoneSender;
    }

    public String getEmailSender() {
        if (this.txtemailSender.getText().isEmpty())
            return null;
        else
            return this.txtemailSender.getText();
    }
    public void setEmailSender(String id) {
        this.txtemailSender.setText(id);
    }

    public String getEmailRec() {
        if (this.txtemailRec.getText().isEmpty())
            return null;
        else
            return this.txtemailRec.getText();
    }
    public void setEmailRec(String id) {
        this.txtemailRec.setText(id);
    }

    public String getPhoneSender() {
        if (this.txtphoneSender.getText().isEmpty())
            return null;
        else
            return this.txtphoneSender.getText();
    }
    public void setPhoneSender(String id) {
        this.txtphoneSender.setText(id);
    }

    public String getPhoneRec() {
        if (this.txtphoneRec.getText().isEmpty())
            return null;
        else
            return this.txtphoneSender.getText();
    }
    public void setPhoneRec(String id) {
        this.txtphoneRec.setText(id);
    }

    public JTextField getTxtphoneRec() {
        return txtphoneRec;
    }

    public void setTxtphoneRec(JTextField txtphoneRec) {
        this.txtphoneRec = txtphoneRec;
    }

    public JTextField getTxtemailRec() {
        return txtemailRec;
    }

    public void setTxtemailRec(JTextField txtemailRec) {
        this.txtemailRec = txtemailRec;
    }

    public JTextField getTxtIdSender() {
        return txtIdSender;
    }

    public void setTxtIdSender(JTextField txtIdSender) {
        this.txtIdSender = txtIdSender;
    }

    public JTextField getTxtIdRec() {
        return txtIdRec;
    }

    public void setTxtIdRec(JTextField txtIdRec) {
        this.txtIdRec = txtIdRec;
    }

    public JTextField getTxtDirectionSender() {
        return txtDirectionSender;
    }

    public void setTxtDirectionSender(JTextField txtDirectionSender) {
        this.txtDirectionSender = txtDirectionSender;
    }

    public JTextField getTxtDirectionRec() {
        return txtDirectionRec;
    }

    public void setTxtDirectionRec(JTextField txtDirectionRec) {
        this.txtDirectionRec = txtDirectionRec;
    }

    public JLabel getLblPrice(){
        return lblPrice;
    }

}
