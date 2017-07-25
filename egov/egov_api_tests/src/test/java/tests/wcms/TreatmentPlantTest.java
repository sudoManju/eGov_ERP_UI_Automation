package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.getPlantTypes.SearchPlantTypesRequestBuilder;
import builders.wcms.searchBoundary.SearchBoundaryRequestBuilder;
import builders.wcms.treatmentPlant.create.CreateTreatmentPlantRequestBuilder;
import builders.wcms.treatmentPlant.create.TreatmentPlantsBuilder;
import builders.wcms.treatmentPlant.search.SearchTreatmentPlantRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.getPlantTypes.SearchPlantTypesRequest;
import entities.requests.wcms.searchBoundary.SearchBoundaryRequest;
import entities.requests.wcms.treatmentPlant.create.CreateTreatmentPlantRequest;
import entities.requests.wcms.treatmentPlant.create.TreatmentPlants;
import entities.requests.wcms.treatmentPlant.search.SearchTreatmentPlantRequest;
import entities.responses.wcms.getPlantTypes.SearchPlantTypesResponse;
import entities.responses.wcms.searchBoundary.Boundary;
import entities.responses.wcms.searchBoundary.SearchBoundaryResponse;
import entities.responses.wcms.storageReservoir.CreateStorageReservoirResponse;
import entities.responses.wcms.storageReservoir.StorageReservoirs;
import entities.responses.wcms.treatmentPlant.CreateTreatmentPlantResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.SearchParameterData.*;
import static data.SearchParameterData.WITH_HIERARCHY_LOCATION;
import static data.UserData.MANAS;

public class TreatmentPlantTest extends BaseAPITest {

    private SearchBoundaryResponse searchBoundaryResponseWithZone;
    private SearchBoundaryResponse searchBoundaryResponseWithWard;
    private SearchBoundaryResponse searchBoundaryResponseWithLocation;
    private SearchPlantTypesResponse searchPlantTypesResponse;
    private CreateStorageReservoirResponse searchStorageReservoirResponse;

    @Test(groups = {Categories.WCMS, Categories.SANITY})
    public void createSearchAndUpdateTreatmentPlantTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        searchBoundaryResponseWithZone = searchBoundaryWith(WITH_BOUNDARY_ZONE, WITH_HIERARCHY_REVENUE); // Search Boundary With Zone
        searchBoundaryResponseWithWard = searchBoundaryWith(WITH_BOUNDARY_WARD, WITH_HIERARCHY_REVENUE); // Search Boundary With Ward
        searchBoundaryResponseWithLocation = searchBoundaryWith(WITH_BOUNDARY_LOCALITY, WITH_HIERARCHY_LOCATION); // Search Boundary With Location
        searchPlantTypesResponse = searchPlants(); // Search Plants
        searchStorageReservoirResponse = new StorageReservoirTest().getAllStorageReservoirs(); // Get All StorageReservoir's

        CreateTreatmentPlantResponse createTreatmentPlantResponse = createTreatmentPlant(2); // Create TreatmentPlant
        searchTreatmentPlant(createTreatmentPlantResponse, WITH_CODE, "BEFORE_UPDATE"); // Search TreatmentPlant With CODE
        CreateTreatmentPlantResponse[] searchTreatmentPlantResponseList = searchTreatmentPlant(createTreatmentPlantResponse, WITH_NAME, "BEFORE_UPDATE"); // Search TreatmentPlant With NAME

        CreateTreatmentPlantResponse updateTreatmentPlantResponseWithZone = updateTreatmentPlantWithZoneName(createTreatmentPlantResponse); // Update TreatmentPlant With Zone Name
        CreateTreatmentPlantResponse updateTreatmentPlantResponseWithLocation = updateTreatmentPlantWithLocationName(updateTreatmentPlantResponseWithZone); // Update TreatmentPlant With Location Name
        CreateTreatmentPlantResponse updateTreatmentPlantResponseWithWard = updateTreatmentPlantWithWardName(updateTreatmentPlantResponseWithLocation); // Update TreatmentPlant With Ward Name
        updateTreatmentPlantWithStorageReservoir(updateTreatmentPlantResponseWithWard); // Update TreatmentPlant With StorageReservoir

