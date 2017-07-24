package tests.wcms;

import builders.wcms.RequestInfoBuilder;
import builders.wcms.getReservoirsTypes.SearchReservoirTypesRequestBuilder;
import builders.wcms.searchBoundary.SearchBoundaryRequestBuilder;
import builders.wcms.storageReservoir.create.CreateStorageReservoirRequestBuilder;
import builders.wcms.storageReservoir.create.StorageReservoirBuilder;
import builders.wcms.storageReservoir.search.SearchStorageReservoirRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.getReservoirsTypes.SearchReservoirTypesRequest;
import entities.requests.wcms.searchBoundary.SearchBoundaryRequest;
import entities.requests.wcms.storageReservoir.create.CreateStorageReservoirRequest;
import entities.requests.wcms.storageReservoir.create.StorageReservoir;
import entities.requests.wcms.storageReservoir.search.SearchStorageReservoirRequest;
import entities.responses.wcms.categoryType.create.CategoryTypes;
import entities.responses.wcms.getReservoirTypes.DataModelList;
import entities.responses.wcms.getReservoirTypes.SearchReservoirTypesResponse;
import entities.responses.wcms.searchBoundary.SearchBoundaryResponse;
import entities.responses.wcms.storageReservoir.CreateStorageReservoirResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.wcms.WCMSResource;
import tests.BaseAPITest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.SearchParameterData.*;
import static data.UserData.MANAS;

public class StorageReservoirTest extends BaseAPITest {

    private SearchBoundaryResponse searchBoundaryResponseWithZone;
    private SearchBoundaryResponse searchBoundaryResponseWithWard;
    private SearchBoundaryResponse searchBoundaryResponseWithLocation;
    private SearchReservoirTypesResponse searchReservoirTypesResponse;

