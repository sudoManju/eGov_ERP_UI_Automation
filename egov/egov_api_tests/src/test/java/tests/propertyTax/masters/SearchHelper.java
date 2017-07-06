package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.usage.SearchUsageMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.SearchMasterRequest;
import entities.responses.propertyTax.masters.department.create.DepartmentsMasterResponse;
import entities.responses.propertyTax.masters.department.search.SearchDepartmentsResponse;
import entities.responses.propertyTax.masters.floorTypes.create.FloorTypesResponse;
import entities.responses.propertyTax.masters.floorTypes.search.SearchFloorTypesResponse;
import entities.responses.propertyTax.masters.occupancy.create.OccupancyMasterResponse;
import entities.responses.propertyTax.masters.occupancy.search.SearchOccupancyMasterResponse;
import entities.responses.propertyTax.masters.propertyTypes.create.PropertyTypesResponse;
import entities.responses.propertyTax.masters.propertyTypes.search.SearchPropertyTypesResponse;
import entities.responses.propertyTax.masters.roofTypes.create.RoofTypeMasterResponse;
import entities.responses.propertyTax.masters.roofTypes.search.SearchRoofTypeMasterResponse;
import entities.responses.propertyTax.masters.structureClass.create.StructureClassResponse;
import entities.responses.propertyTax.masters.structureClass.search.SearchStructureClassResponse;
import entities.responses.propertyTax.masters.usage.search.SearchUsageMasterResponse;
import entities.responses.propertyTax.masters.usage.create.UsageMasterResponse;
import entities.responses.propertyTax.masters.wallTypes.create.WallTypesResponse;
import entities.responses.propertyTax.masters.wallTypes.search.SearchWallTypesMasterResponse;
import entities.responses.propertyTax.masters.woodTypes.create.WoodTypesResponse;
import entities.responses.propertyTax.masters.woodTypes.search.SearchWoodTypesResponse;
import org.testng.Assert;
import resources.propertyTax.masters.*;
import tests.BaseAPITest;
import utils.APILogger;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.SearchParameterData.*;

public class SearchHelper extends BaseAPITest {

    private String json;

     SearchHelper() {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchMasterRequest request = new SearchUsageMasterRequestBuilder().withRequestInfo(requestInfo).build();
        json = RequestHelper.getJsonString(request);
    }

     void searchForUsageMaster(UsageMasterResponse responseObjectOfUsage) throws IOException {
        new APILogger().log("Search Usage Master is Started --");
        Response responseForId = new UsageMasterResource().search(json, IDS + responseObjectOfUsage.getUsageMasters()[0].getId());
        checkResponseForUsage(responseForId, responseObjectOfUsage);
        new APILogger().log("Search Usage Master with Id is Success");

        Response responseForName = new UsageMasterResource().search(json, WITH_NAME + responseObjectOfUsage.getUsageMasters()[0].getName());
        checkResponseForUsage(responseForName, responseObjectOfUsage);
        new APILogger().log("Search Usage Master with Name is Success");

        Response responseForCode = new UsageMasterResource().search(json, WITH_CODE + responseObjectOfUsage.getUsageMasters()[0].getCode());
        checkResponseForUsage(responseForCode, responseObjectOfUsage);
        new APILogger().log("Search Usage Master with code is Success");

        Response responseForNameLocal = new UsageMasterResource().search(json, NAMELOCAL + responseObjectOfUsage.getUsageMasters()[0].getNameLocal());
        checkResponseForUsage(responseForNameLocal, responseObjectOfUsage);
        new APILogger().log("Search Usage Master with Name Local is Success");
        new APILogger().log("Search Usage Master is Completed --");
    }

    private void checkResponseForUsage(Response response, UsageMasterResponse create) throws IOException {
        SearchUsageMasterResponse responseObject = (SearchUsageMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchUsageMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getUsageMasters()[0].getId(), create.getUsageMasters()[0].getId());
        Assert.assertEquals(responseObject.getUsageMasters()[0].getName(), create.getUsageMasters()[0].getName());
        Assert.assertEquals(responseObject.getUsageMasters()[0].getCode(), create.getUsageMasters()[0].getCode());
        Assert.assertEquals(responseObject.getUsageMasters()[0].getNameLocal(), create.getUsageMasters()[0].getNameLocal());
    }

