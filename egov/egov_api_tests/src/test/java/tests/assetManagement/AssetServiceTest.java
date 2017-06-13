package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetService.CreateAssetServiceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetService.CreateAssetServiceRequest;
import entities.responses.assetManagement.assetServices.AssetServiceResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.AssetServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.AssetServiceUser;

public class AssetServiceTest extends BaseAPITest {

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void searchAssetService() throws IOException {

        // Login Test
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(AssetServiceUser);
        // Search Asset Service Test
        searchAssetServiceTestMethod(sessionId);
    }

    private void searchAssetServiceTestMethod(String sessionId) throws IOException {

//        RequestInfo requestInfo = new RequestInfoBuilder().withRequesterId("Ghanshyam").build();
//
//        SearchAssetRequest request = new AssetCategorySearchRequestBuilder().withRequestInfo(requestInfo).build();
//
//        String jsonString = RequestHelper.getJsonString(request);
//
//        Response response = new AssetServiceResource().getSearchAssetService(jsonString, sessionId);
//
//        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
//                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertTrue(assetServiceResponse.getAssets().length > 0);
//        new APILogger().log("Search Asset Service Request is Completed  -- ");
//
//        // Logout Test
//        pilotLogoutService(sessionId);
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void createAssetService() throws IOException {

        // Login Test
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(AssetServiceUser);

        // Create Asset Service Test
        createAssetServiceTestMethod(sessionId);

    }

    private void createAssetServiceTestMethod(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();

        CreateAssetServiceRequest createAssetServiceRequest = new CreateAssetServiceRequestBuilder().withRequestInfo(requestInfo)
                .build();

        String jsonString = RequestHelper.getJsonString(createAssetServiceRequest);

        Response response = new AssetServiceResource().getCreateAssetService(jsonString, sessionId);

        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);

        Assert.assertEquals(assetServiceResponse.getAssets()[0].getName(), createAssetServiceRequest.getAsset().getName());
        Assert.assertEquals(response.getStatusCode(), 201);

        new APILogger().log("Create Asset Service Request is Completed  -- ");
    }
}
