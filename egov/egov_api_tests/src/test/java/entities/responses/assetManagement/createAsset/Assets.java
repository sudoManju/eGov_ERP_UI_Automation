package entities.responses.assetManagement.createAsset;

public class Assets {
    private String code;
    private AssetCategory assetCategory;
    private Object length;
    private String description;
    private Object yearWiseDepreciation;
    private Object modeOfAcquisition;
    private Object version;
    private double accumulatedDepreciation;
    private int dateOfCreation;
    private AssetAttributes[] assetAttributes;
    private boolean enableYearWiseDepreciation;
    private String assetDetails;
    private double grossValue;
    private String tenantId;
    private String name;
    private LocationDetails locationDetails;
    private Object width;
    private int assetReference;
    private int id;
    private double depreciationRate;
    private Department department;
    private Object remarks;
    private String status;
    private Object totalArea;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AssetCategory getAssetCategory() {
        return this.assetCategory;
    }

    public void setAssetCategory(AssetCategory assetCategory) {
        this.assetCategory = assetCategory;
    }

    public Object getLength() {
        return this.length;
    }

    public void setLength(Object length) {
        this.length = length;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getYearWiseDepreciation() {
        return this.yearWiseDepreciation;
    }

    public void setYearWiseDepreciation(Object yearWiseDepreciation) {
        this.yearWiseDepreciation = yearWiseDepreciation;
    }

    public Object getModeOfAcquisition() {
        return this.modeOfAcquisition;
    }

    public void setModeOfAcquisition(Object modeOfAcquisition) {
        this.modeOfAcquisition = modeOfAcquisition;
    }

    public Object getVersion() {
        return this.version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public double getAccumulatedDepreciation() {
        return this.accumulatedDepreciation;
    }

    public void setAccumulatedDepreciation(double accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    public int getDateOfCreation() {
        return this.dateOfCreation;
    }

    public void setDateOfCreation(int dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public AssetAttributes[] getAssetAttributes() {
        return this.assetAttributes;
    }

    public void setAssetAttributes(AssetAttributes[] assetAttributes) {
        this.assetAttributes = assetAttributes;
    }

    public boolean getEnableYearWiseDepreciation() {
        return this.enableYearWiseDepreciation;
    }

    public void setEnableYearWiseDepreciation(boolean enableYearWiseDepreciation) {
        this.enableYearWiseDepreciation = enableYearWiseDepreciation;
    }

    public String getAssetDetails() {
        return this.assetDetails;
    }

    public void setAssetDetails(String assetDetails) {
        this.assetDetails = assetDetails;
    }

    public double getGrossValue() {
        return this.grossValue;
    }

    public void setGrossValue(double grossValue) {
        this.grossValue = grossValue;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDetails getLocationDetails() {
        return this.locationDetails;
    }

    public void setLocationDetails(LocationDetails locationDetails) {
        this.locationDetails = locationDetails;
    }

    public Object getWidth() {
        return this.width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public int getAssetReference() {
        return this.assetReference;
    }

    public void setAssetReference(int assetReference) {
        this.assetReference = assetReference;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDepreciationRate() {
        return this.depreciationRate;
    }

    public void setDepreciationRate(double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Object getRemarks() {
        return this.remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getTotalArea() {
        return this.totalArea;
    }

    public void setTotalArea(Object totalArea) {
        this.totalArea = totalArea;
    }
}
