package builders.login;

import entities.login.LoginUserRequest;
import entities.login.ResponseInfo;
import entities.login.User;

public class LoginUserBuilder {

	LoginUserRequest request = new LoginUserRequest();

    public LoginUserBuilder() {
        User user = new UserBuilder().build();
        ResponseInfo responseInfo = new ResponseInfoBuilder().build();

//        request.setTenant_id("848834");
        request.setAccess_token("abc");
        request.setScope("abc");
        request.setUser(user);
        request.setResponseInfo(responseInfo);
    }

    public LoginUserRequest build(){ return request; }

}

