package tests.propertyTax.billingServices;

import builders.propertyTax.RequestInfoBuilder;
import builders.propertyTax.billingServices.BillingServiceSearchRequestBuilder;
import builders.propertyTax.billingServices.taxHeadMaster.GlcodesBuilder;
import builders.propertyTax.billingServices.taxHeadMaster.TaxHeadMasterRequestBuilder;
import builders.propertyTax.billingServices.taxHeadMaster.TaxHeadMastersBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.billingServices.BillingServiceSearchRequest;
import entities.requests.propertyTax.billingServices.taxHeadMaster.Glcodes;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasterRequest;
import entities.requests.propertyTax.billingServices.taxHeadMaster.TaxHeadMasters;
import entities.responses.propertyTax.billingServices.taHeadMaster.TaxHeadMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.TaxHeadMasterResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.ConstantData.serviceName;
import static data.UserData.NARASAPPA;

public class TaxHeadMasterVerificationTest extends BaseAPITest {

    private Glcodes[] glcodes;
    private TaxHeadMasters[] taxHeadMasters;
    private RequestInfo requestInfo;

    public TaxHeadMasterVerificationTest(){
        glcodes = new Glcodes[1];
        taxHeadMasters = new TaxHeadMasters[1];
    }

    @Test
    public void taxHeadMasterTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);                                                            //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        for (int i=0;i<7;i++) {
            TaxHeadMasterResponse createObject = create(i);                                              // Create
            search(createObject);                                                                        // Search

            TaxHeadMasterResponse updateObject = update(createObject.getTaxHeadMasters()[0].getId(),i);  //Update
            search(updateObject);                                                                        //Search
        }

        LoginAndLogoutHelper.logout();                                                                   //Logout
    }

    public TaxHeadMasterResponse getTaxHeadDetails(int i,String service) throws IOException {
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        return createForDemand(i,service);         //Create
    }

    private TaxHeadMasterResponse update(String id, int i) throws IOException {

        new APILogger().log("Update TaxHead Master is started --");
        taxHeadMasters = getTaxHeadMasters(i);
        taxHeadMasters[0].setId(Integer.valueOf(id));
        taxHeadMasters[0].setService(serviceName);
        taxHeadMasters[0].getGlcodes()[0].setService(serviceName);

        TaxHeadMasterRequest request = new TaxHeadMasterRequestBuilder().withRequestInfo(requestInfo).withTaxHeadMaster(taxHeadMasters).build();

        Response response = new TaxHeadMasterResource().update(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(),200);
        TaxHeadMasterResponse responseObject = checkAssertsForCreate(request,response);
        new APILogger().log("Update TaxHead Master is completed --");

        return responseObject;
    }

    private TaxHeadMasters[] getTaxHeadMasters(int i){

        switch (i){

            case 0 :
                glcodes[0] = new GlcodesBuilder().withTaxHead("PT_TAX").withGlcode("1100101").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("PT_TAX").withName("Property Tax").withCategory("TAX").withGlcode(glcodes).build();

                break;

            case 1 :
                glcodes[0] = new GlcodesBuilder().withTaxHead("EMP_GUA_TAX").withGlcode("3503001").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("EMP_GUA_TAX").withName("Employee Guarantee Cess").withCategory("TAX").withGlcode(glcodes).build();

                break;

            case 2 :
                glcodes[0] = new GlcodesBuilder().withTaxHead("EDU_CESS").withGlcode("3503002").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("EDU_CESS").withName("Education Cess").withCategory("TAX").withGlcode(glcodes).build();

                break;

            case 3 :
                glcodes[0] = new GlcodesBuilder().withTaxHead("LATE_PENALTY").withGlcode("1402002").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("LATE_PENALTY").withName("Late Pay Penalty").withCategory("PENALTY").withGlcode(glcodes).build();

                break;

            case 4:
                glcodes[0] = new GlcodesBuilder().withTaxHead("ADVANCE").withGlcode("3504102").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("ADVANCE").withName("Advance Collection").withCategory("ADVANCE_COLLECTION").withGlcode(glcodes).build();

                break;

            case 5 :
                glcodes[0] = new GlcodesBuilder().withTaxHead("CHQ_BOUNCE_PENALTY").withGlcode("1808002").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("CHQ_BOUNCE_PENALTY").withName("Cheque Bounce Penalty").withCategory("FINES").withGlcode(glcodes).build();

                break;

            case 6 :
                glcodes[0] = new GlcodesBuilder().withTaxHead("LIB_CESS").withGlcode("3503002").build();
                taxHeadMasters[0] = new TaxHeadMastersBuilder()
                        .withCode("LIB_CESS").withName("Library Cess").withCategory("TAX").withGlcode(glcodes).build();
        }

        return taxHeadMasters;
    }

    private void search(TaxHeadMasterResponse createObject) throws IOException {
        new APILogger().log("Search TaxHead Master is started for "+createObject.getTaxHeadMasters()[0].getName());
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        BillingServiceSearchRequest request = new BillingServiceSearchRequestBuilder().withRequestInfo(requestInfo).build();

        Response responseForId = new TaxHeadMasterResource().search(RequestHelper.getJsonString(request),"&id="+createObject.getTaxHeadMasters()[0].getId());
        checkAssertsForSearch(createObject,responseForId);

        Response responseForName = new TaxHeadMasterResource().search(RequestHelper.getJsonString(request),"&name="+createObject.getTaxHeadMasters()[0].getName());
        checkAssertsForSearch(createObject,responseForName);

        Response responseForCode = new TaxHeadMasterResource().search(RequestHelper.getJsonString(request),"&code="+createObject.getTaxHeadMasters()[0].getCode());
        checkAssertsForSearch(createObject,responseForCode);

        Response responseForCategory = new TaxHeadMasterResource().search(RequestHelper.getJsonString(request),"&category="+createObject.getTaxHeadMasters()[0].getCategory());
        checkAssertsForSearch(createObject,responseForCategory);
        new APILogger().log("Search TaxHead Master is completed for "+createObject.getTaxHeadMasters()[0].getName());
    }

    private void checkAssertsForSearch(TaxHeadMasterResponse createObject, Response response) throws IOException {
        TaxHeadMasterResponse responseObject = (TaxHeadMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),TaxHeadMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        Assert.assertEquals(responseObject.getTaxHeadMasters()[0].getCategory(),createObject.getTaxHeadMasters()[0].getCategory());
        Assert.assertEquals(responseObject.getTaxHeadMasters()[0].getName(),createObject.getTaxHeadMasters()[0].getName());
        Assert.assertEquals(responseObject.getTaxHeadMasters()[0].getCode(),createObject.getTaxHeadMasters()[0].getCode());
    }

    private TaxHeadMasterResponse create(int i) throws IOException {
        new APILogger().log("Create TaxHead Master is started");
        TaxHeadMasterRequest request = new TaxHeadMasterRequestBuilder().withRequestInfo(requestInfo).withTaxHeadMaster(getTaxHeadMasters(i)).build();

        Response response = new TaxHeadMasterResource().create(RequestHelper.getJsonString(request));

        TaxHeadMasterResponse responseObject = checkAssertsForCreate(request,response);
        Assert.assertEquals(response.getStatusCode(),201);
        new APILogger().log("Create TaxHead Master is completed for "+responseObject.getTaxHeadMasters()[0].getName());

        return responseObject;
    }

    private TaxHeadMasterResponse createForDemand(int i,String service) throws IOException{
        new APILogger().log("Create TaxHead Master is started");
        taxHeadMasters = getTaxHeadMasters(i);
        taxHeadMasters[0].setService(service);
        String time = get5DigitRandomInt()+get3DigitRandomInt();
        taxHeadMasters[0].getGlcodes()[0].setService(service);
        taxHeadMasters[0].setValidFrom(time);
        taxHeadMasters[0].setValidTill(time);
        taxHeadMasters[0].setName(service);
        TaxHeadMasterRequest request = new TaxHeadMasterRequestBuilder().withRequestInfo(requestInfo).withTaxHeadMaster(taxHeadMasters).build();

        Response response = new TaxHeadMasterResource().create(RequestHelper.getJsonString(request));

        TaxHeadMasterResponse responseObject = checkAssertsForCreate(request,response);
        Assert.assertEquals(response.getStatusCode(),201);
        new APILogger().log("Create TaxHead Master is completed for "+responseObject.getTaxHeadMasters()[0].getName());

        return responseObject;
    }

    private TaxHeadMasterResponse checkAssertsForCreate(TaxHeadMasterRequest request, Response response) throws IOException {
        TaxHeadMasterResponse responseObject = (TaxHeadMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),TaxHeadMasterResponse.class);

        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        Assert.assertEquals(request.getTaxHeadMasters()[0].getService(),responseObject.getTaxHeadMasters()[0].getService());
        Assert.assertEquals(request.getTaxHeadMasters()[0].getCode(),responseObject.getTaxHeadMasters()[0].getCode());
        Assert.assertEquals(request.getTaxHeadMasters()[0].getCategory(),responseObject.getTaxHeadMasters()[0].getCategory());
        return responseObject;
    }
}
