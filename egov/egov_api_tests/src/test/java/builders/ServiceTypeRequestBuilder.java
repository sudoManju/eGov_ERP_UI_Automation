package builders;

import entities.ServiceRequestForAnonymousRequest;



public class ServiceTypeRequestBuilder {

    ServiceRequestForAnonymousRequest request = new ServiceRequestForAnonymousRequest();

    public ServiceTypeRequestBuilder() {
        request.setId("1dc40f4d-659f-3430-edc8-163ace2d4be4");
        request.setDataMode("raw");
        request.setVersion("2");
        request.setCurrentHelper("normal");
        request.setDescription("service request for anonymous.");
        request.setCollectionId("97c6b615-71ef-fe7d-cace-82f6ddbcbfb2");
        request.setRawModeData("{\n  \"RequestInfo\": {\n    \"api_id\": \"org.egov.pgr_collection\",\n    \"ver\": \"1.0\",\n    \"ts\": \"23/12/2016\",\n    \"action\": \"POST\",\n    \"did\": \"4354648646\",\n    \"key\": \"xyz\",\n    \"msg_id\": \"654654\",\n    \"requester_id\": \"9531489645\",\n    \"auth_token\": \"byrfyrfieruiuirugiergh\"\n  },\n  \"ServiceRequest\": {\n    \"service_request_id\": \"\",\n    \"status\": true,\n    \"service_name\": \"Broken Bin\",\n    \"service_code\": \"BRKNB\",\n    \"description\": \"Complaints regarding broken bin\",\n    \"requested_datetime\": \"2016-12-26 12:30 IST\",\n    \"address\": \"Near central building area\",\n    \"address_id\": \"269\",\n    \"lat\": 0,\n    \"long\": 0,\n    \"media_url\": \"\",\n    \"first_name\": \"Raman\",\n    \"phone\": \"9536584725\",\n    \"email\": \"raman@gmail.com\"\n  }\n}");
    }

    public ServiceRequestForAnonymousRequest build(){ return request; }

}
