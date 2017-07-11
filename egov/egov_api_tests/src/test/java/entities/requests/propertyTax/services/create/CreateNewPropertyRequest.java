package entities.requests.propertyTax.services.create;

public class CreateNewPropertyRequest
{
    private RequestInfo requestInfo;

    private Properties[] properties;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public Properties[] getProperties() {
        return properties;
    }

    public void setProperties(Properties[] properties) {
        this.properties = properties;
    }
}
