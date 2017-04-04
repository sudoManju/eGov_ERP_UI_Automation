package builders.eGovEIS.createEmployee;


import entities.requests.eGovEIS.createEmployee.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("emp");
        requestInfo.setVer("1.0");
        requestInfo.setTs("10/03/2017");
        requestInfo.setAction("create");
        requestInfo.setAuthToken("");
        requestInfo.setRequesterId("rajesh");
        requestInfo.setDid("1");
        requestInfo.setKey("abcdkey");
        requestInfo.setMsgId("20170310130900");

    }

    public RequestInfoBuilder withApiId(String apiId) {
        requestInfo.setApiId(apiId);
        return this;
    }

    public RequestInfoBuilder withVer(String ver) {
        requestInfo.setVer(ver);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfoBuilder withMsgId(String msgId) {
        requestInfo.setMsgId(msgId);
        return this;
    }

    public RequestInfoBuilder withAuthToken(String authToken) {
        requestInfo.setAuthToken(authToken);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }

}
