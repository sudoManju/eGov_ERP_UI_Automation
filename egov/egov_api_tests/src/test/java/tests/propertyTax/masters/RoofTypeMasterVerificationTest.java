package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.roofType.RoofTypeMasterRequest;
import entities.requests.propertyTax.masters.roofType.RoofTypes;
import org.testng.annotations.Test;
import tests.BaseAPITest;
import utils.Categories;
import utils.LoginAndLogoutHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class RoofTypeMasterVerificationTest extends BaseAPITest {

    RoofTypes[] roofTypes;
    RequestInfo requestInfo;
    SearchHelper helper;

    public RoofTypeMasterVerificationTest(){
        roofTypes = new RoofTypes[1];
    }

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void roofTypesTest() throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        helper = new SearchHelper();
        createRoofTypesMaster();

        updateRoofTypesMaster();
    }

    private void updateRoofTypesMaster() {
    }

    private void createRoofTypesMaster() {
    }

    private void checkAsserts(RoofTypeMasterRequest request, Response response){}
}
