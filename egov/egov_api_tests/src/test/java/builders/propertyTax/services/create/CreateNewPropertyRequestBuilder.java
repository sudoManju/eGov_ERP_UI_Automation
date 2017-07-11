package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.CreateNewPropertyRequest;
import entities.requests.propertyTax.services.create.Properties;
import entities.requests.propertyTax.services.create.RequestInfo;

public class CreateNewPropertyRequestBuilder {

    CreateNewPropertyRequest request = new CreateNewPropertyRequest();

    Properties[] properties1 = new Properties[1];

    Properties properties = new PropertiesBuilder().build();

    public CreateNewPropertyRequestBuilder(){
        properties1[0] = properties;
        request.setProperties(properties1);
    }

    public CreateNewPropertyRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreateNewPropertyRequestBuilder withProperties(Properties[] properties){
        request.setProperties(properties);
        return this;
    }

    public CreateNewPropertyRequest build(){
        return request;
    }
}
