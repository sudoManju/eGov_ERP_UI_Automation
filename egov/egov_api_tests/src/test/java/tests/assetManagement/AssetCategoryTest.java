package tests.assetManagement;


import builders.assetManagement.assetCategory.AssetCategoryCreateRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.assetCategory.AssetCategoryCreateRequest;
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

        System.out.println(jsonString);

        Response response = new AssetCategoryResource().create(jsonString);
        System.out.println(response.getStatusCode());
//
//        System.out.println(response.prettyPrint());
//
//        Assert.assertEquals(response.getStatusCode(),201);
    }
}
