package entities;

public class CreateSevaServiceRequest {
    private String jurisdiction_id;
	public String getJurisdiction_id() {
		return jurisdiction_id;
	}
	public void setJurisdiction_id(String jurisdiction_id) {
		this.jurisdiction_id = jurisdiction_id;
	}
	private String  service_code;
    private String description;
    private String phone;
	public String getService_code() {
		return service_code;
	}
	public void setService_code(String service_code) {
		this.service_code = service_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
