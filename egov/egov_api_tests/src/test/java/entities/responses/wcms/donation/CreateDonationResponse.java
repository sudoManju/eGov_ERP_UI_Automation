package entities.responses.wcms.donation;

import entities.responses.wcms.ResponseInfo;

public class CreateDonationResponse {
    private Donations[] donations;
    private ResponseInfo responseInfo;

    public Donations[] getDonations() {
        return this.donations;
    }

    public void setDonations(Donations[] donations) {
        this.donations = donations;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
