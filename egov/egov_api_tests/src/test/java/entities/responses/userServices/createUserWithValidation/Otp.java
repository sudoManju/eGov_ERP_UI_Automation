package entities.responses.userServices.createUserWithValidation;

import org.codehaus.jackson.annotate.JsonProperty;

public class Otp {

    private String identity;
    private String tenantId;
    private String otp;

    @JsonProperty("UUID")
    private String UUID;

    private boolean isValidationSuccessful;

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public boolean getIsValidationSuccessful() {
        return this.isValidationSuccessful;
    }

    public void setIsValidationSuccessful(boolean isValidationSuccessful) {
        this.isValidationSuccessful = isValidationSuccessful;
    }
}