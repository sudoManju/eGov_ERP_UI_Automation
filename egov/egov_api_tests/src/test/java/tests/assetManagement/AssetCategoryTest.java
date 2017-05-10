package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetCategory.AssetCategoryCreateRequestBuilder;
import builders.assetManagement.assetCategory.AssetCategorySearchRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.SearchAssetRequest;
import entities.requests.assetManagement.assetCategory.AssetCategoryCreateRequest;
import entities.responses.assetManagement.assetCategory.AssetCategoryResponse;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.AssetCategoryResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class AssetCategoryTest extends BaseAPITest {

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void CreateAssetCategoryTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create Asset Category Test
        createAssetCategoryTestMethod(loginResponse);
    }


    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void SearchAssetCategoryTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Search Asset Category Test
        searchAssetCategoryTestMethod(loginResponse);
    }

    private void createAssetCategoryTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        AssetCategoryCreateRequest request = new AssetCategoryCreateRequestBuilder().withRequestInfo(requestInfo).build();

        String jsonString = RequestHelper.getJsonString(request);
        Response response = new AssetCategoryResource().create(jsonString, loginResponse.getAccess_token());

        AssetCategoryResponse assetCategoryResponse = (AssetCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetCategoryResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(assetCategoryResponse.getAssetCategory()[0].getName(), request.getAssetCategory().getName());

        new APILogger().log("Create Asset Category Test is Completed --");
    }

    private void searchAssetCategoryTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        SearchAssetRequest request = new AssetCategorySearchRequestBuilder().withRequestInfo(requestInfo).build();
        String jsonString = RequestHelper.getJsonString(request);

        Response response = new AssetCategoryResource().search(jsonString, loginResponse.getAccess_token());

        AssetCategoryResponse assetCategoryResponse = (AssetCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetCategoryResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Asset Category Test is Completed --");
    }
}
