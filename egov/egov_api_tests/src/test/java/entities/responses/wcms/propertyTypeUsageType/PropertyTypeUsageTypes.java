package entities.responses.wcms.propertyTypeUsageType;

public class PropertyTypeUsageTypes {
    private String propertyTypeId;
    private String usageTypeId;
    private String propertyType;
    private Object auditDetails;
    private String tenantId;
    private boolean active;
    private int id;
    private String usageType;

    public String getPropertyTypeId() {
        return this.propertyTypeId;
    }

    public void setPropertyTypeId(String propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public String getUsageTypeId() {
        return this.usageTypeId;
    }

    public void setUsageTypeId(String usageTypeId) {
        this.usageTypeId = usageTypeId;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Object getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(Object auditDetails) {
        this.auditDetails = auditDetails;
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsageType() {
        return this.usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }
}
