package entities.responses.wcms.propertyCategoryType;

public class PropertyCategories {
    private String propertyTypeId;
    private String propertyTypeName;
    private String categoryTypeName;
    private String auditDetails;
    private String tenantId;
    private boolean active;
    private String categoryTypeId;
    private String id;
    private String version;

    public String getPropertyTypeId() {
        return this.propertyTypeId;
    }

    public void setPropertyTypeId(String propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public String getPropertyTypeName() {
        return this.propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getCategoryTypeName() {
        return this.categoryTypeName;
    }

    public void setCategoryTypeName(String categoryTypeName) {
        this.categoryTypeName = categoryTypeName;
    }

    public String getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(String auditDetails) {
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

    public String getCategoryTypeId() {
        return this.categoryTypeId;
    }

    public void setCategoryTypeId(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
