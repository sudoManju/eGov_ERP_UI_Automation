package tests.commonMasters.holiday;

import builders.commonMaster.createHoliday.CalendarYearBuilder;
import builders.commonMaster.createHoliday.CreateHolidayRequestBuilder;
import builders.commonMaster.createHoliday.HolidayBuilder;
import builders.commonMaster.createHoliday.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.createHoliday.CalendarYear;
import entities.requests.commonMasters.createHoliday.CreateHolidayRequest;
import entities.requests.commonMasters.createHoliday.Holiday;
import entities.requests.commonMasters.createHoliday.RequestInfo;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

public class CreateHolidayTest {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void createHolidayTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Create Holiday Test
        createHolidayTestMethod(loginResponse);

    }

    private void createHolidayTestMethod(LoginResponse loginResponse) {

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withAuth_token(loginResponse.getAccess_token())
                .build();

        CalendarYear calendarYear = new CalendarYearBuilder()
                .build();

        Holiday holiday = new HolidayBuilder()
                .withCalendarYear(calendarYear)
                .withId(30)
                .withName("Good Friday")
                .withApplicableOn("14/04/2017")
                .build();

        CreateHolidayRequest createHolidayRequest = new CreateHolidayRequestBuilder()
                .withHoliday(holiday)
                .withRequestInfo(requestInfo)
                .build();

        Response response = new CommonMasterResource()
                .createHoliday(RequestHelper.getJsonString(createHolidayRequest));

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
