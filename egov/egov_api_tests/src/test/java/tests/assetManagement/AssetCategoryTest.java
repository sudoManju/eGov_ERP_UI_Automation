package tests.assetManagement;


import builders.assetManagement.AssetCategotyCreateRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.assetCategory.AssetCategotyCreateRequest;
import org.testng.annotations.Test;
import resources.AssetCategoryResource;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;

public class AssetCategoryTest extends BaseAPITest {

    @Test
    public void CreateAssetCategoryTest() throws IOException{
        AssetCategotyCreateRequest request = new AssetCategotyCreateRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new AssetCategoryResource().create(jsonString);
    }
}
