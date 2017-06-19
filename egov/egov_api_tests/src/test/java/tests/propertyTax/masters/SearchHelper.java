package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.usage.SearchUsageMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.SearchMasterRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.department.create.DepartmentsMasterResponse;
import entities.responses.propertyTax.masters.department.search.SearchDepartmentsResponse;
import entities.responses.propertyTax.masters.floorTypes.create.FloorTypesResponse;
import entities.responses.propertyTax.masters.floorTypes.search.SearchFloorTypesResponse;
import entities.responses.propertyTax.masters.occupancy.create.CreateOccupancyMasterResponse;
import entities.responses.propertyTax.masters.occupancy.search.SearchOccupancyMasterResponse;
import entities.responses.propertyTax.masters.propertyTypes.create.PropertyTypesResponse;
import entities.responses.propertyTax.masters.propertyTypes.search.SearchPropertyTypesResponse;
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

public class SearchHelper extends BaseAPITest {


    private String json;

    public SearchHelper(LoginResponse loginResponse) {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        SearchMasterRequest request = new SearchUsageMasterRequestBuilder().withRequestInfo(requestInfo).build();

        json = RequestHelper.getJsonString(request);
    }

    public void searchForUsageMaster(UsageMasterResponse responseObjectOfUsage) throws IOException {

        new APILogger().log("Search Usage Master is started --");

        Response responseForId = new UsageMasterResource().searchUsageMaster(json, "&ids=" + responseObjectOfUsage.getUsageMasters()[0].getId());

        checkResponseForUsage(responseForId,responseObjectOfUsage);

        new APILogger().log("Search Usage Master with Id is success");

        Response responseForName = new UsageMasterResource().searchUsageMaster(json, "&name=" + responseObjectOfUsage.getUsageMasters()[0].getName());

        checkResponseForUsage(responseForName,responseObjectOfUsage);

        new APILogger().log("Search Usage Master with Name is success");

        Response responseForCode = new UsageMasterResource().searchUsageMaster(json, "&code=" + responseObjectOfUsage.getUsageMasters()[0].getCode());

        checkResponseForUsage(responseForCode,responseObjectOfUsage);

        new APILogger().log("Search Usage Master with code is success");

        Response responseForNameLocal = new UsageMasterResource().searchUsageMaster(json,"&nameLocal="+responseObjectOfUsage.getUsageMasters()[0].getNameLocal());

        checkResponseForUsage(responseForNameLocal,responseObjectOfUsage);

        new APILogger().log("Search Usage Master with Name Local is success");

        new APILogger().log("Search Usage Master is Completed --");
    }

    private void checkResponseForUsage(Response response, UsageMasterResponse create) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchUsageMasterResponse response1 = (SearchUsageMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchUsageMasterResponse.class);

