package builders;

import entities.AgreementNoticeRequest;


public class AgreementNoticeBuilder {
	AgreementNoticeRequest request=new AgreementNoticeRequest();
	
	public AgreementNoticeBuilder()
	{
		request.setTenant_id("7484");
		request.setAgreement_id("783shf");
		request.setApi_id("bchbj");
		request.setVer("v123");
		request.setTs("2017-02-13T09:18:20.692Z");
		
     }
	
	public AgreementNoticeRequest build(){
		return request;
	}
}

