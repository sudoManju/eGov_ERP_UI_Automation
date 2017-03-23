package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetService.CreateAssetServiceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetService.CreateAssetServiceRequest;
import entities.responses.assetManagement.assetService.AssetServiceResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.AssetServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class AssetServiceTest extends BaseAPITest {

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void searchAssetService() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "malathi");

        // Search Asset Service Test
        searchAssetServiceTestMethod(loginResponse);
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void createAssetService() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "malathi");

        // Create Asset Service Test
        createAssetServiceTestMethod(loginResponse);

    }

    private void searchAssetServiceTestMethod(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withRequesterId("Ghanshyam")
                .build();

        String jsonString = RequestHelper.getJsonString(requestInfo);
        Response response = new AssetServiceResource().getSearchAssetService(jsonString, loginResponse.getAccess_token());

        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Asset Service Request is Completed  -- ");
    }

    private void createAssetServiceTestMethod(LoginResponse loginResponse) throws IOException {
        CreateAssetServiceRequest createAssetServiceRequest = new CreateAssetServiceRequestBuilder()
                .build();

        String jsonString = RequestHelper.getJsonString(createAssetServiceRequest);

        Response response = new AssetServiceResource().getCreateAssetService(jsonString, loginResponse.getAccess_token());

        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);

        Assert.assertEquals(assetServiceResponse.getAssets()[0].getName(), createAssetServiceRequest.getAsset().getName());
        Assert.assertEquals(response.getStatusCode(), 201);

        new APILogger().log("Create Asset Service Request is Completed  -- ");
    }
}
