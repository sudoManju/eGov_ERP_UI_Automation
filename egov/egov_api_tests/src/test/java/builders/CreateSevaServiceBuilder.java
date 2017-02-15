package builders;


import entities.CreateSevaServiceRequest;

public class CreateSevaServiceBuilder {
CreateSevaServiceRequest request=new CreateSevaServiceRequest ();
	
	public CreateSevaServiceBuilder()
	{
	request.setService_code("ser123");
	request.setDescription("my seva");
	request.setPhone("848234299");
	request.setJurisdiction_id("526377");
	
	}
	
	public CreateSevaServiceRequest build(){
		return request;
	}
}
