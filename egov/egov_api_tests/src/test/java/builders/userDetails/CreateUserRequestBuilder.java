package builders.userDetails;

import entities.requests.userDetails.CreateUserRequest;
import entities.requests.userDetails.RequestInfo;
import entities.requests.userDetails.User;

public final class CreateUserRequestBuilder {
    CreateUserRequest createUserRequest = new CreateUserRequest();
    RequestInfo requestInfo = new RequestInfoBuilder().build();
    User user = new UserBuilder().build();


    public CreateUserRequestBuilder() {
        createUserRequest.setRequestInfo(requestInfo);
        createUserRequest.setUser(user);
    }

    public CreateUserRequestBuilder withUser(User User) {
        createUserRequest.setUser(User);
        return this;
    }

    public CreateUserRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createUserRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateUserRequest build() {
        return createUserRequest;
    }
}
