package builders.wcms.donation.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.donation.create.CreateDonationRequest;
import entities.requests.wcms.donation.create.Donation;

public class CreateDonationRequestBuilder {
    CreateDonationRequest createDonationRequest = new CreateDonationRequest();

    public CreateDonationRequestBuilder withDonation(Donation donation) {
        createDonationRequest.setDonation(donation);
        return this;
    }

    public CreateDonationRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        createDonationRequest.setRequestInfo(requestInfo);
        return this;
    }

    public CreateDonationRequest build() {
        return createDonationRequest;
    }
}
