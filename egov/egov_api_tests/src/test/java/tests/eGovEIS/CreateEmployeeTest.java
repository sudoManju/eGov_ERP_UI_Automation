package tests.eGovEIS;

import entities.responses.login.LoginResponse;
import tests.BaseAPITest;

import java.io.IOException;

public class CreateEmployeeTest extends BaseAPITest {


    public void createEmployeeInEIS() throws IOException {
        LoginResponse loginResponse = loginTestMethod("narasappa");
    }
}
