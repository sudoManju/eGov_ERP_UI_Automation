package entities.requests.assetManagement.assetCategory;

public class UserInfo {
    private Object mobileNumber;
    private Object roles;
    private Object name;
    private Object emailId;
    private int id;
    private Object userName;
    private Object type;

    public Object getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(Object mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Object getRoles() {
        return this.roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }

    public Object getName() {
        return this.name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getEmailId() {
        return this.emailId;
    }

    public void setEmailId(Object emailId) {
        this.emailId = emailId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUserName() {
        return this.userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getType() {
        return this.type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}
