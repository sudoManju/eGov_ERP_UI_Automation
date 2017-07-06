package tests.propertyTax.masters;

import builders.propertyTax.masters.usage.UsageMasterRequestBuilder;
import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.usage.UsageMastersBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.usage.UsageMasterRequest;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.UsageMasters;
import entities.responses.propertyTax.masters.usage.create.UsageMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.UsageMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class  UsageMasterVerificationTest extends BaseAPITest {

    UsageMasters[] usageMasters;
    RequestInfo requestInfo;
    PTISMasterSearchHelper helper;

    public UsageMasterVerificationTest(){usageMasters = new UsageMasters[1];}

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void usageMasterTest() throws IOException {

        LoginAndLogoutHelper.login(NARASAPPA);     //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new PTISMasterSearchHelper();
        UsageMasterResponse create = createUsageMaster();   //Create
        helper.searchForUsageMaster(create); //Search

        UsageMasterResponse update = updateusageMaster(create.getUsageMasters()[0].getId());  //Update
        helper.searchForUsageMaster(update);              //Search
        LoginAndLogoutHelper.logout();          //Logout
    }

    private UsageMasterResponse createUsageMaster() throws IOException {
        new APILogger().log("Create Usage Master Test Started");
        usageMasters[0] = new UsageMastersBuilder().withName("Test"+ get5DigitRandomInt()).withCode(get5DigitRandomInt())
                .withNameLocal("Local"+ get5DigitRandomInt()).withOrderNumber(Integer.parseInt(get5DigitRandomInt())).build();
        UsageMasterRequest request = new UsageMasterRequestBuilder().withRequestInfo(requestInfo)
                .withUsageMasters(usageMasters).build();

        Response response = new UsageMasterResource().create(RequestHelper.getJsonString(request));
        UsageMasterResponse responseObject = checkAsserts(request,response);
        new APILogger().log("Create Usage Master Test Completed");
        return responseObject;
    }

    private UsageMasterResponse checkAsserts(UsageMasterRequest request, Response response) throws IOException {
        UsageMasterResponse responseObject = (UsageMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),UsageMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"SUCCESSFUL");
        Assert.assertEquals(responseObject.getUsageMasters()[0].getName(),request.getUsageMasters()[0].getName());
        Assert.assertEquals(responseObject.getUsageMasters()[0].getCode(),request.getUsageMasters()[0].getCode());
        Assert.assertEquals(responseObject.getUsageMasters()[0].getNameLocal(),request.getUsageMasters()[0].getNameLocal());
        Assert.assertEquals(responseObject.getUsageMasters()[0].getDescription(),request.getUsageMasters()[0].getDescription());

        return responseObject;
    }

    private UsageMasterResponse updateusageMaster(int id) throws IOException {
        new APILogger().log("Update Usage Master Test Started");
        usageMasters[0] = new UsageMastersBuilder().withId(id)
                 .withName("Test_"+ get5DigitRandomInt()).withCode(get5DigitRandomInt())
                .withNameLocal("Test_"+ get5DigitRandomInt()).withOrderNumber(Integer.parseInt(get5DigitRandomInt())).build();
        UsageMasterRequest request = new UsageMasterRequestBuilder().withRequestInfo(requestInfo)
                .withUsageMasters(usageMasters).build();

        Response response = new UsageMasterResource().update(RequestHelper.getJsonString(request));
        UsageMasterResponse responseObject = checkAsserts(request,response);
        Assert.assertEquals(responseObject.getUsageMasters()[0].getId(),id);
        new APILogger().log("Update Usage Master Test Completed");
        return responseObject;
    }
}
