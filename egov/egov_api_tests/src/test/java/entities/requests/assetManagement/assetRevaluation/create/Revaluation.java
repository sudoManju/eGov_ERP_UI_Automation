package entities.requests.assetManagement.assetRevaluation.create;

public class Revaluation {
    private double revaluationAmount;
    private String comments;
    private int scheme;
    private double currentCapitalizedValue;
    private String typeOfChange;
    private String reevaluatedBy;
    private String reasonForRevaluation;
    private long revaluationDate;
    private int fund;
    private int subScheme;
    private int assetId;
    private double valueAfterRevaluation;
    private int function;
    private String tenantId;
    private int fixedAssetsWrittenOffAccount;
    private String status;

    public double getRevaluationAmount() {
        return this.revaluationAmount;
    }

    public void setRevaluationAmount(double revaluationAmount) {
        this.revaluationAmount = revaluationAmount;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getScheme() {
        return this.scheme;
    }

    public void setScheme(int scheme) {
        this.scheme = scheme;
    }

    public double getCurrentCapitalizedValue() {
        return this.currentCapitalizedValue;
    }

    public void setCurrentCapitalizedValue(double currentCapitalizedValue) {
        this.currentCapitalizedValue = currentCapitalizedValue;
    }

    public String getTypeOfChange() {
        return this.typeOfChange;
    }

    public void setTypeOfChange(String typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

    public String getReevaluatedBy() {
        return this.reevaluatedBy;
    }

    public void setReevaluatedBy(String reevaluatedBy) {
        this.reevaluatedBy = reevaluatedBy;
    }

    public String getReasonForRevaluation() {
        return this.reasonForRevaluation;
    }

    public void setReasonForRevaluation(String reasonForRevaluation) {
        this.reasonForRevaluation = reasonForRevaluation;
    }

    public long getRevaluationDate() {
        return this.revaluationDate;
    }

    public void setRevaluationDate(long revaluationDate) {
        this.revaluationDate = revaluationDate;
    }

    public int getFund() {
        return this.fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public int getSubScheme() {
        return this.subScheme;
    }

    public void setSubScheme(int subScheme) {
        this.subScheme = subScheme;
    }

    public int getAssetId() {
        return this.assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public double getValueAfterRevaluation() {
        return this.valueAfterRevaluation;
    }

    public void setValueAfterRevaluation(double valueAfterRevaluation) {
        this.valueAfterRevaluation = valueAfterRevaluation;
    }

    public int getFunction() {
        return this.function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getFixedAssetsWrittenOffAccount() {
        return this.fixedAssetsWrittenOffAccount;
    }

    public void setFixedAssetsWrittenOffAccount(int fixedAssetsWrittenOffAccount) {
        this.fixedAssetsWrittenOffAccount = fixedAssetsWrittenOffAccount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
