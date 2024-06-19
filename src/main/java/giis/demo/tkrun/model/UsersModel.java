package giis.demo.tkrun.model;

public class UsersModel {

    private String userId;
    private String name;
    private String email;
    private String phone;


    public UsersModel() {
    }

    public UsersModel(String userId) {
        this.userId = userId;
    }

    public UsersModel(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
