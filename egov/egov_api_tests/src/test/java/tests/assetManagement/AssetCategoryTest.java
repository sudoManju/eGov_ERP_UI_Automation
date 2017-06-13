package tests.assetManagement;

import builders.assetManagement.assetCategory.AssetCategoryBuilder;
import builders.assetManagement.assetCategory.CreateAssetCategoryRequestBuilder;
import builders.assetManagement.assetCategory.SearchAssetCategoryRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.assetCategory.AssetCategory;
import entities.requests.assetManagement.assetCategory.CreateAssetCategoryRequest;
import entities.requests.assetManagement.assetCategory.SearchAssetCategoryRequest;
import entities.responses.assetManagement.assetCategory.CreateAssetCategoryResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.AssetCategoryResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.ADMIN;

public class AssetCategoryTest extends BaseAPITest {


    @Test
    public void assetCategoryTest() throws IOException {

        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN);  //Login

        CreateAssetCategoryResponse create = createAssetCategoryTest(sessionId);

        searchAssetCategoryTest(sessionId,create.getAssetCategory()[0].getCode());
    }

    private void searchAssetCategoryTest(String sessionId, String code) {

        new APILogger().log("Search Asset Category Test started");

        SearchAssetCategoryRequest request = new SearchAssetCategoryRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new AssetCategoryResource().search(jsonString,sessionId,code);

        Assert.assertEquals(response.getStatusCode(),200);
    }

    private CreateAssetCategoryResponse createAssetCategoryTest(String id) throws IOException {

        new APILogger().log("Create Asset Category Test started");

        AssetCategory category = new AssetCategoryBuilder().withName("Shop_"+get3DigitRandomInt()).build();

        CreateAssetCategoryRequest request = new CreateAssetCategoryRequestBuilder().withAssetCategory(category).build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new AssetCategoryResource().create(jsonString,id);

        Assert.assertEquals(response.getStatusCode(),201);

        CreateAssetCategoryResponse response1 = (CreateAssetCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(),CreateAssetCategoryResponse.class);

        return response1;
    }

}
