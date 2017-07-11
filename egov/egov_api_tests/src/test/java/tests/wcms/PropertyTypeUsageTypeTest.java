package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.propertyTypeUsageType.SearchPropertyTypeUsageTypeRequestBuilder;
import builders.wcms.propertyTypeUsageType.create.CreatePropertyTypeUsageTypeRequestBuilder;
import builders.wcms.propertyTypeUsageType.create.PropertyTypeUsageTypeBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyTypeUsageType.create.CreatePropertyTypeUsageTypeRequest;
import entities.requests.wcms.propertyTypeUsageType.create.PropertyTypeUsageType;
import entities.requests.wcms.propertyTypeUsageType.search.SearchPropertyTypeUsageTypeRequest;
import entities.responses.propertyTax.masters.propertyTypes.search.SearchPropertyTypesResponse;
import entities.responses.propertyTax.masters.usage.search.SearchUsageMasterResponse;
import entities.responses.wcms.propertyTypeUsageType.CreatePropertyTypeUsageTypeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import tests.propertyTax.masters.PTISMasterSearchHelper;
import utils.*;

import java.io.IOException;

import static data.UserData.MANAS;

public class PropertyTypeUsageTypeTest extends BaseAPITest {

    /*
    * Search PropertyTypes Test - PropertyTax
    * Search UsageTypes Test - PropertyTax
    * CreatePropertyTypeUsageType
    * SearchPropertyTypeUsageType
    * UpdatePropertyTypeUsageType With UsageType
     */
    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createPropertyTypeUsageTypeWithUpdateAsUsageTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login

        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        SearchUsageMasterResponse searchUsageMasterResponse = new PTISMasterSearchHelper().getAllUsageTypes(); // Get UsageTypes

        CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageTypeResponse = createPropertyTypeUsageType(searchPropertyTypesResponse, searchUsageMasterResponse); // Create PropertyTypeUsageType
        int propertyTypeUsageTypeId = searchPropertyTypeUsageType(createPropertyTypeUsageTypeResponse); // Search PropertyTypeUsageType
        updatePropertyTypeUsageTypeWithUsageType(createPropertyTypeUsageTypeResponse, searchUsageMasterResponse, propertyTypeUsageTypeId); // Update PropertyTypeUsageType with UsageType