        Assert.assertEquals(response1.getUsageMasters()[0].getId(),create.getUsageMasters()[0].getId());
        Assert.assertEquals(response1.getUsageMasters()[0].getName(),create.getUsageMasters()[0].getName());
        Assert.assertEquals(response1.getUsageMasters()[0].getCode(),create.getUsageMasters()[0].getCode());
        Assert.assertEquals(response1.getUsageMasters()[0].getNameLocal(),create.getUsageMasters()[0].getNameLocal());
    }

    public void searchStructureClassMaster(StructureClassResponse responseObjectOfCreate)throws IOException{

        new APILogger().log("Search Structure Classes master is started --");

        Response responseForId = new StructureClassResource().searchStructureClass(json,"&ids="+responseObjectOfCreate.getStructureClasses()[0].getId());

        checkResponseForStructure(responseForId,responseObjectOfCreate);

        new APILogger().log("Search Structure Classes master with Id is success");

        Response responseForName = new StructureClassResource().searchStructureClass(json, "&name=" + responseObjectOfCreate.getStructureClasses()[0].getName());

        checkResponseForStructure(responseForName,responseObjectOfCreate);

        new APILogger().log("Search Structure Classes Master with Name is success");

        Response responseForCode = new StructureClassResource().searchStructureClass(json, "&code=" + responseObjectOfCreate.getStructureClasses()[0].getCode());

        checkResponseForStructure(responseForCode,responseObjectOfCreate);

        new APILogger().log("Search Structure Classes Master with code is success");

        Response responseForNameLocal = new StructureClassResource().searchStructureClass(json,"&nameLocal="+responseObjectOfCreate.getStructureClasses()[0].getNameLocal());

        checkResponseForStructure(responseForNameLocal,responseObjectOfCreate);

        new APILogger().log("Search Structure Classes Master with Name Local is success");

        new APILogger().log("Search Structure Classes Master is Completed --");
    }

    private void checkResponseForStructure(Response response, StructureClassResponse responseObjectOfCreate) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchStructureClassResponse response1 = (SearchStructureClassResponse) ResponseHelper.getResponseAsObject(response.asString(),SearchStructureClassResponse.class);

        Assert.assertEquals(response1.getStructureClasses()[0].getId(),responseObjectOfCreate.getStructureClasses()[0].getId());
        Assert.assertEquals(response1.getStructureClasses()[0].getName(),responseObjectOfCreate.getStructureClasses()[0].getName());
        Assert.assertEquals(response1.getStructureClasses()[0].getCode(),responseObjectOfCreate.getStructureClasses()[0].getCode());
        Assert.assertEquals(response1.getStructureClasses()[0].getNameLocal(),responseObjectOfCreate.getStructureClasses()[0].getNameLocal());
    }

    public void searchFloorTypesMaster(FloorTypesResponse responseObjectOfCreate) throws IOException {

        new APILogger().log("Search FloorTypes master is started --");

        Response responseForId = new FloorTypesResource().searchFloorTypeMaster(json,"&ids="+responseObjectOfCreate.getFloorTypes()[0].getId());

        checkAssertsForFloorTypes(responseForId,responseObjectOfCreate);

        new APILogger().log("Search FloorTypes master with Id is success");

        Response responseForName = new FloorTypesResource().searchFloorTypeMaster(json, "&name=" + responseObjectOfCreate.getFloorTypes()[0].getName());

        checkAssertsForFloorTypes(responseForName,responseObjectOfCreate);

        new APILogger().log("Search FloorTypes Master with Name is success");

        Response responseForCode = new FloorTypesResource().searchFloorTypeMaster(json, "&code=" + responseObjectOfCreate.getFloorTypes()[0].getCode());

        checkAssertsForFloorTypes(responseForCode,responseObjectOfCreate);

        new APILogger().log("Search FloorTypes Master with code is success");

        Response responseForNameLocal = new FloorTypesResource().searchFloorTypeMaster(json,"&nameLocal="+responseObjectOfCreate.getFloorTypes()[0].getNameLocal());

        checkAssertsForFloorTypes(responseForNameLocal,responseObjectOfCreate);

        new APILogger().log("Search FloorTypes Master with Name Local is success");

        new APILogger().log("Search FloorTypes Master is Completed --");

    }

    private void checkAssertsForFloorTypes(Response response,FloorTypesResponse requestObject) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchFloorTypesResponse response1 = (SearchFloorTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchFloorTypesResponse.class);

        Assert.assertEquals(response1.getFloorTypes()[0].getId(),requestObject.getFloorTypes()[0].getId());
        Assert.assertEquals(response1.getFloorTypes()[0].getName(),requestObject.getFloorTypes()[0].getName());
        Assert.assertEquals(response1.getFloorTypes()[0].getCode(),requestObject.getFloorTypes()[0].getCode());
        Assert.assertEquals(response1.getFloorTypes()[0].getNameLocal(),requestObject.getFloorTypes()[0].getNameLocal());
    }

    public void searchWoodTypesMaster(WoodTypesResponse create) throws IOException {

        new APILogger().log("Search WoodTypes is started --");

        Response responseForId = new WoodTypesResource().searchWoodTypes(json,"&ids="+create.getWoodTypes()[0].getId());

        checkAssertsForWoodTypes(responseForId,create);

        new APILogger().log("Search WoodTypes Master with Id is success");

        Response responseForName = new WoodTypesResource().searchWoodTypes(json,"&name="+create.getWoodTypes()[0].getName());

        checkAssertsForWoodTypes(responseForName,create);

        new APILogger().log("Search WoodTypes Master with name is success");

        Response responseForCode = new WoodTypesResource().searchWoodTypes(json,"&code="+create.getWoodTypes()[0].getCode());

        checkAssertsForWoodTypes(responseForCode,create);

        new APILogger().log("Search WoodTypes Master with code is success");

        Response responseForNameLocal = new WoodTypesResource().searchWoodTypes(json,"&nameLocal="+create.getWoodTypes()[0].getNameLocal());

        checkAssertsForWoodTypes(responseForNameLocal,create);

        new APILogger().log("Search WoodTypes Master with nameLocal is success");

        new APILogger().log("earch WoodTypes is completed --");
    }

    private void checkAssertsForWoodTypes(Response response,WoodTypesResponse requestObject) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchWoodTypesResponse response1 = (SearchWoodTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchWoodTypesResponse.class);

        Assert.assertEquals(response1.getWoodTypes()[0].getId(),requestObject.getWoodTypes()[0].getId());
        Assert.assertEquals(response1.getWoodTypes()[0].getName(),requestObject.getWoodTypes()[0].getName());
        Assert.assertEquals(response1.getWoodTypes()[0].getCode(),requestObject.getWoodTypes()[0].getCode());
