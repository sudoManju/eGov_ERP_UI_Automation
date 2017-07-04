package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.documentTypeApplicationType.create.CreateDocumentTypeApplicationTypeRequestBuilder;
import builders.wcms.documentTypeApplicationType.create.DocumentTypeApplicationTypeBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentTypeApplicationType.create.CreateDocumentTypeApplicationTypeRequest;
import entities.requests.wcms.documentTypeApplicationType.create.DocumentTypeApplicationType;
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

    private DocumentTypeTest documentTypeTest;

    public DocumentTypeApplicationTypeTest() {
        documentTypeTest = new DocumentTypeTest();
    }

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void documentTypeApplicationTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDocumentTypeResponse createDocumentTypeResponse = documentTypeTest.createDocumentType(); // Create DocumentType
        CreateDocumentTypeResponse searchDocumentTypeResponse = documentTypeTest.searchDocumentType(createDocumentTypeResponse , WITH_NAME); // Search DocumentType
        createDocumentTypeApplicationType(searchDocumentTypeResponse.getDocumentTypes()[0].getId());
//        searchDocumentTypeApplicationType(searchDocumentTypeResponse.getDocumentTypes()[0].getId());
        LoginAndLogoutHelper.logout(); // Logout
    }

    private void createDocumentTypeApplicationType(int documentTypeId) throws IOException {
        new APILogger().log("Create DocumentType - ApplicationType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        DocumentTypeApplicationType documentTypeApplicationType = new DocumentTypeApplicationTypeBuilder()
                .withDocumentTypeId(documentTypeId).build();
        CreateDocumentTypeApplicationTypeRequest createDocumentTypeApplicationTypeRequest =
                new CreateDocumentTypeApplicationTypeRequestBuilder()
                        .withDocumentTypeApplicationType(documentTypeApplicationType)
                        .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource()
                .createDocumentTypeApplicationTypeResource(RequestHelper.getJsonString(createDocumentTypeApplicationTypeRequest));
        CreateDocumentTypeApplicationTypeResponse createDocumentTypeApplicationTypeResponse = (CreateDocumentTypeApplicationTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDocumentTypeApplicationTypeResponse.class);

        Assert.assertEquals(createDocumentTypeApplicationTypeResponse.getResponseInfo().getStatus(), "201");
        Assert.assertEquals(createDocumentTypeApplicationTypeResponse.getDocumentTypeApplicationType()[0].getDocumentTypeId(),
                documentTypeApplicationType.getDocumentTypeId());
        new APILogger().log("Create DocumentType - ApplicationType Test is Completed ---");
    }
}
