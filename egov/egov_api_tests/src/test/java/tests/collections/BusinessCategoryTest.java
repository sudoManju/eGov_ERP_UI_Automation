package tests.collections;

import builders.collections.RequestInfoBuilder;
import builders.collections.businessCategory.create.BusinessCategoryInfoBuilder;
import builders.collections.businessCategory.create.CreateBusinessCategoryRequestBuilder;
import builders.collections.businessCategory.search.SearchBusinessCategoryRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.collections.RequestInfo;
import entities.requests.collections.businessCategory.create.BusinessCategoryInfo;
import entities.requests.collections.businessCategory.create.CreateBusinessCategoryRequest;
import entities.requests.collections.businessCategory.search.SearchBusinessCategoryRequest;
import entities.responses.collections.businessCategory.CreateBusinessCategoryResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.collections.CollectionsResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_NAME;
import static data.UserData.NARASAPPA;

public class BusinessCategoryTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.COLLECTIONS})
    public void createSearchUpdateBusinessCategoryTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA); // Login
        CreateBusinessCategoryResponse createBusinessCategoryResponse = createBusinessCategory(); // Create BusinessCategory
        searchBusinessCategory(createBusinessCategoryResponse, WITH_NAME); // Search BusinessCategory
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreateBusinessCategoryResponse createBusinessCategory() throws IOException {
        new APILogger().log("Create Business Category Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        BusinessCategoryInfo businessCategoryInfo = new BusinessCategoryInfoBuilder().build();
        CreateBusinessCategoryRequest createBusinessCategoryRequest = new CreateBusinessCategoryRequestBuilder()
                .withRequestInfo(requestInfo)
                .withBusinessCategoryInfo(businessCategoryInfo)
                .build();

        Response response = new CollectionsResource()
                .createBusinessCategoryResource(RequestHelper.getJsonString(createBusinessCategoryRequest));
        CreateBusinessCategoryResponse createBusinessCategoryResponse = (CreateBusinessCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateBusinessCategoryResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createBusinessCategoryResponse.getBusinessCategoryInfo()[0].getName(), businessCategoryInfo.getName());
        Assert.assertEquals(createBusinessCategoryResponse.getBusinessCategoryInfo()[0].getCode(), businessCategoryInfo.getCode());
        new APILogger().log("Create Business Category Test Request is Completed ---");
        return createBusinessCategoryResponse;
    }

    private void searchBusinessCategory(CreateBusinessCategoryResponse createBusinessCategoryResponse, String parameter) throws IOException {
        new APILogger().log("Search Business Category Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchBusinessCategoryRequest searchBusinessCategoryRequest = new SearchBusinessCategoryRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path = pathBuilder("&code=", createBusinessCategoryResponse.getBusinessCategoryInfo()[0].getCode());

        Response response = new CollectionsResource()
                .searchBusinessCategoryResource(RequestHelper.getJsonString(searchBusinessCategoryRequest), path);
        CreateBusinessCategoryResponse searchBusinessCategoryResponse = (CreateBusinessCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateBusinessCategoryResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createBusinessCategoryResponse.getBusinessCategoryInfo()[0].getName(), searchBusinessCategoryResponse.getBusinessCategoryInfo()[0].getName());
        Assert.assertEquals(createBusinessCategoryResponse.getBusinessCategoryInfo()[0].getCode(), searchBusinessCategoryResponse.getBusinessCategoryInfo()[0].getCode());
        new APILogger().log("Search Business Category Test Request is Completed ---");
    }
}
