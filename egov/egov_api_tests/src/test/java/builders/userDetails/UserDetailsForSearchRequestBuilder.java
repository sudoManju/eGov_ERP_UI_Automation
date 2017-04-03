package builders.userDetails;

import entities.requests.userDetails.RequestInfo;
import entities.requests.userDetails.UserDetailsForSearchRequest;

public final class UserDetailsForSearchRequestBuilder {

    UserDetailsForSearchRequest userDetailsForSearchRequest = new UserDetailsForSearchRequest();
    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public UserDetailsForSearchRequestBuilder() {
        int a[] = {1};
        userDetailsForSearchRequest.setId(a);
        userDetailsForSearchRequest.setRequestInfo(requestInfo);
    }

    public UserDetailsForSearchRequestBuilder withId(int[] id) {
        userDetailsForSearchRequest.setId(id);
        return this;
    }

    public UserDetailsForSearchRequestBuilder withRequestInfo(RequestInfo requestInfo1){
        userDetailsForSearchRequest.setRequestInfo(requestInfo1);
        return this;
    }

    public UserDetailsForSearchRequest build() {
        return userDetailsForSearchRequest;
    }
}