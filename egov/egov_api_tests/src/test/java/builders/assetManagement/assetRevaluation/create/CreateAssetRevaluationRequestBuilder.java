package builders.assetManagement.assetRevaluation.create;

import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetRevaluation.create.CreateAssetRevaluationRequest;
import entities.requests.assetManagement.assetRevaluation.create.Revaluation;

public class CreateAssetRevaluationRequestBuilder {
    CreateAssetRevaluationRequest createAssetRevaluationRequest = new CreateAssetRevaluationRequest();

    public CreateAssetRevaluationRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createAssetRevaluationRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateAssetRevaluationRequestBuilder withRevaluation(Revaluation Revaluation) {
        createAssetRevaluationRequest.setRevaluation(Revaluation);
        return this;
    }

    public CreateAssetRevaluationRequest build() {
        return createAssetRevaluationRequest;
    }
}
