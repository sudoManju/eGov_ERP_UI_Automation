package builders.commonMasters;

import entities.requests.commonMasters.CommonMasterRequest;

public final class CommonMasterRequestBuilder {
    CommonMasterRequest commonMasterRequest = new CommonMasterRequest();

    public CommonMasterRequestBuilder() {
        commonMasterRequest.setApiId("string");
        commonMasterRequest.setVer("string");
        commonMasterRequest.setTs("");
        commonMasterRequest.setAction("string");
        commonMasterRequest.setDid("string");
        commonMasterRequest.setKey("string");
        commonMasterRequest.setMsgId("string");
        commonMasterRequest.setRequesterId("string");
        commonMasterRequest.setAuthToken("aeiou");
    }

    public CommonMasterRequestBuilder withVer(String ver) {
        commonMasterRequest.setVer(ver);
        return this;
    }

    public CommonMasterRequestBuilder withRequesterId(String requesterId) {
        commonMasterRequest.setRequesterId(requesterId);
        return this;
    }

    public CommonMasterRequestBuilder withAuthToken(String authToken) {
        commonMasterRequest.setAuthToken(authToken);
        return this;
    }

    public CommonMasterRequestBuilder withAction(String action) {
        commonMasterRequest.setAction(action);
        return this;
    }

    public CommonMasterRequestBuilder withMsgId(String msgId) {
        commonMasterRequest.setMsgId(msgId);
        return this;
    }

    public CommonMasterRequestBuilder withApiId(String apiId) {
        commonMasterRequest.setApiId(apiId);
        return this;
    }

    public CommonMasterRequestBuilder withDid(String did) {
        commonMasterRequest.setDid(did);
        return this;
    }

    public CommonMasterRequestBuilder withKey(String key) {
        commonMasterRequest.setKey(key);
        return this;
    }

    public CommonMasterRequestBuilder withTs(String ts) {
        commonMasterRequest.setTs(ts);
        return this;
    }

    public CommonMasterRequest build() {
        return commonMasterRequest;
    }
}
