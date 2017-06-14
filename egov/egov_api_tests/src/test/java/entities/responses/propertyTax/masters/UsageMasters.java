package entities.responses.propertyTax.masters;

public class UsageMasters {
    private String code;
    private int orderNumber;
    private Object data;
    private boolean isResidential;
    private UsageMastersAuditDetails auditDetails;
    private String tenantId;
    private String name;
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean getIsResidential() {
        return this.isResidential;
    }

    public void setIsResidential(boolean isResidential) {
        this.isResidential = isResidential;
    }

    public UsageMastersAuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(UsageMastersAuditDetails auditDetails) {
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
