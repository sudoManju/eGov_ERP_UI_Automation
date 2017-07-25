package builders.propertyTax.services.create;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.services.create.*;

public class PropertyDetailBuilder {

    PropertyDetail propertyDetail = new PropertyDetail();

    Floors[] floor1 = new Floors[1];

    Floors floors = new FloorsBuilder().build();

    Documents[] documents1 = new Documents[1];

    Documents documents = new DocumentBuilder().build();

    WorkFlowDetails workFlowDetails = new WorkFlowDetailsBuilder().build();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public PropertyDetailBuilder(){
        propertyDetail.setSource("MUNICIPAL_RECORDS");
        propertyDetail.setRegdDocNo("rdn2");
        propertyDetail.setRegdDocDate("15/02/2017");
        propertyDetail.setReason("Trying to create");
        propertyDetail.setStatus("ACTIVE");
        propertyDetail.setIsVerified(true);
        propertyDetail.setVerificationDate("15/02/2017");
        propertyDetail.setIsExempted(false);
        propertyDetail.setExemptionReason("Testing the test");
        propertyDetail.setPropertyType("house");
        propertyDetail.setCategory("land");
        propertyDetail.setUsage("no");
        propertyDetail.setDepartment("incomeTax");
        propertyDetail.setApartment("no");
        propertyDetail.setLandOwner("kumar");
        propertyDetail.setFloorType("Marble");
        propertyDetail.setWoodType("TekWood");
        propertyDetail.setWallType("Cement");
        propertyDetail.setRoofType("Plastering");
        propertyDetail.setIsSuperStructure(false);
        propertyDetail.setSiteLength(12);
        propertyDetail.setSiteBreadth(15);
        propertyDetail.setTotalBuiltupArea(12);
        propertyDetail.setSitalArea(14);
        propertyDetail.setUndividedShare(17);
        propertyDetail.setNoOfFloors(1);
        propertyDetail.setStateId("si2");
        floor1[0] = floors;
        documents1[0] = documents;
        propertyDetail.setFloors(floor1);
        propertyDetail.setDocuments(documents1);
        propertyDetail.setWorkFlowDetails(workFlowDetails);
        propertyDetail.setAuditDetails(auditDetails);
    }

    public PropertyDetailBuilder withWoodType(String woodType){
        propertyDetail.setWoodType(woodType);
        return this;
    }

    public PropertyDetailBuilder withWallType(String wallType){
        propertyDetail.setWallType(wallType);
        return this;
    }

    public PropertyDetailBuilder withRoofType(String roofType){
        propertyDetail.setRoofType(roofType);
        return this;
    }

    public PropertyDetailBuilder withFloorType(String floorType){
        propertyDetail.setFloorType(floorType);
        return this;
    }

    public PropertyDetail build(){
        return propertyDetail;
    }
}
