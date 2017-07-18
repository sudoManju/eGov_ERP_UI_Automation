package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.propertyPipeSize.create.CreatePropertyPipeSizeRequestBuilder;
import builders.wcms.propertyPipeSize.create.PropertyPipeSizeBuilder;
import builders.wcms.propertyPipeSize.search.SearchPropertyPipeSizeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyPipeSize.create.CreatePropertyPipeSizeRequest;
import entities.requests.wcms.propertyPipeSize.create.PropertyTypePipeSize;
import entities.requests.wcms.propertyPipeSize.search.SearchPropertyPipeSizeRequest;
import entities.responses.propertyTax.masters.propertyTypes.search.PropertyTypes;
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

import static data.SearchParameterData.*;
import static data.UserData.MANAS;

public class PropertyTypePipeSizesTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdatePropertyPipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        CreatePipeSizeResponse createPipeSizeResponse = new PipeSizesTest().createPipeSize(); // Create PipeSizes
        CreatePipeSizeResponse searchPipeSizeResponse = new PipeSizesTest().searchPipeSize(createPipeSizeResponse, WITH_MILLIMETERSIZE); // Search PipeSizes

        CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse = createPropertyPipeSize(searchPropertyTypesResponse, searchPipeSizeResponse); // Create PropertyType - PipeSizes
        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse = searchPropertyPipeSize(createPropertyPipeSizeResponse, WITH_PARAMETER); // Search PropertyType - PipeSizes
        CreatePropertyPipeSizeResponse updatePropertyPipeSizeResponse = updatePropertyPipeSize(createPropertyPipeSizeResponse, searchPropertyTypesResponse, searchPropertyPipeSizeResponse);
        searchPropertyPipeSize(updatePropertyPipeSizeResponse, WITH_ID); // Search PropertyType - PipeSizes After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void searchPropertyPipeSizeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        getAllPropertyPipeSizes(); // Get ALL PropertyPipeSizes
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreatePropertyPipeSizeResponse createPropertyPipeSize(SearchPropertyTypesResponse searchPropertyTypesResponse, CreatePipeSizeResponse searchPipeSizeResponse) throws IOException {
        new APILogger().log("Create PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PropertyTypePipeSize propertyTypePipeSize = new PropertyPipeSizeBuilder()
                .withPropertyTypeName(searchPropertyTypesResponse
                        .getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName())
                .withPipeSize(String.valueOf(searchPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter()))
                .build();
        CreatePropertyPipeSizeRequest createPropertyPipeSizeRequest = new CreatePropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo)
                .withPropertyPipeSize(propertyTypePipeSize).build();

        Response response = new WCMSResource().createPropertyPipeSize(RequestHelper.getJsonString(createPropertyPipeSizeRequest));
        CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(createPropertyPipeSizeResponse.getResponseInfo().getStatus(), "201");
        Assert.assertEquals(createPropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPipeSize(), searchPipeSizeResponse.getPipeSizes()[0].getSizeInMilimeter());
        new APILogger().log("Create PropertyType - PipeSizes Test is Completed ---");
        return createPropertyPipeSizeResponse;
    }

    private CreatePropertyPipeSizeResponse searchPropertyPipeSize(CreatePropertyPipeSizeResponse createOrUpdatePropertyPipeSizeResponse, String parameter) throws IOException {
        new APILogger().log("Search PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyPipeSizeRequest searchPropertyPipeSizeRequest = new SearchPropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path;
        if (parameter.equalsIgnoreCase(""))
            path = WITH_PROPERTY_TYPE + createOrUpdatePropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPropertyTypeName()
                    + WITH_PIPESIZE + createOrUpdatePropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPipeSize();
        else path = pathBuilder(WITH_ID, createOrUpdatePropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getId());

        Response response = new WCMSResource()
                .searchPropertyPipeSizeResource(RequestHelper.getJsonString(searchPropertyPipeSizeRequest), path);
        CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(searchPropertyPipeSizeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertTrue(searchPropertyPipeSizeResponse.getPropertyTypePipeSize().length == 1);
        Assert.assertEquals(searchPropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPipeSize(),
                createOrUpdatePropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPipeSize());
        new APILogger().log("Search PropertyType - PipeSizes Test is Completed ---");
        return searchPropertyPipeSizeResponse;
    }

    private CreatePropertyPipeSizeResponse updatePropertyPipeSize(CreatePropertyPipeSizeResponse createPropertyPipeSizeResponse,
                                                                  SearchPropertyTypesResponse searchPropertyTypesResponse,
                                                                  CreatePropertyPipeSizeResponse searchPropertyPipeSizeResponse) throws IOException {

        new APILogger().log("Update PropertyType - PipeSizes Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        String propertyTypeName = createPropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPropertyTypeName();
        PropertyTypes[] propertyTypesList = searchPropertyTypesResponse.getPropertyTypes();
        for (PropertyTypes propertyType : propertyTypesList) {
            if (!(propertyType.getName().equals(propertyTypeName))) {
                propertyTypeName = propertyType.getName();
                break;
            }

        }

        PropertyTypePipeSize propertyTypePipeSize = new PropertyPipeSizeBuilder()
                .withPropertyTypeName(propertyTypeName)
                .withPipeSize(String.valueOf(createPropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPipeSize()))
                .build();
        CreatePropertyPipeSizeRequest updatePropertyPipeSizeRequest = new CreatePropertyPipeSizeRequestBuilder()
                .withRequestInfo(requestInfo)
                .withPropertyPipeSize(propertyTypePipeSize).build();

        Response response = new WCMSResource()
                .updatePropertyPipeSizeResource(RequestHelper.getJsonString(updatePropertyPipeSizeRequest),
                        searchPropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getId());
        CreatePropertyPipeSizeResponse updatePropertyPipeSizeResponse = (CreatePropertyPipeSizeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyPipeSizeResponse.class);

        Assert.assertEquals(updatePropertyPipeSizeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertNotEquals(updatePropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPropertyTypeName(),
                createPropertyPipeSizeResponse.getPropertyTypePipeSize()[0].getPropertyTypeName());
        new APILogger().log("Update PropertyType - PipeSizes Test is Completed ---");
        return updatePropertyPipeSizeResponse;
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
        Assert.assertTrue(searchPropertyPipeSizeResponse.getPropertyTypePipeSize().length > 0);
        new APILogger().log("Get All PropertyType - PipeSizes Test is Completed ---");
    }
}
