package tests.pgrCollection;

import builders.pgrCollection.fetchComplaints.FetchComplaintRequestBuilder;
import builders.pgrCollection.fetchComplaints.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.fetchComplaints.FetchComplaintRequest;
import entities.requests.pgrCollections.fetchComplaints.RequestInfo;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

public class FetchComplaintTypeTest extends BaseAPITest{

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.DEV})
    public void FeatchComplaintType()throws IOException{

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        //Fetch All Complaint
        fetchAllComplaints(loginResponse);

        //Fetch Complaint By Id
        fetchComplaintById(loginResponse);
    }

    private void fetchComplaintById(LoginResponse loginResponse) {
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        FetchComplaintRequest request = new FetchComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        String  json = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getFetchComplaintById(json);

        Assert.assertEquals(response.getStatusCode(),200);
    }

    private void fetchAllComplaints(LoginResponse loginResponse) {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        FetchComplaintRequest request = new FetchComplaintRequestBuilder().withRequestInfo(requestInfo).build();

        String  json = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getFetchComplaint(json);

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
