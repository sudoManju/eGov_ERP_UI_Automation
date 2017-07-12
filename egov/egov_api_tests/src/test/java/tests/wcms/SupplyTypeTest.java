package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.supplyType.create.CreateSupplyTypeRequestBuilder;
import builders.wcms.supplyType.create.SupplyTypeBuilder;
import builders.wcms.supplyType.search.SearchSupplyTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.supplyType.create.CreateSupplyTypeRequest;
import entities.requests.wcms.supplyType.create.SupplyType;
import entities.requests.wcms.supplyType.search.SearchSupplyTypeRequest;
import entities.responses.wcms.supplytType.CreateSupplyTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_CODE;
import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class SupplyTypeTest extends BaseAPITest {

    private RequestInfo requestInfo;

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateSupplyTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateSupplyTypeResponse createSupplyTypeResponse = createSupplyType(); // Create SupplyType
        CreateSupplyTypeResponse searchSupplyTypeResponse = searchSupplyType(createSupplyTypeResponse, WITH_NAME); // Search SupplyType
        CreateSupplyTypeResponse updateSupplyTypeResponse = updateSupplyType(searchSupplyTypeResponse); // Update SupplyType
        searchSupplyType(updateSupplyTypeResponse, WITH_CODE); // Search SupplyType After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void searchSupplyTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        getAllSupplyTypes(); // Get All SupplyTypes
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreateSupplyTypeResponse createSupplyType() throws IOException {
        new APILogger().log("Create SupplyType Test is Started ---");
        SupplyType supplyType = new SupplyTypeBuilder().build();
        CreateSupplyTypeRequest createSupplyTypeRequest = new CreateSupplyTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSupplyType(supplyType).build();

        Response response = new WCMSResource().createSupplyTypeResource(RequestHelper.getJsonString(createSupplyTypeRequest));
        CreateSupplyTypeResponse createSupplyTypeResponse = (CreateSupplyTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSupplyTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(supplyType.getName(), createSupplyTypeResponse.getSupplytypes()[0].getName());
        new APILogger().log("Create SupplyType Test is Completed ---");
        return createSupplyTypeResponse;
    }

    private CreateSupplyTypeResponse searchSupplyType(CreateSupplyTypeResponse createSupplyTypeResponse, String parameter) throws IOException {
        new APILogger().log("Search SupplyType Test With " + parameter + " is Started ---");
        SearchSupplyTypeRequest searchSupplyTypeRequest = new SearchSupplyTypeRequestBuilder().withRequestInfo(requestInfo).build();

        String path;
        if (parameter.contains("&name="))
            path = pathBuilder(parameter, createSupplyTypeResponse.getSupplytypes()[0].getName());
        else
            path = pathBuilder(parameter, createSupplyTypeResponse.getSupplytypes()[0].getCode());

        Response response = new WCMSResource().searchSupplyTypeResource(RequestHelper.getJsonString(searchSupplyTypeRequest), path);
        CreateSupplyTypeResponse searchSupplyTypeResponse = (CreateSupplyTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSupplyTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchSupplyTypeResponse.getSupplytypes().length == 1);
        Assert.assertEquals(createSupplyTypeResponse.getSupplytypes()[0].getName(), searchSupplyTypeResponse.getSupplytypes()[0].getName());
        new APILogger().log("Search SupplyType Test With " + parameter + " is Completed ---");
        return searchSupplyTypeResponse;
    }

    private CreateSupplyTypeResponse updateSupplyType(CreateSupplyTypeResponse searchSupplyTypeResponse) throws IOException {
        new APILogger().log("Update SupplyType Test is Started ---");
        SupplyType supplyType = new SupplyTypeBuilder()
                .withName(searchSupplyTypeResponse.getSupplytypes()[0].getName() + "-Updated").build();
        CreateSupplyTypeRequest updateSupplyTypeRequest = new CreateSupplyTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSupplyType(supplyType).build();

        Response response = new WCMSResource().updateSupplyTypeResource(RequestHelper.getJsonString(updateSupplyTypeRequest), searchSupplyTypeResponse.getSupplytypes()[0].getCode());
        CreateSupplyTypeResponse updateSupplyTypeResponse = (CreateSupplyTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSupplyTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals("Updated", updateSupplyTypeResponse.getSupplytypes()[0].getName().split("-")[1]);
        new APILogger().log("Update SupplyType Test is Completed ---");
        return updateSupplyTypeResponse;
    }

    private void getAllSupplyTypes() throws IOException {
        new APILogger().log("Search SupplyType Test Request is Started ---");
        SearchSupplyTypeRequest searchSupplyTypeRequest = new SearchSupplyTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchSupplyTypeResource(RequestHelper.getJsonString(searchSupplyTypeRequest), "");
        CreateSupplyTypeResponse searchSupplyTypeResponse = (CreateSupplyTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSupplyTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchSupplyTypeResponse.getSupplytypes().length > 0);
        new APILogger().log("Search SupplyType Test Request is Started ---");
    }
}
