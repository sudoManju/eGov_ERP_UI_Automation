package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.pipeSize.create.CreatePipeSizeRequestBuilder;
import builders.wcms.pipeSize.create.PipeSizeBuilder;
import builders.wcms.pipeSize.search.SearchPipeSizeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.pipeSize.create.CreatePipeSizeRequest;
import entities.requests.wcms.pipeSize.create.PipeSize;
import entities.requests.wcms.pipeSize.search.SearchPipeSizeRequest;
import entities.responses.wcms.pipeSize.CreatePipeSizeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_CODE;
import static data.SearchParameterData.WITH_MILLIMETERSIZE;
import static data.UserData.MANAS;

public class PipeSizesTest extends BaseAPITest {

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createSearchUpdatePipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreatePipeSizeResponse createPipeSizeResponse = createPipeSize(); // Create PipeSizes
        CreatePipeSizeResponse searchPipeSizeResponse = searchPipeSize(createPipeSizeResponse, WITH_MILLIMETERSIZE); // Search PipeSizes
        CreatePipeSizeResponse updatePipeSizeResponse = updatePipeSize(searchPipeSizeResponse); // Update PipeSizes
        searchPipeSize(updatePipeSizeResponse, WITH_CODE); // Search PipeSizes After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void searchPipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        getAllPipeSizes(); // Get All PipeSizes
        LoginAndLogoutHelper.logout(); // Logout
    }

    CreatePipeSizeResponse createPipeSize() throws IOException {
        new APILogger().log("Create PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PipeSize pipeSize = new PipeSizeBuilder().build();
        CreatePipeSizeRequest createPipeSizeRequest = new CreatePipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).withPipeSize(pipeSize).build();

        Response response = new WCMSResource().createPipeSizeResource(RequestHelper.getJsonString(createPipeSizeRequest));
        CreatePipeSizeResponse createPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(pipeSize.getSizeInMilimeter(), String.valueOf(createPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter()));
        new APILogger().log("Create PipeSizes Test is Completed ---");
        return createPipeSizeResponse;
    }

    CreatePipeSizeResponse searchPipeSize(CreatePipeSizeResponse createOrUpdatePipeSizeResponse, String parameter) throws IOException {
        new APILogger().log("Search PipeSizes Test with " + parameter + " is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPipeSizeRequest searchPipeSizeRequest = new SearchPipeSizeRequestBuilder().withRequestInfo(requestInfo).build();

        String path;
        if (parameter.contains("&sizeInMilimeter"))
            path = pathBuilder(parameter, String.valueOf(createOrUpdatePipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter()));
        else
            path = pathBuilder(parameter, createOrUpdatePipeSizeResponse.getPipeSizes()[0].getCode());

        Response response = new WCMSResource().searchPipeSizeResource(RequestHelper.getJsonString(searchPipeSizeRequest), path);
        CreatePipeSizeResponse searchPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(createOrUpdatePipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter(), searchPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter());
        Assert.assertTrue(searchPipeSizeResponse.getPipeSizes().length == 1);
        new APILogger().log("Search PipeSizes Test with " + parameter + " is Completed ---");
        return searchPipeSizeResponse;
    }

    private CreatePipeSizeResponse updatePipeSize(CreatePipeSizeResponse searchPipeSizeResponse) throws IOException {
        new APILogger().log("Update PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PipeSize pipeSize = new PipeSizeBuilder().build();
        CreatePipeSizeRequest updatePipeSizeRequest = new CreatePipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).withPipeSize(pipeSize).build();

        Response response = new WCMSResource()
                .updatePipeSizeResource(RequestHelper.getJsonString(updatePipeSizeRequest), searchPipeSizeResponse.getPipeSizes()[0].getCode());
        CreatePipeSizeResponse updatePipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(searchPipeSizeResponse.getPipeSizes()[0].getCode(), updatePipeSizeResponse.getPipeSizes()[0].getCode());
        Assert.assertNotEquals(searchPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter(), updatePipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter());
        new APILogger().log("Update PipeSizes Test is Completed ---");
        return updatePipeSizeResponse;
    }

    public CreatePipeSizeResponse getAllPipeSizes() throws IOException {
        new APILogger().log("Search All PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPipeSizeRequest searchPipeSizeRequest = new SearchPipeSizeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchPipeSizeResource(RequestHelper.getJsonString(searchPipeSizeRequest), "");
        CreatePipeSizeResponse searchPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(searchPipeSizeResponse.getPipeSizes().length >= 1);
        new APILogger().log("Search All PipeSizes Test is Completed ---");
        return searchPipeSizeResponse;
    }
}
