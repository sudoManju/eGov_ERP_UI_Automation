package builders.eGovEIS;


import entities.requests.eGovEIS.createAttendance.RequestInfo;

public class RequestInfoBuilder {
    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("_asset _search");
        requestInfo.setVer("ver");
        requestInfo.setTs("2017-02-20T08:30:49.799+0000");
        requestInfo.setMsgId("user id");
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