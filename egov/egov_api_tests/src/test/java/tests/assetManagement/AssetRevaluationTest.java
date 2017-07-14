package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetRevaluation.create.CreateAssetRevaluationRequestBuilder;
import builders.assetManagement.assetRevaluation.create.RevaluationBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetRevaluation.create.CreateAssetRevaluationRequest;
import entities.requests.assetManagement.assetRevaluation.create.Revaluation;
import entities.responses.assetManagement.assetServices.CreateAssetServiceResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.asset.AssetRevaluationResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

import static data.SearchParameterData.WITH_STATUS;
import static data.UserData.AssetServiceUser;

public class AssetRevaluationTest extends BaseAPITest {

    private AssetServiceHelper assetServiceHelper;

    public AssetRevaluationTest() {
        assetServiceHelper = new AssetServiceHelper();
    }

    @Test(groups = {Categories.ASSET, Categories.SANITY, Categories.PILOT})
    public void createSearchAssetRevaluationTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(AssetServiceUser); // Login
        CreateAssetServiceResponse createAssetServiceResponse = assetServiceHelper.searchAssetService(WITH_STATUS, "CAPITALIZED"); // Search Asset Service With Status as CAPITALIZED
        createAssetRevaluation(createAssetServiceResponse);
        pilotLogoutService(); // Logout
    }

    private void createAssetRevaluation(CreateAssetServiceResponse createAssetServiceResponse) {
        new APILogger().log("Create Asset Revaluation Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        Revaluation revaluation = new RevaluationBuilder()
                .withAssetId(createAssetServiceResponse.getAssets()[getRandomIntFromRange(0,
                        createAssetServiceResponse.getAssets().length)].getId()).build();
        CreateAssetRevaluationRequest createAssetRevaluationRequest = new CreateAssetRevaluationRequestBuilder()
                .withRequestInfo(requestInfo).withRevaluation(revaluation).build();

        Response response = new AssetRevaluationResource()
                .createRevaluationResource(RequestHelper.getJsonString(createAssetRevaluationRequest));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
