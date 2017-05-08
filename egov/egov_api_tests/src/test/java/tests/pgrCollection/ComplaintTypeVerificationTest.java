package tests.pgrCollection;

import builders.pgrCollection.complaintType.ComplaintTypeRequestBuilder;
import builders.pgrCollection.complaintType.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.pgrCollections.complaintType.ComplaintTypeRequest;
import entities.requests.pgrCollections.complaintType.RequestInfo;
import entities.responses.login.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

public class ComplaintTypeVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PGR, Categories.SANITY, Categories.QA})
    public void complaintType()throws IOException{

        //Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login("narasappa");

        //complaintTypeByServiceCode
        complaintTypeByServiceCode(loginResponse);

        //complaintTypeCategories
        complaintTypeCategories(loginResponse);

    }

    private void complaintTypeByServiceCode(LoginResponse loginResponse)throws IOException{

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        ComplaintTypeRequest request = new ComplaintTypeRequestBuilder().withRequestInfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new PGRResource().getComplaintTypeByServiceCode(json);

        Assert.assertEquals(response.getStatusCode(),200);
     }

     private void complaintTypeCategories(LoginResponse loginResponse)throws IOException{

         RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

         ComplaintTypeRequest request = new ComplaintTypeRequestBuilder().withRequestInfo(requestInfo).build();

         String json = RequestHelper.getJsonString(request);

         Response response = new PGRResource().getComplaintCategories(json);

         Assert.assertEquals(response.getStatusCode(),200);
     }
}
