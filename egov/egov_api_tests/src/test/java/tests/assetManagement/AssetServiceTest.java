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
import resources.AssetServices;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class AssetServiceTest extends BaseAPITest{

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void searchAssetService() throws IOException {
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl,"malathi");

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withRequesterId("Ghanshyam")
                .build();

        String jsonString = RequestHelper.getJsonString(requestInfo);
        Response response = new AssetServices().getSearchAssetService(jsonString, loginResponse.getAccess_token());

        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);

        Assert.assertEquals(response.getStatusCode() , 200);
//        Assert.assertEquals(assetServiceResponse.getAssets()[0].getAssetDetails() , "Asset details");

        new APILogger().log("Found the Asset Service  -- ");
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY})
    public void createAssetService() throws IOException{
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl,"malathi");

        CreateAssetServiceRequest createAssetServiceRequest = new CreateAssetServiceRequestBuilder()
                .build();

        String jsonString = RequestHelper.getJsonString(createAssetServiceRequest);

        Response response = new AssetServices().getCreateAssetService(jsonString,loginResponse.getAccess_token());

        AssetServiceResponse assetServiceResponse = (AssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), AssetServiceResponse.class);

        Assert.assertEquals(assetServiceResponse.getAssets()[0].getName() ,createAssetServiceRequest.getAsset().getName() );
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
