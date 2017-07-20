package builders.wcms.donation.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.donation.search.SearchDonationRequest;

public class SearchDonationRequestBuilder {
    SearchDonationRequest searchDonationRequest = new SearchDonationRequest();

    public SearchDonationRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchDonationRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchDonationRequest build() {
        return searchDonationRequest;
    }
}
