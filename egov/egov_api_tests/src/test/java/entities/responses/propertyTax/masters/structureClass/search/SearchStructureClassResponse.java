package entities.responses.propertyTax.masters.structureClass.search;

import entities.responses.propertyTax.masters.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchStructureClassResponse {

    @JsonProperty("StructureClasses")
    private StructureClasses[] structureClasses;

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
