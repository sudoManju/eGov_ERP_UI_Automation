package tests.serviceType;

import builders.ServiceTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.ServiceTypeRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.ServiceTypeResource;
import tests.BaseAPITest;
import utils.RequestHelper;

import java.io.IOException;


public class firstTest extends BaseAPITest {

    @Test
    public void shouldNotAllowRequestIfServiceTypeIsMissing() throws IOException{
        ServiceTypeRequest estimateBookingRequest = new ServiceTypeRequestBuilder()
                                .build();
        String jsonString = RequestHelper.getJsonString(estimateBookingRequest);
        Response response = new ServiceTypeResource().post(jsonString);
        Assert.assertEquals(420,420);
    }

}
