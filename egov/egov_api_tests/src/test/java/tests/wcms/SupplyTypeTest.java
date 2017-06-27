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

import static data.UserData.MANAS;

public class SupplyTypeTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void supplyTypeTest() throws IOException {
        LoginAndLogoutHelper.login1(MANAS); // Login
        String supplyTypeName = createSupplyType(); // Create SupplyType
        CreateSupplyTypeResponse searchSupplyTypeResponse = searchSupplyType(supplyTypeName); // Search SupplyType
        updateSupplyType(searchSupplyTypeResponse); // Update SupplyType
        LoginAndLogoutHelper.logout1(); // Logout
    }

    private String createSupplyType() throws IOException {
        new APILogger().log("Create SupplyType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SupplyType supplyType = new SupplyTypeBuilder().build();
        CreateSupplyTypeRequest createSupplyTypeRequest = new CreateSupplyTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSupplyType(supplyType).build();

        Response response = new WCMSResource().createSupplyTypeResource(RequestHelper.getJsonString(createSupplyTypeRequest));
        CreateSupplyTypeResponse createSupplyTypeResponse = (CreateSupplyTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSupplyTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(supplyType.getName(), createSupplyTypeResponse.getSupplytypes()[0].getName());
        new APILogger().log("Create SupplyType Test is Completed ---");
        return createSupplyTypeResponse.getSupplytypes()[0].getName();
    }

    private CreateSupplyTypeResponse searchSupplyType(String supplyTypeName) throws IOException {
        new APILogger().log("Search SupplyType Test With Name is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchSupplyTypeRequest searchSupplyTypeRequest = new SearchSupplyTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchSupplyTypeResource(RequestHelper.getJsonString(searchSupplyTypeRequest), supplyTypeName);
        CreateSupplyTypeResponse searchSupplyTypeResponse = (CreateSupplyTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSupplyTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(supplyTypeName, searchSupplyTypeResponse.getSupplytypes()[0].getName());
        new APILogger().log("Search SupplyType Test With Name is Completed ---");
        return searchSupplyTypeResponse;
    }

    private void updateSupplyType(CreateSupplyTypeResponse searchSupplyTypeResponse) throws IOException {
        new APILogger().log("Update SupplyType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
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
    }
}
