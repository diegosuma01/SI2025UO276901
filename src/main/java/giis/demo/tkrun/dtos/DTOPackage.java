package giis.demo.tkrun.dtos;

public class DTOPackage {

    private String packageId;
    private String name_sender;
    private String name_rec;
    private String EMAIL_REC;
    private String EMAIL_SEND;
    private String PHONE_REC;
    private String PHONE_SEND;
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
    private String senderName;
    private String receiverName;
    
    public DTOPackage() {
    }
    

    public DTOPackage(String packageId) {
        this.packageId = packageId;
    }

    public DTOPackage(String citySender, String adressSender,
            String cityReceiver, String adressReceiver, String status, Double weigth, 
            Double height, Double length, Double depth, Integer price,String name_sender, 
            String name_rec, String EMAIL_REC, String EMAIL_SEND, String PHONE_REC, 
            String PHONE_SEND) {


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
        this.name_sender = name_sender;
        this.name_rec = name_rec;
        this.EMAIL_REC = EMAIL_REC;
        this.EMAIL_SEND = EMAIL_SEND;
        this.PHONE_REC = PHONE_REC;
        this.PHONE_SEND = PHONE_SEND;
    }

    public String getPackageId() {
        return packageId;
    }
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getname_sender() {
        return name_sender;
    }
    public void setname_sender(String name_sender) {
        this.name_sender = name_sender;
    }

    public String getname_rec() {
        return name_rec;
    }
    public void setname_rec(String name_rec) {
        this.name_rec = name_rec;
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

    public String getEMAIL_REC() {
        return EMAIL_REC;
    }
    public void setEMAIL_REC(String EMAIL_REC) {
        this.EMAIL_REC = EMAIL_REC;
    }

    public String getEMAIL_SEND() {
        return EMAIL_SEND;
    }

    public void setEMAIL_SEND(String EMAIL_SEND) {
        this.EMAIL_SEND = EMAIL_SEND;
    }

    public String getPHONE_REC() {
        return PHONE_REC;
    }

    public void setPHONE_REC(String PHONE_REC) {
        this.PHONE_REC = PHONE_REC;
    }

    public String getPHONE_SEND() {
        return PHONE_SEND;
    }

    public void setPHONE_SEND(String PHONE_SEND) {
        this.PHONE_SEND = PHONE_SEND;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

}
