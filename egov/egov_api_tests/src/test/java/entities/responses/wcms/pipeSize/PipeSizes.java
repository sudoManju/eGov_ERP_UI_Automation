package entities.responses.wcms.pipeSize;

public class PipeSizes {
    private String code;
    private double sizeInInch;
    private String tenantId;
    private int sizeInMilimeter;
    private String description;
    private boolean active;
    private Object id;
    private Object auditDeatils;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getSizeInInch() {
        return this.sizeInInch;
    }

    public void setSizeInInch(double sizeInInch) {
        this.sizeInInch = sizeInInch;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getSizeInMilimeter() {
        return this.sizeInMilimeter;
    }

    public void setSizeInMilimeter(int sizeInMilimeter) {
        this.sizeInMilimeter = sizeInMilimeter;
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

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getAuditDeatils() {
        return this.auditDeatils;
    }

    public void setAuditDeatils(Object auditDeatils) {
        this.auditDeatils = auditDeatils;
    }
}
