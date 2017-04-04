package builders.eGovEIS;

import entities.requests.eGovEIS.SearchAttendanceRequest;

public final class SearchAttendanceRequestBuilder {

    SearchAttendanceRequest searchAttendanceRequest = new SearchAttendanceRequest();

    public SearchAttendanceRequestBuilder() {

        searchAttendanceRequest.setApiId("emp");
        searchAttendanceRequest.setTs("10/03/2017");
        searchAttendanceRequest.setRequesterId("rajesh");
        searchAttendanceRequest.setAuthToken("");
        searchAttendanceRequest.setMsgId("20170310130900");
        searchAttendanceRequest.setAction("create");
        searchAttendanceRequest.setDid("1");
        searchAttendanceRequest.setVer("1.0");
        searchAttendanceRequest.setKey("abcdkey");
    }

    public SearchAttendanceRequestBuilder withApiId(String apiId) {
        searchAttendanceRequest.setApiId(apiId);
        return this;
    }

    public SearchAttendanceRequestBuilder withTs(String ts) {
        searchAttendanceRequest.setTs(ts);
        return this;
    }

    public SearchAttendanceRequestBuilder withRequesterId(String requesterId) {
        searchAttendanceRequest.setRequesterId(requesterId);
        return this;
    }

    public SearchAttendanceRequestBuilder withAuthToken(String authToken) {
        searchAttendanceRequest.setAuthToken(authToken);
        return this;
    }

    public SearchAttendanceRequestBuilder withMsgId(String msgId) {
        searchAttendanceRequest.setMsgId(msgId);
        return this;
    }

    public SearchAttendanceRequestBuilder withAction(String action) {
        searchAttendanceRequest.setAction(action);
        return this;
    }

    public SearchAttendanceRequestBuilder withDid(String did) {
        searchAttendanceRequest.setDid(did);
        return this;
    }

    public SearchAttendanceRequestBuilder withVer(String ver) {
        searchAttendanceRequest.setVer(ver);
        return this;
    }

    public SearchAttendanceRequestBuilder withKey(String key) {
        searchAttendanceRequest.setKey(key);
        return this;
    }

    public SearchAttendanceRequest build() {
        return searchAttendanceRequest;
    }
}
