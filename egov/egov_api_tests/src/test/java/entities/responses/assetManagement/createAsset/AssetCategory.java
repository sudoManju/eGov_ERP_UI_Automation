package entities.responses.assetManagement.createAsset;

public class AssetCategory {
    private Object unitOfMeasurement;
    private Object parent;
    private String code;
    private Object assetAccount;
    private String depreciationMethod;
    private String version;
    private Object assetCategoryType;
    private boolean isAssetAllow;
    private Object depreciationExpenseAccount;
    private Object revaluationReserveAccount;
    private String tenantId;
    private String name;
    private AssetFieldsDefination[] assetFieldsDefination;
    private int id;
    private Object depreciationRate;
    private Object accumulatedDepreciationAccount;

    public Object getUnitOfMeasurement() {
        return this.unitOfMeasurement;
    }

    public void setUnitOfMeasurement(Object unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Object getParent() {
        return this.parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getAssetAccount() {
        return this.assetAccount;
    }

    public void setAssetAccount(Object assetAccount) {
        this.assetAccount = assetAccount;
    }

    public String getDepreciationMethod() {
        return this.depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getAssetCategoryType() {
        return this.assetCategoryType;
    }

    public void setAssetCategoryType(Object assetCategoryType) {
        this.assetCategoryType = assetCategoryType;
    }

    public boolean getIsAssetAllow() {
        return this.isAssetAllow;
    }

    public void setIsAssetAllow(boolean isAssetAllow) {
        this.isAssetAllow = isAssetAllow;
    }

    public Object getDepreciationExpenseAccount() {
        return this.depreciationExpenseAccount;
    }

    public void setDepreciationExpenseAccount(Object depreciationExpenseAccount) {
        this.depreciationExpenseAccount = depreciationExpenseAccount;
    }

    public Object getRevaluationReserveAccount() {
        return this.revaluationReserveAccount;
    }

    public void setRevaluationReserveAccount(Object revaluationReserveAccount) {
        this.revaluationReserveAccount = revaluationReserveAccount;
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

    public AssetFieldsDefination[] getAssetFieldsDefination() {
        return this.assetFieldsDefination;
    }

    public void setAssetFieldsDefination(AssetFieldsDefination[] assetFieldsDefination) {
        this.assetFieldsDefination = assetFieldsDefination;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getDepreciationRate() {
        return this.depreciationRate;
    }

    public void setDepreciationRate(Object depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public Object getAccumulatedDepreciationAccount() {
        return this.accumulatedDepreciationAccount;
    }

    public void setAccumulatedDepreciationAccount(Object accumulatedDepreciationAccount) {
        this.accumulatedDepreciationAccount = accumulatedDepreciationAccount;
    }
}
