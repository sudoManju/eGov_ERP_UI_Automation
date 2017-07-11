package entities.requests.propertyTax.services.create;

public class AuditDetails
{
    private int lastModifiedTime;

    private String createdBy;

    private int createdTime;

    private String lastModifiedBy;

    public int getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(int lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(int createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
