package entities;

public class PGRComplaintResponse {

    private Response_info response_info;

    private String[] service_requests;

    public Response_info getResponse_info() {
        return response_info;
    }

    public void setResponse_info(Response_info response_info) {
        this.response_info = response_info;
    }

    public String[] getService_requests() {
        return service_requests;
    }

    public void setService_requests(String[] service_requests) {
        this.service_requests = service_requests;
    }
}
