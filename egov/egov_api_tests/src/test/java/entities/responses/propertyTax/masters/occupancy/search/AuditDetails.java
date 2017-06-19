package entities.responses.propertyTax.masters.occupancy.search;

public class AuditDetails {
    private Object lastModifiedTime;
    private String createdBy;
    private String lastModifiedBy;
    private Object createdTime;

    public Object getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(Object lastModifiedTime) {
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

    public Object getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
    }
}
