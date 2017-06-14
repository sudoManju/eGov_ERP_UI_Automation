package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.recruitmentQuota.SearchRecruitmentQuotaResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISRecruitmentQuotaTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchRecruitmentQuotaTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchRecruitmentQuota(sessionId); // Search RecruitmentQuota
    }

    private void searchRecruitmentQuota(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EISMasterResource().
                searchRecruitmentQuotaResource(RequestHelper.getJsonString(searchEmployeeMasterRequest), sessionId);
        SearchRecruitmentQuotaResponse searchRecruitmentQuotaResponse = (SearchRecruitmentQuotaResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchRecruitmentQuotaResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchRecruitmentQuotaResponse.getRecruitmentQuota().length > 0);
        new APILogger().log("Search Recruitment Quota Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
