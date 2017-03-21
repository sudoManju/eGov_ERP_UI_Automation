package tests.AssetManagement;

import builders.AssetManagement.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.AssetManagement.RequestInfo;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.AssetServices;
import utils.APILogger;
import utils.RequestHelper;

import java.io.IOException;

public class AssetServiceTest {

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

        Assert.assertEquals(response.getStatusCode() , 200);

        new APILogger().log("Found the Asset Service  -- ");
    }
}
