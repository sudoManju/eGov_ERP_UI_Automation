package tests.assetManagement;

import builders.assetManagement.assetCategory.AssetCategoryBuilder;
import builders.assetManagement.assetCategory.CreateAssetCategoryRequestBuilder;
import builders.assetManagement.assetCategory.SearchAssetCategoryRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.assetCategory.AssetCategory;
import entities.requests.assetManagement.assetCategory.CreateAssetCategoryRequest;
import entities.requests.assetManagement.assetCategory.SearchAssetCategoryRequest;
import entities.responses.assetManagement.assetCategory.createCategory.CreateAssetCategoryResponse;
import entities.responses.assetManagement.assetCategory.searchCategory.SearchAssetCategoryResponse;
import org.testng.Assert;
import resources.asset.AssetCategoryResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class AssetCategoryHelper extends BaseAPITest {

    public CreateAssetCategoryResponse createAssetCategory() throws IOException {
        AssetCategory category = new AssetCategoryBuilder().withName("LAND").build();
        CreateAssetCategoryRequest request = new CreateAssetCategoryRequestBuilder().withAssetCategory(category).build();

        Response response = new AssetCategoryResource().create(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 201);

        CreateAssetCategoryResponse createAssetCategoryResponse = (CreateAssetCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAssetCategoryResponse.class);
        new APILogger().log("Create Asset Category Test Is Completed");
        return createAssetCategoryResponse;
    }

    public int searchAssetCategory(String assetCode) throws IOException {
        SearchAssetCategoryRequest request = new SearchAssetCategoryRequestBuilder().build();
        Response response = new AssetCategoryResource().search(RequestHelper.getJsonString(request), assetCode);
        SearchAssetCategoryResponse searchAssetCategoryResponse = (SearchAssetCategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchAssetCategoryResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Search Asset Category Test Is Completed");
        return searchAssetCategoryResponse.getAssetCategory()[0].getId();
    }
}