     void searchStructureClassMaster(StructureClassResponse responseObjectOfCreate) throws IOException {
        new APILogger().log("Search Structure Classes master is Started --");
        Response responseForId = new StructureClassMasterResource().search(json, IDS + responseObjectOfCreate.getStructureClasses()[0].getId());
        checkResponseForStructure(responseForId, responseObjectOfCreate);
        new APILogger().log("Search Structure Classes master with Id is Success");

        Response responseForName = new StructureClassMasterResource().search(json, WITH_NAME + responseObjectOfCreate.getStructureClasses()[0].getName());
        checkResponseForStructure(responseForName, responseObjectOfCreate);
        new APILogger().log("Search Structure Classes Master with Name is Success");

        Response responseForCode = new StructureClassMasterResource().search(json, WITH_CODE + responseObjectOfCreate.getStructureClasses()[0].getCode());
        checkResponseForStructure(responseForCode, responseObjectOfCreate);
        new APILogger().log("Search Structure Classes Master with code is Success");

        Response responseForNameLocal = new StructureClassMasterResource().search(json, NAMELOCAL + responseObjectOfCreate.getStructureClasses()[0].getNameLocal());
        checkResponseForStructure(responseForNameLocal, responseObjectOfCreate);
        new APILogger().log("Search Structure Classes Master with Name Local is Success");
        new APILogger().log("Search Structure Classes Master is Completed --");
    }

    private void checkResponseForStructure(Response response, StructureClassResponse responseObjectOfCreate) throws IOException {
        SearchStructureClassResponse responseObject = (SearchStructureClassResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchStructureClassResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getStructureClasses()[0].getId(), responseObjectOfCreate.getStructureClasses()[0].getId());
        Assert.assertEquals(responseObject.getStructureClasses()[0].getName(), responseObjectOfCreate.getStructureClasses()[0].getName());
        Assert.assertEquals(responseObject.getStructureClasses()[0].getCode(), responseObjectOfCreate.getStructureClasses()[0].getCode());
        Assert.assertEquals(responseObject.getStructureClasses()[0].getNameLocal(), responseObjectOfCreate.getStructureClasses()[0].getNameLocal());
    }

     void searchFloorTypesMaster(FloorTypesResponse responseObjectOfCreate) throws IOException {
        new APILogger().log("Search FloorTypes master is Started --");
        Response responseForId = new FloorTypesMasterResource().search(json, IDS + responseObjectOfCreate.getFloorTypes()[0].getId());
        checkAssertsForFloorTypes(responseForId, responseObjectOfCreate);
        new APILogger().log("Search FloorTypes master with Id is Success");

        Response responseForName = new FloorTypesMasterResource().search(json, WITH_NAME + responseObjectOfCreate.getFloorTypes()[0].getName());
        checkAssertsForFloorTypes(responseForName, responseObjectOfCreate);
        new APILogger().log("Search FloorTypes Master with Name is Success");

        Response responseForCode = new FloorTypesMasterResource().search(json, WITH_CODE + responseObjectOfCreate.getFloorTypes()[0].getCode());
        checkAssertsForFloorTypes(responseForCode, responseObjectOfCreate);
        new APILogger().log("Search FloorTypes Master with code is Success");

        Response responseForNameLocal = new FloorTypesMasterResource().search(json, NAMELOCAL + responseObjectOfCreate.getFloorTypes()[0].getNameLocal());
        checkAssertsForFloorTypes(responseForNameLocal, responseObjectOfCreate);
        new APILogger().log("Search FloorTypes Master with Name Local is Success");
        new APILogger().log("Search FloorTypes Master is Completed --");
    }

