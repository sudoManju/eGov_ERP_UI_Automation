package tests.assetManagement;


import builders.assetManagement.assetCategory.AssetCategoryCreateRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.assetCategory.AssetCategoryCreateRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.AssetCategoryResource;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;

public class AssetCategoryTest extends BaseAPITest {

    @Test
    public void CreateAssetCategoryTest() throws IOException{
        AssetCategoryCreateRequest request = new AssetCategoryCreateRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);

        jsonString = jsonString.replace("assetCategory", "AssetCategory");
        jsonString = jsonString.replace("RequestInfo", "RequestInfo");

        Response response = new AssetCategoryResource().create(jsonString);

        Assert.assertEquals(response.getStatusCode(),201);

    }
}
