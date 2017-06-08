package tests.commonMasters.holiday;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.holiday.HolidayResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class SearchHolidayTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void holidayTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        // Search Holiday Test
        holidayTestMethod(loginResponse);
    }

    private void holidayTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();
        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchHolidayTest(jsonString);

        HolidayResponse holidayResponse = (HolidayResponse)
                ResponseHelper.getResponseAsObject(response.asString(), HolidayResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(holidayResponse.getHoliday().length, 4);

        new APILogger().log("Search Holiday Test is Completed --");
    }

}
