package builders.wcms.documentTypeApplicationType.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentTypeApplicationType.search.SearchDocumentTypeApplicationTypeRequest;

public class SearchDocumentTypeApplicationTypeRequestBuilder {

    SearchDocumentTypeApplicationTypeRequest searchDocumentTypeApplicationTypeRequest = new SearchDocumentTypeApplicationTypeRequest();

    public SearchDocumentTypeApplicationTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchDocumentTypeApplicationTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchDocumentTypeApplicationTypeRequest build() {
        return searchDocumentTypeApplicationTypeRequest;
    }
}
