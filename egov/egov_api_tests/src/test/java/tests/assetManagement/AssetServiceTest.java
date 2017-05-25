package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetCategory.AssetCategorySearchRequestBuilder;
import builders.assetManagement.assetService.CreateAssetServiceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.SearchAssetRequest;
import entities.requests.assetManagement.assetService.CreateAssetServiceRequest;
import entities.responses.assetManagement.assetService.AssetServiceResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.AssetServiceResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.usernames.malathi;

public class AssetServiceTest extends BaseAPITest {

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void searchAssetService() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(malathi);


        // Search Asset Service Test
        searchAssetServiceTestMethod(loginResponse);
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void createAssetService() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(malathi);

        // Create Asset Service Test
        createAssetServiceTestMethod(loginResponse);

    }

    private void searchAssetServiceTestMethod(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder().withRequesterId("Ghanshyam").withAuthToken(loginResponse.getAccess_token()).build();

        SearchAssetRequest request = new AssetCategorySearchRequestBuilder().withRequestInfo(requestInfo).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new AssetServiceResource().getSearchAssetService(jsonString, loginResponse.getAccess_token());

        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Asset Service Request is Completed  -- ");
    }

    private void createAssetServiceTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateAssetServiceRequest createAssetServiceRequest = new CreateAssetServiceRequestBuilder().withRequestInfo(requestInfo)
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
