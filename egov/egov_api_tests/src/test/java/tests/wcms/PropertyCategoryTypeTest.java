package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.propertyCategoryType.PropertyCategoryBuilder;
import builders.wcms.propertyCategoryType.create.CreatePropertyCategoryTypeRequestBuilder;
import builders.wcms.propertyCategoryType.search.SearchPropertyCategoryTypeRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyCategoryType.create.CreatePropertyCategoryTypeRequest;
import entities.requests.wcms.propertyCategoryType.create.PropertyCategory;
import entities.requests.wcms.propertyCategoryType.search.SearchPropertyCategoryTypeRequest;
import entities.responses.propertyTax.masters.propertyTypes.search.SearchPropertyTypesResponse;
import entities.responses.wcms.categoryType.create.CreateCategoryTypeResponse;
import entities.responses.wcms.propertyCategoryType.CreatePropertyCategoryTypeResponse;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import tests.propertyTax.masters.PTISMasterSearchHelper;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.WITH_NAME;
import static data.UserData.MANAS;

public class PropertyCategoryTypeTest extends BaseAPITest {

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createSearchUpdatePropertyCategoryTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        CreateCategoryTypeResponse createCategoryTypeResponse = new CategoryTypeTest().createCategoryType(); // Create CategoryType
        CreateCategoryTypeResponse searchCategoryTypeResponse = new CategoryTypeTest().searchCategoryType(createCategoryTypeResponse, WITH_NAME); // Search CategoryType

