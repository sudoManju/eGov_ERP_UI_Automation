package builders;

import entities.LoginAUserRequest;

public class LoginAUserBuilder {
	LoginAUserRequest request = new LoginAUserRequest();

    public LoginAUserBuilder() {
        request.setTenant_id("848834");
      
    }

    public LoginAUserRequest build(){ return request; }

}

