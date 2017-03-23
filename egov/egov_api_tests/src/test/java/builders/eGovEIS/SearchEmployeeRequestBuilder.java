package builders.eGovEIS;

import entities.requests.eGovEIS.SearchEmployeeRequest;

public final class SearchEmployeeRequestBuilder {

    SearchEmployeeRequest searchEmployeeRequest = new SearchEmployeeRequest();

    public SearchEmployeeRequestBuilder() {
        searchEmployeeRequest.setApiId("string");
        searchEmployeeRequest.setTs("2017-01-18T07:18:23.130Z");
        searchEmployeeRequest.setRequesterId("string");
        searchEmployeeRequest.setAuthToken("aeiou");
        searchEmployeeRequest.setMsgId("string");
        searchEmployeeRequest.setAction("string");
        searchEmployeeRequest.setDid("string");
        searchEmployeeRequest.setVer("string");
        searchEmployeeRequest.setKey("string");
    }

    public SearchEmployeeRequestBuilder withApiId(String apiId) {
        searchEmployeeRequest.setApiId(apiId);
        return this;
    }

    public SearchEmployeeRequestBuilder withTs(String ts) {
        searchEmployeeRequest.setTs(ts);
        return this;
    }

    public SearchEmployeeRequestBuilder withRequesterId(String requesterId) {
        searchEmployeeRequest.setRequesterId(requesterId);
        return this;
    }

    public SearchEmployeeRequestBuilder withAuthToken(String authToken) {
        searchEmployeeRequest.setAuthToken(authToken);
        return this;
    }

    public SearchEmployeeRequestBuilder withMsgId(String msgId) {
        searchEmployeeRequest.setMsgId(msgId);
        return this;
    }

    public SearchEmployeeRequestBuilder withAction(String action) {
        searchEmployeeRequest.setAction(action);
        return this;
    }

    public SearchEmployeeRequestBuilder withDid(String did) {
        searchEmployeeRequest.setDid(did);
        return this;
    }

    public SearchEmployeeRequestBuilder withVer(String ver) {
        searchEmployeeRequest.setVer(ver);
        return this;
    }

    public SearchEmployeeRequestBuilder withKey(String key) {
        searchEmployeeRequest.setKey(key);
        return this;
    }

    public SearchEmployeeRequest build() {
        return searchEmployeeRequest;
    }
}
