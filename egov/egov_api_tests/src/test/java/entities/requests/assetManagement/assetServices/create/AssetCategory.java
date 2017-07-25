package entities.requests.assetManagement.assetServices.create;

public class AssetCategory
{
    private String tenantId;

    private String assetCategoryType;

    private String depreciationRate;

    private String unitOfMeasurement;

    private String accumulatedDepreciationAccount;

    private String parent;

    private String isAssetAllow;

    private String code;

    private String version;

    private String id;

    private String depreciationMethod;

    private String revaluationReserveAccount;

    private String depreciationExpenseAccount;

    private String name;

    private AssetFieldsDefination[] assetFieldsDefination;

    private String assetAccount;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAssetCategoryType() {
        return assetCategoryType;
    }

    public void setAssetCategoryType(String assetCategoryType) {
        this.assetCategoryType = assetCategoryType;
    }

    public String getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(String depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getAccumulatedDepreciationAccount() {
        return accumulatedDepreciationAccount;
    }

    public void setAccumulatedDepreciationAccount(String accumulatedDepreciationAccount) {
        this.accumulatedDepreciationAccount = accumulatedDepreciationAccount;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIsAssetAllow() {
        return isAssetAllow;
    }

    public void setIsAssetAllow(String isAssetAllow) {
        this.isAssetAllow = isAssetAllow;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public String getRevaluationReserveAccount() {
        return revaluationReserveAccount;
    }

    public void setRevaluationReserveAccount(String revaluationReserveAccount) {
        this.revaluationReserveAccount = revaluationReserveAccount;
    }

    public String getDepreciationExpenseAccount() {
        return depreciationExpenseAccount;
    }

    public void setDepreciationExpenseAccount(String depreciationExpenseAccount) {
        this.depreciationExpenseAccount = depreciationExpenseAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetFieldsDefination[] getAssetFieldsDefination() {
        return assetFieldsDefination;
    }

    public void setAssetFieldsDefination(AssetFieldsDefination[] assetFieldsDefination) {
        this.assetFieldsDefination = assetFieldsDefination;
    }

    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }
}
