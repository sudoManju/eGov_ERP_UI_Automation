package builders;

import entities.requests.UserDetailsRequest;

public final class UserDetailsRequestBuilder {

    UserDetailsRequest userDetailsRequest = new UserDetailsRequest();

    public UserDetailsRequestBuilder() {
        int a[] = {1};
        userDetailsRequest.setId(a);
    }

    public UserDetailsRequestBuilder withId(int[] id) {
        userDetailsRequest.setId(id);
        return this;
    }

    public UserDetailsRequest build() {
        return userDetailsRequest;
    }
}