//        Assert.assertEquals(response1.getWoodTypes()[0].getNameLocal(),requestObject.getWoodTypes()[0].getNameLocal());
    }

    public void searchPropertyTypeMaster(PropertyTypesResponse createObject)throws IOException{

        new APILogger().log("Search Property type is started --");

        Response responseForId = new PropertyTypeMasterResource().searchPropertyType(json,"&ids="+createObject.getPropertyTypes()[0].getId());

        checkAssertsForPropertyTypes(responseForId,createObject);

        new APILogger().log("Search Property type with Id is success");

        Response responseForName = new PropertyTypeMasterResource().searchPropertyType(json,"&name="+createObject.getPropertyTypes()[0].getName());

        checkAssertsForPropertyTypes(responseForName,createObject);

        new APILogger().log("Search Property type with name is success");

        Response responseForCode = new PropertyTypeMasterResource().searchPropertyType(json,"&code="+createObject.getPropertyTypes()[0].getCode());

        checkAssertsForPropertyTypes(responseForCode,createObject);

        new APILogger().log("Search Property type with code is success");

        Response responseForNameLocal = new PropertyTypeMasterResource().searchPropertyType(json,"&nameLocal="+createObject.getPropertyTypes()[0].getNameLocal());

        checkAssertsForPropertyTypes(responseForNameLocal,createObject);

        new APILogger().log("Search Property type with nameLocal is success");

        new APILogger().log("Search Property type is completed --");
    }

    private void checkAssertsForPropertyTypes(Response response, PropertyTypesResponse createObject) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchPropertyTypesResponse response1 = (SearchPropertyTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchPropertyTypesResponse.class);

        Assert.assertEquals(response1.getPropertyTypes()[0].getId(),createObject.getPropertyTypes()[0].getId());
        Assert.assertEquals(response1.getPropertyTypes()[0].getName(),createObject.getPropertyTypes()[0].getName());
        Assert.assertEquals(response1.getPropertyTypes()[0].getCode(),createObject.getPropertyTypes()[0].getCode());
        Assert.assertEquals(response1.getPropertyTypes()[0].getNameLocal(),createObject.getPropertyTypes()[0].getNameLocal());
    }

    public void searchDepartmentMaster(DepartmentsMasterResponse createObject) throws IOException {

        new APILogger().log("Search Department Master is started --");

        Response responseForId = new DepartmentsMasterResource().searchDepartment(json,"&ids="+createObject.getDepartments()[0].getId());

        checkAssertsForDepartments(responseForId,createObject);

        new APILogger().log("Search Department Master with Id is success");

        Response responseForName = new DepartmentsMasterResource().searchDepartment(json,"&name="+createObject.getDepartments()[0].getName());

        checkAssertsForDepartments(responseForName,createObject);

        new APILogger().log("Search Department Master with name is success");

        Response responseForCode = new DepartmentsMasterResource().searchDepartment(json,"&code="+createObject.getDepartments()[0].getCode());

        checkAssertsForDepartments(responseForCode,createObject);

        new APILogger().log("Search Department Master with code is success");

        Response responseForNameLocal = new DepartmentsMasterResource().searchDepartment(json,"&nameLocal="+createObject.getDepartments()[0].getNameLocal());

        checkAssertsForDepartments(responseForNameLocal,createObject);

        new APILogger().log("Search Department Master with nameLocal is success");

        new APILogger().log("Search Department Master is completed --");

    }

   private void checkAssertsForDepartments(Response response,DepartmentsMasterResponse requestObject) throws IOException {

       Assert.assertEquals(response.getStatusCode(),200);

       SearchDepartmentsResponse response1 = (SearchDepartmentsResponse)
               ResponseHelper.getResponseAsObject(response.asString(),SearchDepartmentsResponse.class);

       Assert.assertEquals(response1.getDepartments()[0].getId(),requestObject.getDepartments()[0].getId());
       Assert.assertEquals(response1.getDepartments()[0].getName(),requestObject.getDepartments()[0].getName());
       Assert.assertEquals(response1.getDepartments()[0].getCode(),requestObject.getDepartments()[0].getCode());
       Assert.assertEquals(response1.getDepartments()[0].getNameLocal(),requestObject.getDepartments()[0].getNameLocal());
   }

    public void searchOccupancyMaster(CreateOccupancyMasterResponse createObject)throws IOException {

        new APILogger().log("Search Occupancy Master is started --");

        Response responseForId = new OccupancyMasterResource().searchOccupancy(json,"&ids="+createObject.getOccuapancyMasters()[0].getId());

        checkAssertsForOccupancyMaster(responseForId,createObject);

        new APILogger().log("Search Occupancy Master with Id is success");

        Response responseForName = new OccupancyMasterResource().searchOccupancy(json,"&name="+createObject.getOccuapancyMasters()[0].getName());

        checkAssertsForOccupancyMaster(responseForName,createObject);

        new APILogger().log("Search Occupancy Master with name is success");

        Response responseForCode = new OccupancyMasterResource().searchOccupancy(json,"&code="+createObject.getOccuapancyMasters()[0].getCode());

        checkAssertsForOccupancyMaster(responseForCode,createObject);

        new APILogger().log("Search Occupancy Master with code is success");

        Response responseForNameLocal = new OccupancyMasterResource().searchOccupancy(json,"&nameLocal="+createObject.getOccuapancyMasters()[0].getNameLocal());

        checkAssertsForOccupancyMaster(responseForNameLocal,createObject);

        new APILogger().log("Search Occupancy Master with nameLocal is success");

        new APILogger().log("Search Occupancy Master is completed --");

    }

    private void checkAssertsForOccupancyMaster(Response response,CreateOccupancyMasterResponse requestObject) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchOccupancyMasterResponse response1 = (SearchOccupancyMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchOccupancyMasterResponse.class);

        Assert.assertEquals(response1.getOccuapancyMasters()[0].getId(),requestObject.getOccuapancyMasters()[0].getId());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getName(),requestObject.getOccuapancyMasters()[0].getName());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getCode(),requestObject.getOccuapancyMasters()[0].getCode());
        Assert.assertEquals(response1.getOccuapancyMasters()[0].getNameLocal(),requestObject.getOccuapancyMasters()[0].getNameLocal());
    }

    public void searchWallTypeMaster(WallTypesResponse createObject) throws IOException {

        new APILogger().log("Search WallTypes Master is started --");

        Response responseForId = new WallTypeResource().searchWallTypeMaster(json,"&ids="+createObject.getWallTypes()[0].getId());

        checkAssertsForWallTypeMaster(responseForId,createObject);

        new APILogger().log("Search wallTypes Master with Id is success");

        Response responseForName = new WallTypeResource().searchWallTypeMaster(json,"&name="+createObject.getWallTypes()[0].getName());

        checkAssertsForWallTypeMaster(responseForName,createObject);

        new APILogger().log("Search WallTypes Master with name is success");

        Response responseForCode = new WallTypeResource().searchWallTypeMaster(json,"&code="+createObject.getWallTypes()[0].getCode());

        checkAssertsForWallTypeMaster(responseForCode,createObject);

        new APILogger().log("Search WallTypes Master with code is success");

        Response responseForNameLocal = new WallTypeResource().searchWallTypeMaster(json,"&nameLocal="+createObject.getWallTypes()[0].getNameLocal());

        checkAssertsForWallTypeMaster(responseForNameLocal,createObject);

        new APILogger().log("Search WallTypes Master with nameLocal is success");

        new APILogger().log("Search WallTypes Master is completed --");
    }

    private void checkAssertsForWallTypeMaster(Response response,WallTypesResponse requestObject) throws IOException {

        Assert.assertEquals(response.getStatusCode(),200);

        SearchWallTypesMasterResponse response1 = (SearchWallTypesMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchWallTypesMasterResponse.class);

        Assert.assertEquals(response1.getWallTypes()[0].getId(),requestObject.getWallTypes()[0].getId());
        Assert.assertEquals(response1.getWallTypes()[0].getName(),requestObject.getWallTypes()[0].getName());
        Assert.assertEquals(response1.getWallTypes()[0].getCode(),requestObject.getWallTypes()[0].getCode());
//        Assert.assertEquals(response1.getWallTypes()[0].getNameLocal(),requestObject.getWallTypes()[0].getNameLocal());
    }
}
