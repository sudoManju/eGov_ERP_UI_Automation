package tests.pgrCollection;

import builders.pgrCollection.complaintType.ComplaintTypeRequestBuilder;
import builders.pgrCollection.complaintType.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.complaintType.ComplaintTypeRequest;
import entities.requests.pgrCollections.complaintType.RequestInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class ComplaintTypeVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void complaintTypeTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA); // Login
        complaintTypeByServiceCode(); // Complaint Type By Service Code
        complaintTypeCategories(); // Complaint Type By Categories
        LoginAndLogoutHelper.logout(); // Logout
    }

    private void complaintTypeByServiceCode() throws IOException {
        new APILogger().log("Get Complaint Type By Service Code Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        ComplaintTypeRequest request = new ComplaintTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().complaintTypeByServiceCodeResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Get Complaint Type By Service Code Test is Completed ---");
    }

    private void complaintTypeCategories() throws IOException {
        new APILogger().log("Get Complaint Type By Categories Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        ComplaintTypeRequest request = new ComplaintTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().complaintTypeCategoriesResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Get Complaint Type By Categories Test is Completed ---");
    }
}
