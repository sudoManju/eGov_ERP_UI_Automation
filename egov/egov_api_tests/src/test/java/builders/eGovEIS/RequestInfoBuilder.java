package builders.eGovEIS;


import entities.requests.eGovEIS.createAttendance.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("123456");
        requestInfo.setVer("1");
        requestInfo.setTs("");
        requestInfo.setMsgId("1");
        requestInfo.setStatus("200");
        requestInfo.setResMsgId("uief87324");
    }

    public RequestInfoBuilder withApiId(String apiId) {
        requestInfo.setApiId(apiId);
        return this;
    }

    public RequestInfoBuilder withVer(String ver) {
        requestInfo.setVer(ver);
        return this;
    }

    public RequestInfoBuilder withTs(String ts) {
        requestInfo.setTs(ts);
        return this;
    }

    public RequestInfoBuilder withMsgId(String msgId) {
        requestInfo.setMsgId(msgId);
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

    public RequestInfo build() {
        return requestInfo;
    }
}