    @Test
    public void createSearchAndUpdateStorageReservoirTest() throws IOException {
        LoginAndLogoutHelper.login(MANAS); // Login
        searchBoundaryResponseWithZone = searchBoundaryWith(WITH_BOUNDARY_ZONE, WITH_HIERARCHY_REVENUE); // Search Boundary With Zone
        searchBoundaryResponseWithWard = searchBoundaryWith(WITH_BOUNDARY_WARD, WITH_HIERARCHY_REVENUE); // Search Boundary With Ward
        searchBoundaryResponseWithLocation = searchBoundaryWith(WITH_BOUNDARY_LOCALITY, WITH_HIERARCHY_LOCATION); // Search Boundary With Location
        searchReservoirTypesResponse = searchReservoirTypes(); // Search ReservoirTypes

        CreateStorageReservoirResponse createStorageReservoirResponse = createStorageReservoir(); // Create Storage Reservoir
        CreateStorageReservoirResponse searchStorageReservoirResponse = searchStorageReservoir(createStorageReservoirResponse, WITH_NAME); // Search Storage Reservoir
        updateStorageReservoirWithReservoirType(createStorageReservoirResponse, searchStorageReservoirResponse.getStorageReservoirs()[0].getId()); // Update Storage Reservoir
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

    private SearchReservoirTypesResponse searchReservoirTypes() throws IOException {
        new APILogger().log("Search All Reservoir Types Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchReservoirTypesRequest searchReservoirTypesRequest = new SearchReservoirTypesRequestBuilder()
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().getReservoirTypes(RequestHelper.getJsonString(searchReservoirTypesRequest));
        SearchReservoirTypesResponse searchReservoirTypesResponse = (SearchReservoirTypesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchReservoirTypesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("Search All Reservoir Types Test Request is Completed --");
        return searchReservoirTypesResponse;
    }

    private CreateStorageReservoirResponse createStorageReservoir() throws IOException {
        new APILogger().log("Create Storage Reservoir Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        StorageReservoir[] storageReservoirs = new StorageReservoir[1];
        StorageReservoir storageReservoir = new StorageReservoirBuilder()
                .withZoneName(searchBoundaryResponseWithZone.getBoundary()[getRandomIntFromRange(0, searchBoundaryResponseWithZone.getBoundary().length)].getName())
                .withWardName(searchBoundaryResponseWithWard.getBoundary()[getRandomIntFromRange(0, searchBoundaryResponseWithWard.getBoundary().length)].getName())
                .withLocationName(searchBoundaryResponseWithLocation.getBoundary()[getRandomIntFromRange(0, searchBoundaryResponseWithLocation.getBoundary().length)].getName())
                .withReservoirType(searchReservoirTypesResponse.getDataModelList()[getRandomIntFromRange(0, searchReservoirTypesResponse.getDataModelList().length)].getKey())
                .build();
        storageReservoirs[0] = storageReservoir;
        CreateStorageReservoirRequest createStorageReservoirRequest = new CreateStorageReservoirRequestBuilder()
                .withStorageReservoir(storageReservoirs)
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().createStorageReservoir(RequestHelper.getJsonString(createStorageReservoirRequest));
        CreateStorageReservoirResponse createStorageReservoirResponse = (CreateStorageReservoirResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateStorageReservoirResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createStorageReservoirResponse.getStorageReservoirs()[0].getReservoirType(),
                storageReservoir.getReservoirType());
        Assert.assertEquals(createStorageReservoirResponse.getStorageReservoirs()[0].getLocationName(),
                storageReservoir.getLocationName());
        Assert.assertEquals(createStorageReservoirResponse.getStorageReservoirs()[0].getWardName(),
                storageReservoir.getWardName());
        Assert.assertEquals(createStorageReservoirResponse.getStorageReservoirs()[0].getZoneName(),
                storageReservoir.getZoneName());

        new APILogger().log("Create Storage Reservoir Test Request is Completed --");
        return createStorageReservoirResponse;
    }

    private CreateStorageReservoirResponse searchStorageReservoir(CreateStorageReservoirResponse createStorageReservoirResponse, String parameter) throws IOException {
        new APILogger().log("Search Storage Reservoir Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        SearchStorageReservoirRequest searchStorageReservoirRequest = new SearchStorageReservoirRequestBuilder()
                .withRequestInfo(requestInfo).build();

        String path = pathBuilder(parameter, createStorageReservoirResponse.getStorageReservoirs()[0].getName());
        Response response = new WCMSResource()
                .searchStorageReservoirResource(RequestHelper.getJsonString(searchStorageReservoirRequest), path);
        CreateStorageReservoirResponse searchStorageReservoirResponse = (CreateStorageReservoirResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateStorageReservoirResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(createStorageReservoirResponse.getStorageReservoirs()[0].getReservoirType(),
                searchStorageReservoirResponse.getStorageReservoirs()[0].getReservoirType());
        Assert.assertEquals(createStorageReservoirResponse.getStorageReservoirs()[0].getName(),
                searchStorageReservoirResponse.getStorageReservoirs()[0].getName());
        new APILogger().log("Search Storage Reservoir Test Request is Completed --");
        return searchStorageReservoirResponse;
    }

    private void updateStorageReservoirWithReservoirType(CreateStorageReservoirResponse createStorageReservoirResponse, int id) throws IOException {
        new APILogger().log("Update Storage Reservoir Test Request is Started --");
        RequestInfo requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();

        String reservoirType = createStorageReservoirResponse.getStorageReservoirs()[0].getReservoirType();
        DataModelList[] dataModelLists = searchReservoirTypesResponse.getDataModelList();
        for (DataModelList dataModel : dataModelLists) {
            if (!(dataModel.getKey().equals(reservoirType))) {
                reservoirType = dataModel.getKey();
                break;
            }

        }

        StorageReservoir[] storageReservoirs = new StorageReservoir[1];
        StorageReservoir storageReservoir = new StorageReservoirBuilder()
                .withZoneName(createStorageReservoirResponse.getStorageReservoirs()[0].getZoneName())
                .withWardName(createStorageReservoirResponse.getStorageReservoirs()[0].getWardName())
                .withLocationName(createStorageReservoirResponse.getStorageReservoirs()[0].getLocationName())
                .withReservoirType(reservoirType)
                .build();
        storageReservoirs[0] = storageReservoir;
        CreateStorageReservoirRequest createStorageReservoirRequest = new CreateStorageReservoirRequestBuilder()
                .withStorageReservoir(storageReservoirs)
                .withRequestInfo(requestInfo).build();

        Response response = new WCMSResource().updateStorageReservoirResource(RequestHelper.getJsonString(createStorageReservoirRequest), id);
        CreateStorageReservoirResponse updateStorageReservoirResponse = (CreateStorageReservoirResponse)
                ResponseHelper.getResponseAsObject(response.asString(), CreateStorageReservoirResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(updateStorageReservoirResponse.getStorageReservoirs()[0].getReservoirType(),
                createStorageReservoirResponse.getStorageReservoirs()[0].getReservoirType());
        Assert.assertEquals(updateStorageReservoirResponse.getStorageReservoirs()[0].getLocationName(),
                storageReservoir.getLocationName());
        Assert.assertEquals(updateStorageReservoirResponse.getStorageReservoirs()[0].getWardName(),
                storageReservoir.getWardName());
        Assert.assertEquals(updateStorageReservoirResponse.getStorageReservoirs()[0].getZoneName(),
                storageReservoir.getZoneName());
        new APILogger().log("Update Storage Reservoir Test Request is Completed --");
    }
}
