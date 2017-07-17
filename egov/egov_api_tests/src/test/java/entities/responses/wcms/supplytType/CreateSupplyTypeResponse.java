package entities.responses.wcms.supplytType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateSupplyTypeResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("supplyTypes")
    private SupplyTypes[] supplytypes;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public SupplyTypes[] getSupplytypes() {
        return this.supplytypes;
    }

    public void setSupplytypes(SupplyTypes[] supplytypes) {
        this.supplytypes = supplytypes;
    }
}
