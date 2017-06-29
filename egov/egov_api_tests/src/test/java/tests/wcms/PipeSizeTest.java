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

import static data.UserData.MANAS;

public class PipeSizeTest extends BaseAPITest {

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void pipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        String millimeterSize = createPipeSize(); // Create PipeSize
        CreatePipeSizeResponse searchPipeSizeResponse = searchPipeSize(millimeterSize); // Search PipeSize
        updatePipeSize(searchPipeSizeResponse); // Update PipeSize
        LoginAndLogoutHelper.logout(); // Logout
    }

    private String createPipeSize() throws IOException {
        new APILogger().log("Create PipeSize Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PipeSize pipeSize = new PipeSizeBuilder().build();
        CreatePipeSizeRequest createPipeSizeRequest = new CreatePipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).withPipeSize(pipeSize).build();

        Response response = new WCMSResource().createPipeSizeResource(RequestHelper.getJsonString(createPipeSizeRequest));
        CreatePipeSizeResponse createPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(pipeSize.getSizeInMilimeter(), String.valueOf(createPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter()));
        new APILogger().log("Create PipeSize Test is Completed ---");
        return pipeSize.getSizeInMilimeter();
    }

    private CreatePipeSizeResponse searchPipeSize(String millimeterSize) throws IOException {
        new APILogger().log("Search PipeSize Test with Name is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPipeSizeRequest searchPipeSizeRequest = new SearchPipeSizeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchPipeSizeResource(RequestHelper.getJsonString(searchPipeSizeRequest), millimeterSize);
        CreatePipeSizeResponse searchPipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(millimeterSize, String.valueOf(searchPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter()));
        Assert.assertTrue(searchPipeSizeResponse.getPipeSize().length == 1);
        new APILogger().log("Search PipeSize Test With Name is Completed ---");
        return searchPipeSizeResponse;
    }

    private void updatePipeSize(CreatePipeSizeResponse searchPipeSizeResponse) throws IOException {
        new APILogger().log("Update PipeSize Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PipeSize pipeSize = new PipeSizeBuilder().build();
        CreatePipeSizeRequest updatePipeSizeRequest = new CreatePipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).withPipeSize(pipeSize).build();

        Response response = new WCMSResource()
                .updatePipeSizeResource(RequestHelper.getJsonString(updatePipeSizeRequest) , searchPipeSizeResponse.getPipeSize()[0].getCode());
        CreatePipeSizeResponse updatePipeSizeResponse = (CreatePipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePipeSizeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(searchPipeSizeResponse.getPipeSize()[0].getCode(), updatePipeSizeResponse.getPipeSize()[0].getCode());
        Assert.assertNotEquals(searchPipeSizeResponse.getPipeSize()[0].getSizeInMilimeter(), updatePipeSizeResponse.getPipeSize()[0].getSizeInMilimeter());
        new APILogger().log("Update PipeSize Test is Completed ---");
    }
}
