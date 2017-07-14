package builders.assetManagement.assetRevaluation.create;

import entities.requests.assetManagement.assetRevaluation.create.Revaluation;
import tests.BaseAPITest;

import static data.SearchParameterData.TENANT_KURNOOL;

public class RevaluationBuilder {

    Revaluation revaluation = new Revaluation();

    public RevaluationBuilder() {
        revaluation.setRevaluationAmount(Float.parseFloat(new BaseAPITest().get3DigitRandomInt()));
        revaluation.setComments("comments");
        revaluation.setScheme(4);
        revaluation.setCurrentCapitalizedValue(Float.parseFloat(new BaseAPITest().get3DigitRandomInt()));
        revaluation.setTypeOfChange("DECREASED");
        revaluation.setReevaluatedBy("5");
        revaluation.setReasonForRevaluation("Improvements");
        revaluation.setRevaluationDate(Long.parseLong("1496430744825"));
        revaluation.setFund(3);
        revaluation.setSubScheme(5);
        revaluation.setValueAfterRevaluation(Float.parseFloat(new BaseAPITest().get3DigitRandomInt()));
        revaluation.setFunction(124);
        revaluation.setTenantId(TENANT_KURNOOL);
        revaluation.setFixedAssetsWrittenOffAccount(1);
        revaluation.setStatus("APPROVED");
    }

    public RevaluationBuilder withRevaluationAmount(double revaluationAmount) {
        revaluation.setRevaluationAmount(revaluationAmount);
        return this;
    }

    public RevaluationBuilder withComments(String comments) {
        revaluation.setComments(comments);
        return this;
    }

    public RevaluationBuilder withScheme(int scheme) {
        revaluation.setScheme(scheme);
        return this;
    }

    public RevaluationBuilder withCurrentCapitalizedValue(double currentCapitalizedValue) {
        revaluation.setCurrentCapitalizedValue(currentCapitalizedValue);
        return this;
    }

    public RevaluationBuilder withTypeOfChange(String typeOfChange) {
        revaluation.setTypeOfChange(typeOfChange);
        return this;
    }

    public RevaluationBuilder withReevaluatedBy(String reevaluatedBy) {
        revaluation.setReevaluatedBy(reevaluatedBy);
        return this;
    }

    public RevaluationBuilder withReasonForRevaluation(String reasonForRevaluation) {
        revaluation.setReasonForRevaluation(reasonForRevaluation);
        return this;
    }

    public RevaluationBuilder withRevaluationDate(long revaluationDate) {
        revaluation.setRevaluationDate(revaluationDate);
        return this;
    }

    public RevaluationBuilder withFund(int fund) {
        revaluation.setFund(fund);
        return this;
    }

    public RevaluationBuilder withSubScheme(int subScheme) {
        revaluation.setSubScheme(subScheme);
        return this;
    }

    public RevaluationBuilder withAssetId(int assetId) {
        revaluation.setAssetId(assetId);
        return this;
    }

    public RevaluationBuilder withValueAfterRevaluation(double valueAfterRevaluation) {
        revaluation.setValueAfterRevaluation(valueAfterRevaluation);
        return this;
    }

    public RevaluationBuilder withFunction(int function) {
        revaluation.setFunction(function);
        return this;
    }

    public RevaluationBuilder withTenantId(String tenantId) {
        revaluation.setTenantId(tenantId);
        return this;
    }

    public RevaluationBuilder withFixedAssetsWrittenOffAccount(int fixedAssetsWrittenOffAccount) {
        revaluation.setFixedAssetsWrittenOffAccount(fixedAssetsWrittenOffAccount);
        return this;
    }

    public RevaluationBuilder withStatus(String status) {
        revaluation.setStatus(status);
        return this;
    }

    public Revaluation build() {
        return revaluation;
    }
}
