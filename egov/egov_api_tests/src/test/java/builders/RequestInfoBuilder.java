package builders;


import entities.requests.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder(){}

    public RequestInfoBuilder withApiId(String apiId){
        requestInfo.setApiId(apiId);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfoBuilder withMsgid(String msgid) {
        requestInfo.setMsgId(msgid);
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

    public RequestInfoBuilder withAuthtoken(String authtoken) {
        requestInfo.setAuthToken(authtoken);
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

    public RequestInfoBuilder withRequesterId(String requesterId) {
        requestInfo.setRequesterId(requesterId);
        return this;
    }

    public RequestInfoBuilder withStatus(String status) {
        requestInfo.setStatus(status);
        return this;
    }

    public RequestInfoBuilder withResMsgId(String resMsgId) {
        requestInfo.setResMsgId(resMsgId);
        return this;
    }

    public RequestInfoBuilder withApi_id(String api_id) {
        requestInfo.setApi_id(api_id);
        return this;
    }

    public RequestInfoBuilder withMsg_id(String msg_id) {
        requestInfo.setMsg_id(msg_id);
        return this;
    }

    public RequestInfoBuilder withAuth_token(String auth_token) {
        requestInfo.setAuth_token(auth_token);
        return this;
    }

    public RequestInfoBuilder withRequester_id(String requester_id) {
        requestInfo.setRequester_id(requester_id);
        return this;
    }

    public RequestInfo build(){
        return requestInfo;
    }

}
