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
import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class PipeSizeTest extends BaseAPITest {

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void pipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreatePipeSizeResponse createPipeSizeResponse = createPipeSize(); // Create PipeSize
        CreatePipeSizeResponse searchPipeSizeResponse = searchPipeSize(createPipeSizeResponse, WITH_MILLIMETERSIZE); // Search PipeSize
        CreatePipeSizeResponse updatePipeSizeResponse = updatePipeSize(searchPipeSizeResponse); // Update PipeSize
        searchPipeSize(updatePipeSizeResponse, WITH_CODE); // Search PipeSize After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreatePipeSizeResponse createPipeSize() throws IOException {
        new APILogger().log("Create PipeSize Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PipeSize pipeSize = new PipeSizeBuilder().build();
        CreatePipeSizeRequest createPipeSizeRequest = new CreatePipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).withPipeSize(pipeSize).build();

        Response response = new WCMSResource().createPipeSizeResource(RequestHelper.getJsonString(createPipeSizeRequest));
        CreatePipeSizeResponse createPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(pipeSize.getSizeInMilimeter(), String.valueOf(createPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter()));
        new APILogger().log("Create PipeSize Test is Completed ---");
        return createPipeSizeResponse;
    }

    private CreatePipeSizeResponse searchPipeSize(CreatePipeSizeResponse createPipeSizeResponse, String parameter) throws IOException {
        new APILogger().log("Search PipeSize Test with " + parameter + " is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPipeSizeRequest searchPipeSizeRequest = new SearchPipeSizeRequestBuilder().withRequestInfo(requestInfo).build();

        String path;
        if (parameter.contains("&sizeInMilimeter"))
            path = pathBuilder(parameter, String.valueOf(createPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter()));
        else
            path = pathBuilder(parameter, createPipeSizeResponse.getPipeSize()[0].getCode());
        System.out.println(path);
        Response response = new WCMSResource().searchPipeSizeResource(RequestHelper.getJsonString(searchPipeSizeRequest), path);
        CreatePipeSizeResponse searchPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(createPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter(), searchPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter());
        Assert.assertTrue(searchPipeSizeResponse.getPipeSize().length == 1);
        new APILogger().log("Search PipeSize Test with " + parameter + " is Completed ---");
        return searchPipeSizeResponse;
    }

    private CreatePipeSizeResponse updatePipeSize(CreatePipeSizeResponse searchPipeSizeResponse) throws IOException {
        new APILogger().log("Update PipeSize Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PipeSize pipeSize = new PipeSizeBuilder().build();
        CreatePipeSizeRequest updatePipeSizeRequest = new CreatePipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).withPipeSize(pipeSize).build();

        Response response = new WCMSResource()
                .updatePipeSizeResource(RequestHelper.getJsonString(updatePipeSizeRequest), searchPipeSizeResponse.getPipeSize()[0].getCode());
        CreatePipeSizeResponse updatePipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(searchPipeSizeResponse.getPipeSize()[0].getCode(), updatePipeSizeResponse.getPipeSize()[0].getCode());
        Assert.assertNotEquals(searchPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter(), updatePipeSizeResponse.getPipeSize()[0].getSizeInMilimeter());
        new APILogger().log("Update PipeSize Test is Completed ---");
        return updatePipeSizeResponse;
    }
}
