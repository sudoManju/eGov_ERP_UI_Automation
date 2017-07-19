package tests.propertyTax.billingServices;

import builders.propertyTax.billingServices.RequestInfoBuilder;
import builders.propertyTax.billingServices.taxPeriodMaster.TaxPeriodMasterRequestBuilder;
import builders.propertyTax.billingServices.taxPeriodMaster.TaxPeriodsBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.billingServices.RequestInfo;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriodMasterRequest;
import entities.requests.propertyTax.billingServices.taxPeriodMaster.TaxPeriods;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.TaxPeriodMasterResource;
import tests.BaseAPITest;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class TaxPeriodMasterVerificationTest extends BaseAPITest {

    TaxPeriods[] taxPeriods;
    RequestInfo requestInfo;

    public TaxPeriodMasterVerificationTest(){
        taxPeriods = new TaxPeriods[2];
    }

    @Test
    public void taxPeriodsTest() throws IOException{

        LoginAndLogoutHelper.login(NARASAPPA);     //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        createTaxPeriods();                       //Create
    }

    private void createTaxPeriods() throws IOException{

        int year = 1960;
        int year1 = 61;
        String time1 = "00:00:00"; String time2 = "23:59:59";
        String date1 = "-04-01"; String date2 = "-10-01";
        String date3 = "-09-30"; String date4 = "-03-31";

        for(int i=0;i<140;i++){

            taxPeriods[0] = new TaxPeriodsBuilder().withCode(String.valueOf(year)+"-"+String.valueOf(year+1)+"-"+"1")
                                .withFinancialYear(String.valueOf(year)+"-"+String.valueOf(year1))
                                .withFromDate(String.valueOf(year)+date1+" "+time1)
                                .withToDate(String.valueOf(year)+date3+" "+time2).build();

            taxPeriods[1] = new TaxPeriodsBuilder().withCode(String.valueOf(year)+"-"+String.valueOf(year+1)+"-"+"2")
                    .withFinancialYear(String.valueOf(year)+"-"+String.valueOf(year1))
                    .withFromDate(String.valueOf(year)+date2+" "+time1)
                    .withToDate(String.valueOf(year+1)+date4+" "+time2).build();

            TaxPeriodMasterRequest request = new TaxPeriodMasterRequestBuilder().withRequestInfo(requestInfo).withTaxPeriods(taxPeriods).build();
            Response response = new TaxPeriodMasterResource().create(RequestHelper.getJsonString(request));

            year = year +1;
            year1 = year1+1;
        }
    }
}
