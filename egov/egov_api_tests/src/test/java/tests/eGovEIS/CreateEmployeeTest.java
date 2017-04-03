package tests.eGovEIS;

import entities.responses.login.LoginResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Properties;

import java.io.IOException;

public class CreateEmployeeTest extends BaseAPITest {

    @Test
    public void createEmployeeInEIS() throws IOException
    {
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");


    }
}
