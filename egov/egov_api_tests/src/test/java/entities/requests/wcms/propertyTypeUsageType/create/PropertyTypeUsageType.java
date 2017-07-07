package entities.requests.wcms.propertyTypeUsageType.create;

public class PropertyTypeUsageType {
    private String propertyType;
    private String tenantId;
    private boolean active;
    private String usageType;

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsageType() {
        return this.usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }
}
