package tests.propertyTax.billingServices;

import builders.propertyTax.billingServices.BillingServiceSearchRequestBuilder;
import builders.propertyTax.billingServices.RequestInfoBuilder;
import builders.propertyTax.billingServices.taxPeriodMaster.TaxPeriodsMasterRequestBuilder;
import builders.propertyTax.billingServices.taxPeriodMaster.TaxPeriodsBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.billingServices.BillingServiceSearchRequest;
import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriodsMasterRequest;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriods;
import entities.responses.propertyTax.billingServices.taxPeriodsMaster.TaxPeriodsMasterResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.TaxPeriodsMasterResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static data.UserData.NARASAPPA;

public class TaxPeriodMasterVerificationTest extends BaseAPITest {

    private TaxPeriods[] taxPeriods;
    private RequestInfo requestInfo;

    private int year = 1960;
    private int year1 = 61;
    private String time1 = "00:00:00";
    private String time2 = "23:59:59";
    private String date1 = "-04-01"; String date2 = "-10-01";
    private String date3 = "-09-30"; String date4 = "-03-31";

    public TaxPeriodMasterVerificationTest(){
        taxPeriods = new TaxPeriods[2];
    }

    @Test
    public void taxPeriodsTest() throws IOException, ParseException {

        LoginAndLogoutHelper.login(NARASAPPA);                                 //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        for (int i = 0; i < 2; i++) {
            TaxPeriodsMasterResponse createObject =  createTaxPeriods();      //Create
            searchTaxPeriods(createObject);                                   //Search

            year = year +1;
            year1 = year1+1;
        }
        LoginAndLogoutHelper.logout();                                       //Logout
    }

    private void searchTaxPeriods(TaxPeriodsMasterResponse createObject) throws IOException {

        new APILogger().log("Search TaxPeriods Master is started --");
        BillingServiceSearchRequest request = new BillingServiceSearchRequestBuilder().withRequestInfo(requestInfo).build();

        Response responseForId1 = new TaxPeriodsMasterResource().search(RequestHelper.getJsonString(request),"&id="+createObject.getTaxPeriods()[0].getId());
        checkAssertsForSearch(createObject,responseForId1);

        Response responseForId2 = new TaxPeriodsMasterResource().search(RequestHelper.getJsonString(request),"&id="+createObject.getTaxPeriods()[1].getId());
        checkAssertsForSearch(createObject,responseForId2);

        Response responseForCode1 = new TaxPeriodsMasterResource().search(RequestHelper.getJsonString(request),"&code="+createObject.getTaxPeriods()[0].getCode());
        checkAssertsForSearch(createObject,responseForCode1);

        Response responseForCode2 = new TaxPeriodsMasterResource().search(RequestHelper.getJsonString(request),"&code="+createObject.getTaxPeriods()[1].getCode());
        checkAssertsForSearch(createObject,responseForCode2);
        new APILogger().log("Search TaxPeriods Master is Completed --");
    }

    private void checkAssertsForSearch(TaxPeriodsMasterResponse createObject, Response response) throws IOException {
        TaxPeriodsMasterResponse responseObject = (TaxPeriodsMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),TaxPeriodsMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        String code = responseObject.getTaxPeriods()[0].getCode();
        for (int i=0;i<createObject.getTaxPeriods().length;i++){
             if(code.equals(createObject.getTaxPeriods()[i].getCode())){
                 Assert.assertEquals(createObject.getTaxPeriods()[i].getId(),responseObject.getTaxPeriods()[0].getId());
                 Assert.assertEquals(createObject.getTaxPeriods()[i].getFinancialYear(),responseObject.getTaxPeriods()[0].getFinancialYear());
                 Assert.assertEquals(createObject.getTaxPeriods()[i].getCode(),responseObject.getTaxPeriods()[0].getCode());
                 Assert.assertEquals(createObject.getTaxPeriods()[i].getPeriodCycle(),responseObject.getTaxPeriods()[0].getPeriodCycle());
                 Assert.assertEquals(createObject.getTaxPeriods()[i].getService(),responseObject.getTaxPeriods()[0].getService());
             }
        }
    }

    private TaxPeriodsMasterResponse createTaxPeriods() throws IOException, ParseException {

            new APILogger().log("Create TaxPeriods Master is started --");
            taxPeriods[0] = new TaxPeriodsBuilder().withCode(String.valueOf(year)+"-"+String.valueOf(year+1)+"-"+"1")
                                .withFinancialYear(String.valueOf(year)+"-"+String.valueOf(year1))
                                .withFromDate(getTime(String.valueOf(year)+date1+" "+time1))
                                .withToDate(getTime(String.valueOf(year)+date3+" "+time2)).build();

            taxPeriods[1] = new TaxPeriodsBuilder().withCode(String.valueOf(year)+"-"+String.valueOf(year+1)+"-"+"2")
                    .withFinancialYear(String.valueOf(year)+"-"+String.valueOf(year1))
                    .withFromDate(getTime(String.valueOf(year)+date2+" "+time1))
                    .withToDate(getTime(String.valueOf(year+1)+date4+" "+time2)).build();

            TaxPeriodsMasterRequest request = new TaxPeriodsMasterRequestBuilder().withRequestInfo(requestInfo).withTaxPeriods(taxPeriods).build();

            Response response = new TaxPeriodsMasterResource().create(RequestHelper.getJsonString(request));
            TaxPeriodsMasterResponse responseObject =  checkAssertsForCreate(request,response);

            new APILogger().log("Create TaxPeriods Master is completed --");

        return responseObject;
    }

    private TaxPeriodsMasterResponse checkAssertsForCreate(TaxPeriodsMasterRequest request, Response response) throws IOException {
        TaxPeriodsMasterResponse responseObject = (TaxPeriodsMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),TaxPeriodsMasterResponse.class);

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"200");
        Assert.assertEquals(responseObject.getTaxPeriods()[0].getCode(),request.getTaxPeriods()[0].getCode());
        Assert.assertEquals(responseObject.getTaxPeriods()[1].getCode(),request.getTaxPeriods()[1].getCode());
        Assert.assertEquals(responseObject.getTaxPeriods()[0].getFinancialYear(),request.getTaxPeriods()[0].getFinancialYear());
        Assert.assertEquals(responseObject.getTaxPeriods()[1].getFinancialYear(),request.getTaxPeriods()[1].getFinancialYear());

        return responseObject;
    }

    private long getTime(String date1) throws ParseException {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = df.parse(date1);
       return date.getTime();
    }
}
