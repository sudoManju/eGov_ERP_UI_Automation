package entities.responses.wcms.propertyPipeSize;

public class PropertyTypePipeSizes {
    private String propertyTypeId;
    private String propertyTypeName;
    private String pipeSizeId;
    private String auditDetails;
    private int pipeSize;
    private String tenantId;
    private boolean active;
    private String id;

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

    public String getPipeSizeId() {
        return this.pipeSizeId;
    }

    public void setPipeSizeId(String pipeSizeId) {
        this.pipeSizeId = pipeSizeId;
    }

    public String getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(String auditDetails) {
        this.auditDetails = auditDetails;
    }

    public int getPipeSize() {
        return this.pipeSize;
    }

    public void setPipeSize(int pipeSize) {
        this.pipeSize = pipeSize;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
