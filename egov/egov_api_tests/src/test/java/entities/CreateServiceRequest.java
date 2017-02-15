package entities;

public class CreateServiceRequest {

	
	private String jurisdiction_id;
	
	private String service_code;
	
	private String service_name;

	public String getJurisdiction_id() {
		return jurisdiction_id;
	}

	public void setJurisdiction_id(String jurisdiction_id) {
		this.jurisdiction_id = jurisdiction_id;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
}
