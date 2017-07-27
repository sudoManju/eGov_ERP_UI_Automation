package entities.responses.assetManagement.createAsset;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateAssetResponse {

   @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

   @JsonProperty("Assets")
   private Assets[] Assets;


    public entities.responses.assetManagement.createAsset.ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(entities.responses.assetManagement.createAsset.ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public entities.responses.assetManagement.createAsset.Assets[] getAssets() {
        return this.Assets;
    }

    public void setAssets(entities.responses.assetManagement.createAsset.Assets[] Assets) {
        this.Assets = Assets;
    }
}
