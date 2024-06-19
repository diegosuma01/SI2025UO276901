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



public class PackageView {

    private JFrame frame;

    private JTextField txtIdSender;

    private JTextField txtDirectionSender;
    private JComboBox<String> comboCitySender;

    private JTextField txtIdRec;

    private JTextField txtDirectionRec;
    private JComboBox<String> comboCityRec;

    private JTextField txtWeight;
    private JTextField txtLength;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JLabel lblPrice;
    private JButton btnSend;
    private JButton btnPrice;


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
        frame.getContentPane().setLayout(new GridLayout(22, 2, 10, 10));

        // Sender Information
        final JLabel lblSenderInfo = new JLabel("Sender Information:");
        frame.getContentPane().add(lblSenderInfo);
        frame.getContentPane().add(new JLabel("")); // Empty placeholder for alignment

        final JLabel lblIdSender = new JLabel("ID:");
        frame.getContentPane().add(lblIdSender);
        txtIdSender = new JTextField();
        txtIdSender.setName("txtIdSender");
        frame.getContentPane().add(txtIdSender);
        txtIdSender.setColumns(10);

    

        final JLabel lblCitySender = new JLabel("City:");
        frame.getContentPane().add(lblCitySender);
        comboCitySender = new JComboBox<>(new String[]{"Gijon", "Madrid", "Valencia", "Sevilla"});
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

        final JLabel lblIdRec = new JLabel("ID:");
        frame.getContentPane().add(lblIdRec);
        txtIdRec = new JTextField();
        txtIdRec.setName("txtIdRec");
        frame.getContentPane().add(txtIdRec);
        txtIdRec.setColumns(10);

        
        final JLabel lblCityRec = new JLabel("City:");
        frame.getContentPane().add(lblCityRec);
        comboCityRec = new JComboBox<>(new String[]{"Gijon", "Madrid", "Valencia", "Sevilla"});
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


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add action listener for calculate price button
        btnPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePrice();
            }
        });
    }

    private void calculatePrice() {
        try {
            String lengthText = txtLength.getText();
            String widthText = txtWidth.getText();
            String heightText = txtHeight.getText();
            String weightText = txtWeight.getText();

            if (lengthText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Length is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (widthText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Width is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (heightText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Height is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (weightText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Weight is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double length = Double.parseDouble(lengthText);
            double width = Double.parseDouble(widthText);
            double height = Double.parseDouble(heightText);
            double weight = Double.parseDouble(weightText);

            double price = calculatePrice(length, width, height, weight);
            lblPrice.setText(String.format("$%.2f", price));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculatePrice(double length, double width, double height, double weight) {
        // Implement your pricing logic here. For example:
        double volume = length * width * height;
        double basePrice = 5.0;
        double price = basePrice + (volume * 0.01) + (weight * 0.5);
        return price;
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
}