        LoginAndLogoutHelper.logout(); // Logout
    }

    /*
    * Search PropertyTypes Test - PropertyTax
    * Search UsageTypes Test - PropertyTax
    * CreatePropertyTypeUsageType
    * SearchPropertyTypeUsageType
    * UpdatePropertyTypeUsageType With PropertyType
     */
    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createPropertyTypeUsageTypeWithUpdateAsPropertyTypeTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login

        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        SearchUsageMasterResponse searchUsageMasterResponse = new PTISMasterSearchHelper().getAllUsageTypes(); // Get UsageTypes

        CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageTypeResponse = createPropertyTypeUsageType(searchPropertyTypesResponse, searchUsageMasterResponse); // Create PropertyTypeUsageType
        int propertyTypeUsageTypeId = searchPropertyTypeUsageType(createPropertyTypeUsageTypeResponse); // Search PropertyTypeUsageType
        updatePropertyTypeUsageTypeWithPropertyType(createPropertyTypeUsageTypeResponse, searchPropertyTypesResponse, propertyTypeUsageTypeId); // Update PropertyTypeUsageType with PropertyType

        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageType(SearchPropertyTypesResponse searchPropertyTypesResponse, SearchUsageMasterResponse searchUsageMasterResponse) throws IOException {
        new APILogger().log("Create PropertyType - UsageType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PropertyTypeUsageType propertyTypeUsageType;
        CreatePropertyTypeUsageTypeRequest createPropertyTypeUsageTypeRequest;
        boolean foundCombination = false;
        CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageTypeResponse = null;

        // Recursively checking the every combination of PropertyType & UsageType
        for (int i = 0; i < searchPropertyTypesResponse.getPropertyTypes().length && !foundCombination; i++) {
            for (int j = 0; j < searchUsageMasterResponse.getUsageMasters().length && !foundCombination; j++) {

                propertyTypeUsageType = new PropertyTypeUsageTypeBuilder()
                        .withPropertyType(searchPropertyTypesResponse.getPropertyTypes()[i].getName())
                        .withUsageType(searchUsageMasterResponse.getUsageMasters()[j].getName())
                        .build();
                createPropertyTypeUsageTypeRequest = new CreatePropertyTypeUsageTypeRequestBuilder()
                        .withPropertyTypeUsageType(propertyTypeUsageType)
                        .withRequestInfo(requestInfo).build();

                Response response = new WCMSResource()
                        .createPropertyTypeUsageType(RequestHelper.getJsonString(createPropertyTypeUsageTypeRequest));

                if (response.getStatusCode() == 200) {
                    foundCombination = true;
                    new APILogger().log("Create PropertyType - UsageType Test Request is Started with --" + RequestHelper.getJsonString(createPropertyTypeUsageTypeRequest));
                    createPropertyTypeUsageTypeResponse = (CreatePropertyTypeUsageTypeResponse)
                            ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyTypeUsageTypeResponse.class);

                    Assert.assertTrue(createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes().length == 1);
                    Assert.assertEquals(propertyTypeUsageType.getPropertyType(), createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getPropertyType());
                    Assert.assertEquals(propertyTypeUsageType.getUsageType(), createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getUsageType());
                    new APILogger().log("Create PropertyType - UsageType Test Request is Generated as  --" + response.asString());
                    break;
                }
            }
        }
        new APILogger().log("Create PropertyType - UsageType Test is Completed ---");
        return createPropertyTypeUsageTypeResponse;
    }

    private int searchPropertyTypeUsageType(CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageTypeResponse) throws IOException {
        new APILogger().log("Search PropertyType - UsageType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyTypeUsageTypeRequest searchPropertyTypeUsageTypeRequest =
                new SearchPropertyTypeUsageTypeRequestBuilder().withRequestInfo(requestInfo).build();
        String path = "&propertyType=" + createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getPropertyType() +
                "&usageType=" + createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getUsageType();

        Response response = new WCMSResource()
                .searchPropertyTypeUsageTypeResource(RequestHelper.getJsonString(searchPropertyTypeUsageTypeRequest), path);
        CreatePropertyTypeUsageTypeResponse searchPropertyTypeUsageTypeResponse = (CreatePropertyTypeUsageTypeResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyTypeUsageTypeResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes().length == 1);
        new APILogger().log("Search PropertyType - UsageType Test is Completed ---");
        return searchPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getId();
    }

    // This method will Update only UsageType
    private void updatePropertyTypeUsageTypeWithUsageType(CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageTypeResponse,
                                                          SearchUsageMasterResponse searchUsageMasterResponse, int propertyTypeUsageTypeId) throws IOException {
        new APILogger().log("Update PropertyType - UsageType With UsageType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PropertyTypeUsageType propertyTypeUsageType;
        CreatePropertyTypeUsageTypeRequest updatePropertyTypeUsageTypeRequest;
        boolean foundCombination = false;
        CreatePropertyTypeUsageTypeResponse updatePropertyTypeUsageTypeResponse = null;

        // Recursively checking the every combination of PropertyType & UsageType
        for (int j = 0; j < searchUsageMasterResponse.getUsageMasters().length && !foundCombination; j++) {

            propertyTypeUsageType = new PropertyTypeUsageTypeBuilder()
                    .withPropertyType(createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getPropertyType())
                    .withUsageType(searchUsageMasterResponse.getUsageMasters()[j].getName())
                    .build();
            updatePropertyTypeUsageTypeRequest = new CreatePropertyTypeUsageTypeRequestBuilder()
                    .withPropertyTypeUsageType(propertyTypeUsageType)
                    .withRequestInfo(requestInfo).build();

            Response response = new WCMSResource()
                    .updatePropertyTypeUsageType(RequestHelper.getJsonString(updatePropertyTypeUsageTypeRequest), propertyTypeUsageTypeId);

            if (response.getStatusCode() == 200 && !(propertyTypeUsageType.getUsageType().equals(createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getUsageType()))) {
                foundCombination = true;
                new APILogger().log("Update PropertyType - UsageType With UsageType Test Request is Started with --" + RequestHelper.getJsonString(updatePropertyTypeUsageTypeRequest));
                new APILogger().log("Update PropertyType - UsageType With UsageType Test Request is Generated as  --" + response.asString());
                updatePropertyTypeUsageTypeResponse = (CreatePropertyTypeUsageTypeResponse)
                        ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyTypeUsageTypeResponse.class);

                Assert.assertTrue(updatePropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes().length == 1);
                Assert.assertEquals(propertyTypeUsageType.getPropertyType(), updatePropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getPropertyType());

                Assert.assertNotEquals(propertyTypeUsageType.getUsageType(), createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getUsageType());
                break;
            }
        }
        new APILogger().log("Update PropertyType - UsageType With UsageType Test is Completed ---");
    }

    // This method will Update only UsageType
    private void updatePropertyTypeUsageTypeWithPropertyType(CreatePropertyTypeUsageTypeResponse createPropertyTypeUsageTypeResponse,
                                                             SearchPropertyTypesResponse searchPropertyTypesResponse, int propertyTypeUsageTypeId) throws IOException {
        new APILogger().log("Update PropertyType - UsageType With PropertyType Test is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        PropertyTypeUsageType propertyTypeUsageType;
        CreatePropertyTypeUsageTypeRequest updatePropertyTypeUsageTypeRequest;
        boolean foundCombination = false;
        CreatePropertyTypeUsageTypeResponse updatePropertyTypeUsageTypeResponse = null;

        // Recursively checking the every combination of PropertyType & UsageType
        for (int j = 0; j < searchPropertyTypesResponse.getPropertyTypes().length && !foundCombination; j++) {

            propertyTypeUsageType = new PropertyTypeUsageTypeBuilder()
                    .withPropertyType(searchPropertyTypesResponse.getPropertyTypes()[j].getName())
                    .withUsageType(createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getUsageType())
                    .build();
            updatePropertyTypeUsageTypeRequest = new CreatePropertyTypeUsageTypeRequestBuilder()
                    .withPropertyTypeUsageType(propertyTypeUsageType)
                    .withRequestInfo(requestInfo).build();

            Response response = new WCMSResource()
                    .updatePropertyTypeUsageType(RequestHelper.getJsonString(updatePropertyTypeUsageTypeRequest), propertyTypeUsageTypeId);

            if (response.getStatusCode() == 200 && !(propertyTypeUsageType.getPropertyType().equals(createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getPropertyType()))) {
                foundCombination = true;
                new APILogger().log("Update PropertyType - UsageType With PropertyType Test Request is Started with --" + RequestHelper.getJsonString(updatePropertyTypeUsageTypeRequest));
                new APILogger().log("Update PropertyType - UsageType With PropertyType Test Request is Generated as  --" + response.asString());
                updatePropertyTypeUsageTypeResponse = (CreatePropertyTypeUsageTypeResponse)
                        ResponseHelper.getResponseAsObject(response.asString(), CreatePropertyTypeUsageTypeResponse.class);

                Assert.assertTrue(updatePropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes().length == 1);
                Assert.assertNotEquals(propertyTypeUsageType.getPropertyType(), createPropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getPropertyType());

                Assert.assertEquals(propertyTypeUsageType.getUsageType(), updatePropertyTypeUsageTypeResponse.getPropertyTypeUsageTypes()[0].getUsageType());
                break;
            }
        }
        new APILogger().log("Update PropertyType - UsageType With PropertyType Test is Completed ---");
    }
}
