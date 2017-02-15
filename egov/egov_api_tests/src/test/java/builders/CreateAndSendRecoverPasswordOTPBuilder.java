package builders;


import entities.CreateAndSendRecoverPasswordOTPRequest;

public class CreateAndSendRecoverPasswordOTPBuilder {
	CreateAndSendRecoverPasswordOTPRequest request=new CreateAndSendRecoverPasswordOTPRequest ();
	
	public CreateAndSendRecoverPasswordOTPBuilder()
	{
		request.setTenant_id("47883");
		request.setUsername("dhsuj");
	
	}
	
	public CreateAndSendRecoverPasswordOTPRequest build(){
		return request;
	}
}