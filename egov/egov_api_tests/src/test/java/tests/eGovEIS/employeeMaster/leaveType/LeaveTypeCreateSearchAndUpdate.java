package tests.eGovEIS.employeeMaster.leaveType;

import entities.responses.login.LoginResponse;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;

import java.io.IOException;

public class LeaveType1CreateSearchAndUpdate extends BaseAPITest{

    @Test
    public void leaveTypeCreateSearchAndUpdateTest() throws IOException {

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        // Creating a Leave Type
        leaveTypeCreateTestMethod();
    }

    private void leaveTypeCreateTestMethod() {



    }

}
