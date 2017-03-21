package entities.responses.assetManagement.assetService;

public class AssetCategory {
    private Object unitOfMeasurement;
    private Object parent;
    private String code;
    private Object assetAccount;
    private String depreciationMethod;
    private Object customFields;
    private String assetCategoryType;
    private Object depreciationExpenseAccount;
    private Object revaluationReserveAccount;
    private Object tenantId;
    private String name;
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

    public Object getCustomFields() {
        return this.customFields;
    }

    public void setCustomFields(Object customFields) {
        this.customFields = customFields;
    }

    public String getAssetCategoryType() {
        return this.assetCategoryType;
    }

    public void setAssetCategoryType(String assetCategoryType) {
        this.assetCategoryType = assetCategoryType;
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

    public Object getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(Object tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
