package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.designationType.SearchDesignationResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class EISDesignationsTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void searchEISDesignationTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchEISDesignation(sessionId); // Search EIS Designation Test
    }

    private void searchEISDesignation(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EISMasterResource().
                searchDesignationResource(RequestHelper.getJsonString(searchEmployeeMasterRequest), sessionId);
        SearchDesignationResponse searchEmployeeTypeResponse = (SearchDesignationResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchDesignationResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchEmployeeTypeResponse.getDesignation().length > 0);
        new APILogger().log("Search Designation Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
