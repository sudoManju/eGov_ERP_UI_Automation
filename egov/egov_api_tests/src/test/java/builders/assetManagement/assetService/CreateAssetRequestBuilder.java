package builders.assetManagement.assetService;

import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetServices.create.Asset;
import entities.requests.assetManagement.assetServices.create.CreateAssetRequest;

public class CreateAssetRequestBuilder {

 CreateAssetRequest request = new CreateAssetRequest();

 public CreateAssetRequestBuilder withRequestInfo(RequestInfo requestInfo){
     request.setRequestInfo(requestInfo);
     return this;
 }

 public CreateAssetRequestBuilder withAsset(Asset asset){
     request.setAsset(asset);
     return this;
 }

 public CreateAssetRequest build(){
     return request;
 }
}