    private void checkAssertsForFloorTypes(Response response, FloorTypesResponse requestObject) throws IOException {
        SearchFloorTypesResponse responseObject = (SearchFloorTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchFloorTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getFloorTypes()[0].getId(), requestObject.getFloorTypes()[0].getId());
        Assert.assertEquals(responseObject.getFloorTypes()[0].getName(), requestObject.getFloorTypes()[0].getName());
        Assert.assertEquals(responseObject.getFloorTypes()[0].getCode(), requestObject.getFloorTypes()[0].getCode());
        Assert.assertEquals(responseObject.getFloorTypes()[0].getNameLocal(), requestObject.getFloorTypes()[0].getNameLocal());
    }

     void searchWoodTypesMaster(WoodTypesResponse create) throws IOException {
        new APILogger().log("Search WoodTypes is Started --");
        Response responseForId = new WoodTypesMasterResource().search(json, IDS + create.getWoodTypes()[0].getId());
        checkAssertsForWoodTypes(responseForId, create);
        new APILogger().log("Search WoodTypes Master with Id is Success");

        Response responseForName = new WoodTypesMasterResource().search(json, WITH_NAME + create.getWoodTypes()[0].getName());
        checkAssertsForWoodTypes(responseForName, create);
        new APILogger().log("Search WoodTypes Master with name is Success");

        Response responseForCode = new WoodTypesMasterResource().search(json, WITH_CODE + create.getWoodTypes()[0].getCode());
        checkAssertsForWoodTypes(responseForCode, create);
        new APILogger().log("Search WoodTypes Master with code is Success");

        Response responseForNameLocal = new WoodTypesMasterResource().search(json, NAMELOCAL + create.getWoodTypes()[0].getNameLocal());
        checkAssertsForWoodTypes(responseForNameLocal, create);
        new APILogger().log("Search WoodTypes Master with nameLocal is Success");
        new APILogger().log("Search WoodTypes is Completed --");
    }

    private void checkAssertsForWoodTypes(Response response, WoodTypesResponse requestObject) throws IOException {
        SearchWoodTypesResponse responseObject = (SearchWoodTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchWoodTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getWoodTypes()[0].getId(), requestObject.getWoodTypes()[0].getId());
        Assert.assertEquals(responseObject.getWoodTypes()[0].getName(), requestObject.getWoodTypes()[0].getName());
        Assert.assertEquals(responseObject.getWoodTypes()[0].getCode(), requestObject.getWoodTypes()[0].getCode());
        Assert.assertEquals(responseObject.getWoodTypes()[0].getNameLocal(),requestObject.getWoodTypes()[0].getNameLocal());
    }

     void searchPropertyTypeMaster(PropertyTypesResponse createObject) throws IOException {
        new APILogger().log("Search Property type is Started --");
        Response responseForId = new PropertyTypeMasterResource().search(json, IDS + createObject.getPropertyTypes()[0].getId());
        checkAssertsForPropertyTypes(responseForId, createObject);
        new APILogger().log("Search Property type with Id is Success");

        Response responseForName = new PropertyTypeMasterResource().search(json, WITH_NAME + createObject.getPropertyTypes()[0].getName());
        checkAssertsForPropertyTypes(responseForName, createObject);
        new APILogger().log("Search Property type with name is Success");

        Response responseForCode = new PropertyTypeMasterResource().search(json, WITH_CODE + createObject.getPropertyTypes()[0].getCode());
        checkAssertsForPropertyTypes(responseForCode, createObject);
        new APILogger().log("Search Property type with code is Success");

        Response responseForNameLocal = new PropertyTypeMasterResource().search(json, NAMELOCAL + createObject.getPropertyTypes()[0].getNameLocal());
        checkAssertsForPropertyTypes(responseForNameLocal, createObject);
        new APILogger().log("Search Property type with nameLocal is Success");
        new APILogger().log("Search Property type is Completed --");
    }

