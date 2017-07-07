package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.roofTypes.RoofTypeMasterRequestBuilder;
import builders.propertyTax.masters.roofTypes.RoofTypesBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.roofType.RoofTypeMasterRequest;
import entities.requests.propertyTax.masters.roofType.RoofTypes;
import entities.responses.propertyTax.masters.roofTypes.create.RoofTypeMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.RoofTypesMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class RoofTypeMasterVerificationTest extends BaseAPITest {

    RoofTypes[] roofTypes;
    RequestInfo requestInfo;
    PTISMasterSearchHelper helper;

    public RoofTypeMasterVerificationTest() {
        roofTypes = new RoofTypes[1];
    }

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void roofTypesTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);                       //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new PTISMasterSearchHelper();
        RoofTypeMasterResponse create = createRoofTypesMaster();     //Create
        helper.searchRoofTypeMaster(create);                         //Search

        RoofTypeMasterResponse update = updateRoofTypesMaster(create.getRoofTypes()[0].getId());    //Update
        helper.searchRoofTypeMaster(update);                        //Search
        LoginAndLogoutHelper.logout();                              //Logout
    }

    private RoofTypeMasterResponse updateRoofTypesMaster(int id) throws IOException {
        new APILogger().log("Update RoofType Master has started --");
        roofTypes[0] = new RoofTypesBuilder().withName("Test_" + get5DigitRandomInt()).withCode(get5DigitRandomInt())
                .withNameLocal("Test_" + get5DigitRandomInt()).withId(id).build();
        RoofTypeMasterRequest request = new RoofTypeMasterRequestBuilder().withRequestInfo(requestInfo)
                .withRoofTypes(roofTypes).build();

        Response response = new RoofTypesMasterResource().update(RequestHelper.getJsonString(request));
        RoofTypeMasterResponse responseObject = checkAsserts(request, response);
        new APILogger().log("Update RoofType Master has completed --");

        return responseObject;
    }

    private RoofTypeMasterResponse createRoofTypesMaster() throws IOException {
        new APILogger().log("Create RoofType Master has started --");
        roofTypes[0] = new RoofTypesBuilder().withName("Test_" + get5DigitRandomInt()).withCode(get5DigitRandomInt())
                .withNameLocal("Test_" + get5DigitRandomInt()).build();
        RoofTypeMasterRequest request = new RoofTypeMasterRequestBuilder().withRequestInfo(requestInfo)
                .withRoofTypes(roofTypes).build();

        Response response = new RoofTypesMasterResource().create(RequestHelper.getJsonString(request));
        RoofTypeMasterResponse responseObject = checkAsserts(request, response);
        new APILogger().log("Create RoofType Master has completed --");

        return responseObject;
    }

    private RoofTypeMasterResponse checkAsserts(RoofTypeMasterRequest request, Response response) throws IOException {
        RoofTypeMasterResponse responseObject = (RoofTypeMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(), RoofTypeMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseObject.getRoofTypes()[0].getName(), request.getRoofTypes()[0].getName());
        Assert.assertEquals(responseObject.getRoofTypes()[0].getCode(), request.getRoofTypes()[0].getCode());
        Assert.assertEquals(responseObject.getRoofTypes()[0].getNameLocal(), request.getRoofTypes()[0].getNameLocal());
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(), "SUCCESSFUL");

        return responseObject;
    }
}
