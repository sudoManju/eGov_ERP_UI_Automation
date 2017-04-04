package tests.pgrCollection;

import com.jayway.restassured.response.Response;
import entities.responses.login.LoginResponse;
import entities.responses.pgrCollections.PGRApplicationStatusResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.Categories;
import utils.ResponseHelper;

import java.io.IOException;

public class PGRApplicationStatusTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.PGR, Categories.QA})
    public void pgrApplicationStatus() throws IOException {

        // Login Test
        LoginResponse loginResponse = loginTestMethod("narasappa");

        // Get PGR Application Status
        pgrApplicationStatusTestMethod(loginResponse);
    }

    private void pgrApplicationStatusTestMethod(LoginResponse loginResponse) throws IOException {
        Response response = new PGRResource().getPGRApplicationStatus(loginResponse);

        PGRApplicationStatusResponse[] pgrApplicationStatusResponse = (PGRApplicationStatusResponse[]) ResponseHelper
                .getResponseAsObject(response.asString(), PGRApplicationStatusResponse[].class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(pgrApplicationStatusResponse.length, 9);

        new APILogger().log("Get All Application Status for PGR is Completed  -- ");
    }
}
