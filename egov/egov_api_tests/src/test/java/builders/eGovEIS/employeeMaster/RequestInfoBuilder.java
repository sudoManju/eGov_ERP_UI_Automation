package builders.eGovEIS.employeeMaster;

import entities.requests.eGovEIS.employeeMaster.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("");
        requestInfo.setVer("1");
        requestInfo.setTs("10-03-2017 00:00:00");
        requestInfo.setAction("create");
        requestInfo.setKey("");
        requestInfo.setMsgId("1");
        requestInfo.setRequesterId("");
        requestInfo.setAuthToken("aeiou");
    }

    public RequestInfoBuilder withVer(String ver) {
        requestInfo.setVer(ver);
        return this;
    }

    public RequestInfoBuilder withRequesterId(String requesterId) {
        requestInfo.setRequesterId(requesterId);
        return this;
    }

    public RequestInfoBuilder withAuthToken(String authToken) {
        requestInfo.setAuthToken(authToken);
        return this;
    }

    public RequestInfoBuilder withAction(String action) {
        requestInfo.setAction(action);
        return this;
    }

    public RequestInfoBuilder withMsgId(String msgId) {
        requestInfo.setMsgId(msgId);
        return this;
    }

    public RequestInfoBuilder withApiId(String apiId) {
        requestInfo.setApiId(apiId);
        return this;
    }

    public RequestInfoBuilder withDid(String did) {
        requestInfo.setDid(did);
        return this;
    }

    public RequestInfoBuilder withKey(String key) {
        requestInfo.setKey(key);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
