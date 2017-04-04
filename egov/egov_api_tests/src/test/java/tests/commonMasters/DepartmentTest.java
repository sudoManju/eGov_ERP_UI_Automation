package tests.commonMasters;

import builders.commonMaster.CommonMasterRequestBuilder;
import builders.commonMaster.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.commonMasters.CommonMasterRequest;
import entities.requests.commonMasters.RequestInfo;
import entities.responses.commonMaster.department.DepartmentResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.CommonMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

public class DepartmentTest extends BaseAPITest {

    @Test(groups = {Categories.HR, Categories.SANITY, Categories.DEV})
    public void departmentTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Search Department Test
        departmentTestMethod(loginResponse);
    }

    private void departmentTestMethod(LoginResponse loginResponse) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        CommonMasterRequest commonMasterRequest = new CommonMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        String jsonString = RequestHelper.getJsonString(commonMasterRequest);

        Response response = new CommonMasterResource().searchDepartmentTest(jsonString);

        DepartmentResponse departmentResponse = (DepartmentResponse)
                ResponseHelper.getResponseAsObject(response.asString(), DepartmentResponse.class);

        Assert.assertEquals(departmentResponse.getDepartment().length, 10);
        Assert.assertEquals(response.getStatusCode(), 200);

        new APILogger().log("Search Department Test is Completed --");
    }
}
