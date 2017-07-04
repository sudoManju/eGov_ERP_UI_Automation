package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.categoryType.create.CategoryTypeBuilder;
import builders.wcms.categoryType.create.CreateCategoryTypeRequestBuilder;
import builders.wcms.categoryType.search.SearchCategoryTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import data.SearchParameterData;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.categoryType.create.CategoryType;
import entities.requests.wcms.categoryType.create.CreateCategoryTypeRequest;
import entities.requests.wcms.categoryType.search.SearchCategoryTypeRequest;
import entities.responses.wcms.categoryType.create.CreateCategoryTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static data.SearchParameterData.WITH_CODE;
import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class CategoryTypeTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void categoryTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateCategoryTypeResponse createCategoryTypeResponse = createCategoryType(); // Create CategoryType
        CreateCategoryTypeResponse searchCategoryTypeResponse = searchCategoryType(createCategoryTypeResponse, WITH_NAME); // Search CategoryType
        CreateCategoryTypeResponse updateCategoryTypeResponse = updateCategoryType(searchCategoryTypeResponse); // Update CategoryType
        searchCategoryType(updateCategoryTypeResponse, WITH_CODE); // Search After Update CategoryType
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreateCategoryTypeResponse createCategoryType() throws IOException {
        new APILogger().log("Create CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CategoryType category = new CategoryTypeBuilder().build();
        CreateCategoryTypeRequest createCategoryTypeRequest = new CreateCategoryTypeRequestBuilder()
                .withCategory(category).withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().createCategoryTypeResource(RequestHelper.getJsonString(createCategoryTypeRequest));
        CreateCategoryTypeResponse createCategoryTypeResponse = (CreateCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateCategoryTypeResponse.class);

        Assert.assertEquals(createCategoryTypeResponse.getResponseInfo().getStatus(), "201");
        Assert.assertEquals(category.getName(), createCategoryTypeResponse.getCategory()[0].getName());
        new APILogger().log("Create CategoryType Test is Completed ---");
        return createCategoryTypeResponse;
    }

    private CreateCategoryTypeResponse searchCategoryType(CreateCategoryTypeResponse createCategoryTypeResponse, String parameter) throws IOException {
        new APILogger().log("Search CategoryType Test " + parameter + " is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchCategoryTypeRequest searchCategoryTypeRequest = new SearchCategoryTypeRequestBuilder().withRequestInfo(requestInfo).build();

        String path;
        if (parameter.contains("&name="))
            path = pathBuilder(parameter, createCategoryTypeResponse.getCategory()[0].getName());
        else
            path = pathBuilder(parameter, createCategoryTypeResponse.getCategory()[0].getCode());

        Response response = new WCMSResource().searchCategoryTypeResource(RequestHelper.getJsonString(searchCategoryTypeRequest),
                path);
        CreateCategoryTypeResponse searchCategoryTypeResponse = (CreateCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateCategoryTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(searchCategoryTypeResponse.getCategory().length == 1);
        Assert.assertEquals(createCategoryTypeResponse.getCategory()[0].getName(), searchCategoryTypeResponse.getCategory()[0].getName());
        new APILogger().log("Search CategoryType Test " + parameter + "is Completed ---");
        return searchCategoryTypeResponse;
    }

    private CreateCategoryTypeResponse updateCategoryType(CreateCategoryTypeResponse searchCategoryTypeResponse) throws IOException {
        new APILogger().log("Update CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CategoryType category = new CategoryTypeBuilder().withName(searchCategoryTypeResponse.getCategory()[0].getName() + "-Updated").build();
        CreateCategoryTypeRequest createCategoryTypeRequest = new CreateCategoryTypeRequestBuilder()
                .withCategory(category).withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().updateCategoryTypeResource(RequestHelper.getJsonString(createCategoryTypeRequest),
                searchCategoryTypeResponse.getCategory()[0].getCode());
        CreateCategoryTypeResponse updateCategoryTypeResponse = (CreateCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateCategoryTypeResponse.class);

        Assert.assertEquals(updateCategoryTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertEquals("Updated", updateCategoryTypeResponse.getCategory()[0].getName().split("-")[1]);
        new APILogger().log("Update CategoryType Test is Completed ---");
        return updateCategoryTypeResponse;
    }
}
