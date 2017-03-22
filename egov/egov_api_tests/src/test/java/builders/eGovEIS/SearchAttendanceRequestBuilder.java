package builders.eGovEIS;

import entities.requests.eGovEIS.SearchAttendanceRequest;

public final class SearchAttendanceRequestBuilder {

    SearchAttendanceRequest searchAttendanceRequest = new SearchAttendanceRequest();

    public SearchAttendanceRequestBuilder() {

        searchAttendanceRequest.setApiId("string");
        searchAttendanceRequest.setTs("");
        searchAttendanceRequest.setRequesterId("string");
        searchAttendanceRequest.setAuthToken("aeiou");
        searchAttendanceRequest.setMsgId("string");
        searchAttendanceRequest.setAction("string");
        searchAttendanceRequest.setDid("string");
        searchAttendanceRequest.setVer("string");
        searchAttendanceRequest.setKey("string");
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
