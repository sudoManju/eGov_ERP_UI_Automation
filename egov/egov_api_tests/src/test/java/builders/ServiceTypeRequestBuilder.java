package builders;

import entities.ServiceTypeRequest;


/**
 * Created by soumyaghosh on 25/08/16.
 */
public class ServiceTypeRequestBuilder {

    ServiceTypeRequest request = new ServiceTypeRequest();

    public ServiceTypeRequestBuilder() {
        request.setJurisdiction_id("1234");
    }

    public ServiceTypeRequest build(){ return request; }

}
