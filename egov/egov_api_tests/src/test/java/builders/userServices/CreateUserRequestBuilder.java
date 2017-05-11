package builders.userServices;

import entities.requests.userServices.CreateUserRequest;
import entities.requests.userServices.RequestInfo;
import entities.requests.userServices.User;

public class CreateUserRequestBuilder {

  CreateUserRequest request = new CreateUserRequest();

  public CreateUserRequestBuilder withRequestInfo(RequestInfo requestInfo){
      request.setRequestInfo(requestInfo);
      return this;
  }

  public CreateUserRequestBuilder withUser(User user){
      request.setUser(user);
      return this;
  }

  public CreateUserRequest build(){
      return request;
  }
}
