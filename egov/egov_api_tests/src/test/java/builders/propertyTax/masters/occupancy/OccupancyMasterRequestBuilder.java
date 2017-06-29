package builders.propertyTax.masters.occupancy;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.occupancy.OccupancyMasterRequest;
import entities.requests.propertyTax.masters.occupancy.OccuapancyMasters;

public class OccupancyMasterRequestBuilder {

   OccupancyMasterRequest request = new OccupancyMasterRequest();

   public OccupancyMasterRequestBuilder(){}

   public OccupancyMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
       request.setRequestInfo(requestInfo);
       return this;
   }

   public OccupancyMasterRequestBuilder withOccupancyMaster(OccuapancyMasters[] occupancyMasters){
       request.setOccuapancyMasters(occupancyMasters);
       return this;
   }

   public OccupancyMasterRequest build(){
       return request;
   }
}
