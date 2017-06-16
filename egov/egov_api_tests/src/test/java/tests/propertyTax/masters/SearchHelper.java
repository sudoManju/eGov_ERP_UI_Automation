package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.usage.SearchUsageMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.SearchMasterRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.floorTypes.create.FloorTypesResponse;
import entities.responses.propertyTax.masters.floorTypes.search.SearchFloorTypesResponse;
import entities.responses.propertyTax.masters.structureClass.create.StructureClassResponse;
import entities.responses.propertyTax.masters.structureClass.search.SearchStructureClassResponse;
import entities.responses.propertyTax.masters.usage.search.SearchUsageMasterResponse;
import entities.responses.propertyTax.masters.usage.create.UsageMasterResponse;
import org.testng.Assert;
import resources.propertyTax.masters.FloorTypesResource;
import resources.propertyTax.masters.StructureClassResource;
import resources.propertyTax.masters.UsageMasterResource;
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
}
