package builders;

import entities.CreateAndSendOTPRequest;

public class CreateAndSendOtpBuilder {
	CreateAndSendOTPRequest request=new CreateAndSendOTPRequest ();
	
	public CreateAndSendOtpBuilder()
	{
		request.setTenant_id("47883");
		request.setUsername("huhjd");
	
	}
	
	public CreateAndSendOTPRequest build(){
		return request;
	}
}