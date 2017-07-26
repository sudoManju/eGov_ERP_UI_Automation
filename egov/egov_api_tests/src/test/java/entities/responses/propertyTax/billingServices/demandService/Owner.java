package entities.responses.propertyTax.billingServices.demandService;

public class Owner {
    private Object aadhaarNumber;
    private String mobileNumber;
    private String tenantId;
    private String name;
    private Object emailId;
    private int id;
    private Object permanentAddress;
    private String userName;

    public Object getAadhaarNumber() {
        return this.aadhaarNumber;
    }

    public void setAadhaarNumber(Object aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
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

    public Object getPermanentAddress() {
        return this.permanentAddress;
    }

    public void setPermanentAddress(Object permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
