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
import java.util.ArrayList;


public class firstTest extends BaseAPITest {

    @Test
    public void shouldNotAllowRequestIfServiceTypeIsMissing() throws IOException{
        ServiceTypeRequest request = new ServiceTypeRequestBuilder()
                                    .build();
        Response response = new ServiceTypeResource().serviceTypeValidation(RequestHelper.getJsonString(request));
        org.junit.Assert.assertTrue("Actual response code " + response.getStatusCode(), isGoodResponse(response));

    }

    private boolean isGoodResponse(Response response) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(200);
                    arrayList.add(201);
                    arrayList.add(202);
                    arrayList.add(203);
                    return arrayList.contains(response.getStatusCode());
                }

}
