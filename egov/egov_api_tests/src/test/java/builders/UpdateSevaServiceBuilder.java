package builders;


import entities.UpdateSevaServiceRequest;

public class UpdateSevaServiceBuilder {
UpdateSevaServiceRequest request=new UpdateSevaServiceRequest();
	
	public UpdateSevaServiceBuilder()
	{
   request.setJurisdiction_id("6676");
   request.setService_request_id("363656");
   request.setService_code("ser234");
   request.setPhone("834853085");
   request.setDescription("describemkacjkaj");
     }
	
	public UpdateSevaServiceRequest build(){
		return request;
	}
}
