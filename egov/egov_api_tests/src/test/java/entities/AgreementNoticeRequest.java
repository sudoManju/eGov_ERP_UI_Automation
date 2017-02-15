package entities;

public class AgreementNoticeRequest {
private String tenant_id;

private String agreement_id;


private String api_id;

private String ver;

private String ts;



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

public String getApi_id() {
	return api_id;
}

public void setApi_id(String api_id) {
	this.api_id = api_id;
}

public String getVer() {
	return ver;
}

public void setVer(String ver) {
	this.ver = ver;
}

public String getTs() {
	return ts;
}

public void setTs(String ts) {
	this.ts = ts;
}

}
