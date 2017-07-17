package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.sourceType.create.CreateSourceTypeRequestBuilder;
import builders.wcms.sourceType.create.SourceTypeBuilder;
import builders.wcms.sourceType.search.SearchSourceTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.sourceType.create.CreateSourceTypeRequest;
import entities.requests.wcms.sourceType.create.SourceType;
import entities.requests.wcms.sourceType.search.SearchSourceTypeRequest;
import entities.responses.wcms.sourceType.create.CreateSourceTypeResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_CODE;
import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class SourceTypeTest extends BaseAPITest {

    private RequestInfo requestInfo;

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateSourceTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateSourceTypeResponse createSourceTypeResponse = createSourceType(); // Create SourceType
        CreateSourceTypeResponse searchSourceTypeResponse = searchSourceType(createSourceTypeResponse, WITH_NAME); // Search SourceType
        CreateSourceTypeResponse updateSourceTypeResponse = updateSourceType(searchSourceTypeResponse); // Update SourceType
        searchSourceType(updateSourceTypeResponse, WITH_CODE); // Search SourceType After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void searchSourceTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        getAllSourceTypes(); // Get All SourceTypes
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreateSourceTypeResponse createSourceType() throws IOException {
        new APILogger().log("Create SourceType Test is Started ---");
        SourceType sourceType = new SourceTypeBuilder().build();
        CreateSourceTypeRequest createSourceTypeRequest = new CreateSourceTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSourceType(sourceType).build();

        Response response = new WCMSResource().createSourceTypeResource(RequestHelper.getJsonString(createSourceTypeRequest));
        CreateSourceTypeResponse createSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(sourceType.getName(), createSourceTypeResponse.getSourceTypes()[0].getName());
        new APILogger().log("Create SourceType Test is Completed ---");
        return createSourceTypeResponse;
    }

    private CreateSourceTypeResponse searchSourceType(CreateSourceTypeResponse createSourceTypeResponse, String parameter) throws IOException {
        new APILogger().log("Search SourceType Test With " + parameter + " is Started ---");
        SearchSourceTypeRequest searchSourceTypeRequest = new SearchSourceTypeRequestBuilder().withRequestInfo(requestInfo).build();

        String path;
        if (parameter.contains("&name="))
            path = pathBuilder(parameter, createSourceTypeResponse.getSourceTypes()[0].getName());
        else
            path = pathBuilder(parameter, createSourceTypeResponse.getSourceTypes()[0].getCode());

        Response response = new WCMSResource().searchSourceTypeResource(RequestHelper.getJsonString(searchSourceTypeRequest), path);
        CreateSourceTypeResponse searchSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createSourceTypeResponse.getSourceTypes()[0].getName(), searchSourceTypeResponse.getSourceTypes()[0].getName());
        Assert.assertTrue(searchSourceTypeResponse.getSourceTypes().length == 1);
        new APILogger().log("Search SourceType Test With " + parameter + " is Completed ---");
        return searchSourceTypeResponse;
    }

    private CreateSourceTypeResponse updateSourceType(CreateSourceTypeResponse searchSourceTypeResponse) throws IOException {
        new APILogger().log("Update SourceType Test is Started ---");
        SourceType sourceType = new SourceTypeBuilder().withCode(searchSourceTypeResponse.getSourceTypes()[0].getCode())
                .withName(searchSourceTypeResponse.getSourceTypes()[0].getName() + "-Updated")
                .build();
        CreateSourceTypeRequest updateSourceTypeRequest = new CreateSourceTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSourceType(sourceType).build();

        Response response = new WCMSResource().updateSourceTypeResource(RequestHelper.getJsonString(updateSourceTypeRequest),
                searchSourceTypeResponse.getSourceTypes()[0].getCode());
        CreateSourceTypeResponse updateSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals("Updated", updateSourceTypeResponse.getSourceTypes()[0].getName().split("-")[1]);
        new APILogger().log("Update SourceType Test is Completed ---");
        return updateSourceTypeResponse;
    }

    private void getAllSourceTypes() throws IOException {
        new APILogger().log("Get ALL SourceType Test Request is Started ---");
        SearchSourceTypeRequest searchSourceTypeRequest = new SearchSourceTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchSourceTypeResource(RequestHelper.getJsonString(searchSourceTypeRequest), "");
        CreateSourceTypeResponse searchSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchSourceTypeResponse.getSourceTypes().length > 0);
        new APILogger().log("Get ALL SourceType Test Request is Started ---");
    }
}
