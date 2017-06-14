package builders.eGovEIS.employeeMaster.leaveApplication;

import entities.requests.eGovEIS.employeeMaster.leaveApplication.RequestInfo;
import entities.requests.eGovEIS.employeeMaster.leaveApplication.UserInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder() {
        requestInfo.setApiId("");
        requestInfo.setVer("1");
        requestInfo.setTs("01-04-2017 01:01:01");
        requestInfo.setAction("createLeaveTypeResource");
        requestInfo.setDid("");
        requestInfo.setKey("");
        requestInfo.setMsgId("");
        requestInfo.setAuthToken("aeiou");
    }

    public RequestInfoBuilder withUserInfo(UserInfo userInfo) {
        requestInfo.setUserInfo(userInfo);
        return this;
    }

    public RequestInfo build() {
        return requestInfo;
    }
}
