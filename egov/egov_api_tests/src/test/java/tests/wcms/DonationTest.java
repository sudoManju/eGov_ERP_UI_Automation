package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.donation.create.CreateDonationRequestBuilder;
import builders.wcms.donation.create.DonationBuilder;
import builders.wcms.donation.search.SearchDonationRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.donation.create.CreateDonationRequest;
import entities.requests.wcms.donation.create.Donation;
import entities.requests.wcms.donation.search.SearchDonationRequest;
import entities.responses.propertyTax.masters.propertyTypes.search.PropertyTypes;
import entities.responses.propertyTax.masters.propertyTypes.search.SearchPropertyTypesResponse;
import entities.responses.propertyTax.masters.usage.search.SearchUsageMasterResponse;
import entities.responses.propertyTax.masters.usage.search.UsageMasters;
import entities.responses.wcms.categoryType.create.CategoryTypes;
import entities.responses.wcms.categoryType.create.CreateCategoryTypeResponse;
import entities.responses.wcms.donation.CreateDonationResponse;
import entities.responses.wcms.pipeSize.CreatePipeSizeResponse;
import entities.responses.wcms.pipeSize.PipeSizes;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import tests.propertyTax.masters.PTISMasterSearchHelper;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.*;
import static data.UserData.MANAS;

