package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.department.CreateDepartmentRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.department.CreateDepartmentRequest;
import entities.responses.propertyTax.masters.department.create.DepartmentsMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.DepartmentsMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class DepartmentVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void DepartmentMasterTest() throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);         //Login

        DepartmentsMasterResponse create = createDepartmentMaster();   //Create

        SearchHelper helper = new SearchHelper();

        helper.searchDepartmentMaster(create);     //Search

        LoginAndLogoutHelper.logout1();  //Logout
    }

    private DepartmentsMasterResponse createDepartmentMaster() throws IOException {
        new APILogger().log("Create Department Master is Started");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateDepartmentRequest request = new CreateDepartmentRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new DepartmentsMasterResource().create(RequestHelper.getJsonString(request));
        DepartmentsMasterResponse response1 = (DepartmentsMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),DepartmentsMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        Assert.assertEquals(response1.getDepartments()[0].getName(),request.getDepartments()[0].getName());
        Assert.assertEquals(response1.getDepartments()[0].getCode(),request.getDepartments()[0].getCode());
        Assert.assertEquals(response1.getDepartments()[0].getNameLocal(),request.getDepartments()[0].getNameLocal());
        new APILogger().log("Create Department Master is Completed as");

        return response1;
    }
}
