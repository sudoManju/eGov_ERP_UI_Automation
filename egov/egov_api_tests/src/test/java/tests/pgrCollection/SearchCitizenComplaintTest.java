package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.searchCitizenComplaint.SearchCitizenComplaintResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Properties;
import utils.ResponseHelper;

import java.io.IOException;

public class SearchCitizenComplaintTest extends BaseAPITest {

    @Test
    public void searchCitizenComplaint() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod(Properties.devServerUrl, "narasappa");

        // Search Citizen Complaint Test
        searchCitizenComplaintTestMethod(loginResponse);
    }

    private void searchCitizenComplaintTestMethod(LoginResponse loginResponse) throws IOException {

        Response response = new PGRResource().getSearchCitizenComplaints(loginResponse);

        SearchCitizenComplaintResponse searchCitizenComplaintResponse = (SearchCitizenComplaintResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchCitizenComplaintResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(searchCitizenComplaintResponse.getService_requests().length , 103);
        new APILogger().log("Search Citizen Complaints for PGR is Completed  -- ");
    }

}
