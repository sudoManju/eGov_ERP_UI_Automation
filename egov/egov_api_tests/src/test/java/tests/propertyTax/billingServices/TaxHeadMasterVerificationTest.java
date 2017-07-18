package tests.propertyTax.billingServices;

import builders.propertyTax.billingServices.taxHeadMaster.GlcodesBuilder;
import builders.propertyTax.billingServices.RequestInfoBuilder;
import builders.propertyTax.billingServices.taxHeadMaster.TaxHeadMasterRequestBuilder;
import builders.propertyTax.billingServices.taxHeadMaster.TaxHeadMastersBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.billingServices.taxHeadMaster.Glcodes;
import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasterRequest;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasters;
import entities.responses.propertyTax.billingServices.TaxHeadMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.TaxHeadMasterResource;
import tests.BaseAPITest;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.ConstantData.serviceName;
import static data.UserData.NARASAPPA;

public class TaxHeadMasterVerificationTest extends BaseAPITest {

    Glcodes[] glcodes;
    TaxHeadMasters[] taxHeadMasters;
    RequestInfo requestInfo;

    public TaxHeadMasterVerificationTest(){
        glcodes = new Glcodes[1];
        taxHeadMasters = new TaxHeadMasters[6];
    }

    @Test
    public void taxHeadMasterTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);             //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        createTaxHeadMaster();                             //Create
    }

    private void createTaxHeadMaster() throws IOException {

        glcodes[0] = new GlcodesBuilder().withTaxHead("PT_TAX").withGlcode("1100101").build();
        taxHeadMasters[0] = new TaxHeadMastersBuilder()
                .withCode("PT_TAX").withName("Property Tax").withCategory("TAX").withGlcode(glcodes).build();

        glcodes[0] = new GlcodesBuilder().withTaxHead("EMP_GUA_TAX").withGlcode("3503001").build();
        taxHeadMasters[1] = new TaxHeadMastersBuilder()
                .withCode("EMP_GUA_TAX").withName("Employee Guarantee Cess").withCategory("TAX").withGlcode(glcodes).build();

        glcodes[0] = new GlcodesBuilder().withTaxHead("EDU_CESS").withGlcode("3503002").build();
        taxHeadMasters[2] = new TaxHeadMastersBuilder()
                .withCode("EDU_CESS").withName("Education Cess").withCategory("TAX").withGlcode(glcodes).build();

        glcodes[0] = new GlcodesBuilder().withTaxHead("LATE_PENALTY").withGlcode("1402002").build();
        taxHeadMasters[3] = new TaxHeadMastersBuilder()
                .withCode("LATE_PENALTY").withName("Late Pay Penalty").withCategory("PENALTY").withGlcode(glcodes).build();

        glcodes[0] = new GlcodesBuilder().withTaxHead("ADVANCE").withGlcode("3504102").build();
        taxHeadMasters[4] = new TaxHeadMastersBuilder()
                .withCode("ADVANCE").withName("Advance Collection").withCategory("ADVANCE_COLLECTION").withGlcode(glcodes).build();

        glcodes[0] = new GlcodesBuilder().withTaxHead("CHQ_BOUNCE_PENALTY").withGlcode("1808002").build();
        taxHeadMasters[5] = new TaxHeadMastersBuilder()
                .withCode("CHQ_BOUNCE_PENALTY").withName("Cheque Bounce Penalty").withCategory("FINES").withGlcode(glcodes).build();
        TaxHeadMasterRequest request = new TaxHeadMasterRequestBuilder().withRequestInfo(requestInfo).withTaxHeadMaster(taxHeadMasters).build();

        Response response = new TaxHeadMasterResource().create(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(),201);
        checkAsserts(request,response);
    }

    private void checkAsserts(TaxHeadMasterRequest request, Response response) throws IOException {
        TaxHeadMasterResponse responseObject = (TaxHeadMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),TaxHeadMasterResponse.class);

        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        for(int i=0;i<request.getTaxHeadMasters().length;i++){
            String name = request.getTaxHeadMasters()[i].getService();
            for(int j=0;j<responseObject.getTaxHeadMasters().length;j++){
               if(name.equals(responseObject.getTaxHeadMasters()[j].getService())){
                   Assert.assertEquals(request.getTaxHeadMasters()[i].getService(),responseObject.getTaxHeadMasters()[j].getService());
                   Assert.assertEquals(request.getTaxHeadMasters()[i].getCode(),responseObject.getTaxHeadMasters()[j].getCode());
                   Assert.assertEquals(request.getTaxHeadMasters()[i].getCategory(),responseObject.getTaxHeadMasters()[j].getCategory());
                   Assert.assertEquals(request.getTaxHeadMasters()[i].getGlcodes()[0].getService(),responseObject.getTaxHeadMasters()[j].getGlCodes()[0].getService());
                   Assert.assertEquals(request.getTaxHeadMasters()[i].getGlcodes()[0].getTaxHead(),responseObject.getTaxHeadMasters()[j].getGlCodes()[0].getTaxHead());
                   Assert.assertEquals(request.getTaxHeadMasters()[i].getGlcodes()[0].getGlCode(),responseObject.getTaxHeadMasters()[j].getGlCodes()[0].getGlCode());
                   break;
               }
            }
        }
    }
}
