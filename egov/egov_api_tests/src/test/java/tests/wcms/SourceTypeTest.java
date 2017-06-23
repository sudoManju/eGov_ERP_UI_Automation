package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.sourceType.create.CreateSourceTypeRequestBuilder;
import builders.wcms.sourceType.create.SourceTypeBuilder;
import builders.wcms.sourceType.search.SearchSourceTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.sourceType.create.CreateSourceTypeRequest;
import entities.requests.wcms.sourceType.create.SourceType;
import entities.requests.wcms.sourceType.search.SearchSourceTypeRequest;
import entities.responses.wcms.sourceType.create.CreateSourceTypeResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.MANAS;

public class SourceTypeTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void sourceTypeTest() throws IOException {
        LoginAndLogoutHelper.login1(MANAS); // Login
        String sourceTypeName = createSourceType(); // Create SourceType
        CreateSourceTypeResponse searchSourceTypeResponse = searchSourceType(sourceTypeName); // Search SourceType
        updateSourceType(searchSourceTypeResponse); // Update SourceType
        LoginAndLogoutHelper.logout1(); // Logout
    }

    private String createSourceType() throws IOException {
        new APILogger().log("Create SourceType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SourceType sourceType = new SourceTypeBuilder().build();
        CreateSourceTypeRequest createSourceTypeRequest = new CreateSourceTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSourceType(sourceType).build();

        Response response = new WCMSResource().createSourceTypeResource(RequestHelper.getJsonString(createSourceTypeRequest));
        CreateSourceTypeResponse createSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(sourceType.getName(), createSourceTypeResponse.getWaterSourceType()[0].getName());
        new APILogger().log("Create SourceType Test is Completed ---");
        return createSourceTypeResponse.getWaterSourceType()[0].getName();
    }

    private CreateSourceTypeResponse searchSourceType(String sourceTypeName) throws IOException {
        new APILogger().log("Search SourceType Test With Name is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchSourceTypeRequest searchSourceTypeRequest = new SearchSourceTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchSourceTypeResource(RequestHelper.getJsonString(searchSourceTypeRequest), sourceTypeName);
        CreateSourceTypeResponse searchSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(sourceTypeName, searchSourceTypeResponse.getWaterSourceType()[0].getName());
        Assert.assertTrue(searchSourceTypeResponse.getWaterSourceType().length == 1);
        new APILogger().log("Search SourceType Test With Name is Completed ---");
        return searchSourceTypeResponse;
    }

    private void updateSourceType(CreateSourceTypeResponse searchSourceTypeResponse) throws IOException {
        new APILogger().log("Update SourceType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SourceType sourceType = new SourceTypeBuilder().withCode(searchSourceTypeResponse.getWaterSourceType()[0].getCode())
                .withName(searchSourceTypeResponse.getWaterSourceType()[0].getName() + "-Updated")
                .build();
        CreateSourceTypeRequest updateSourceTypeRequest = new CreateSourceTypeRequestBuilder()
                .withRequestInfo(requestInfo).withSourceType(sourceType).build();

        Response response = new WCMSResource().updateSourceTypeResource(RequestHelper.getJsonString(updateSourceTypeRequest),
                searchSourceTypeResponse.getWaterSourceType()[0].getCode());
        CreateSourceTypeResponse updateSourceTypeResponse = (CreateSourceTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateSourceTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Updated", updateSourceTypeResponse.getWaterSourceType()[0].getName().split("-")[1]);
        new APILogger().log("Update SourceType Test is Completed ---");
    }
}
