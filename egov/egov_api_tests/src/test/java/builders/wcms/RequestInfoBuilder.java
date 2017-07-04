package builders.wcms;

import entities.requests.wcms.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("1");
        requestInfo.setVer("1");
//        requestInfo.setTs("13-04-2017 12:13:30");
        requestInfo.setTs("6546546464");
        requestInfo.setAction("");
        requestInfo.setDid("");
        requestInfo.setKey("");
        requestInfo.setMsgId("");
        requestInfo.setRequesterId("");
        requestInfo.setAuthToken("5c4cecb3-50eb-4cf7-8160-d764f47f5dbb");
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
