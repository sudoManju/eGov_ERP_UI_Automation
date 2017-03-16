package builders;

import entities.pgrCollection.RequestInfo;

/**
 * Created by karthik on 16/3/17.
 */
public final class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    RequestInfoBuilder() {
        requestInfo.setApi_id("1");
        requestInfo.setTs(null);
        requestInfo.setMsg_id("");
        requestInfo.setAction("Create");
        requestInfo.setDid("");
        requestInfo.setAuth_token(null);
        requestInfo.setVer("1");
        requestInfo.setKey("");
        requestInfo.setRequester_id("");
    }

    public RequestInfoBuilder withApi_id(String api_id) {
        requestInfo.setApi_id(api_id);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }
    public RequestInfoBuilder withMsg_id(String msg_id) {
        requestInfo.setMsg_id(msg_id);
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
        requestInfo.setAuth_token(auth_token);
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
        requestInfo.setRequester_id(requester_id);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
