package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.structureClass.CreateStructureClassRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.structureClass.CreateStructureClassRequest;
import entities.responses.login.LoginResponse;
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

        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA); // Login

        StructureClassResponse create = createStructureClass(loginResponse);  //Create

        SearchHelper helper = new SearchHelper(loginResponse);

        helper.searchStructureClassMaster(create);    //Search

        LoginAndLogoutHelper.logout(loginResponse);  //Logout
    }

    private StructureClassResponse createStructureClass(LoginResponse loginResponse) throws IOException {

        new APILogger().log("Create StructureClass Master started");

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        CreateStructureClassRequest request = new CreateStructureClassRequestBuilder().withRequestinfo(requestInfo).build();

        String json = RequestHelper.getJsonString(request);

        Response response = new StructureClassResource().createStructureClass(json);

        Assert.assertEquals(response.getStatusCode(),200);

        StructureClassResponse response1 = (StructureClassResponse)
                ResponseHelper.getResponseAsObject(response.asString(),StructureClassResponse.class);

        Assert.assertEquals(response1.getStructureClasses()[0].getName(),request.getStructureClasses()[0].getName());
        Assert.assertEquals(response1.getStructureClasses()[0].getCode(),request.getStructureClasses()[0].getCode());
        Assert.assertEquals(response1.getStructureClasses()[0].getNameLocal(),request.getStructureClasses()[0].getNameLocal());
        Assert.assertEquals(response1.getStructureClasses()[0].getOrderNumber(),request.getStructureClasses()[0].getOrderNumber());

        Assert.assertEquals(response1.getResponseInfo().getStatus(),"SUCCESSFUL");

        new APILogger().log("Create StructureClass Master completed");

        return response1;
    }
}
