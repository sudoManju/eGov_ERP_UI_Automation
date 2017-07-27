package entities.requests.wcms.supplyType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateSupplyTypeRequest {

    @JsonProperty("SupplyType")
    private SupplyType SupplyType;
    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public SupplyType getSupplyType() {
        return this.SupplyType;
    }

    public void setSupplyType(SupplyType SupplyType) {
        this.SupplyType = SupplyType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
