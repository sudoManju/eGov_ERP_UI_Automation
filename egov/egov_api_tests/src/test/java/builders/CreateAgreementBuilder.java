package builders;


import entities.Asset;
import entities.CreateAgreementRequest;
import entities.Allottee;
import entities.Asset;



public class CreateAgreementBuilder {
	CreateAgreementRequest request=new CreateAgreementRequest();
	
	public CreateAgreementBuilder()
	{
        Allottee allottee = new Allottee();
        Asset asset = new Asset();

        request.setTenant_id("7484");
		request.setAgreement_id("9999");
		request.setAgreement_number("9999xyz");
		request.setAgreement_date("2017-02-13");

        allottee.setName("Sherlok");
		allottee.setContact_no(987654321);
		allottee.setAddress("221B Baker Street");
        request.setAllottee(allottee);


        asset.setCategory("A");
		asset.setName("Sherlok");
		asset.setLocality("east");
		asset.setStreet("Bekar Street");
        asset.setZone("north");
        asset.setWard("A");
        asset.setBlock("5th");
        asset.setElectionward("Z");
        request.setAsset(asset);

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
