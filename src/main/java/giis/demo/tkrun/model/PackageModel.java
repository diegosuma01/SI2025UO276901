package giis.demo.tkrun.model;

import giis.demo.tkrun.controller.PackageController;

public class PackageModel {
    // creame en funciona a la base de datos los datos del paquete que se va a enviar

    private String packageId;
    private String senderName;
    private String receiverName;
    private String citySender;
    private String adressSender;
    private String cityReceiver;
    private String adressReceiver;
    private String status;
    private Double weigth;
    private Double height;
    private Double length;
    private Double depth;
    private Integer price;
    private double distance;
    
    public PackageModel() {
    }

    public PackageModel(String packageId) {
        this.packageId = packageId;
    }

    public PackageModel(String packageId, String senderName, String receiverName, String citySender, String adressSender,
            String cityReceiver, String adressReceiver, String status, Double weigth, Double height, Double length, Double depth, Integer price) {
        this.packageId = packageId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.citySender = citySender;
        this.adressSender = adressSender;
        this.cityReceiver = cityReceiver;
        this.adressReceiver = adressReceiver;
        this.status = status;
        this.weigth = weigth;
        this.height = height;
        this.length = length;
        this.depth = depth;
        this.price = price;
    }

    public String getPackageId() {
        return packageId;
    }
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getsenderName() {
        return senderName;
    }
    public void setsenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getreceiverName() {
        return receiverName;
    }
    public void setreceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getCitySender() {
        return citySender;
    }
    public void setCitySender(String citySender) {
        this.citySender = citySender;
    }

    public String getAdressSender() {
        return adressSender;
    }
    public void setAdressSender(String adressSender) {
        this.adressSender = adressSender;
    }

    public String getCityReceiver() {
        return cityReceiver;
    }
    public void setCityReceiver(String cityReceiver) {
        this.cityReceiver = cityReceiver;
    }

    public String getAdressReceiver() {
        return adressReceiver;
    }
    public void setAdressReceiver(String adressReceiver) {
        this.adressReceiver = adressReceiver;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Double getWeigth() {
        return weigth;
    }
    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLength() {
        return length;
    }
    public void setLength(Double length) {
        this.length = length;
    }

    public Double getDepth() {
        return depth;
    }
    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

}
