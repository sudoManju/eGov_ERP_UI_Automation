package tests.commonMasters.holiday;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.holiday.HolidayResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class SearchHolidayTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void holidayTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchHoliday(sessionId);  // Search Holiday
    }

    private void searchHoliday(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new CommonMasterResource()
                .searchHolidayResource(RequestHelper.getJsonString(commonMasterRequest), sessionId);
        HolidayResponse holidayResponse = (HolidayResponse)
                ResponseHelper.getResponseAsObject(response.asString(), HolidayResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(holidayResponse.getHoliday().length >= 0);

        new APILogger().log("Search Holiday Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
