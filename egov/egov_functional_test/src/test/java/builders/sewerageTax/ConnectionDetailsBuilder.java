package builders.sewerageTax;

import entities.sewerageTax.ConnectionDetails;

public class ConnectionDetailsBuilder {

    ConnectionDetails connectionDetails = new ConnectionDetails();

    public ConnectionDetailsBuilder(){}

    public ConnectionDetailsBuilder withPropertyType(String propertyType){
        connectionDetails.setPropertyType(propertyType);
        return this;
    }

    public ConnectionDetailsBuilder withNumberOfClosets(String numberOfClosets){
        connectionDetails.setNumOfClosets(numberOfClosets);
        return this;
    }

    public ConnectionDetailsBuilder withDocumentNum(String documentNum){
        connectionDetails.setDocumentNum(documentNum);
        return this;
    }

    public ConnectionDetails build(){
        return connectionDetails;
    }
}
