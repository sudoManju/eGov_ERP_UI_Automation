package builders;

import entities.RequestInfo;
import entities.ServiceRequest;
import entities.ServiceRequestForBrokenBinRequest;

/**
 * Created by soumyaghosh on 22/02/17.
 */
public class ServiceRequestForBrokenBinRequestBuilder {
    ServiceRequestForBrokenBinRequest request = new ServiceRequestForBrokenBinRequest();

    public ServiceRequestForBrokenBinRequestBuilder() {

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setApi_id("org.egov.pgr");
        requestInfo.setVer("1.0");
        requestInfo.setTs("23/12/2016");
        requestInfo.setAction("POST");
        requestInfo.setDid("4354648646");
        requestInfo.setKey("xyz");
        requestInfo.setMsg_id("654654");
        requestInfo.setRequester_id("9531489645");
        requestInfo.setAuth_token("byrfyrfieruiuirugiergh");

        request.setRequestInfo(requestInfo);

        ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setService_request_id("");
            serviceRequest.setStatus("true");
            serviceRequest.setService_name("Broken Bin");
            serviceRequest.setService_code("BRKNB");
            serviceRequest.setDescription("Complaints regarding broken bin");
            serviceRequest.setRequested_datetime("2016-12-26 12:30 IST");
            serviceRequest.setAddress("Near central building area");
            serviceRequest.setAddress_id("269");
            serviceRequest.setMedia_url("");
            serviceRequest.setFirst_name("Raman");
            serviceRequest.setPhone("9536584725");
            serviceRequest.setEmail("raman@gmail.com");

        request.setServiceRequest(serviceRequest);


    }

    public ServiceRequestForBrokenBinRequest build(){ return request; }


}
