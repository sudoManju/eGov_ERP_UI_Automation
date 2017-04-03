package tests.eGovEIS;

import builders.eGovEIS.createEmployee.CreateEmployeeRequestBuilder;
import builders.eGovEIS.createEmployee.RequestInfoBuilder;
import entities.requests.eGovEIS.createEmployee.CreateEmployeeRequest;
import entities.requests.eGovEIS.createEmployee.RequestInfo;
import entities.responses.login.LoginResponse;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;

public class CreateEmployeeTest extends BaseAPITest {

    public void createEmployeeInEIS() throws IOException
    {
        LoginResponse loginResponse = loginTestMethod("narasappa");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();
        CreateEmployeeRequest request = new CreateEmployeeRequestBuilder().withRequestInfo(requestInfo).build();

        String JsonData = RequestHelper.getJsonString(request);
        System.out.println(JsonData);
    }
}