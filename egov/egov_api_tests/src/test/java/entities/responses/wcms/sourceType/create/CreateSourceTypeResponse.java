package entities.responses.wcms.sourceType.create;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateSourceTypeResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("WaterSourceType")
    private WaterSourceType[] waterSourceType;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public WaterSourceType[] getWaterSourceType() {
        return this.waterSourceType;
    }

    public void setWaterSourceType(WaterSourceType[] waterSourceType) {
        this.waterSourceType = waterSourceType;
    }
}
