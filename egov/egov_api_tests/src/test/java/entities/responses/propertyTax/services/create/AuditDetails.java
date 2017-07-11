package entities.responses.propertyTax.services.create;

public class AuditDetails {
    private int lastModifiedTime;
    private String createdBy;
    private String lastModifiedBy;
    private int createdTime;

    public int getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(int lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public int getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(int createdTime) {
        this.createdTime = createdTime;
    }
}
