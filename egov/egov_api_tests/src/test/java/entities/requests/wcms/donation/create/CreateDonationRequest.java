package entities.requests.wcms.donation.create;

import entities.requests.wcms.RequestInfo;

public class CreateDonationRequest {
    private Donation donation;
    private RequestInfo requestInfo;

    public Donation getDonation() {
        return this.donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
