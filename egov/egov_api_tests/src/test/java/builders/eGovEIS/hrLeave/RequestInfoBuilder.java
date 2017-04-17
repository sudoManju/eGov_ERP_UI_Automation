package builders.eGovEIS.hrLeave;


import entities.requests.eGovEIS.hrLeave.openingBalance.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("1");
        requestInfo.setTs("");
        requestInfo.setMsgId("");
        requestInfo.setAction("Create");
        requestInfo.setDid("");
        requestInfo.setAuthToken("4f77f679-d609-45e8-beb7-26f449ca209f");
        requestInfo.setVer("1");
        requestInfo.setKey("");
        requestInfo.setRequesterId("");
    }

    public RequestInfoBuilder withApiId(String api_id) {
        requestInfo.setApiId(api_id);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfoBuilder withMsgId(String msg_id) {
        requestInfo.setMsgId(msg_id);
        return this;
    }

    public RequestInfoBuilder withAction(String action) {
        requestInfo.setAction(action);
        return this;
    }

    public RequestInfoBuilder withDid(String did) {
        requestInfo.setDid(did);
        return this;
    }

    public RequestInfoBuilder withAuthToken(String auth_token) {
        requestInfo.setAuthToken(auth_token);
        return this;
    }

    public RequestInfoBuilder withVer(String ver) {
        requestInfo.setVer(ver);
        return this;
    }

    public RequestInfoBuilder withKey(String key) {
        requestInfo.setKey(key);
        return this;
    }

    public RequestInfoBuilder withRequesterId(String requester_id) {
        requestInfo.setRequesterId(requester_id);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
