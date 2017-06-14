package builders.eGovEIS.searchEISMaster;

import entities.requests.eGovEIS.searchEISMaster.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("1");
        requestInfo.setVer("1");
        requestInfo.setTs("08-06-2017 01:01:01");
        requestInfo.setAction("");
        requestInfo.setAuthToken("aeiou");
        requestInfo.setRequesterId("");
        requestInfo.setDid("");
        requestInfo.setKey("");
        requestInfo.setMsgId("");

    }

    public RequestInfoBuilder withAuthToken(String authToken) {
        requestInfo.setAuthToken(authToken);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
