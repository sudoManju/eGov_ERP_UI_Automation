package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetService.AssetBuilder;
import builders.assetManagement.assetService.CreateAssetRequestBuilder;
import builders.assetManagement.assetService.SearchAssetServiceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetServices.create.Asset;
import entities.requests.assetManagement.assetServices.create.CreateAssetRequest;
import entities.requests.assetManagement.assetServices.search.SearchAssetServiceRequest;
import entities.responses.assetManagement.createAsset.CreateAssetResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.asset.AssetServiceResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import static data.UserData.NARASAPPA;

public class AssetServiceTest extends BaseAPITest{

    RequestInfo requestInfo;

    /* Tests Description
   ==========================
   Create Asset and
   Search Asset.
   Modify Asset and
   Search Asset.
*/
    //Create Asset
    @Test(groups = {Categories.ASSET, Categories.DEV})
    public void assetTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA); //login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        createAsset(); //Create Asset
        searchAsset(); //Search Asset
    }

    private void createAsset() throws IOException {
        Asset asset = new AssetBuilder("Land").withName("Land_"+get3DigitRandomInt()).build();
//        Asset asset = new AssetBuilder("Market").withName("Market_"+get3DigitRandomInt()).build();
        CreateAssetRequest request = new CreateAssetRequestBuilder().withRequestInfo(requestInfo).withAsset(asset).build();
        Response response = new AssetServiceResource().createAssetService(RequestHelper.getJsonString(request));

        CreateAssetResponse createAssetResponse = (CreateAssetResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAssetResponse.class);
        Assert.assertEquals(createAssetResponse.getAssets()[0].getAssetCategory().getName(),
                request.getAsset().getAssetCategory().getName());
    }
    private void searchAsset(){
        SearchAssetServiceRequest request = new SearchAssetServiceRequestBuilder().withRequestInfo(requestInfo).build();
        Response response = new AssetServiceResource().searchAssetService(RequestHelper.getJsonString(request));
    }
}