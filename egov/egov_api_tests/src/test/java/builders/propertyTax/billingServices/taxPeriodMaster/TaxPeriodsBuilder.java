package builders.propertyTax.billingServices.taxPeriodMaster;

import builders.propertyTax.billingServices.AuditDetailsBuilder;
import entities.requests.propertyTax.billingServices.AuditDetails;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriods;

import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class TaxPeriodsBuilder {

    TaxPeriods taxPeriods = new TaxPeriods();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public TaxPeriodsBuilder(){
        taxPeriods.setId(0);
        taxPeriods.setService(serviceName);
        taxPeriods.setTenantId(tenantId);
        taxPeriods.setAuditDetails(auditDetails);
    }

    public TaxPeriodsBuilder withCode(String code){
        taxPeriods.setCode(code);
        return this;
    }

    public TaxPeriodsBuilder withFromDate(String fromDate){
        taxPeriods.setFromDate(fromDate);
        return this;
    }

    public TaxPeriodsBuilder withToDate(String toDate){
        taxPeriods.setToDate(toDate);
        return this;
    }

    public TaxPeriodsBuilder withFinancialYear(String year){
        taxPeriods.setFinancialYear(year);
        return this;
    }

    public TaxPeriods build(){
        return taxPeriods;
    }
}