        CreatePropertyCategoryTypeResponse createPropertyCategoryTypeResponse = createPropertyCategoryType(searchPropertyTypesResponse, searchCategoryTypeResponse); // Create PropertyType - CategoryType
        CreatePropertyCategoryTypeResponse searchPropertyCategoryTypeResponse = searchPropertyCategoryType(createPropertyCategoryTypeResponse); // Search PropertyType - CategoryType
        updatePropertyCategoryType(createPropertyCategoryTypeResponse, searchPropertyCategoryTypeResponse, searchPropertyTypesResponse); // Update PropertyType - CategoryType
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void SearchPropertyCategoryTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        getAllPropertyCategoryTypes(); // Get ALL PropertyTYpe - CategoryTypes
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreatePropertyCategoryTypeResponse createPropertyCategoryType(SearchPropertyTypesResponse searchPropertyTypesResponse, CreateCategoryTypeResponse searchCategoryTypeResponse) throws IOException {
        new APILogger().log("Create PropertyType - CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PropertyCategory propertyCategory = new PropertyCategoryBuilder()
                .withCategoryTypeName(searchCategoryTypeResponse.getCategory()[0].getName())
                .withPropertyTypeName(searchPropertyTypesResponse
                        .getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName())
                .build();
        CreatePropertyCategoryTypeRequest createPropertyCategoryTypeRequest = new CreatePropertyCategoryTypeRequestBuilder()
                .withRequestInfo(requestInfo)
                .withPropertyCategory(propertyCategory).build();

        Response response = new WCMSResource()
                .createPropertyCategoryTypeResource(RequestHelper.getJsonString(createPropertyCategoryTypeRequest));
        CreatePropertyCategoryTypeResponse createPropertyCategoryTypeResponse = (CreatePropertyCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyCategoryTypeResponse.class);

        Assert.assertEquals(createPropertyCategoryTypeResponse.getResponseInfo().getStatus(), "201");
        Assert.assertEquals(searchCategoryTypeResponse.getCategory()[0].getName(), createPropertyCategoryTypeResponse.getPropertyCategories()[0].getCategoryTypeName());
        new APILogger().log("Create PropertyType - CategoryType Test is Completed ---");
        return createPropertyCategoryTypeResponse;
    }

    private CreatePropertyCategoryTypeResponse searchPropertyCategoryType(CreatePropertyCategoryTypeResponse createPropertyCategoryTypeResponse) throws IOException {
        new APILogger().log("Search PropertyType - CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyCategoryTypeRequest searchPropertyCategoryTypeRequest = new SearchPropertyCategoryTypeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path = "&propertyType=" + createPropertyCategoryTypeResponse.getPropertyCategories()[0].getPropertyTypeName() +
                "&categoryType=" + createPropertyCategoryTypeResponse.getPropertyCategories()[0].getCategoryTypeName();

        Response response = new WCMSResource()
                .searchPropertyCategoryTypeResource(RequestHelper.getJsonString(searchPropertyCategoryTypeRequest), path);
        CreatePropertyCategoryTypeResponse searchPropertyCategoryTypeResponse = (CreatePropertyCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyCategoryTypeResponse.class);

        Assert.assertEquals(searchPropertyCategoryTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertEquals(searchPropertyCategoryTypeResponse.getPropertyCategories()[0].getCategoryTypeName(),
                createPropertyCategoryTypeResponse.getPropertyCategories()[0].getCategoryTypeName());
        new APILogger().log("Search PropertyType - CategoryType Test is Completed ---");
        return searchPropertyCategoryTypeResponse;
    }

    private void updatePropertyCategoryType(CreatePropertyCategoryTypeResponse createPropertyCategoryTypeResponse,
                                            CreatePropertyCategoryTypeResponse searchPropertyCategoryTypeResponse,
                                            SearchPropertyTypesResponse searchPropertyTypesResponse) throws IOException {

        new APILogger().log("Update PropertyType - CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        String propertyTypeName = createPropertyCategoryTypeResponse.getPropertyCategories()[0].getPropertyTypeName();

        while (!(propertyTypeName.equals(searchPropertyTypesResponse.getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName()))) {
            propertyTypeName = searchPropertyTypesResponse.getPropertyTypes()[RandomUtils.nextInt(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName();
            break;
        }

        PropertyCategory propertyCategory = new PropertyCategoryBuilder()
                .withCategoryTypeName(searchPropertyCategoryTypeResponse.getPropertyCategories()[0].getCategoryTypeName())
                .withPropertyTypeName(propertyTypeName).build();
        CreatePropertyCategoryTypeRequest createPropertyCategoryTypeRequest = new CreatePropertyCategoryTypeRequestBuilder()
                .withRequestInfo(requestInfo)
                .withPropertyCategory(propertyCategory).build();

        Response response = new WCMSResource().updatePropertyCategoryTypeResource(RequestHelper.getJsonString(createPropertyCategoryTypeRequest),
                searchPropertyCategoryTypeResponse.getPropertyCategories()[0].getId());
        CreatePropertyCategoryTypeResponse updatePropertyCategoryTypeResponse = (CreatePropertyCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyCategoryTypeResponse.class);

        Assert.assertEquals(updatePropertyCategoryTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertNotEquals(searchPropertyCategoryTypeResponse.getPropertyCategories()[0].getPropertyTypeId(),
                updatePropertyCategoryTypeResponse.getPropertyCategories()[0].getPropertyTypeId());
        new APILogger().log("Update PropertyType - CategoryType Test is Completed ---");
    }

    private void getAllPropertyCategoryTypes() throws IOException {
        new APILogger().log("Get All PropertyType - CategoryType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyCategoryTypeRequest searchPropertyCategoryTypeRequest = new SearchPropertyCategoryTypeRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource()
                .searchPropertyCategoryTypeResource(RequestHelper.getJsonString(searchPropertyCategoryTypeRequest), "");
        CreatePropertyCategoryTypeResponse searchPropertyCategoryTypeResponse = (CreatePropertyCategoryTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyCategoryTypeResponse.class);

        Assert.assertEquals(searchPropertyCategoryTypeResponse.getResponseInfo().getStatus(), "200");
        Assert.assertTrue(searchPropertyCategoryTypeResponse.getPropertyCategories().length > 0);
        new APILogger().log("Get All PropertyType - CategoryType Test is Completed ---");
    }
}
