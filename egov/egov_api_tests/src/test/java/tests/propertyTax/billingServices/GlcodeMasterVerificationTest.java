package tests.propertyTax.billingServices;

import builders.propertyTax.billingServices.glcodesMaster.GlcodeMastersBuilder;
import builders.propertyTax.billingServices.glcodesMaster.GlcodesMasterRequestBuilder;
import builders.propertyTax.billingServices.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasters;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodesMasterRequest;
import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.responses.propertyTax.billingServices.glcodesMaster.GlcodesMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.GlcodesMasterResource;
import tests.BaseAPITest;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class GlcodeMasterVerificationTest extends BaseAPITest{

    GlcodeMasters[]  glcodes;
    RequestInfo requestInfo;

    public GlcodeMasterVerificationTest(){
        glcodes = new GlcodeMasters[6];
    }

    @Test
    public void glcodeMasterTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);       //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        createGlcodesMaster();                      //Create
    }

    private void createGlcodesMaster() throws IOException {
        glcodes[0] = new GlcodeMastersBuilder().withTaxHead("PT_TAX").withGlcode("1100101").build();
        glcodes[1] = new GlcodeMastersBuilder().withTaxHead("EMP_GUA_TAX").withGlcode("3503001").build();
        glcodes[2] = new GlcodeMastersBuilder().withTaxHead("EDU_CESS").withGlcode("3503002").build();
        glcodes[3] = new GlcodeMastersBuilder().withTaxHead("LATE_PENALTY").withGlcode("1402002").build();
        glcodes[4] = new GlcodeMastersBuilder().withTaxHead("ADVANCE").withGlcode("3504102").build();
        glcodes[5] = new GlcodeMastersBuilder().withTaxHead("CHQ_BOUNCE_PENALTY").withGlcode("1808002").build();
        GlcodesMasterRequest request = new GlcodesMasterRequestBuilder().withRequestInfo(requestInfo).withGlcodes(glcodes).build();

        Response response = new GlcodesMasterResource().create(RequestHelper.getJsonString(request));
        checkAsserts(request,response);
    }

    private void checkAsserts(GlcodesMasterRequest request, Response response) throws IOException {
        GlcodesMasterResponse responseObject = (GlcodesMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),GlcodesMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        for(int i = 0; i<request.getGlcodeMasters().length; i++){
            String name = request.getGlcodeMasters()[i].getTaxHead();
            for(int j=0;j<responseObject.getGlCodeMasters().length;j++){
                if(name.equals(responseObject.getGlCodeMasters()[j].getTaxHead())){
                    Assert.assertEquals(request.getGlcodeMasters()[i].getService(),responseObject.getGlCodeMasters()[j].getService());
                    Assert.assertEquals(request.getGlcodeMasters()[i].getGlCode(),responseObject.getGlCodeMasters()[j].getGlCode());
                    Assert.assertEquals(request.getGlcodeMasters()[i].getTaxHead(),responseObject.getGlCodeMasters()[j].getTaxHead());
                    break;
                }
            }
        }
    }

}
