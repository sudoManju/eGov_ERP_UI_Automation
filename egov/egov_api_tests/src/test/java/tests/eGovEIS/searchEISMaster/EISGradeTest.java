package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.grade.SearchGradeResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISGradeTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchGradeTest() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchGrade(); // Search Grade
        pilotLogoutService(); // Logout
    }

    private void searchGrade() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new EISMasterResource().
                searchGradeResource(RequestHelper.getJsonString(searchEmployeeMasterRequest));
        SearchGradeResponse searchGradeResponse = (SearchGradeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchGradeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchGradeResponse.getGrade().length > 0);
        new APILogger().log("Search Grade Test is Completed--");
    }

}