    private void checkAssertsForPropertyTypes(Response response, PropertyTypesResponse createObject) throws IOException {
        SearchPropertyTypesResponse responseObject = (SearchPropertyTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchPropertyTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getId(), createObject.getPropertyTypes()[0].getId());
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getName(), createObject.getPropertyTypes()[0].getName());
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getCode(), createObject.getPropertyTypes()[0].getCode());
        Assert.assertEquals(responseObject.getPropertyTypes()[0].getNameLocal(), createObject.getPropertyTypes()[0].getNameLocal());
    }

     void searchDepartmentMaster(DepartmentsMasterResponse createObject) throws IOException {
        new APILogger().log("Search Department Master is Started --");
        Response responseForId = new DepartmentsMasterResource().search(json, IDS + createObject.getDepartments()[0].getId());
        checkAssertsForDepartments(responseForId, createObject);
        new APILogger().log("Search Department Master with Id is Success");

        Response responseForName = new DepartmentsMasterResource().search(json, WITH_NAME + createObject.getDepartments()[0].getName());
        checkAssertsForDepartments(responseForName, createObject);
        new APILogger().log("Search Department Master with name is Success");

        Response responseForCode = new DepartmentsMasterResource().search(json, WITH_CODE + createObject.getDepartments()[0].getCode());
        checkAssertsForDepartments(responseForCode, createObject);
        new APILogger().log("Search Department Master with code is Success");

        Response responseForNameLocal = new DepartmentsMasterResource().search(json, NAMELOCAL + createObject.getDepartments()[0].getNameLocal());
        checkAssertsForDepartments(responseForNameLocal, createObject);
        new APILogger().log("Search Department Master with nameLocal is Success");
        new APILogger().log("Search Department Master is Completed --");
    }

    private void checkAssertsForDepartments(Response response, DepartmentsMasterResponse requestObject) throws IOException {
        SearchDepartmentsResponse responseObject = (SearchDepartmentsResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchDepartmentsResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getDepartments()[0].getId(), requestObject.getDepartments()[0].getId());
        Assert.assertEquals(responseObject.getDepartments()[0].getName(), requestObject.getDepartments()[0].getName());
        Assert.assertEquals(responseObject.getDepartments()[0].getCode(), requestObject.getDepartments()[0].getCode());
        Assert.assertEquals(responseObject.getDepartments()[0].getNameLocal(), requestObject.getDepartments()[0].getNameLocal());
    }

     void searchOccupancyMaster(OccupancyMasterResponse createObject) throws IOException {
        new APILogger().log("Search Occupancy Master is Started --");
        Response responseForId = new OccupancyMasterResource().search(json, IDS + createObject.getOccuapancyMasters()[0].getId());
        checkAssertsForOccupancyMaster(responseForId, createObject);
        new APILogger().log("Search Occupancy Master with Id is Success");

        Response responseForName = new OccupancyMasterResource().search(json, WITH_NAME + createObject.getOccuapancyMasters()[0].getName());
        checkAssertsForOccupancyMaster(responseForName, createObject);
        new APILogger().log("Search Occupancy Master with name is Success");

        Response responseForCode = new OccupancyMasterResource().search(json, WITH_CODE + createObject.getOccuapancyMasters()[0].getCode());
        checkAssertsForOccupancyMaster(responseForCode, createObject);
        new APILogger().log("Search Occupancy Master with code is Success");

        Response responseForNameLocal = new OccupancyMasterResource().search(json, NAMELOCAL + createObject.getOccuapancyMasters()[0].getNameLocal());
        checkAssertsForOccupancyMaster(responseForNameLocal, createObject);
        new APILogger().log("Search Occupancy Master with nameLocal is Success");
        new APILogger().log("Search Occupancy Master is Completed --");
    }

    private void checkAssertsForOccupancyMaster(Response response, OccupancyMasterResponse requestObject) throws IOException {
        SearchOccupancyMasterResponse responseObject = (SearchOccupancyMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchOccupancyMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getOccuapancyMasters()[0].getId(), requestObject.getOccuapancyMasters()[0].getId());
        Assert.assertEquals(responseObject.getOccuapancyMasters()[0].getName(), requestObject.getOccuapancyMasters()[0].getName());
        Assert.assertEquals(responseObject.getOccuapancyMasters()[0].getCode(), requestObject.getOccuapancyMasters()[0].getCode());
        Assert.assertEquals(responseObject.getOccuapancyMasters()[0].getNameLocal(), requestObject.getOccuapancyMasters()[0].getNameLocal());
    }

     void searchWallTypeMaster(WallTypesResponse createObject) throws IOException {
        new APILogger().log("Search WallTypes Master is Started --");
        Response responseForId = new WallTypesMasterResource().search(json, IDS + createObject.getWallTypes()[0].getId());
        checkAssertsForWallTypeMaster(responseForId, createObject);
        new APILogger().log("Search wallTypes Master with Id is Success");

        Response responseForName = new WallTypesMasterResource().search(json, WITH_NAME + createObject.getWallTypes()[0].getName());
        checkAssertsForWallTypeMaster(responseForName, createObject);
        new APILogger().log("Search WallTypes Master with name is Success");

        Response responseForCode = new WallTypesMasterResource().search(json, WITH_CODE + createObject.getWallTypes()[0].getCode());
        checkAssertsForWallTypeMaster(responseForCode, createObject);
        new APILogger().log("Search WallTypes Master with code is Success");

        Response responseForNameLocal = new WallTypesMasterResource().search(json, NAMELOCAL + createObject.getWallTypes()[0].getNameLocal());
        checkAssertsForWallTypeMaster(responseForNameLocal, createObject);
        new APILogger().log("Search WallTypes Master with nameLocal is Success");
        new APILogger().log("Search WallTypes Master is Completed --");
    }

    private void checkAssertsForWallTypeMaster(Response response, WallTypesResponse requestObject) throws IOException {
        SearchWallTypesMasterResponse responseObject = (SearchWallTypesMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchWallTypesMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getWallTypes()[0].getId(), requestObject.getWallTypes()[0].getId());
        Assert.assertEquals(responseObject.getWallTypes()[0].getName(), requestObject.getWallTypes()[0].getName());
        Assert.assertEquals(responseObject.getWallTypes()[0].getCode(), requestObject.getWallTypes()[0].getCode());
        Assert.assertEquals(responseObject.getWallTypes()[0].getNameLocal(),requestObject.getWallTypes()[0].getNameLocal());
    }

    void searchRoofTypeMaster(RoofTypeMasterResponse createObject) throws IOException {
        new APILogger().log("Search RoofType Master is Started --");
        Response responseForId = new RoofTypesMasterResource().search(json, IDS + createObject.getRoofTypes()[0].getId());
        checkAssertsForRoofTypeMaster(responseForId, createObject);
        new APILogger().log("Search RoofType Master with Id is Success");

        Response responseForName = new RoofTypesMasterResource().search(json, WITH_NAME + createObject.getRoofTypes()[0].getName());
        checkAssertsForRoofTypeMaster(responseForName, createObject);
        new APILogger().log("Search RoofType Master with name is Success");

        Response responseForCode = new RoofTypesMasterResource().search(json, WITH_CODE + createObject.getRoofTypes()[0].getCode());
        checkAssertsForRoofTypeMaster(responseForCode, createObject);
        new APILogger().log("Search RoofType Master with code is Success");

        Response responseForNameLocal = new RoofTypesMasterResource().search(json, NAMELOCAL + createObject.getRoofTypes()[0].getNameLocal());
        checkAssertsForRoofTypeMaster(responseForNameLocal, createObject);
        new APILogger().log("Search RoofType Master with nameLocal is Success");
        new APILogger().log("Search RoofType Master is Completed --");
    }

    private void checkAssertsForRoofTypeMaster(Response response, RoofTypeMasterResponse requestObject) throws IOException {
        SearchRoofTypeMasterResponse responseObject = (SearchRoofTypeMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchRoofTypeMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getRoofTypes()[0].getId(), requestObject.getRoofTypes()[0].getId());
        Assert.assertEquals(responseObject.getRoofTypes()[0].getName(), requestObject.getRoofTypes()[0].getName());
        Assert.assertEquals(responseObject.getRoofTypes()[0].getCode(), requestObject.getRoofTypes()[0].getCode());
        Assert.assertEquals(responseObject.getRoofTypes()[0].getNameLocal(),requestObject.getRoofTypes()[0].getNameLocal());
    }
}
