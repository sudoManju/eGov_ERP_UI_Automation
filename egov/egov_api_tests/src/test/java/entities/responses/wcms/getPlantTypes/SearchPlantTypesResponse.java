package entities.responses.wcms.getPlantTypes;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchPlantTypesResponse {
    @JsonProperty("DataModelList")
    private DataModelList[] DataModelList;
    private ResponseInfo responseInfo;

    public DataModelList[] getDataModelList() {
        return this.DataModelList;
    }

    public void setDataModelList(DataModelList[] DataModelList) {
        this.DataModelList = DataModelList;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