        for (CreateTreatmentPlantResponse searchResponse : searchTreatmentPlantResponseList)
            searchTreatmentPlant(searchResponse, WITH_CODE, "AFTER_UPDATE"); // Search TreatmentPlant With CODE
        LoginAndLogoutHelper.logout(); // Logout
    }

    private SearchBoundaryResponse searchBoundaryWith(String boundary, String hierarchy) throws IOException {
        new APILogger().log("Search All Boundary With " + boundary + " And Hierarchy With " + hierarchy + " Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchBoundaryRequest searchBoundaryRequest = new SearchBoundaryRequestBuilder().withRequestInfo(requestInfo).build();

        String path = boundary + hierarchy;

        Response response = new WCMSResource().getBoundaries(RequestHelper.getJsonString(searchBoundaryRequest), path);
        SearchBoundaryResponse searchBoundaryResponse = (SearchBoundaryResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchBoundaryResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(searchBoundaryResponse.getBoundary().length > 0);
        new APILogger().log("Search All Boundary With " + boundary + " And Hierarchy With " + hierarchy + " Test Request is Completed --");
        return searchBoundaryResponse;
    }

    private SearchPlantTypesResponse searchPlants() throws IOException {
        new APILogger().log("Search All Plants Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchPlantTypesRequest searchPlantTypesRequest = new SearchPlantTypesRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().getPlantTypesResource(RequestHelper.getJsonString(searchPlantTypesRequest));
        SearchPlantTypesResponse searchPlantTypesResponse = (SearchPlantTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchPlantTypesResponse.class);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(searchPlantTypesResponse.getDataModelList().length > 0);
        new APILogger().log("Search All Plants Test Request is Completed --");
        return searchPlantTypesResponse;
    }

    private CreateTreatmentPlantResponse createTreatmentPlant(int bulk) throws IOException {
        new APILogger().log("Create TreatmentPlant Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        TreatmentPlants[] treatmentPlantsList = new TreatmentPlants[bulk];

        for (int i = 0; i < bulk; i++) {
            TreatmentPlants treatmentPlants = new TreatmentPlantsBuilder()
                    .withPlantType(searchPlantTypesResponse.getDataModelList()[getRandomIntFromRange(0, searchPlantTypesResponse.getDataModelList().length)].getKey())
                    .withStorageReservoirName(searchStorageReservoirResponse.getStorageReservoirs()[getRandomIntFromRange(0, searchStorageReservoirResponse.getStorageReservoirs().length)].getName())
                    .withZoneName(searchBoundaryResponseWithZone.getBoundary()[getRandomIntFromRange(0, searchBoundaryResponseWithZone.getBoundary().length)].getName())
                    .withWardName(searchBoundaryResponseWithWard.getBoundary()[getRandomIntFromRange(0, searchBoundaryResponseWithWard.getBoundary().length)].getName())
                    .withLocationName(searchBoundaryResponseWithLocation.getBoundary()[getRandomIntFromRange(0, searchBoundaryResponseWithLocation.getBoundary().length)].getName())
                    .build();

            treatmentPlantsList[i] = treatmentPlants;
        }
        CreateTreatmentPlantRequest createTreatmentPlantRequest = new CreateTreatmentPlantRequestBuilder()
                .withRequestInfo(requestInfo)
                .withTreatmentPlants(treatmentPlantsList)
                .build();

        Response response = new WCMSResource().createTreatmentPlantResource(RequestHelper.getJsonString(createTreatmentPlantRequest));
        CreateTreatmentPlantResponse createTreatmentPlantResponse = (CreateTreatmentPlantResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateTreatmentPlantResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        for (int i = 0; i < bulk; i++) {
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getName(), treatmentPlantsList[i].getName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType(), treatmentPlantsList[i].getPlantType());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName(), treatmentPlantsList[i].getStorageReservoirName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName(), treatmentPlantsList[i].getZoneName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getWardName(), treatmentPlantsList[i].getWardName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName(), treatmentPlantsList[i].getLocationName());
        }
        new APILogger().log("Create TreatmentPlant Test Request is Completed --");
        return createTreatmentPlantResponse;
    }

    private CreateTreatmentPlantResponse[] searchTreatmentPlant(CreateTreatmentPlantResponse response1, String parameter, String switchParameter) throws IOException {
        new APILogger().log("Search TreatmentPlant With " + parameter + " Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        CreateTreatmentPlantResponse[] searchTreatmentPlantList = new CreateTreatmentPlantResponse[response1.getTreatmentPlants().length];
        for (int i = 0; i < response1.getTreatmentPlants().length; i++) {

            SearchTreatmentPlantRequest searchTreatmentPlantRequest = new SearchTreatmentPlantRequestBuilder()
                    .withRequestInfo(requestInfo).build();

            String path = "";
            if (parameter.contains("code"))
                path = pathBuilder(parameter, response1.getTreatmentPlants()[i].getCode());
            else if (parameter.contains("name"))
                path = pathBuilder(parameter, response1.getTreatmentPlants()[i].getName());

            Response response = new WCMSResource().searchTreatmentPlantResource(RequestHelper.getJsonString(searchTreatmentPlantRequest), path);
            CreateTreatmentPlantResponse searchTreatmentPlantResponse = (CreateTreatmentPlantResponse)
                    ResponseHelper.getResponseAsObject(response.asString(), CreateTreatmentPlantResponse.class);
            searchTreatmentPlantList[i] = searchTreatmentPlantResponse;

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertTrue(searchTreatmentPlantResponse.getTreatmentPlants().length == 1);
            switch (switchParameter) {
                case "BEFORE_UPDATE":
                    Assert.assertEquals(response1.getTreatmentPlants()[i].getName(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getName());
                    Assert.assertEquals(response1.getTreatmentPlants()[i].getPlantType(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getPlantType());
                    Assert.assertEquals(response1.getTreatmentPlants()[i].getStorageReservoirName(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getStorageReservoirName());
                    break;

                case "AFTER_UPDATE":
//                    Assert.assertNotEquals(response1.getTreatmentPlants()[i].getName(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getName());
                    Assert.assertNotEquals(response1.getTreatmentPlants()[i].getStorageReservoirName(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getStorageReservoirName());
                    Assert.assertNotEquals(response1.getTreatmentPlants()[i].getWard(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getWard());
                    Assert.assertNotEquals(response1.getTreatmentPlants()[i].getLocation(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getLocation());
                    Assert.assertNotEquals(response1.getTreatmentPlants()[i].getZone(), searchTreatmentPlantResponse.getTreatmentPlants()[0].getZone());
                    break;
            }
        }
        new APILogger().log("Search TreatmentPlant With " + parameter + " Test Request is Completed --");
        return searchTreatmentPlantList;
    }

    private CreateTreatmentPlantResponse updateTreatmentPlantWithZoneName(CreateTreatmentPlantResponse createTreatmentPlantResponse) throws IOException {
        new APILogger().log("Update TreatmentPlant With Zone Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        TreatmentPlants[] treatmentPlantsList = new TreatmentPlants[createTreatmentPlantResponse.getTreatmentPlants().length];

        for (int i = 0; i < createTreatmentPlantResponse.getTreatmentPlants().length; i++) {
            String zoneName = createTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName();
            Boundary[] boundariesWithZone = searchBoundaryResponseWithZone.getBoundary();

            for (Boundary boundary : boundariesWithZone) {
                if (!(boundary.getName().equals(zoneName))) {
                    zoneName = boundary.getName();
                    break;
                }
            }
            TreatmentPlants treatmentPlants = new TreatmentPlantsBuilder()
                    .withName(createTreatmentPlantResponse.getTreatmentPlants()[i].getName())
                    .withCode(createTreatmentPlantResponse.getTreatmentPlants()[i].getCode())
                    .withPlantType(createTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType())
                    .withStorageReservoirName(createTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName())
                    .withZoneName(zoneName)
                    .withWardName(createTreatmentPlantResponse.getTreatmentPlants()[i].getWardName())
                    .withLocationName(createTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName())
                    .build();

            treatmentPlantsList[i] = treatmentPlants;
        }

        CreateTreatmentPlantRequest updateTreatmentPlantRequest = new CreateTreatmentPlantRequestBuilder()
                .withRequestInfo(requestInfo)
                .withTreatmentPlants(treatmentPlantsList)
                .build();

        Response response = new WCMSResource().updateTreatmentPlantResource(RequestHelper.getJsonString(updateTreatmentPlantRequest));
        CreateTreatmentPlantResponse updateTreatmentPlantResponse = (CreateTreatmentPlantResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateTreatmentPlantResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        for (int i = 0; i < createTreatmentPlantResponse.getTreatmentPlants().length; i++) {
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName());
            Assert.assertNotEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getWardName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getWardName());
            Assert.assertEquals(createTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName());
        }
        new APILogger().log("Update TreatmentPlant With Zone Test Request is Completed --");
        return updateTreatmentPlantResponse;
    }

    private CreateTreatmentPlantResponse updateTreatmentPlantWithLocationName(CreateTreatmentPlantResponse updateTreatmentPlantResponseWithZone) throws IOException {
        new APILogger().log("Update TreatmentPlant With Location Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        TreatmentPlants[] treatmentPlantsList = new TreatmentPlants[updateTreatmentPlantResponseWithZone.getTreatmentPlants().length];

        for (int i = 0; i < updateTreatmentPlantResponseWithZone.getTreatmentPlants().length; i++) {

            String locationName = updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getLocationName();
            Boundary[] boundariesWithZone = searchBoundaryResponseWithLocation.getBoundary();

            for (Boundary boundary : boundariesWithZone) {
                if (!(boundary.getName().equals(locationName))) {
                    locationName = boundary.getName();
                    break;
                }
            }
            TreatmentPlants treatmentPlants = new TreatmentPlantsBuilder()
                    .withName(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getName())
                    .withCode(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getCode())
                    .withPlantType(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getPlantType())
                    .withStorageReservoirName(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getStorageReservoirName())
                    .withZoneName(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getZoneName())
                    .withWardName(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getWardName())
                    .withLocationName(locationName)
                    .build();
            treatmentPlantsList[i] = treatmentPlants;
        }
        CreateTreatmentPlantRequest updateTreatmentPlantRequest = new CreateTreatmentPlantRequestBuilder()
                .withRequestInfo(requestInfo)
                .withTreatmentPlants(treatmentPlantsList)
                .build();

        Response response = new WCMSResource().updateTreatmentPlantResource(RequestHelper.getJsonString(updateTreatmentPlantRequest));
        CreateTreatmentPlantResponse updateTreatmentPlantResponse = (CreateTreatmentPlantResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateTreatmentPlantResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        for (int i = 0; i < updateTreatmentPlantResponseWithZone.getTreatmentPlants().length; i++) {
            Assert.assertEquals(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getName());
            Assert.assertEquals(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getPlantType(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType());
            Assert.assertEquals(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getStorageReservoirName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName());
            Assert.assertEquals(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getZoneName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName());
            Assert.assertEquals(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getWardName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getWardName());
            Assert.assertNotEquals(updateTreatmentPlantResponseWithZone.getTreatmentPlants()[i].getLocationName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName());
        }
        new APILogger().log("Update TreatmentPlant With Location Test Request is Completed --");
        return updateTreatmentPlantResponse;
    }

    private CreateTreatmentPlantResponse updateTreatmentPlantWithWardName(CreateTreatmentPlantResponse updateTreatmentPlantResponseWithLocation) throws IOException {
        new APILogger().log("Update TreatmentPlant With Ward Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        TreatmentPlants[] treatmentPlantsList = new TreatmentPlants[updateTreatmentPlantResponseWithLocation.getTreatmentPlants().length];

        for (int i = 0; i < updateTreatmentPlantResponseWithLocation.getTreatmentPlants().length; i++) {

            String wardName = updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getWardName();
            Boundary[] boundariesWithZone = searchBoundaryResponseWithWard.getBoundary();

            for (Boundary boundary : boundariesWithZone) {
                if (!(boundary.getName().equals(wardName))) {
                    wardName = boundary.getName();
                    break;
                }
            }

            TreatmentPlants treatmentPlants = new TreatmentPlantsBuilder()
                    .withName(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getName())
                    .withCode(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getCode())
                    .withPlantType(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getPlantType())
                    .withStorageReservoirName(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getStorageReservoirName())
                    .withZoneName(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getZoneName())
                    .withWardName(wardName)
                    .withLocationName(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getLocationName())
                    .build();

            treatmentPlantsList[i] = treatmentPlants;
        }
        CreateTreatmentPlantRequest updateTreatmentPlantRequest = new CreateTreatmentPlantRequestBuilder()
                .withRequestInfo(requestInfo)
                .withTreatmentPlants(treatmentPlantsList)
                .build();

        Response response = new WCMSResource().updateTreatmentPlantResource(RequestHelper.getJsonString(updateTreatmentPlantRequest));
        CreateTreatmentPlantResponse updateTreatmentPlantResponse = (CreateTreatmentPlantResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateTreatmentPlantResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        for (int i = 0; i < updateTreatmentPlantResponseWithLocation.getTreatmentPlants().length; i++) {
            Assert.assertEquals(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getName());
            Assert.assertEquals(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getPlantType(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType());
            Assert.assertEquals(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getStorageReservoirName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName());
            Assert.assertEquals(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getZoneName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName());
            Assert.assertNotEquals(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getWardName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getWardName());
            Assert.assertEquals(updateTreatmentPlantResponseWithLocation.getTreatmentPlants()[i].getLocationName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName());
        }
        new APILogger().log("Update TreatmentPlant With Ward Test Request is Completed --");
        return updateTreatmentPlantResponse;
    }

    private CreateTreatmentPlantResponse updateTreatmentPlantWithStorageReservoir(CreateTreatmentPlantResponse updateTreatmentPlantResponseWithWard) throws IOException {
        new APILogger().log("Update TreatmentPlant With StorageReservoir Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        TreatmentPlants[] treatmentPlantsList = new TreatmentPlants[updateTreatmentPlantResponseWithWard.getTreatmentPlants().length];

        for (int i = 0; i < updateTreatmentPlantResponseWithWard.getTreatmentPlants().length; i++) {

            String storageReservoirName = updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getStorageReservoirName();
            StorageReservoirs[] storageReservoirs = searchStorageReservoirResponse.getStorageReservoirs();

            for (StorageReservoirs s : storageReservoirs) {
                if (!(s.getName().equals(storageReservoirName))) {
                    storageReservoirName = s.getName();
                    break;
                }
            }

            TreatmentPlants treatmentPlants = new TreatmentPlantsBuilder()
                    .withName(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getName())
                    .withCode(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getCode())
                    .withPlantType(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getPlantType())
                    .withStorageReservoirName(storageReservoirName)
                    .withZoneName(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getZoneName())
                    .withWardName(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getWardName())
                    .withLocationName(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getLocationName())
                    .build();

            treatmentPlantsList[i] = treatmentPlants;
        }
        CreateTreatmentPlantRequest updateTreatmentPlantRequest = new CreateTreatmentPlantRequestBuilder()
                .withRequestInfo(requestInfo)
                .withTreatmentPlants(treatmentPlantsList)
                .build();

        Response response = new WCMSResource().updateTreatmentPlantResource(RequestHelper.getJsonString(updateTreatmentPlantRequest));
        CreateTreatmentPlantResponse updateTreatmentPlantResponse = (CreateTreatmentPlantResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateTreatmentPlantResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        for (int i = 0; i < updateTreatmentPlantResponseWithWard.getTreatmentPlants().length; i++) {
            Assert.assertEquals(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getName());
            Assert.assertEquals(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getPlantType(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getPlantType());
            Assert.assertNotEquals(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getStorageReservoirName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getStorageReservoirName());
            Assert.assertEquals(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getZoneName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getZoneName());
            Assert.assertEquals(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getWardName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getWardName());
            Assert.assertEquals(updateTreatmentPlantResponseWithWard.getTreatmentPlants()[i].getLocationName(), updateTreatmentPlantResponse.getTreatmentPlants()[i].getLocationName());
        }
        new APILogger().log("Update TreatmentPlant With StorageReservoir Test Request is Completed --");
        return updateTreatmentPlantResponse;
    }
}
