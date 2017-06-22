package tests.wcms;

import builders.wcms.categoryType.create.CategoryBuilder;
import builders.wcms.categoryType.create.CreateCategoryTypeRequestBuilder;
import builders.wcms.RequestInfoBuilder;
import builders.wcms.categoryType.search.SearchCategoryTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.categoryType.create.Category;
import entities.requests.wcms.categoryType.create.CreateCategoryTypeRequest;
import entities.requests.wcms.categoryType.search.SearchCategoryTypeRequest;
import entities.responses.wcms.categoryType.create.CreateCategoryTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.MANAS;

public class CategoryTypeTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void categoryTypeTest() throws IOException {
        LoginAndLogoutHelper.login1(MANAS); // Login
        String categoryTypeName = createCategoryType(); // Create CategoryType
        CreateCategoryTypeResponse searchCategoryTypeResponse = searchCategoryType(categoryTypeName); // Search CategoryType
        updateCategoryType(searchCategoryTypeResponse); // Update CategoryType
        LoginAndLogoutHelper.logout1(); // Logout
    }

    private String createCategoryType() throws IOException {
        new APILogger().log("Create CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        Category category = new CategoryBuilder().build();
        CreateCategoryTypeRequest createCategoryTypeRequest = new CreateCategoryTypeRequestBuilder()
                .withCategory(category).withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().createCategoryTypeResource(RequestHelper.getJsonString(createCategoryTypeRequest));
        CreateCategoryTypeResponse createCategoryTypeResponse = (CreateCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateCategoryTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(category.getName(), createCategoryTypeResponse.getCategory()[0].getName());
        new APILogger().log("Create CategoryType Test is Completed ---");
        return createCategoryTypeResponse.getCategory()[0].getName();
    }

    private CreateCategoryTypeResponse searchCategoryType(String categoryTypeName) throws IOException {
        new APILogger().log("Search CategoryType Test With Name is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchCategoryTypeRequest searchCategoryTypeRequest = new SearchCategoryTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchCategoryTypeResource(RequestHelper.getJsonString(searchCategoryTypeRequest),
                categoryTypeName, "&name=");
        CreateCategoryTypeResponse searchCategoryTypeResponse = (CreateCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateCategoryTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(1, searchCategoryTypeResponse.getCategory().length);
        Assert.assertEquals(categoryTypeName, searchCategoryTypeResponse.getCategory()[0].getName());
        new APILogger().log("Search CategoryType Test With Name is Completed ---");
        return searchCategoryTypeResponse;
    }

    private void updateCategoryType(CreateCategoryTypeResponse searchCategoryTypeResponse) throws IOException {
        new APILogger().log("Update CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        Category category = new CategoryBuilder().withName(searchCategoryTypeResponse.getCategory()[0].getName() + "-Updated").build();
        CreateCategoryTypeRequest createCategoryTypeRequest = new CreateCategoryTypeRequestBuilder()
                .withCategory(category).withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().updateCategoryTypeResource(RequestHelper.getJsonString(createCategoryTypeRequest),
                searchCategoryTypeResponse.getCategory()[0].getCode());
        CreateCategoryTypeResponse updateCategoryTypeResponse = (CreateCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateCategoryTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Updated", updateCategoryTypeResponse.getCategory()[0].getName().split("-")[1]);
        new APILogger().log("Update CategoryType Test is Completed ---");
    }
}
