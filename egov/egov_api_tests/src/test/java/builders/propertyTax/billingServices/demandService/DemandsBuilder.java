package builders.propertyTax.billingServices.demandService;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.billingServices.demandService.DemandDetails;
import entities.requests.propertyTax.billingServices.demandService.Demands;
import entities.requests.propertyTax.billingServices.demandService.Owner;

import static data.ConstantData.tenantId;

public class DemandsBuilder {

    Demands demands = new Demands();
    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public DemandsBuilder(){
        demands.setId(0);
        demands.setTenantId(tenantId);
        demands.setConsumerType("EMPLOYEE");
        demands.setMinimumAmountPayable(50);
        demands.setAuditDetails(auditDetails);
    }

    public DemandsBuilder withConsumerCode(String code){
        demands.setConsumerCode(code);
        return this;
    }

    public DemandsBuilder withBusinessService(String service){
        demands.setBusinessService(service);
        return this;
    }

    public DemandsBuilder withOwner(Owner owner){
        demands.setOwner(owner);
        return this;
    }

    public DemandsBuilder withTaxPeriodFrom(String taxPeriodFrom){
        demands.setTaxPeriodFrom(taxPeriodFrom);
        return this;
    }

    public DemandsBuilder withTaxPeriodTo(String taxPeriodTo){
        demands.setTaxPeriodTo(taxPeriodTo);
        return this;
    }

    public DemandsBuilder withDemandDetails(DemandDetails[] demandDetails){
        demands.setDemandDetails(demandDetails);
        return this;
    }

    public Demands build(){
        return demands;
    }
}
