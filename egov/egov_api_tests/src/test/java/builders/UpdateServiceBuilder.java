package builders;


import entities.UpdateServiceRequest;

public class UpdateServiceBuilder {
UpdateServiceRequest request=new UpdateServiceRequest();
	
	public UpdateServiceBuilder()
	{
		request.setJurisdiction_id("78457");
		request.setService_code("ser567");
		request.setService_name("servicename123");
		
     }
	
	public UpdateServiceRequest build(){
		return request;
	}
}
