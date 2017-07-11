package entities.requests.wcms.propertyPipeSize.create;

public class PropertyPipeSize {
    private String propertyTypeName;
    private String pipeSize;
    private String tenantId;
    private String active;

    public String getPropertyTypeName() {
        return this.propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getPipeSize() {
        return this.pipeSize;
    }

    public void setPipeSize(String pipeSize) {
        this.pipeSize = pipeSize;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
