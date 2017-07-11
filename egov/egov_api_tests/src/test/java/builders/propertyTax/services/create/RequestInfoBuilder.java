package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder(){
        requestInfo.setKey("abcdkey");
        requestInfo.setAction("create");
        requestInfo.setApiId("emp");
        requestInfo.setDid("1");
        requestInfo.setMsgId("20170310130900");
        requestInfo.setRequesterId("rajesh");
        requestInfo.setTs("10032107");
        requestInfo.setVer("1.0");
    }

    public RequestInfoBuilder withAuthToken(String authToken){
        requestInfo.setAuthToken(authToken);
        return this;
    }

    public RequestInfo build(){
        return requestInfo;
    }
}
