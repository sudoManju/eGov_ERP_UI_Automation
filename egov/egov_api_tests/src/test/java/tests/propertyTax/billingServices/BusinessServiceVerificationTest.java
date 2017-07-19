package tests.propertyTax.billingServices;

import builders.propertyTax.billingServices.RequestInfoBuilder;
import builders.propertyTax.billingServices.businessServiceMaster.BusinessServiceDetailsBuilder;
import builders.propertyTax.billingServices.businessServiceMaster.BusinessServiceMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.businessServiceMaster.BusinessServiceDetails;
import entities.requests.propertyTax.billingServices.businessServiceMaster.BusinessServiceMasterRequest;
import entities.responses.propertyTax.billingServices.businessService.BusinessServiceMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.BusinessServiceMasterResource;
import tests.BaseAPITest;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class BusinessServiceVerificationTest extends BaseAPITest{

      BusinessServiceDetails[] details;
      RequestInfo requestInfo;

    public BusinessServiceVerificationTest(){
        details = new BusinessServiceDetails[1];
    }

    @Test
    public void businessServiceTest() throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);           //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        createBusinessServiceMaster();                  //Create
    }

    private void createBusinessServiceMaster() throws IOException {

        details[0] = new BusinessServiceDetailsBuilder().build();
        BusinessServiceMasterRequest request = new BusinessServiceMasterRequestBuilder().withRequestInfo(requestInfo)
                .withBusinessServiceDetails(details).build();

        Response response = new BusinessServiceMasterResource().create(RequestHelper.getJsonString(request));
        checkAsserts(request,response);
    }

    private void checkAsserts(BusinessServiceMasterRequest request, Response response) throws IOException {
        BusinessServiceMasterResponse responseObject = (BusinessServiceMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),BusinessServiceMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),201);
    }
}
