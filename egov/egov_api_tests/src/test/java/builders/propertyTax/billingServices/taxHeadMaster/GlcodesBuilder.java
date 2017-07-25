package builders.propertyTax.billingServices.taxHeadMaster;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.billingServices.taxHeadMaster.Glcodes;

import static data.ConstantData.serviceName;

public class GlcodesBuilder {

    Glcodes glcodes = new Glcodes();

    AuditDetails auditDetails   = new AuditDetailsBuilder().build();

    public GlcodesBuilder(){
        glcodes.setId(0);
        glcodes.setFromDate(23456789);
        glcodes.setToDate(23456789);
        glcodes.setAuditDetails(auditDetails);
        glcodes.setService(serviceName);
    }

    public GlcodesBuilder withTaxHead(String taxHead){
        glcodes.setTaxHead(taxHead);
        return this;
    }

    public GlcodesBuilder withGlcode(String glcode){
        glcodes.setGlCode(glcode);
        return this;
    }

    public Glcodes build(){
        return glcodes;
    }
}
