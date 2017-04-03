package tests.eGovEIS;

import entities.requests.eGovEIS.createEmployee.Employee;
import entities.responses.login.LoginResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Properties;

import java.io.IOException;

public class CreateEmployeeTest extends BaseAPITest {

    
    public void createEmployeeInEIS() throws IOException
    {
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");
    }
}
