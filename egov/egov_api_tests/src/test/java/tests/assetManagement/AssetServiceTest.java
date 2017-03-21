package tests.assetManagement;

import builders.assetManagement.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.assetManagement.assetCategory.RequestInfo;
import entities.responses.assetManagement.assetService.SearchAssetServiceResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.AssetServices;
import tests.BaseAPITest;
import utils.APILogger;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class AssetServiceTest extends BaseAPITest{

    @Test
    public void searchAssetService() throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withApiId(null)
                .withVer(null)
                .withTs(null)
                .withAction(null)
                .withDid(null)
                .withKey(null)
                .withMsgId(null)
                .withRequesterId("Ghanshyam")
                .withAuthToken(null)
                .build();

        String jsonString = RequestHelper.getJsonString(requestInfo);
        Response response = new AssetServices().getSearchAssetService(jsonString);

        SearchAssetServiceResponse searchAssetServiceResponse = (SearchAssetServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchAssetServiceResponse.class);

        Assert.assertEquals(response.getStatusCode() , 200);
        Assert.assertEquals(searchAssetServiceResponse.getAssets()[0].getAssetDetails() , "Asset details");

        new APILogger().log("Found the Asset Service  -- ");
    }
}
