package tests.propertyTax.billingServices;

import builders.propertyTax.RequestInfoBuilder;
import builders.propertyTax.billingServices.BillingServiceSearchRequestBuilder;
import builders.propertyTax.billingServices.glcodesMaster.GlcodeMastersBuilder;
import builders.propertyTax.billingServices.glcodesMaster.GlcodesMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.billingServices.BillingServiceSearchRequest;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasters;
import entities.requests.propertyTax.billingServices.glcodesMaster.GlcodeMasterRequest;
import entities.responses.propertyTax.billingServices.glcodesMaster.GlcodesMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.GlcodesMasterResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class GlcodeMasterVerificationTest extends BaseAPITest{

    private GlcodeMasters[]  glcodes;
    private RequestInfo requestInfo;

    public GlcodeMasterVerificationTest(){
        glcodes = new GlcodeMasters[1];
    }

    @Test
    public void glcodeMasterTest() throws IOException {
        LoginAndLogoutHelper.login(NARASAPPA);                                                                        //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        for (int i=0;i<6;i++){
            GlcodesMasterResponse createObject =  createGlcodesMaster(i);                                            //Create
            searchGlcodesMaster(createObject);                                                                      //Search

            GlcodesMasterResponse updateObject = updateGlcodeMaster(createObject.getGlCodeMasters()[0].getId(),i);  //Update
            searchGlcodesMaster(updateObject);                                                                     //Search
       }

        LoginAndLogoutHelper.logout();                                                                            //Logout
    }

    private GlcodesMasterResponse updateGlcodeMaster(String id, int i) throws IOException {

        new APILogger().log("Update Glcode Master API is started --");
        glcodes = getGlcodeMaster(i);
        glcodes[0].setId(Integer.valueOf(id));
        GlcodeMasterRequest request = new GlcodesMasterRequestBuilder().withRequestInfo(requestInfo).withGlcodes(glcodes).build();

        Response response = new GlcodesMasterResource().update(RequestHelper.getJsonString(request));
        GlcodesMasterResponse responseObject = checkAssertsForCreate(request,response);
        new APILogger().log("Update Glcode Master API is Completed --");

        return responseObject;
    }

    private void searchGlcodesMaster(GlcodesMasterResponse createObject) throws IOException{

        new APILogger().log("Search Glcode Master is started --");
        BillingServiceSearchRequest request = new BillingServiceSearchRequestBuilder().withRequestInfo(requestInfo).build();

        Response responseForId = new GlcodesMasterResource().search(RequestHelper.getJsonString(request),"&id="+createObject.getGlCodeMasters()[0].getId());
        checkAssertsForSearch(createObject,responseForId);

        Response responseForTaxHead = new GlcodesMasterResource().search(RequestHelper.getJsonString(request),"&taxHead"+createObject.getGlCodeMasters()[0].getTaxHead());
        checkAssertsForSearch(createObject,responseForTaxHead);

        Response responseForGlcode = new GlcodesMasterResource().search(RequestHelper.getJsonString(request),"&taxHead"+createObject.getGlCodeMasters()[0].getGlCode());
        checkAssertsForSearch(createObject,responseForGlcode);
        new APILogger().log("Search Glcode Master is completed");
    }

    private void checkAssertsForSearch(GlcodesMasterResponse createObject, Response response) throws IOException{
        GlcodesMasterResponse responseObject = (GlcodesMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),GlcodesMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        Assert.assertEquals(responseObject.getGlCodeMasters()[0].getGlCode(),createObject.getGlCodeMasters()[0].getGlCode());
        Assert.assertEquals(responseObject.getGlCodeMasters()[0].getTaxHead(),createObject.getGlCodeMasters()[0].getTaxHead());
        Assert.assertEquals(responseObject.getGlCodeMasters()[0].getId(),createObject.getGlCodeMasters()[0].getId());
    }

    private GlcodesMasterResponse createGlcodesMaster(int i) throws IOException {

        new APILogger().log("Create Glcode Master is started --");
        GlcodeMasterRequest request = new GlcodesMasterRequestBuilder().withRequestInfo(requestInfo).withGlcodes(getGlcodeMaster(i)).build();

        Response response = new GlcodesMasterResource().create(RequestHelper.getJsonString(request));
        Assert.assertEquals(response.getStatusCode(),201);
        GlcodesMasterResponse responseObject = checkAssertsForCreate(request,response);
        new APILogger().log("Create Glcode Master is completed --");

        return responseObject;
    }

    private GlcodeMasters[] getGlcodeMaster(int i){

        switch (i){

            case 0:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("PT_TAX").withGlcode("1100101").build();
                break;

            case 1:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("EMP_GUA_TAX").withGlcode("3503001").build();
                break;

            case 2:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("EDU_CESS").withGlcode("3503002").build();
                break;

            case 3:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("LATE_PENALTY").withGlcode("1402002").build();
                break;

            case 4:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("ADVANCE").withGlcode("3504102").build();
                break;

            case 5:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("CHQ_BOUNCE_PENALTY").withGlcode("1808002").build();
                break;

            case 6:

                glcodes[0] = new GlcodeMastersBuilder().withTaxHead("LIB_CESS").withGlcode("3503002").build();
                break;
        }
        return glcodes;
    }

    private GlcodesMasterResponse checkAssertsForCreate(GlcodeMasterRequest request, Response response) throws IOException {
        GlcodesMasterResponse responseObject = (GlcodesMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),GlcodesMasterResponse.class);

        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        Assert.assertEquals(request.getGlcodeMasters()[0].getService(),responseObject.getGlCodeMasters()[0].getService());
        Assert.assertEquals(request.getGlcodeMasters()[0].getGlCode(),responseObject.getGlCodeMasters()[0].getGlCode());
        Assert.assertEquals(request.getGlcodeMasters()[0].getTaxHead(),responseObject.getGlCodeMasters()[0].getTaxHead());

        return responseObject;
    }
}
