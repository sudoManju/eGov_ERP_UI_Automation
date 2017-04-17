package builders.commonMaster.createHoliday;

import entities.requests.commonMasters.createHoliday.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("emp");
        requestInfo.setTs("10/03/2017");
        requestInfo.setMsgId("20170310130900");
        requestInfo.setAction("Create");
        requestInfo.setDid("1");
        requestInfo.setAuthToken("4f77f679-d609-45e8-beb7-26f449ca209f");
        requestInfo.setVer("1.0");
        requestInfo.setKey("abcdkey");
        requestInfo.setRequesterId("1");
    }

    public RequestInfoBuilder withApi_id(String api_id) {
        requestInfo.setApiId(api_id);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfoBuilder withMsg_id(String msg_id) {
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

    public RequestInfoBuilder withAuth_token(String auth_token) {
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

    public RequestInfoBuilder withRequester_id(String requester_id) {
        requestInfo.setRequesterId(requester_id);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
