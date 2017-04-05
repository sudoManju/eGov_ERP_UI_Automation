package tests.pgrCollection;


import com.jayway.restassured.response.Response;
import entities.responses.pgrCollections.ReceivingModesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.PGRResource;
import utils.ResponseHelper;

import java.io.IOException;

public class ReceivingModesTest {

    @Test
    public void receivingModesTest() throws IOException{

        Response response = new PGRResource().getReceivingModes();

        Assert.assertEquals(response.getStatusCode(),200);

        ReceivingModesResponse[] receivingModesResponses = (ReceivingModesResponse[])
                ResponseHelper.getResponseAsObject(response.asString(), ReceivingModesResponse[].class);

        Assert.assertEquals(receivingModesResponses.length,6);
    }
}
