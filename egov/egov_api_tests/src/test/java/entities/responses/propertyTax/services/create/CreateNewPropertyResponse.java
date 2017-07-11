package entities.responses.propertyTax.services.create;

public class CreateNewPropertyResponse {
    private ResponseInfo responseInfo;
    private Properties[] properties;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public Properties[] getProperties() {
        return this.properties;
    }

    public void setProperties(Properties[] properties) {
        this.properties = properties;
    }
}
