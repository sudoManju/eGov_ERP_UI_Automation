package tests.assetManagement;


import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetCategory.AssetCategoryCreateRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetCategory.AssetCategoryCreateRequest;
import entities.responses.assetManagement.assetCategory.AssetCategoryResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.AssetCategoryResource;
import tests.BaseAPITest;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class AssetCategoryTest extends BaseAPITest {

    @Test
    public void CreateAssetCategoryTest() throws IOException{
        AssetCategoryCreateRequest request = new AssetCategoryCreateRequestBuilder().build();

        String jsonString = RequestHelper.getJsonString(request);

        Response response = new AssetCategoryResource().create(jsonString);

        Assert.assertEquals(response.getStatusCode(),201);

        AssetCategoryResponse assetCategoryResponse = (AssetCategoryResponse) ResponseHelper.getResponseAsObject(response.asString(),AssetCategoryResponse.class);

        Assert.assertEquals(assetCategoryResponse.getAssetCategory()[0].getName(),request.getAssetCategory().getName());
    }

    @Test
    public void SearchAssetCategoryTest() throws IOException{
        RequestInfo requestInfo = new RequestInfoBuilder().build();

        String jsonString = RequestHelper.getJsonString(requestInfo);

        Response response = new AssetCategoryResource().search(jsonString);

        Assert.assertEquals(response.getStatusCode(),200);

        AssetCategoryResponse assetCategoryResponse = (AssetCategoryResponse) ResponseHelper.getResponseAsObject(response.asString(),AssetCategoryResponse.class);

        Assert.assertEquals(assetCategoryResponse.getAssetCategory()[0].getCustomFields()[0].getName(), "Land");
    }
}
