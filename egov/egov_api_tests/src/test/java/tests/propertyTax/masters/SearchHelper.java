package tests.propertyTax.masters;

import builders.propertyTax.masters.RequestInfoBuilder;
import builders.propertyTax.masters.usage.SearchUsageMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.SearchMasterRequest;
import entities.responses.login.LoginResponse;
import entities.responses.propertyTax.masters.usage.search.SearchUsageMasterResponse;
import entities.responses.propertyTax.masters.usage.create.UsageMasterResponse;
import org.testng.Assert;
import resources.propertyTax.masters.UsageMasterResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

public class SearchHelper extends BaseAPITest {


    private String json;

    public SearchHelper(LoginResponse loginResponse) {

        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(loginResponse.getAccess_token()).build();

        SearchMasterRequest request = new SearchUsageMasterRequestBuilder().withRequestInfo(requestInfo).build();

        json = RequestHelper.getJsonString(request);
    }

    public void searchForUsageMaster(UsageMasterResponse response) throws IOException {

        new APILogger().log("Search Usage Master is started --");

        Response responseForId = new UsageMasterResource().searchUsageMaster(json, "&ids=" + response.getUsageMasters()[0].getId());

        Assert.assertEquals(responseForId.getStatusCode(), 200);

        CheckResponseForUsage(responseForId,response);

        new APILogger().log("Search Usage Master with Id is success");

        Response responseForName = new UsageMasterResource().searchUsageMaster(json, "&name=" + response.getUsageMasters()[0].getName());

        Assert.assertEquals(responseForName.getStatusCode(), 200);

        CheckResponseForUsage(responseForName,response);

        new APILogger().log("Search Usage Master with Name is success");

        Response responseForCode = new UsageMasterResource().searchUsageMaster(json, "&code=" + response.getUsageMasters()[0].getCode());

        Assert.assertEquals(responseForCode.getStatusCode(), 200);

        CheckResponseForUsage(responseForCode,response);

        new APILogger().log("Search Usage Master with code is success");

        Response responseForNameLocal = new UsageMasterResource().searchUsageMaster(json,"&nameLocal="+response.getUsageMasters()[0].getNameLocal());

        Assert.assertEquals(responseForNameLocal.getStatusCode(),200);

        CheckResponseForUsage(responseForNameLocal,response);

        new APILogger().log("Search Usage Master with Name Local is success");

        new APILogger().log("Search Usage Master is Completed --");
    }

    private void CheckResponseForUsage(Response response,UsageMasterResponse create) throws IOException {

        SearchUsageMasterResponse response1 = (SearchUsageMasterResponse)
                ResponseHelper.getResponseAsObject(response.asString(),SearchUsageMasterResponse.class);

        Assert.assertEquals(response1.getUsageMasters()[0].getId(),create.getUsageMasters()[0].getId());
        Assert.assertEquals(response1.getUsageMasters()[0].getName(),create.getUsageMasters()[0].getName());
        Assert.assertEquals(response1.getUsageMasters()[0].getCode(),create.getUsageMasters()[0].getCode());
        Assert.assertEquals(response1.getUsageMasters()[0].getNameLocal(),create.getUsageMasters()[0].getNameLocal());
    }
}
