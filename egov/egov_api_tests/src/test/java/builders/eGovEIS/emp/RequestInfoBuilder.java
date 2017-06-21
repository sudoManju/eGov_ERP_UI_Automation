package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.RequestInfo;

public class RequestInfoBuilder {

    RequestInfo requestInfo = new RequestInfo();

    public RequestInfoBuilder(){
        requestInfo.setApiId("org.egov.pgr");
        requestInfo.setVer("1.0");
        requestInfo.setTs("24-04-2016 12:12:12");
        requestInfo.setAction("asd");
        requestInfo.setDid("4354648646");
        requestInfo.setKey("xyz");
        requestInfo.setMsgId("");
        requestInfo.setRequesterId("61");
    }

    public RequestInfo build(){
        return requestInfo;
    }
}
