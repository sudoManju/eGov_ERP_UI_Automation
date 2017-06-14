package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.category.CategoryResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class CategoryTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void categoryTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchCategory(sessionId);  // Search Category
    }

    private void searchCategory(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new CommonMasterResource().
                searchCategoryResource(RequestHelper.getJsonString(commonMasterRequest), sessionId);
        CategoryResponse categoryResponse = (CategoryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CategoryResponse.class);

        Assert.assertTrue(categoryResponse.getCategory().length > 0);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Category Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
