package entities.responses.assetManagement.createAsset;

public class ResponseInfo {
    private String ver;
    private Object resMsgId;
    private String msgId;
    private String apiId;
    private String ts;
    private Object status;

    public String getVer() {
        return this.ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public Object getResMsgId() {
        return this.resMsgId;
    }

    public void setResMsgId(Object resMsgId) {
        this.resMsgId = resMsgId;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getApiId() {
        return this.apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTs() {
        return this.ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Object getStatus() {
        return this.status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
