package builders.ptis;

import entities.ptis.ConnectionDetails;

/**
 * Created by karthik on 22/11/16.
 */
public class ConnectionDetailsBuilder {

    ConnectionDetails connectionDetails = new ConnectionDetails();

    public ConnectionDetailsBuilder(){}

    public ConnectionDetailsBuilder withWaterSourceType(String waterSourceType){
        connectionDetails.setWaterSourceType(waterSourceType);
        return this;
    }

    public ConnectionDetailsBuilder withConnectionType(String connectionType){
        connectionDetails.setConnectionType(connectionType);
        return this;
    }

    public ConnectionDetailsBuilder withPropertyType(String propertyType){
        connectionDetails.setPropertyType(propertyType);
        return this;
    }

    public ConnectionDetailsBuilder withCategory(String category){
        connectionDetails.setCategory(category);
        return this;
    }

    public ConnectionDetailsBuilder withUsageType(String usageType){
        connectionDetails.setUsageType(usageType);
        return this;
    }

    public ConnectionDetailsBuilder withHscPipeSize(String hscPipeSize){
        connectionDetails.setHscPipeSize(hscPipeSize);
        return this;
    }

    public ConnectionDetails build(){
        return connectionDetails;
    }

}
