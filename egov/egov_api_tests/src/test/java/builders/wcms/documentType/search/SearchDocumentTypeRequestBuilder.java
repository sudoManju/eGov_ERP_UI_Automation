package builders.wcms.documentType.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentType.search.SearchDocumentTypeRequest;

public class SearchDocumentTypeRequestBuilder {
    SearchDocumentTypeRequest searchDocumentTypeRequest = new SearchDocumentTypeRequest();

    public SearchDocumentTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchDocumentTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchDocumentTypeRequest build() {
        return searchDocumentTypeRequest;
    }
}
