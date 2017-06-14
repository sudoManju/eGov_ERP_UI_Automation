package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.department.DepartmentResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class DepartmentTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.PILOT})
    public void departmentTest() throws IOException {
        String sessionId = LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        searchDepartment(sessionId);  // Search Department
    }

    private void searchDepartment(String sessionId) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new CommonMasterResource()
                .searchDepartmentResource(RequestHelper.getJsonString(commonMasterRequest), sessionId);
        DepartmentResponse departmentResponse = (DepartmentResponse)
                ResponseHelper.getResponseAsObject(response.asString(), DepartmentResponse.class);

        Assert.assertTrue(departmentResponse.getDepartment().length > 0);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Department Test is Completed --");
        pilotLogoutService(sessionId); // Logout
    }
}
