package entities.requests.wcms.propertyCategoryType.create;

public class PropertyTypeCategoryType {
    private String propertyTypeName;
    private String categoryTypeName;
    private String tenantId;
    private String active;

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
