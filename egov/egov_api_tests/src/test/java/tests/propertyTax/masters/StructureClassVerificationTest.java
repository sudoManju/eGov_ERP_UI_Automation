package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.structureClass.CreateStructureClassRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.structureClass.CreateStructureClassRequest;
import entities.responses.propertyTax.masters.structureClass.create.StructureClassResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.masters.StructureClassResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class StructureClassVerificationTest extends BaseAPITest {

    @Test(groups = {Categories.PTIS, Categories.SANITY})
    public void structureClassTest()throws IOException{

        LoginAndLogoutHelper.login(NARASAPPA); // Login

        StructureClassResponse create = createStructureClass();  //Create

        SearchHelper helper = new SearchHelper();

        helper.searchStructureClassMaster(create);    //Search

        LoginAndLogoutHelper.logout1();  //Logout
    }

    private StructureClassResponse createStructureClass() throws IOException {
        new APILogger().log("Create StructureClass Master Started");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateStructureClassRequest request = new CreateStructureClassRequestBuilder().withRequestinfo(requestInfo).build();

        Response response = new StructureClassResource().create(RequestHelper.getJsonString(request));
        StructureClassResponse response1 = (StructureClassResponse)
                ResponseHelper.getResponseAsObject(response.asString(),StructureClassResponse.class);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response1.getStructureClasses()[0].getName(),request.getStructureClasses()[0].getName());
        Assert.assertEquals(response1.getStructureClasses()[0].getCode(),request.getStructureClasses()[0].getCode());
        Assert.assertEquals(response1.getStructureClasses()[0].getNameLocal(),request.getStructureClasses()[0].getNameLocal());
        Assert.assertEquals(response1.getStructureClasses()[0].getOrderNumber(),request.getStructureClasses()[0].getOrderNumber());
        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");
        new APILogger().log("Create StructureClass Master Completed");

        return response1;
    }
}
