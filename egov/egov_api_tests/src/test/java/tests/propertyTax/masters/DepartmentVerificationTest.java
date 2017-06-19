package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.department.CreateDepartmentRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.department.CreateDepartmentRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.department.create.DepartmentsMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.DepartmentsMasterResource;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class DepartmentVerificationTest {

    @Test
    public void DepartmentMasterTest() throws IOException{

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        DepartmentsMasterResponse create = createDepartmentMaster(loginResponse);

        SearchHelper helper = new SearchHelper(loginResponse);

        helper.searchDepartmentMaster(create);
    }

    private DepartmentsMasterResponse createDepartmentMaster(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create Department Master is started --");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateDepartmentRequest request = new CreateDepartmentRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new DepartmentsMasterResource().createDepartment(RequestHelper.getJsonString(request));

        Assert.assertEquals(response.getStatusCode(),200);

        DepartmentsMasterResponse response1 = (DepartmentsMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),DepartmentsMasterResponse.class);

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        Assert.assertEquals(response1.getDepartments()[0].getName(),request.getDepartments()[0].getName());
        Assert.assertEquals(response1.getDepartments()[0].getCode(),request.getDepartments()[0].getCode());
        Assert.assertEquals(response1.getDepartments()[0].getNameLocal(),request.getDepartments()[0].getNameLocal());

        new APILogger().log("Create Department Master is completed as --");

        return response1;
    }
}
