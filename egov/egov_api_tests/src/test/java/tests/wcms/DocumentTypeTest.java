package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.documentType.create.CreateDocumentTypeRequestBuilder;
import builders.wcms.documentType.create.DocumentTypeBuilder;
import builders.wcms.documentType.search.SearchDocumentTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentType.create.CreateDocumentTypeRequest;
import entities.requests.wcms.documentType.create.DocumentType;
import entities.requests.wcms.documentType.search.SearchDocumentTypeRequest;
import entities.responses.wcms.documentType.CreateDocumentTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_ID;
import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class DocumentTypeTest extends BaseAPITest {

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createSearchUpdateDocumentTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDocumentTypeResponse createDocumentTypeResponse = createDocumentType(); // Create DocumentType
        CreateDocumentTypeResponse searchDocumentTypeResponse = searchDocumentType(createDocumentTypeResponse, WITH_NAME); // Search DocumentType
        CreateDocumentTypeResponse updateDocumentTypeResponse = updateDocumentType(searchDocumentTypeResponse); // Update DocumentType
        searchDocumentType(updateDocumentTypeResponse, WITH_ID); // Search DocumentType After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void searchDocumentTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        getAllDocumentTypes(); // Get All DocumentTypes
        LoginAndLogoutHelper.logout(); // Logout
    }

    CreateDocumentTypeResponse createDocumentType() throws IOException {
        new APILogger().log("Create DocumentType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        DocumentType documentType = new DocumentTypeBuilder().build();
        CreateDocumentTypeRequest createDocumentTypeRequest = new CreateDocumentTypeRequestBuilder()
                .withRequestInfo(requestInfo).withDocumentType(documentType).build();

        Response response = new WCMSResource().createDocumentTypeResource(RequestHelper.getJsonString(createDocumentTypeRequest));
        CreateDocumentTypeResponse createDocumentTypeResponse = (CreateDocumentTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(documentType.getName(), createDocumentTypeResponse.getDocumentTypes()[0].getName());
        new APILogger().log("Create DocumentType Test is Completed ---");
        return createDocumentTypeResponse;
    }

    CreateDocumentTypeResponse searchDocumentType(CreateDocumentTypeResponse createOrUpdateDocumentTypeResponse, String parameter) throws IOException {
        new APILogger().log("Search DocumentType Test With" + parameter + " is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDocumentTypeRequest searchDocumentTypeRequest = new SearchDocumentTypeRequestBuilder().withRequestInfo(requestInfo).build();

        String path;
        if (parameter.contains("&name="))
            path = pathBuilder(parameter, createOrUpdateDocumentTypeResponse.getDocumentTypes()[0].getName());
        else
            path = pathBuilder(parameter, String.valueOf(createOrUpdateDocumentTypeResponse.getDocumentTypes()[0].getId()));

        Response response = new WCMSResource().searchDocumentTypeResource(RequestHelper.getJsonString(searchDocumentTypeRequest), path);
        CreateDocumentTypeResponse searchDocumentTypeResponse = (CreateDocumentTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(searchDocumentTypeResponse.getDocumentTypes().length == 1);
        Assert.assertEquals(createOrUpdateDocumentTypeResponse.getDocumentTypes()[0].getName(), searchDocumentTypeResponse.getDocumentTypes()[0].getName());
        new APILogger().log("Search DocumentType Test With" + parameter + " is Completed ---");
        return searchDocumentTypeResponse;
    }

    private CreateDocumentTypeResponse updateDocumentType(CreateDocumentTypeResponse searchDocumentTypeResponse) throws IOException {
        new APILogger().log("Update DocumentType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        DocumentType documentType = new DocumentTypeBuilder()
                .withName(searchDocumentTypeResponse.getDocumentTypes()[0].getName() + "-Updated")
                .withId(searchDocumentTypeResponse.getDocumentTypes()[0].getId())
                .withCode(searchDocumentTypeResponse.getDocumentTypes()[0].getCode())
                .build();
        CreateDocumentTypeRequest updateDocumentTypeRequest = new CreateDocumentTypeRequestBuilder()
                .withRequestInfo(requestInfo).withDocumentType(documentType).build();

        Response response = new WCMSResource().updateDocumentTypeResource(RequestHelper.getJsonString(updateDocumentTypeRequest),
                searchDocumentTypeResponse.getDocumentTypes()[0].getCode());
        CreateDocumentTypeResponse updateDocumentTypeResponse = (CreateDocumentTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Updated", updateDocumentTypeResponse.getDocumentTypes()[0].getName().split("-")[1]);
        new APILogger().log("Update DocumentType Test is Completed ---");
        return updateDocumentTypeResponse;
    }

    private void getAllDocumentTypes() throws IOException {
        new APILogger().log("Get ALL DocumentType Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDocumentTypeRequest searchDocumentTypeRequest = new SearchDocumentTypeRequestBuilder().withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().searchDocumentTypeResource(RequestHelper.getJsonString(searchDocumentTypeRequest), "");
        CreateDocumentTypeResponse searchDocumentTypeResponse = (CreateDocumentTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeResponse.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(searchDocumentTypeResponse.getDocumentTypes().length >= 1);
        new APILogger().log("Get ALL DocumentType Test Request is Completed ---");
    }
}
