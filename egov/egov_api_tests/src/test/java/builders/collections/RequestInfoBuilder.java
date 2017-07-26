package builders.collections;

import entities.requests.collections.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("1");
        requestInfo.setVer("1");
        requestInfo.setTs(435464864);
        requestInfo.setDid("");
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

    public RequestInfoBuilder withTs(long ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
