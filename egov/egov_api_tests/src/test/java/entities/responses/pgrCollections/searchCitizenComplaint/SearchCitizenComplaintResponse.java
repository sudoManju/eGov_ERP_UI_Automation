package entities.responses.pgrCollections.searchCitizenComplaint;

public class SearchCitizenComplaintResponse {
    private Response_info response_info;
    private Service_requests[] service_requests;

    public Response_info getResponse_info() {
        return this.response_info;
    }

    public void setResponse_info(Response_info response_info) {
        this.response_info = response_info;
    }

    public Service_requests[] getService_requests() {
        return this.service_requests;
    }

    public void setService_requests(Service_requests[] service_requests) {
        this.service_requests = service_requests;
    }
}
