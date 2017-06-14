package builders.eGovEIS.employeeLeave;

import entities.requests.eGovEIS.employeeLeave.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("1");
        requestInfo.setVer("1");
        requestInfo.setTs("08-06-2017 01:01:01");
        requestInfo.setAction("create");
        requestInfo.setDid("");
        requestInfo.setKey("");
        requestInfo.setMsgId("");
        requestInfo.setRequesterId("1");
        requestInfo.setAuthToken("aeiou");
    }

    public RequestInfoBuilder withAuthToken(String authToken) {
        requestInfo.setAuthToken(authToken);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
