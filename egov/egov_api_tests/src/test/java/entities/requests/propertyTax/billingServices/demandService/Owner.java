package entities.requests.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.AuditDetails;

public class Owner {
    private boolean accountLocked;
    private Object pwdExpiryDate;
    private String gender;
    private String mobileNumber;
    private String authToken;
    private Roles[] roles;
    private boolean active;
    private Object emailId;
    private String userName;
    private String locale;
    private String type;
    private UserDetails userDetails;
    private Object aadharNumber;
    private AuditDetails auditDetails;
    private String tenantId;
    private String name;
    private int id;
    private String salutation;

    public boolean getAccountLocked() {
        return this.accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Object getPwdExpiryDate() {
        return this.pwdExpiryDate;
    }

    public void setPwdExpiryDate(Object pwdExpiryDate) {
        this.pwdExpiryDate = pwdExpiryDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Roles[] getRoles() {
        return this.roles;
    }

    public void setRoles(Roles[] roles) {
        this.roles = roles;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Object getEmailId() {
        return this.emailId;
    }

    public void setEmailId(Object emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Object getAadharNumber() {
        return this.aadharNumber;
    }

    public void setAadharNumber(Object aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalutation() {
        return this.salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
}
