package entities.requests.assetManagement.assetCategory;

public class RequestInfo {
    private UserInfo userInfo;
    private Object ver;
    private Object authToken;
    private Object action;
    private Object msgId;
    private Object correlationId;
    private Object apiId;
    private Object did;
    private Object key;
    private Object ts;

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Object getVer() {
        return this.ver;
    }

    public void setVer(Object ver) {
        this.ver = ver;
    }

    public Object getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(Object authToken) {
        this.authToken = authToken;
    }

    public Object getAction() {
        return this.action;
    }

    public void setAction(Object action) {
        this.action = action;
    }

    public Object getMsgId() {
        return this.msgId;
    }

    public void setMsgId(Object msgId) {
        this.msgId = msgId;
    }

    public Object getCorrelationId() {
        return this.correlationId;
    }

    public void setCorrelationId(Object correlationId) {
        this.correlationId = correlationId;
    }

    public Object getApiId() {
        return this.apiId;
    }

    public void setApiId(Object apiId) {
        this.apiId = apiId;
    }

    public Object getDid() {
        return this.did;
    }

    public void setDid(Object did) {
        this.did = did;
    }

    public Object getKey() {
        return this.key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getTs() {
        return this.ts;
    }

    public void setTs(Object ts) {
        this.ts = ts;
    }
}
