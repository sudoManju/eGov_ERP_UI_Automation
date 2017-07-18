package builders.propertyTax.billingServices.glcodesMaster;

import builders.propertyTax.billingServices.AuditDetailsBuilder;
import entities.requests.propertyTax.billingServices.AuditDetails;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasters;

import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class GlcodeMastersBuilder {

    GlcodeMasters glcodeMasters = new GlcodeMasters();

    AuditDetails auditDetails   = new AuditDetailsBuilder().build();

    public GlcodeMastersBuilder(){
        glcodeMasters.setId(0);
        glcodeMasters.setService(serviceName);
        glcodeMasters.setFromDate(23456789);
        glcodeMasters.setToDate(23456789);
        glcodeMasters.setTenantId(tenantId);
        glcodeMasters.setAuditDetails(auditDetails);
    }

    public GlcodeMastersBuilder withTaxHead(String name){
        glcodeMasters.setTaxHead(name);
        return this;
    }

    public GlcodeMastersBuilder withGlcode(String glcode){
        glcodeMasters.setGlCode(glcode);
        return this;
    }

    public GlcodeMasters build(){
        return glcodeMasters;
    }

}
