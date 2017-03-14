package builders;

import entities.Login.LoginRequest;

public final class LoginRequestBuilder {

    private String username;
    private String password;
    private String grant_type;
    private String scope;
    LoginRequest loginRequest = new LoginRequest();


    public LoginRequestBuilder() {
        loginRequest.setUsername("narasappa");
        loginRequest.setPassword("demo");
        loginRequest.setGrant_type("password");
        loginRequest.setScope("read");
    }

    public LoginRequestBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginRequestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginRequestBuilder withGrant_type(String grant_type) {
        this.grant_type = grant_type;
        return this;
    }

    public LoginRequestBuilder withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public LoginRequest build() {
        return loginRequest;
    }
}
