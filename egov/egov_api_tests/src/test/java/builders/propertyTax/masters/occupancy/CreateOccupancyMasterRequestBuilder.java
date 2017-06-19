package builders.propertyTax.masters.occupancy;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.occupancy.CreateOccupancyMasterRequest;
import entities.requests.propertyTax.masters.occupancy.OccuapancyMasters;
import org.apache.commons.lang3.RandomUtils;

public class CreateOccupancyMasterRequestBuilder {

   CreateOccupancyMasterRequest request = new CreateOccupancyMasterRequest();

   String num = String.valueOf((RandomUtils.nextInt(100, 999)));

   OccuapancyMasters[] occuapancyMasters = new OccuapancyMasters[1];

   OccuapancyMasters occuapancyMasters1 = new OccupancyMastersBuilder().withName("Test_"+num)
           .withCode(num).withNameLocal("Test_"+num).build();

    public CreateOccupancyMasterRequestBuilder(){
        occuapancyMasters[0] = occuapancyMasters1;
        request.setOccuapancyMasters(occuapancyMasters);
   }

   public CreateOccupancyMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
       request.setRequestInfo(requestInfo);
       return this;
   }

   public CreateOccupancyMasterRequest build(){
       return request;
   }
}
