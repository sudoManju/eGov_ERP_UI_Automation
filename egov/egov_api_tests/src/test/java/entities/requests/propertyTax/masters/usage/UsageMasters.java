package entities.requests.propertyTax.masters.usage;

import entities.requests.propertyTax.masters.AuditDetails;

public class UsageMasters {
    private String code;
    private int orderNumber;
    private boolean isResidential;
    private AuditDetails auditDetails;
    private String name;
    private String tenantId;
    private String description;
    private boolean active;
    private int id;
    private String nameLocal;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean getIsResidential() {
        return this.isResidential;
    }

    public void setIsResidential(boolean isResidential) {
        this.isResidential = isResidential;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLocal() {
        return this.nameLocal;
    }

    public void setNameLocal(String nameLocal) {
        this.nameLocal = nameLocal;
    }
}
