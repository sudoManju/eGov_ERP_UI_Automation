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

import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class DocumentTypeApplicationTypeTest extends BaseAPITest {

    private DocumentTypeTest documentTypeTest = new DocumentTypeTest();

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createSearchUpdateDocumentTypeApplicationTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDocumentTypeResponse createDocumentTypeResponse = documentTypeTest.createDocumentType(); // Create DocumentType
        CreateDocumentTypeResponse searchDocumentTypeResponse = documentTypeTest.searchDocumentType(createDocumentTypeResponse, WITH_NAME); // Search DocumentType

        CreateDocumentTypeApplicationTypeResponse createDocumentTypeApplicationTypeResponse = createDocumentTypeApplicationType(searchDocumentTypeResponse.getDocumentTypes()[0].getName()); // Create DocumentType - ApplicationType
        CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse = searchDocumentTypeApplicationType(createDocumentTypeApplicationTypeResponse); // Search DocumentType - ApplicationType
        updateDocumentTypeApplicationType(searchDocumentTypeApplicationTypeResponse, searchDocumentTypeResponse); // Update DocumentType - ApplicationType
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
        Assert.assertEquals(createDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getDocumentType(),
                documentTypeApplicationType.getDocumentType());
        new APILogger().log("Create DocumentType - ApplicationType Test is Completed ---");
        return createDocumentTypeApplicationTypeResponse;
    }

    private CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationType(CreateDocumentTypeApplicationTypeResponse createDocumentTypeApplicationTypeResponse) throws IOException {
        new APILogger().log("Search DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDocumentTypeApplicationTypeRequest searchDocumentTypeApplicationTypeRequest = new SearchDocumentTypeApplicationTypeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path = "&applicationType=" + createDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getApplicationType() +
                "&documentType=" + createDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getDocumentType();
        Response response = new WCMSResource()
                .searchDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(searchDocumentTypeApplicationTypeRequest), path);

        CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(searchDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertEquals(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getDocumentType(),
                createDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getDocumentType());
        new APILogger().log("Search DocumentType - ApplicationType Test is Completed ---");
        return searchDocumentTypeApplicationTypeResponse;
    }

    private void updateDocumentTypeApplicationType(CreateDocumentTypeApplicationTypeResponse searchDocumentTypeApplicationTypeResponse, CreateDocumentTypeResponse searchDocumentTypeResponse) throws IOException {
        new APILogger().log("Update DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        String applicationType = searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getApplicationType();

        while (!(applicationType.equals(new ApplicationTypesData().randomApplicationType()))) {
            applicationType = new ApplicationTypesData().randomApplicationType();
            break;
        }
        DocumentTypeApplicationType documentTypeApplicationType = new DocumentTypeApplicationTypeBuilder()
                .withDocumentType(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getDocumentType())
                .withApplicationType(applicationType).build();
        CreateDocumentTypeApplicationTypeRequest updateDocumentTypeApplicationTypeRequest =
                new CreateDocumentTypeApplicationTypeRequestBuilder()
                        .withDocumentTypeApplicationType(documentTypeApplicationType)
                        .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().updateDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(updateDocumentTypeApplicationTypeRequest),
                searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getId());
        CreateDocumentTypeApplicationTypeResponse updateDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(updateDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertNotEquals(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getApplicationType(),
                updateDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getApplicationType());
        new APILogger().log("Update DocumentType - ApplicationType Test is Completed ---");
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
        Assert.assertTrue(searchDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType().length > 0);
    }
}
