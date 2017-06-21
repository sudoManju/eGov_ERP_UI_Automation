package tests.pgrCollection;

import builders.pgrCollection.fetchComplaints.FetchComplaintRequestBuilder;
import builders.pgrCollection.fetchComplaints.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.fetchComplaints.FetchComplaintRequest;
import entities.requests.pgrCollections.fetchComplaints.RequestInfo;
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

public class FetchComplaintTypeTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void FetchComplaintsTest() throws IOException {
        LoginAndLogoutHelper.login1(NARASAPPA); //Login Test
        fetchAllComplaints(); // Fetch All Complaints
        fetchComplaintById(); // Fetch Complaint By Id
        LoginAndLogoutHelper.logout1(); // Logout
    }

    private void fetchComplaintById() {
        new APILogger().log("Fetch Complaint by IDS Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        FetchComplaintRequest request = new FetchComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().fetchComplaintByIdResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Fetch Complaint by IDS Test is Completed ---");
    }

    private void fetchAllComplaints() {
        new APILogger().log("Fetch All Complaints Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        FetchComplaintRequest request = new FetchComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new PGRResource().fetchAllComplaintsResource(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Fetch All Complaints Test is Completed ---");
    }
}
