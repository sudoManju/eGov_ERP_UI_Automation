package tests.commonMasters;

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
import utils.APILogger;
import utils.Properties;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class HolidayTest extends BaseAPITest {

    @Test
    public void holidayTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod("narasappa");

        // Search Department Test
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

        Assert.assertEquals(holidayResponse.getHoliday().length, 4);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Holiday Test is Completed --");
    }

}
