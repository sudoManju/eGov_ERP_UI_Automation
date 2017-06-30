package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.department.DepartmentMasterRequestBuilder;
import builders.propertyTax.masters.department.DepartmentsBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.department.DepartmentMasterRequest;
import entities.requests.propertyTax.masters.department.Departments;
import entities.responses.propertyTax.masters.department.create.DepartmentsMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.DepartmentsMasterResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class DepartmentVerificationTest extends BaseAPITest {

    Departments[] departments;
    RequestInfo requestInfo;
    SearchHelper helper;

    public DepartmentVerificationTest(){departments = new Departments[1];}

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void DepartmentMasterTest() throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);         //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new SearchHelper();
        DepartmentsMasterResponse create = createDepartmentMaster();   //Create
        helper.searchDepartmentMaster(create);     //Search

        DepartmentsMasterResponse update = updateDepartmentsMaster(create.getDepartments()[0].getId());   //Update
        helper.searchDepartmentMaster(update);   //Search
        LoginAndLogoutHelper.logout();  //Logout
    }

    private DepartmentsMasterResponse createDepartmentMaster() throws IOException {
        new APILogger().log("Create Department Master is Started");
        departments[0] = new DepartmentsBuilder().withName("Test_"+ get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test"+ get3DigitRandomInt()).build();
        DepartmentMasterRequest request = new DepartmentMasterRequestBuilder().withRequestInfo(requestInfo)
                .withDepartments(departments).build();

        Response response = new DepartmentsMasterResource().create(RequestHelper.getJsonString(request));
        DepartmentsMasterResponse responseObject = checkAsserts(request,response);
        new APILogger().log("Create Department Master is Completed");

        return responseObject;
    }

    private DepartmentsMasterResponse checkAsserts(DepartmentMasterRequest request, Response response) throws IOException {
        DepartmentsMasterResponse responseObject = (DepartmentsMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),DepartmentsMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"SUCCESSFUL");
        Assert.assertEquals(responseObject.getDepartments()[0].getName(),request.getDepartments()[0].getName());
        Assert.assertEquals(responseObject.getDepartments()[0].getCode(),request.getDepartments()[0].getCode());
        Assert.assertEquals(responseObject.getDepartments()[0].getNameLocal(),request.getDepartments()[0].getNameLocal());

        return responseObject;
    }

    private DepartmentsMasterResponse updateDepartmentsMaster(int id) throws IOException{
        new APILogger().log("Update Department Master is Started");
        departments[0] = new DepartmentsBuilder().withName("Test_"+ get3DigitRandomInt()).withCode(get3DigitRandomInt())
                .withNameLocal("Test"+ get3DigitRandomInt()).withId(id).build();
        DepartmentMasterRequest request = new DepartmentMasterRequestBuilder().withRequestInfo(requestInfo)
                .withDepartments(departments).build();

        Response response = new DepartmentsMasterResource().update(RequestHelper.getJsonString(request));
        DepartmentsMasterResponse responseObject = checkAsserts(request,response);
        Assert.assertEquals(responseObject.getDepartments()[0].getId(),id);
        new APILogger().log("Update Department Master is Completed");

        return responseObject;
    }
}
