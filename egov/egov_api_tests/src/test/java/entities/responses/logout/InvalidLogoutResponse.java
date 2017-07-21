package entities.responses.logout;

import org.codehaus.jackson.annotate.JsonProperty;

public class InvalidLogoutResponse {

    @JsonProperty("TaxPeriodsMasterResponse")
    private ResponseInfo responseInfo;

    @JsonProperty("Error")
    private Error error;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
