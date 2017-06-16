package entities.responses.propertyTax.masters.structureClass.search;

public class StructureClassesAuditDetails {
    private long lastModifiedTime;
    private String createdBy;
    private String lastModifiedBy;
    private long createdTime;

    public long getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
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

    public long getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
