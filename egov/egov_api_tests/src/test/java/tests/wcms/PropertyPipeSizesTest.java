package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.propertyPipeSize.create.CreatePropertyPipeSizeRequestBuilder;
import builders.wcms.propertyPipeSize.create.PropertyPipeSizeBuilder;
import builders.wcms.propertyPipeSize.search.SearchPropertyPipeSizeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyPipeSize.create.CreatePropertyPipeSizeRequest;
import entities.requests.wcms.propertyPipeSize.create.PropertyPipeSize;
import entities.requests.wcms.propertyPipeSize.search.SearchPropertyPipeSizeRequest;
import entities.responses.propertyTax.masters.propertyTypes.search.SearchPropertyTypesResponse;
import entities.responses.wcms.pipeSize.CreatePipeSizeResponse;
import entities.responses.wcms.propertyPipeSize.CreatePropertyPipeSizeResponse;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import tests.propertyTax.masters.PTISMasterSearchHelper;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_MILLIMETERSIZE;
import static data.UserData.MANAS;

public class PropertyPipeSizesTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdatePropertyPipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        CreatePipeSizeResponse createPipeSizeResponse = new PipeSizesTest().createPipeSize(); // Create PipeSizes
        CreatePipeSizeResponse searchPipeSizeResponse = new PipeSizesTest().searchPipeSize(createPipeSizeResponse, WITH_MILLIMETERSIZE); // Search PipeSizes

        CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse = createPropertyPipeSize(searchPropertyTypesResponse, searchPipeSizeResponse); // Create PropertyType - PipeSizes
        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse = searchPropertyPipeSize(createPropertyPipeSizeResponse); // Search PropertyType - PipeSizes
        updatePropertyPipeSize(createPropertyPipeSizeResponse, searchPropertyTypesResponse, searchPropertyPipeSizeResponse);
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void searchPropertyPipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        getAllPropertyPipeSizes(); // Get ALL PropertyPipeSizes
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void propertyPipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        CreatePipeSizeResponse createPipeSizeResponse = new PipeSizesTest().createPipeSize(); // Create PipeSizes
        CreatePipeSizeResponse searchPipeSizeResponse = new PipeSizesTest().searchPipeSize(createPipeSizeResponse, WITH_MILLIMETERSIZE); // Search PipeSizes

        CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse = createPropertyPipeSize(searchPropertyTypesResponse, searchPipeSizeResponse); // Create PropertyType - PipeSizes
        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse = searchPropertyPipeSize(createPropertyPipeSizeResponse); // Search PropertyType - PipeSizes
        updatePropertyPipeSize(createPropertyPipeSizeResponse, searchPropertyTypesResponse, searchPropertyPipeSizeResponse);
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreatePropertyPipeSizeResponse createPropertyPipeSize(SearchPropertyTypesResponse searchPropertyTypesResponse, CreatePipeSizeResponse searchPipeSizeResponse) throws IOException {
        new APILogger().log("Create PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PropertyPipeSize propertyPipeSize = new PropertyPipeSizeBuilder()
                .withPropertyTypeName(searchPropertyTypesResponse
                        .getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName())
                .withPipeSize(String.valueOf(searchPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter()))
                .build();
        CreatePropertyPipeSizeRequest createPropertyPipeSizeRequest = new CreatePropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo)
                .withPropertyPipeSize(propertyPipeSize).build();

        Response response = new WCMSResource().createPropertyPipeSize(RequestHelper.getJsonString(createPropertyPipeSizeRequest));
        CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(createPropertyPipeSizeResponse.getResponseInfo().getStatus(), "201");
        Assert.assertEquals(createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPipeSize(), searchPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter());
        new APILogger().log("Create PropertyType - PipeSizes Test is Completed ---");
        return createPropertyPipeSizeResponse;
    }

    private CreatePropertyPipeSizeResponse searchPropertyPipeSize(CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse) throws IOException {
        new APILogger().log("Search PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyPipeSizeRequest searchPropertyPipeSizeRequest = new SearchPropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path = "&propertyTypeName=" + createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPropertyTypeName()
                + "&pipeSize=" + createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPipeSize();

        Response response = new WCMSResource()
                .searchPropertyPipeSizeResource(RequestHelper.getJsonString(searchPropertyPipeSizeRequest), path);
        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(searchPropertyPipeSizeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertEquals(searchPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPipeSize(),
                createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPipeSize());
        new APILogger().log("Search PropertyType - PipeSizes Test is Completed ---");
        return searchPropertyPipeSizeResponse;
    }

    private void updatePropertyPipeSize(CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse,
                                        SearchPropertyTypesResponse searchPropertyTypesResponse,
                                        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse) throws IOException {

        new APILogger().log("Update PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        String propertyTypeName = createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPropertyTypeName();

        while (!(propertyTypeName.equals(searchPropertyTypesResponse.getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName()))) {
            propertyTypeName = searchPropertyTypesResponse.getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName();
            break;
        }

        PropertyPipeSize propertyPipeSize = new PropertyPipeSizeBuilder()
                .withPropertyTypeName(propertyTypeName)
                .withPipeSize(String.valueOf(createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPipeSize()))
                .build();
        CreatePropertyPipeSizeRequest updatePropertyPipeSizeRequest = new CreatePropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo)
                .withPropertyPipeSize(propertyPipeSize).build();

        Response response = new WCMSResource()
                .updatePropertyPipeSizeResource(RequestHelper.getJsonString(updatePropertyPipeSizeRequest),
                        searchPropertyPipeSizeResponse.getPropertyPipeSize()[0].getId());
        CreatePropertyPipeSizeResponse updatePropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(updatePropertyPipeSizeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertNotEquals(updatePropertyPipeSizeResponse.getPropertyPipeSize()[0].getPropertyTypeName(),
                createPropertyPipeSizeResponse.getPropertyPipeSize()[0].getPropertyTypeName());
        new APILogger().log("Update PropertyType - PipeSizes Test is Completed ---");
    }

    private void getAllPropertyPipeSizes() throws IOException {
        new APILogger().log("Get All PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyPipeSizeRequest searchPropertyPipeSizeRequest = new SearchPropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource()
                .searchPropertyPipeSizeResource(RequestHelper.getJsonString(searchPropertyPipeSizeRequest), "");
        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(searchPropertyPipeSizeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertTrue(searchPropertyPipeSizeResponse.getPropertyPipeSize().length > 0);
        new APILogger().log("Get All PropertyType - PipeSizes Test is Completed ---");
    }
}
