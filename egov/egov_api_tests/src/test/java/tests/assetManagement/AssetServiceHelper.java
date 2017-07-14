package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import builders.assetManagement.assetService.AssetBuilder;
import builders.assetManagement.assetService.AssetCategoryBuilder;
import builders.assetManagement.assetService.CreateAssetServiceRequestBuilder;
import builders.assetManagement.assetService.SearchAssetServiceRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.SearchAssetServiceRequest;
import entities.requests.assetManagement.assetService.Asset;
import entities.requests.assetManagement.assetService.CreateAssetServiceRequest;
import entities.responses.assetManagement.assetServices.AssetCategory;
import entities.responses.assetManagement.assetServices.CreateAssetServiceResponse;
import org.junit.Assert;
import resources.asset.AssetServiceResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class AssetServiceHelper extends BaseAPITest {

    public CreateAssetServiceResponse searchAssetService(String queryParameter, String data) throws IOException {
        new APILogger().log("Search Asset Service Request is Started  -- ");
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchAssetServiceRequest request = new SearchAssetServiceRequestBuilder().withRequestInfo(requestInfo).build();

        String endPoints;
        if (queryParameter.contains("code"))
            endPoints = queryParameter + data;
        else if (queryParameter.contains("status"))
            endPoints = queryParameter + data;
        else endPoints = "";

        Response response = new AssetServiceResource().getSearchAssetServiceResource(RequestHelper.getJsonString(request), endPoints);
        CreateAssetServiceResponse createAssetServiceResponse = (CreateAssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAssetServiceResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(createAssetServiceResponse.getAssets().length > 0);
        new APILogger().log("Search Asset Service Request is Completed  -- ");
        return createAssetServiceResponse;
    }

    public String createAssetService(String assetCode, String name, int id) throws IOException {
        new APILogger().log("Create Asset Service Request is Started  -- ");
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        AssetCategory assetCategory = new AssetCategoryBuilder()
                .withName(name)
                .withId(id)
                .withCode(assetCode)
                .build();
        Asset asset = new AssetBuilder().withAssetCategory(assetCategory).build();
        CreateAssetServiceRequest createAssetServiceRequest = new CreateAssetServiceRequestBuilder().withRequestInfo(requestInfo)
                .withAsset(asset)
                .build();

        Response response = new AssetServiceResource()
                .getCreateAssetService(RequestHelper.getJsonString(createAssetServiceRequest));
        CreateAssetServiceResponse createAssetServiceResponse = (CreateAssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateAssetServiceResponse.class);

        Assert.assertEquals(createAssetServiceResponse.getAssets()[0].getName(), createAssetServiceRequest.getAsset().getName());
        Assert.assertEquals(response.getStatusCode(), 201);
        new APILogger().log("Create Asset Service Request is Completed  -- ");
        return createAssetServiceResponse.getAssets()[0].getCode();
    }
}
