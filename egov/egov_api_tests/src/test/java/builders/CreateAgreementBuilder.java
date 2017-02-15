package builders;


import entities.CreateAgreementRequest;


public class CreateAgreementBuilder {
	CreateAgreementRequest request=new CreateAgreementRequest();
	
	public CreateAgreementBuilder()
	{
		request.setTenant_id("7484");
		request.setAgreement_id("783shf");
		request.setAgreement_number("nvjhj78");
		request.setAgreement_date("2017-02-13");
		request.getAllottee().setName("hfehs");
		request.getAllottee().setContact_no(7485739);
		request.getAllottee().setAddress("jcaijhdiajdfieaf naheijfia");
		request.getAsset().setCategory("nsjdcjsdh");
		request.getAsset().setName("bawdjaudh");
		request.getAsset().setLocality("bdhasbdahs");
		request.getAsset().setStreet("bcjbjanjd");
        request.getAsset().setZone("sjdxjasicj");
        request.getAsset().setWard("asnjdhaj");
        request.getAsset().setBlock("ajsdjISJ");
        request.getAsset().setElectionward("KJDHJDISi");
        request.setStatus("active");
        request.setNature_of_allotment("shdujshu");
        request.setCommencement_date("2017-02-13");
        request.setRent("jrehg4848488");
        request.setPayment_cycle("bch78dhnah");
     }
	
	public CreateAgreementRequest build(){
		return request;
	}
}
