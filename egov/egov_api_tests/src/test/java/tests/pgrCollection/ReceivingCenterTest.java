package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.ReceivingCenterResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.LoginHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class ReceivingCenterTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.PGR, Categories.QA})
    public void receivingCenter() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginHelper.loginTestMethod("narasappa");

        // Receiving Center Test
        receivingCenterTestMethod(loginResponse);
    }

    private void receivingCenterTestMethod(LoginResponse loginResponse) throws IOException {

        Response response = new PGRResource().getReceivingCenter(loginResponse);
        ReceivingCenterResponse[] receivingCenterResponse = (ReceivingCenterResponse[]) ResponseHelper
                .getResponseAsObject(response.asString(), ReceivingCenterResponse[].class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(receivingCenterResponse.length, 10);

        new APILogger().log("Receiving Centers for PGR is Completed  -- ");
    }
}
