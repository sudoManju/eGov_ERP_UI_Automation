package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.recruitmentModes.SearchRecruitmentModesResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISRecruitmentModesTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchRecruitmentModesTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchRecruitmentModes(); // Search RecruitmentModes
        pilotLogoutService(); // Logout
    }

    private void searchRecruitmentModes() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EISMasterResource().
                searchRecruitmentModesResource(RequestHelper.getJsonString(searchEmployeeMasterRequest));
        SearchRecruitmentModesResponse searchRecruitmentModesResponse = (SearchRecruitmentModesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchRecruitmentModesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchRecruitmentModesResponse.getRecruitmentMode().length > 0);
        new APILogger().log("Search Recruitment Modes Test is Completed--");
    }

}
