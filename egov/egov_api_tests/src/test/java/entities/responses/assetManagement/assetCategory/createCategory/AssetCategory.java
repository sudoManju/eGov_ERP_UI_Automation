package entities.responses.assetManagement.assetCategory.createCategory;

public class AssetCategory {
    private int unitOfMeasurement;
    private int parent;
    private String code;
    private int assetAccount;
    private String depreciationMethod;
    private String version;
    private String assetCategoryType;
    private boolean isAssetAllow;
    private int depreciationExpenseAccount;
    private int revaluationReserveAccount;
    private String tenantId;
    private String name;
    private AssetFieldsDefination[] assetFieldsDefination;
    private int id;
    private int depreciationRate;
    private int accumulatedDepreciationAccount;

    public int getUnitOfMeasurement() {
        return this.unitOfMeasurement;
    }

    public void setUnitOfMeasurement(int unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getParent() {
        return this.parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAssetAccount() {
        return this.assetAccount;
    }

    public void setAssetAccount(int assetAccount) {
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

    public String getAssetCategoryType() {
        return this.assetCategoryType;
    }

    public void setAssetCategoryType(String assetCategoryType) {
        this.assetCategoryType = assetCategoryType;
    }

    public boolean getIsAssetAllow() {
        return this.isAssetAllow;
    }

    public void setIsAssetAllow(boolean isAssetAllow) {
        this.isAssetAllow = isAssetAllow;
    }

    public int getDepreciationExpenseAccount() {
        return this.depreciationExpenseAccount;
    }

    public void setDepreciationExpenseAccount(int depreciationExpenseAccount) {
        this.depreciationExpenseAccount = depreciationExpenseAccount;
    }

    public int getRevaluationReserveAccount() {
        return this.revaluationReserveAccount;
    }

    public void setRevaluationReserveAccount(int revaluationReserveAccount) {
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

    public int getDepreciationRate() {
        return this.depreciationRate;
    }

    public void setDepreciationRate(int depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public int getAccumulatedDepreciationAccount() {
        return this.accumulatedDepreciationAccount;
    }

    public void setAccumulatedDepreciationAccount(int accumulatedDepreciationAccount) {
        this.accumulatedDepreciationAccount = accumulatedDepreciationAccount;
    }
}
