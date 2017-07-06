package entities.responses.propertyTax.masters.structureClass.create;

import entities.responses.propertyTax.masters.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class StructureClassResponse {

    @JsonProperty("StructureClasses")
    private StructureClasses[] structureClasses;

    @JsonProperty("ResponseInfo")
    private ResponseInfo responseInfo;

    public StructureClasses[] getStructureClasses() {
        return this.structureClasses;
    }

    public void setStructureClasses(StructureClasses[] structureClasses) {
        this.structureClasses = structureClasses;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
