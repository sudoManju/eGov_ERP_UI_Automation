package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.documentTypeApplicationType.create.CreateDocumentTypeApplicationTypeRequestBuilder;
import builders.wcms.documentTypeApplicationType.create.DocumentTypeApplicationTypeBuilder;
import builders.wcms.documentTypeApplicationType.search.SearchDocumentTypeApplicationTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentTypeApplicationType.ApplicationTypesData;
import entities.requests.wcms.documentTypeApplicationType.create.CreateDocumentTypeApplicationTypeRequest;
import entities.requests.wcms.documentTypeApplicationType.create.DocumentTypeApplicationType;
import entities.requests.wcms.documentTypeApplicationType.search.SearchDocumentTypeApplicationTypeRequest;
import entities.responses.wcms.documentType.CreateDocumentTypeResponse;
import entities.responses.wcms.documentTypeApplicationType.CreateDocumentTypeApplicationTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.*;
import static data.UserData.MANAS;

public class DocumentTypeApplicationTypesTest extends BaseAPITest {

    private DocumentTypeTest documentTypeTest = new DocumentTypeTest();

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createSearchUpdateDocumentTypeApplicationTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDocumentTypeResponse createDocumentTypeResponse = documentTypeTest.createDocumentType(); // Create DocumentType
        CreateDocumentTypeResponse searchDocumentTypeResponse = documentTypeTest.searchDocumentType(createDocumentTypeResponse, WITH_NAME); // Search DocumentType

        CreateDocumentTypeApplicationTypeResponse createDocumentTypeApplicationTypeResponse = createDocumentTypeApplicationType(searchDocumentTypeResponse.getDocumentTypes()[0].getName()); // Create DocumentType - ApplicationType
        CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse = searchDocumentTypeApplicationType(createDocumentTypeApplicationTypeResponse, WITH_PARAMETER); // Search DocumentType - ApplicationType
        CreateDocumentTypeApplicationTypeResponse updateDocumentTypeApplicationTypeResponse = updateDocumentTypeApplicationType(searchDocumentTypeApplicationTypeResponse, searchDocumentTypeResponse); // Update DocumentType - ApplicationType
        searchDocumentTypeApplicationType(updateDocumentTypeApplicationTypeResponse, WITH_ID); // Search DocumentType - ApplicationType After UPDATE
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void searchDocumentTypeApplicationTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        getAllDocumentTypeApplicationTypes(); // Get ALL DocumentTypeApplicationTypes
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreateDocumentTypeApplicationTypeResponse createDocumentTypeApplicationType(String documentTypeName) throws IOException {
        new APILogger().log("Create DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        DocumentTypeApplicationType documentTypeApplicationType = new DocumentTypeApplicationTypeBuilder()
                .withDocumentType(documentTypeName).build();
        CreateDocumentTypeApplicationTypeRequest createDocumentTypeApplicationTypeRequest =
                new CreateDocumentTypeApplicationTypeRequestBuilder()
                        .withDocumentTypeApplicationType(documentTypeApplicationType)
                        .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource()
                .createDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(createDocumentTypeApplicationTypeRequest));
        CreateDocumentTypeApplicationTypeResponse createDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(createDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "201");
        Assert.assertEquals(createDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getDocumentType(),
                documentTypeApplicationType.getDocumentType());
        new APILogger().log("Create DocumentType - ApplicationType Test is Completed ---");
        return createDocumentTypeApplicationTypeResponse;
    }

    private CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationType(CreateDocumentTypeApplicationTypeResponse createOrUpdateDocumentTypeApplicationTypeResponse, String parameter) throws IOException {
        new APILogger().log("Search DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDocumentTypeApplicationTypeRequest searchDocumentTypeApplicationTypeRequest = new SearchDocumentTypeApplicationTypeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path;
        if (parameter.equalsIgnoreCase("")) {
            path = WITH_APPLICATION_TYPE + createOrUpdateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getApplicationType() +
                    WITH_DOCUMENT_TYPE + createOrUpdateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getDocumentType();
        } else
            path = pathBuilder(WITH_ID, String.valueOf(createOrUpdateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getId()));

        Response response = new WCMSResource()
                .searchDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(searchDocumentTypeApplicationTypeRequest), path);
        CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(searchDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertTrue(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes().length == 1);
        Assert.assertEquals(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getApplicationType(),
                createOrUpdateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getApplicationType());
        new APILogger().log("Search DocumentType - ApplicationType Test is Completed ---");
        return searchDocumentTypeApplicationTypeResponse;
    }

    private CreateDocumentTypeApplicationTypeResponse updateDocumentTypeApplicationType(CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse, CreateDocumentTypeResponse searchDocumentTypeResponse) throws IOException {
        new APILogger().log("Update DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        String applicationType = searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getApplicationType();

        while (!(applicationType.equals(new ApplicationTypesData().randomApplicationType()))) {
            applicationType = new ApplicationTypesData().randomApplicationType();
            break;
        }
        DocumentTypeApplicationType documentTypeApplicationType = new DocumentTypeApplicationTypeBuilder()
                .withDocumentType(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getDocumentType())
                .withApplicationType(applicationType).build();
        CreateDocumentTypeApplicationTypeRequest updateDocumentTypeApplicationTypeRequest =
                new CreateDocumentTypeApplicationTypeRequestBuilder()
                        .withDocumentTypeApplicationType(documentTypeApplicationType)
                        .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().updateDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(updateDocumentTypeApplicationTypeRequest),
                searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getId());
        CreateDocumentTypeApplicationTypeResponse updateDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(updateDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertNotEquals(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getApplicationType(),
                updateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getApplicationType());
        new APILogger().log("Update DocumentType - ApplicationType Test is Completed ---");
        return updateDocumentTypeApplicationTypeResponse;
    }

    private void searchDocumentTypeApplicationTypeAfterUpdate(CreateDocumentTypeApplicationTypeResponse updateDocumentTypeApplicationTypeResponse, String withId) throws IOException {
        new APILogger().log("Search DocumentType - ApplicationType After Update Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDocumentTypeApplicationTypeRequest searchDocumentTypeApplicationTypeRequest = new SearchDocumentTypeApplicationTypeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path = withId + updateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getId();

        Response response = new WCMSResource()
                .searchDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(searchDocumentTypeApplicationTypeRequest), path);
        CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeAfterUpdateResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(searchDocumentTypeApplicationTypeAfterUpdateResponse.getResponseInfo().getStatus(), "200");
        Assert.assertTrue(searchDocumentTypeApplicationTypeAfterUpdateResponse.getDocumentTypeApplicationTypes().length == 1);
        Assert.assertEquals(updateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes()[0].getDocumentType(),
                searchDocumentTypeApplicationTypeAfterUpdateResponse.getDocumentTypeApplicationTypes()[0].getDocumentType());
        new APILogger().log("Search DocumentType - ApplicationType After Update Test is Completed ---");
    }

    private void getAllDocumentTypeApplicationTypes() throws IOException {
        new APILogger().log("Search DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDocumentTypeApplicationTypeRequest searchDocumentTypeApplicationTypeRequest = new SearchDocumentTypeApplicationTypeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource()
                .searchDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(searchDocumentTypeApplicationTypeRequest), "");

        CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(searchDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertTrue(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationTypes().length > 0);
    }
}
