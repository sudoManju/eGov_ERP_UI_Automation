package builders;

import entities.CreateUserRequest;
import entities.CreateUserRequest;


/**
 * Created by soumyaghosh on 25/08/16.
 */
public class CreateUserRequestBuilder {

    CreateUserRequest request = new CreateUserRequest();

    public CreateUserRequestBuilder() {
        request.setUsername("asdasd");
        request.setPassword("dsdd");
    }

    public CreateUserRequest build(){ return request; }

}
