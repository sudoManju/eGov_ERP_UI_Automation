package builders.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.billingServices.demandService.DemandDetails;

public class DemandDetailsBuilder {

   DemandDetails demandDetails = new DemandDetails();

   public DemandDetailsBuilder(){
       demandDetails.setTaxAmount(100);
       demandDetails.setCollectionAmount(100);
   }

   public DemandDetailsBuilder withId(int id){
       demandDetails.setId(id);
       return this;
   }

   public DemandDetailsBuilder withTaxHeadMasterCode(String code){
       demandDetails.setTaxHeadMasterCode(code);
       return this;
   }

   public DemandDetails build(){
       return demandDetails;
   }
}
