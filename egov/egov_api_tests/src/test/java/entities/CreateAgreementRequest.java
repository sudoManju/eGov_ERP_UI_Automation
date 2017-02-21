package entities;

import entities.Allottee;
import entities.Asset;

public class CreateAgreementRequest {
	private String tenant_id;
	
	private String   agreement_id;
	
	private String  agreement_number;
	
	private String  agreement_date;
	
	private Allottee allottee;
	
	private Asset asset;
	
	private String status;
	
	private String  nature_of_allotment;
	
	private String commencement_date;

	private String   rent;
	
	private String payment_cycle;



	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getAgreement_id() {
		return agreement_id;
	}

	public void setAgreement_id(String agreement_id) {
		this.agreement_id = agreement_id;
	}

	public String getAgreement_number() {
		return agreement_number;
	}

	public void setAgreement_number(String agreement_number) {
		this.agreement_number = agreement_number;
	}

	public String getAgreement_date() {
		return agreement_date;
	}

	public void setAgreement_date(String agreement_date) {
		this.agreement_date = agreement_date;
	}

	public Allottee getAllottee() {
		return allottee;
	}

	public void setAllottee(Allottee allottee) {
		this.allottee = allottee;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNature_of_allotment() {
		return nature_of_allotment;
	}

	public void setNature_of_allotment(String nature_of_allotment) {
		this.nature_of_allotment = nature_of_allotment;
	}

	public String getCommencement_date() {
		return commencement_date;
	}

	public void setCommencement_date(String commencement_date) {
		this.commencement_date = commencement_date;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getPayment_cycle() {
		return payment_cycle;
	}

	public void setPayment_cycle(String payment_cycle) {
		this.payment_cycle = payment_cycle;
	}
	
	


	
}