public class DonationTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateDonationTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDonationResponse createDonationResponse = createDonation(); // Create Donation
        CreateDonationResponse searchDonationResponse = searchDonation(createDonationResponse, WITH_PARAMETER, "BEFORE_UPDATE"); // Search Donation
        CreateDonationResponse updateDonationWithPropertyTypeResponse = updateDonationWithPropertyType(createDonationResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With PropertyType
        CreateDonationResponse updateDonationWithCategoryTypeResponse = updateDonationWithCategoryType(updateDonationWithPropertyTypeResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With CategoryType
        CreateDonationResponse updateDonationWithUsageTypeResponse = updateDonationWithUsageType(updateDonationWithCategoryTypeResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With UsageType
        CreateDonationResponse updateDonationWithPipeSizeResponse = updateDonationWithPipeSizeType(updateDonationWithUsageTypeResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With PipeSize
        searchDonation(updateDonationWithPipeSizeResponse, WITH_ID, "AFTER_UPDATE"); // Search Donation After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void getAllDonationTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        searchDonation(null, "ALL", "ALL"); // Search All Donations
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateDonationTestWithPropertyType() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDonationResponse createDonationResponse = createDonation(); // Create Donation
        CreateDonationResponse searchDonationResponse = searchDonation(createDonationResponse, WITH_PARAMETER, "BEFORE_UPDATE"); // Search Donation
        CreateDonationResponse updateDonationWithPropertyTypeResponse = updateDonationWithPropertyType(createDonationResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With PropertyType
        searchDonation(updateDonationWithPropertyTypeResponse, WITH_ID, "PROPERTY_TYPE_UPDATE"); // Search Donation After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateDonationTestWithCategoryType() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDonationResponse createDonationResponse = createDonation(); // Create Donation
        CreateDonationResponse searchDonationResponse = searchDonation(createDonationResponse, WITH_PARAMETER, "BEFORE_UPDATE"); // Search Donation
        CreateDonationResponse updateDonationWithCategoryTypeResponse = updateDonationWithCategoryType(createDonationResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With CategoryType
        searchDonation(updateDonationWithCategoryTypeResponse, WITH_ID, "CATEGORY_TYPE_UPDATE"); // Search Donation After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateDonationTestWithUsageType() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDonationResponse createDonationResponse = createDonation(); // Create Donation
        CreateDonationResponse searchDonationResponse = searchDonation(createDonationResponse, WITH_PARAMETER, "BEFORE_UPDATE"); // Search Donation
        CreateDonationResponse updateDonationWithUsageTypeResponse = updateDonationWithUsageType(createDonationResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With UsageType
        searchDonation(updateDonationWithUsageTypeResponse, WITH_ID, "USAGE_TYPE_UPDATE"); // Search Donation After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    @Test(groups = {Categories.SANITY, Categories.WCMS})
    public void createSearchUpdateDonationTestWithPipeSize() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        CreateDonationResponse createDonationResponse = createDonation(); // Create Donation
        CreateDonationResponse searchDonationResponse = searchDonation(createDonationResponse, WITH_PARAMETER, "BEFORE_UPDATE"); // Search Donation
        CreateDonationResponse updateDonationWithPipeSizeResponse = updateDonationWithPipeSizeType(createDonationResponse, searchDonationResponse.getDonations()[0].getId()); // Update Donation With PipeSize
        searchDonation(updateDonationWithPipeSizeResponse, WITH_ID, "PIPESIZE_TYPE_UPDATE"); // Search Donation After Update
        LoginAndLogoutHelper.logout(); // Logout
    }

    private CreateDonationResponse createDonation() throws IOException {
        new APILogger().log("Create Donation Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        CreateCategoryTypeResponse searchCategoryTypeResponse = new CategoryTypeTest().getAllCategoryTypes(); // Get CategoryTypes
        SearchUsageMasterResponse searchUsageMasterResponse = new PTISMasterSearchHelper().getAllUsageTypes(); // Get UsageTypes
        CreatePipeSizeResponse searchPipeSizeResponse = new PipeSizesTest().getAllPipeSizes(); // Get All PipeSizes

        String pipeSize = String.valueOf(searchPipeSizeResponse.getPipeSizes()[getRandomIntFromRange(0, searchPipeSizeResponse.getPipeSizes().length)].getSizeInMilimeter());
        Donation donation = new DonationBuilder()
                .withPropertyType(searchPropertyTypesResponse.getPropertyTypes()[getRandomIntFromRange(0, searchPropertyTypesResponse.getPropertyTypes().length)].getName())
                .withCategory(searchCategoryTypeResponse.getCategory()[getRandomIntFromRange(0, searchCategoryTypeResponse.getCategory().length)].getName())
                .withUsageType(searchUsageMasterResponse.getUsageMasters()[getRandomIntFromRange(0, searchUsageMasterResponse.getUsageMasters().length)].getName())
                .withMaxPipeSize(pipeSize)
                .withMinPipeSize(pipeSize)
                .build();
        CreateDonationRequest createDonationRequest = new CreateDonationRequestBuilder()
                .withRequestInfo(requestInfo)
                .withDonation(donation).build();

        Response response = new WCMSResource().createDonationResource(RequestHelper.getJsonString(createDonationRequest));
        CreateDonationResponse createDonationResponse = (CreateDonationResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDonationResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createDonationResponse.getDonations()[0].getPropertyType(), donation.getPropertyType());
        Assert.assertEquals(createDonationResponse.getDonations()[0].getCategory(), donation.getCategory());
        Assert.assertEquals(createDonationResponse.getDonations()[0].getUsageType(), donation.getUsageType());
        Assert.assertEquals(String.valueOf(createDonationResponse.getDonations()[0].getMaxPipeSize()), donation.getMaxPipeSize());
        new APILogger().log("Create Donation Test Request is Completed ---");
        return createDonationResponse;
    }

    private CreateDonationResponse searchDonation(CreateDonationResponse response, String parameter, String switchParameter) throws IOException {
        new APILogger().log("Search Donation Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchDonationRequest searchDonationRequest = new SearchDonationRequestBuilder().withRequestInfo(requestInfo).build();

        String path = "";
        if (parameter.equalsIgnoreCase(""))
            path = WITH_PROPERTY_TYPE + response.getDonations()[0].getPropertyType() +
                    WITH_CATEGORY_TYPE + response.getDonations()[0].getCategory() +
                    WITH_PIPESIZE + response.getDonations()[0].getMaxPipeSize() +
                    WITH_USAGE_TYPE + response.getDonations()[0].getUsageType();
        else if (parameter.contains("&id"))
            path = pathBuilder(WITH_ID, String.valueOf(response.getDonations()[0].getId()));

        Response response1 = new WCMSResource().searchDonationResource(RequestHelper.getJsonString(searchDonationRequest), path);
        CreateDonationResponse searchDonationResponse = (CreateDonationResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), CreateDonationResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);

        switch (switchParameter) {

            case "ALL":
                Assert.assertTrue(searchDonationResponse.getDonations().length > 0);
                break;

            case "BEFORE_UPDATE":
                Assert.assertEquals(response.getDonations()[0].getCategory(), searchDonationResponse.getDonations()[0].getCategory());
                Assert.assertEquals(response.getDonations()[0].getMaxPipeSize(), searchDonationResponse.getDonations()[0].getMaxPipeSize());
                break;

            case "AFTER_UPDATE":
                Assert.assertNotEquals(response.getDonations()[0].getCategory(), searchDonationResponse.getDonations()[0].getCategory());
                Assert.assertNotEquals(response.getDonations()[0].getMaxPipeSize(), searchDonationResponse.getDonations()[0].getMaxPipeSize());
                break;

            case "PROPERTY_TYPE_UPDATE":
                Assert.assertNotEquals(response.getDonations()[0].getPropertyTypeId(), searchDonationResponse.getDonations()[0].getPropertyTypeId());
                break;

            case "CATEGORY_TYPE_UPDATE":
                Assert.assertNotEquals(response.getDonations()[0].getCategory(), searchDonationResponse.getDonations()[0].getCategory());
                break;

            case "USAGE_TYPE_UPDATE":
                Assert.assertNotEquals(response.getDonations()[0].getUsageTypeId(), searchDonationResponse.getDonations()[0].getUsageTypeId());
                break;

            case "PIPESIZE_TYPE_UPDATE":
                Assert.assertNotEquals(response.getDonations()[0].getMaxPipeSize(), searchDonationResponse.getDonations()[0].getMaxPipeSize());
                break;
        }

        new APILogger().log("Search Donation Test Request is Completed ---");
        return searchDonationResponse;
    }

    private CreateDonationResponse updateDonationWithPropertyType(CreateDonationResponse createDonationResponse, int id) throws IOException {
        new APILogger().log("Update Donation With PropertyType Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPropertyTypesResponse searchPropertyTypesResponse = new PTISMasterSearchHelper().searchAllPropertyTypes(); // Get PropertyTypes
        PropertyTypes[] propertyTypesList = searchPropertyTypesResponse.getPropertyTypes();

        String propertyTypeName = createDonationResponse.getDonations()[0].getPropertyType();
        for (PropertyTypes propertyType : propertyTypesList) {
            if (!(propertyType.getName().equals(propertyTypeName))) {
                propertyTypeName = propertyType.getName();
                break;
            }

        }

        Donation donation = new DonationBuilder()
                .withPropertyType(propertyTypeName)
                .withCategory(createDonationResponse.getDonations()[0].getCategory())
                .withUsageType(createDonationResponse.getDonations()[0].getUsageType())
                .withMaxPipeSize(String.valueOf(createDonationResponse.getDonations()[0].getMaxPipeSize()))
                .withMinPipeSize(String.valueOf(createDonationResponse.getDonations()[0].getMinPipeSize()))
                .build();
        CreateDonationRequest updateDonationRequest = new CreateDonationRequestBuilder()
                .withRequestInfo(requestInfo)
                .withDonation(donation).build();

        Response response = new WCMSResource().updateDonationResource(RequestHelper.getJsonString(updateDonationRequest), id);
        CreateDonationResponse updateDonationWithPropertyTypeResponse = (CreateDonationResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateDonationResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(createDonationResponse.getDonations()[0].getPropertyType(), updateDonationWithPropertyTypeResponse.getDonations()[0].getPropertyType());
        Assert.assertEquals(createDonationResponse.getDonations()[0].getCategory(), updateDonationWithPropertyTypeResponse.getDonations()[0].getCategory());
        Assert.assertEquals(createDonationResponse.getDonations()[0].getUsageType(), updateDonationWithPropertyTypeResponse.getDonations()[0].getUsageType());
        Assert.assertEquals(createDonationResponse.getDonations()[0].getMaxPipeSize(), updateDonationWithPropertyTypeResponse.getDonations()[0].getMaxPipeSize());
        new APILogger().log("Update Donation With PropertyType Test Request is Completed ---");
        return updateDonationWithPropertyTypeResponse;
    }

    private CreateDonationResponse updateDonationWithCategoryType(CreateDonationResponse response, int id) throws IOException {
        new APILogger().log("Update Donation With CategoryType Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateCategoryTypeResponse searchCategoryTypeResponse = new CategoryTypeTest().getAllCategoryTypes(); // Get CategoryTypes
        CategoryTypes[] categoryTypesList = searchCategoryTypeResponse.getCategory();

        String categoryType = response.getDonations()[0].getCategory();
        for (CategoryTypes category : categoryTypesList) {
            if (!(category.getName().equals(categoryType))) {
                categoryType = category.getName();
                break;
            }

        }

        Donation donation = new DonationBuilder()
                .withPropertyType(response.getDonations()[0].getPropertyType())
                .withCategory(categoryType)
                .withUsageType(response.getDonations()[0].getUsageType())
                .withMaxPipeSize(String.valueOf(response.getDonations()[0].getMaxPipeSize()))
                .withMinPipeSize(String.valueOf(response.getDonations()[0].getMinPipeSize()))
                .build();
        CreateDonationRequest updateDonationRequest = new CreateDonationRequestBuilder()
                .withRequestInfo(requestInfo)
                .withDonation(donation).build();

        Response response1 = new WCMSResource().updateDonationResource(RequestHelper.getJsonString(updateDonationRequest), id);
        CreateDonationResponse updateDonationWithCategoryTypeResponse = (CreateDonationResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), CreateDonationResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);
        Assert.assertEquals(response.getDonations()[0].getPropertyType(), updateDonationWithCategoryTypeResponse.getDonations()[0].getPropertyType());
        Assert.assertNotEquals(response.getDonations()[0].getCategory(), updateDonationWithCategoryTypeResponse.getDonations()[0].getCategory());
        Assert.assertEquals(response.getDonations()[0].getUsageType(), updateDonationWithCategoryTypeResponse.getDonations()[0].getUsageType());
        Assert.assertEquals(response.getDonations()[0].getMaxPipeSize(), updateDonationWithCategoryTypeResponse.getDonations()[0].getMaxPipeSize());
        new APILogger().log("Update Donation With Category Test Request is Completed ---");
        return updateDonationWithCategoryTypeResponse;

    }

    private CreateDonationResponse updateDonationWithUsageType(CreateDonationResponse response, int id) throws IOException {
        new APILogger().log("Update Donation With UsageType Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchUsageMasterResponse searchUsageMasterResponse = new PTISMasterSearchHelper().getAllUsageTypes(); // Get UsageTypes
        UsageMasters[] usageMastersList = searchUsageMasterResponse.getUsageMasters();

        String usageType = response.getDonations()[0].getCategory();
        for (UsageMasters usage : usageMastersList) {
            if (!(usage.getName().equals(usageType))) {
                usageType = usage.getName();
                break;
            }

        }

        Donation donation = new DonationBuilder()
                .withPropertyType(response.getDonations()[0].getPropertyType())
                .withCategory(response.getDonations()[0].getCategory())
                .withUsageType(usageType)
                .withMaxPipeSize(String.valueOf(response.getDonations()[0].getMaxPipeSize()))
                .withMinPipeSize(String.valueOf(response.getDonations()[0].getMinPipeSize()))
                .build();
        CreateDonationRequest updateDonationRequest = new CreateDonationRequestBuilder()
                .withRequestInfo(requestInfo)
                .withDonation(donation).build();

        Response response1 = new WCMSResource().updateDonationResource(RequestHelper.getJsonString(updateDonationRequest), id);
        CreateDonationResponse updateDonationWithUsageTypeResponse = (CreateDonationResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), CreateDonationResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);
        Assert.assertEquals(response.getDonations()[0].getPropertyType(), updateDonationWithUsageTypeResponse.getDonations()[0].getPropertyType());
        Assert.assertEquals(response.getDonations()[0].getCategory(), updateDonationWithUsageTypeResponse.getDonations()[0].getCategory());
        Assert.assertNotEquals(response.getDonations()[0].getUsageType(), updateDonationWithUsageTypeResponse.getDonations()[0].getUsageType());
        Assert.assertEquals(response.getDonations()[0].getMaxPipeSize(), updateDonationWithUsageTypeResponse.getDonations()[0].getMaxPipeSize());
        new APILogger().log("Update Donation With UsageType Test Request is Completed ---");
        return updateDonationWithUsageTypeResponse;
    }

    private CreateDonationResponse updateDonationWithPipeSizeType(CreateDonationResponse response, int id) throws IOException {
        new APILogger().log("Update Donation With PipeSize Test Request is Started ---");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreatePipeSizeResponse searchPipeSizeResponse = new PipeSizesTest().getAllPipeSizes(); // Get All PipeSizes
        PipeSizes[] PipeSizes = searchPipeSizeResponse.getPipeSizes();

        String pipeSizeInMilliMeter = String.valueOf(response.getDonations()[0].getMaxPipeSize());
        for (PipeSizes pipeSize : PipeSizes) {
            if (!(String.valueOf(pipeSize.getSizeInMilimeter()).equals(pipeSizeInMilliMeter))) {
                pipeSizeInMilliMeter = String.valueOf(pipeSize.getSizeInMilimeter());
                break;
            }

        }

        Donation donation = new DonationBuilder()
                .withPropertyType(response.getDonations()[0].getPropertyType())
                .withCategory(response.getDonations()[0].getCategory())
                .withUsageType(response.getDonations()[0].getUsageType())
                .withMaxPipeSize(pipeSizeInMilliMeter)
                .withMinPipeSize(pipeSizeInMilliMeter)
                .build();
        CreateDonationRequest updateDonationRequest = new CreateDonationRequestBuilder()
                .withRequestInfo(requestInfo)
                .withDonation(donation).build();

        Response response1 = new WCMSResource().updateDonationResource(RequestHelper.getJsonString(updateDonationRequest), id);
        CreateDonationResponse updateDonationWithPipeSizeResponse = (CreateDonationResponse)
                ResponseHelper.getResponseAsObject(response1.asString(), CreateDonationResponse.class);

        Assert.assertEquals(response1.getStatusCode(), 200);
        Assert.assertEquals(response.getDonations()[0].getPropertyType(), updateDonationWithPipeSizeResponse.getDonations()[0].getPropertyType());
        Assert.assertEquals(response.getDonations()[0].getCategory(), updateDonationWithPipeSizeResponse.getDonations()[0].getCategory());
        Assert.assertEquals(response.getDonations()[0].getUsageType(), updateDonationWithPipeSizeResponse.getDonations()[0].getUsageType());
        Assert.assertNotEquals(response.getDonations()[0].getMaxPipeSize(), updateDonationWithPipeSizeResponse.getDonations()[0].getMaxPipeSize());
        new APILogger().log("Update Donation With PipeSize Test Request is Completed ---");
        return updateDonationWithPipeSizeResponse;
    }
}
