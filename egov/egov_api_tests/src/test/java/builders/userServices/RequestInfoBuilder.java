package builders.userServices;

import entities.requests.userServices.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder(){
        requestInfo.setApiId("emp");
        requestInfo.setVer("1.0");
        requestInfo.setAction("create");
        requestInfo.setDid("1");
        requestInfo.setKey("abcdkey");
        requestInfo.setMsgId("20170310130900");
        requestInfo.setRequesterId("rajesh");
    }

    public RequestInfoBuilder withAuthToken(String token){
        requestInfo.setAuthToken(token);
        return this;
    }

    public RequestInfo build(){
        return requestInfo;
    }
}
