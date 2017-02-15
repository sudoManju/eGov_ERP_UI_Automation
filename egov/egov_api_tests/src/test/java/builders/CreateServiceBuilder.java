package builders;


import entities.CreateServiceRequest;

public class CreateServiceBuilder {
CreateServiceRequest request=new CreateServiceRequest ();
	
	public CreateServiceBuilder()
	{
	request.setJurisdiction_id("78457");
	request.setService_code("ser567");
	request.setService_name("servicename123");
	
	}
	
	public CreateServiceRequest build(){
		return request;
	}
}