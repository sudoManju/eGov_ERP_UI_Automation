package entities.responses.assetManagement.assetServices;

import org.codehaus.jackson.annotate.JsonProperty;

public class AssetServiceResponse {

    private ResponseInfo ResponseInfo;

    @JsonProperty("Assets")
    private Assets[] Assets;

    public entities.responses.assetManagement.assetServices.ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(entities.responses.assetManagement.assetServices.ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public entities.responses.assetManagement.assetServices.Assets[] getAssets() {
        return this.Assets;
    }

    public void setAssets(entities.responses.assetManagement.assetServices.Assets[] Assets) {
        this.Assets = Assets;
    }
}
