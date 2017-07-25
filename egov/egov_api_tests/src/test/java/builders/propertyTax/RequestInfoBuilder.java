package builders.propertyTax;

import entities.requests.propertyTax.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("org.egov.ptis");
        requestInfo.setVer("1.0");
        requestInfo.setTs("20934234234234");
        requestInfo.setAction("asd");
        requestInfo.setDid("4354648646");
        requestInfo.setKey("xyz");
        requestInfo.setMsgId("654654");
        requestInfo.setRequesterId("61");
    }

    public RequestInfoBuilder withAuthToken(String token) {
        requestInfo.setAuthToken(token);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
