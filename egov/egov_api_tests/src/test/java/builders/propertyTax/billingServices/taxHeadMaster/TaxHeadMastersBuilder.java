package builders.propertyTax.billingServices.taxHeadMaster;

import builders.propertyTax.billingServices.AuditDetailsBuilder;
import entities.requests.propertyTax.billingServices.AuditDetails;
import entities.requests.propertyTax.billingServices.taxHeadMaster.Glcodes;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasters;

import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class TaxHeadMastersBuilder {

    TaxHeadMasters taxHeadMasters = new TaxHeadMasters();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public TaxHeadMastersBuilder(){
        taxHeadMasters.setId(0);
        taxHeadMasters.setTenantId(tenantId);
        taxHeadMasters.setService(serviceName);
        taxHeadMasters.setOder(0);
        taxHeadMasters.setIsDebit(false);
        taxHeadMasters.setIsActualDemand(true);
        taxHeadMasters.setValidFrom("2342423424");
        taxHeadMasters.setValidTill("2342423424");
        taxHeadMasters.setAuditDetails(auditDetails);
    }

    public TaxHeadMastersBuilder withName(String service){
        taxHeadMasters.setName(service);
        return this;
    }

    public TaxHeadMastersBuilder withCategory(String category){
        taxHeadMasters.setCategory(category);
        return this;
    }

    public TaxHeadMastersBuilder withCode(String code){
        taxHeadMasters.setCode(code);
        return this;
    }

    public TaxHeadMastersBuilder withGlcode(Glcodes[] glcodes){
        taxHeadMasters.setGlcodes(glcodes);
        return this;
    }

    public TaxHeadMastersBuilder withId(int id){
        taxHeadMasters.setId(id);
        return this;
    }

    public TaxHeadMasters build(){
        return taxHeadMasters;
    }
}
