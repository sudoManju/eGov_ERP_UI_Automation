package builders.wcms.getPlantTypes;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.getPlantTypes.SearchPlantTypesRequest;

public class SearchPlantTypesRequestBuilder {
    SearchPlantTypesRequest searchPlantTypesRequest = new SearchPlantTypesRequest();

    public SearchPlantTypesRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchPlantTypesRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchPlantTypesRequest build() {
        return searchPlantTypesRequest;
    }
}
