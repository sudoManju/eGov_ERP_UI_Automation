package builders;

import entities.pgrCollection.createComplaint.ServiceRequest;
import entities.pgrCollection.createComplaint.Values;

/**
 * Created by karthik on 16/3/17.
 */
public final class ServiceRequestBuilder {
    ServiceRequest serviceRequest = new ServiceRequest();
    Values values = new ValuesBuilder().build();

    ServiceRequestBuilder() {
        serviceRequest.setService_id(null);
        serviceRequest.setTenantId("123");
        serviceRequest.setUpdated_datetime(null);
        serviceRequest.setPhone("9999999990");
        serviceRequest.setApproval_comment(null);
        serviceRequest.setLng("77.6699807");
        serviceRequest.setService_name(null);
        serviceRequest.setApproval_position(null);
        serviceRequest.setService_request_id(null);
        serviceRequest.setFirst_name("SUMANTHUUUUU");
        serviceRequest.setValues(values);
        serviceRequest.setDescription("There is a huge problem");
        serviceRequest.setCrn(null);
        serviceRequest.setAgency_responsible(null);
        serviceRequest.setRequested_datetime(null);
        serviceRequest.setService_notice(null);
        serviceRequest.setService_code("PHDMG");
        serviceRequest.setLat("12.9188214");
        serviceRequest.setStatus_details(null);
        serviceRequest.setAccount_id(null);
        serviceRequest.setStatus(null);
        serviceRequest.setMedia_url(null);
        serviceRequest.setZipcode(null);
        serviceRequest.setDevice_id(null);
        serviceRequest.setExpected_datetime("20-02-2017 20:20:20");
        serviceRequest.setEmail("jake@maildrop.cc");
        serviceRequest.setLast_name("Jake");
    }

    public ServiceRequestBuilder withService_id(String service_id) {
        serviceRequest.setService_id(service_id);
        return this;
    }

    public ServiceRequestBuilder withTenantId(String tenantId) {
        serviceRequest.setTenantId(tenantId);
        return this;
    }

    public ServiceRequestBuilder withUpdated_datetime(String updated_datetime) {
        serviceRequest.setUpdated_datetime(updated_datetime);
        return this;
    }

    public ServiceRequestBuilder withPhone(String phone) {
        serviceRequest.setPhone(phone);
        return this;
    }

    public ServiceRequestBuilder withApproval_comment(String approval_comment) {
        serviceRequest.setApproval_comment(approval_comment);
        return this;
    }

    public ServiceRequestBuilder withLng(String lng) {
        serviceRequest.setLng(lng);
        return this;
    }

    public ServiceRequestBuilder withService_name(String service_name) {
        serviceRequest.setService_name(service_name);
        return this;
    }

    public ServiceRequestBuilder withApproval_position(String approval_position) {
        serviceRequest.setApproval_position(approval_position);
        return this;
    }

    public ServiceRequestBuilder withService_request_id(String service_request_id) {
        serviceRequest.setService_request_id(service_request_id);
        return this;
    }

    public ServiceRequestBuilder withFirst_name(String first_name) {
        serviceRequest.setFirst_name(first_name);
        return this;
    }

    public ServiceRequestBuilder withValues(Values values) {
        serviceRequest.setValues(values);
        return this;
    }

    public ServiceRequestBuilder withDescription(String description) {
        serviceRequest.setDescription(description);
        return this;
    }

    public ServiceRequestBuilder withCrn(String crn) {
        serviceRequest.setCrn(crn);
        return this;
    }

    public ServiceRequestBuilder withAgency_responsible(String agency_responsible) {
        serviceRequest.setAgency_responsible(agency_responsible);
        return this;
    }

    public ServiceRequestBuilder withRequested_datetime(String requested_datetime) {
        serviceRequest.setRequested_datetime(requested_datetime);
        return this;
    }

    public ServiceRequestBuilder withService_notice(String service_notice) {
        serviceRequest.setService_notice(service_notice);
        return this;
    }

    public ServiceRequestBuilder withService_code(String service_code) {
        serviceRequest.setService_code(service_code);
        return this;
    }

    public ServiceRequestBuilder withLat(String lat) {
        serviceRequest.setLat(lat);
        return this;
    }

    public ServiceRequestBuilder withStatus_details(String status_details) {
        serviceRequest.setStatus_details(status_details);
        return this;
    }

    public ServiceRequestBuilder withAccount_id(String account_id) {
        serviceRequest.setAccount_id(account_id);
        return this;
    }

    public ServiceRequestBuilder withStatus(String status) {
        serviceRequest.setStatus(status);
        return this;
    }

    public ServiceRequestBuilder withMedia_url(String media_url) {
        serviceRequest.setMedia_url(media_url);
        return this;
    }

    public ServiceRequestBuilder withZipcode(String zipcode) {
        serviceRequest.setZipcode(zipcode);
        return this;
    }

    public ServiceRequestBuilder withDevice_id(String device_id) {
        serviceRequest.setDevice_id(device_id);
        return this;
    }

    public ServiceRequestBuilder withExpected_datetime(String expected_datetime) {
        serviceRequest.setExpected_datetime(expected_datetime);
        return this;
    }

    public ServiceRequestBuilder withEmail(String email) {
        serviceRequest.setEmail(email);
        return this;
    }

    public ServiceRequestBuilder withLast_name(String last_name) {
        serviceRequest.setLast_name(last_name);
        return this;
    }

    public ServiceRequest build() {
        return serviceRequest;
    }
}
