package builders.wcms.treatmentPlant.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.treatmentPlant.search.SearchTreatmentPlantRequest;

public class SearchTreatmentPlantRequestBuilder {
    SearchTreatmentPlantRequest searchTreatmentPlantRequest = new SearchTreatmentPlantRequest();

    public SearchTreatmentPlantRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchTreatmentPlantRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchTreatmentPlantRequest build() {
        return searchTreatmentPlantRequest;
    }
}
