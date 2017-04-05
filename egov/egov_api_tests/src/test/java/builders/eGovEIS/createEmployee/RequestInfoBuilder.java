package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();
    entities.requests.eGovEIS.RequestInfo requestInfo1 = new entities.requests.eGovEIS.RequestInfo();

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

    public RequestInfoBuilder(String msg) {
        requestInfo1.setApiId("emp");
        requestInfo1.setVer("1.0");
        requestInfo1.setTs("10/03/2017");
        requestInfo1.setAction("create");
        requestInfo1.setAuthToken("");
        requestInfo1.setRequesterId("rajesh");
        requestInfo1.setDid("1");
        requestInfo1.setKey("abcdkey");
        requestInfo1.setMsgId("20170310130900");
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

    public RequestInfoBuilder withAuthToken1(String authToken){
        requestInfo1.setAuthToken(authToken);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }

    public entities.requests.eGovEIS.RequestInfo build1() {
        return requestInfo1;
    }

}
