package builders;

import entities.Login.LoginRequest;

public final class LoginRequestBuilder {

    LoginRequest loginRequest = new LoginRequest();


    public LoginRequestBuilder() {
        loginRequest.setUsername("narasappa");
        loginRequest.setPassword("demo");
        loginRequest.setGrant_type("password");
        loginRequest.setScope("read");
    }

    public LoginRequestBuilder withUsername(String username) {
        loginRequest.setUsername(username);
        return this;
    }

    public LoginRequestBuilder withPassword(String password) {
        loginRequest.setPassword(password);
        return this;
    }

    public LoginRequestBuilder withGrant_type(String grant_type) {
        loginRequest.setGrant_type(grant_type);
        return this;
    }

    public LoginRequestBuilder withScope(String scope) {
        loginRequest.setScope(scope);
        return this;
    }

    public LoginRequest build() {
        return loginRequest;
    }
}
