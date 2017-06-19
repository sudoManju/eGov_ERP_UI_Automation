package tests.eGovEIS.hrMaster;

import builders.eGovEIS.hrMaster.RequestInfoBuilder;
import builders.eGovEIS.hrMaster.designation.create.DesignationBuilder;
import builders.eGovEIS.hrMaster.designation.create.HRMasterDesignationCreateRequestBuilder;
import builders.eGovEIS.hrMaster.designation.search.HRMasterDesignationSearchRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.hrMaster.RequestInfo;
import entities.requests.eGovEIS.hrMaster.designation.create.Designation;
import entities.requests.eGovEIS.hrMaster.designation.create.HRMasterDesignationCreateRequest;
import entities.requests.eGovEIS.hrMaster.designation.search.HRMasterDesignationSearchRequest;
import entities.responses.eGovEIS.hrMaster.designation.create.HRMasterDesignationCreateSearchAndUpdateResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.hrMaster.HRMasterDesignationResource;
import tests.BaseAPITest;
import utils.*;

import java.io.IOException;

import static data.UserData.ADMIN;

public class HRMasterDesignationCreateSearchAndUpdateTest extends BaseAPITest {

    @Test(groups = {Categories.SANITY, Categories.PILOT, Categories.HR})
    public void hrMasterDesignationCreateSearchAndUpdate() throws IOException {
        LoginAndLogoutHelper.loginFromPilotService(ADMIN); // Login
        createDesignation(); // Create HR Designation Type
        pilotLogoutService(); // Logout
    }

    private void createDesignation() throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        Designation designation = new DesignationBuilder().build();

        HRMasterDesignationCreateRequest hrMasterDesignationCreateRequest =
                new HRMasterDesignationCreateRequestBuilder()
                        .withRequestInfo(requestInfo)
                        .withDesignation(designation)
                        .build();

        Response response = new HRMasterDesignationResource()
                .createDesignationResource(RequestHelper.getJsonString(hrMasterDesignationCreateRequest));
        HRMasterDesignationCreateSearchAndUpdateResponse hrMasterDesignationCreateResponse = (HRMasterDesignationCreateSearchAndUpdateResponse)
                ResponseHelper.getResponseAsObject(response.asString(), HRMasterDesignationCreateSearchAndUpdateResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        new APILogger().log("HR Master Designation Create Test is Completed --");
        searchDesignation(hrMasterDesignationCreateResponse.getDesignation()[0].getName(),
                hrMasterDesignationCreateResponse); // Search Designation
    }

    private void searchDesignation(String designationName, HRMasterDesignationCreateSearchAndUpdateResponse hrMasterDesignationCreateRequest) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        HRMasterDesignationSearchRequest hrMasterDesignationSearchRequest =
                new HRMasterDesignationSearchRequestBuilder()
                        .withRequestInfo(requestInfo)
                        .build();

        Response response = new HRMasterDesignationResource()
                .searchDesignationResource(RequestHelper.getJsonString(hrMasterDesignationSearchRequest), designationName);
        HRMasterDesignationCreateSearchAndUpdateResponse hrMasterDesignationSearchResponse = (HRMasterDesignationCreateSearchAndUpdateResponse)
                ResponseHelper.getResponseAsObject(response.asString(), HRMasterDesignationCreateSearchAndUpdateResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(hrMasterDesignationSearchResponse.getDesignation()[0].getName(), designationName);
        new APILogger().log("HR Master Designation Search Test is Completed --");
        updateDesignation(hrMasterDesignationSearchResponse.getDesignation()[0].getId(),
                hrMasterDesignationCreateRequest); // Update Designation
    }

    private void updateDesignation(int id, HRMasterDesignationCreateSearchAndUpdateResponse hrMasterDesignationCreateRequest) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        Designation designation = new DesignationBuilder()
                .withName(hrMasterDesignationCreateRequest.getDesignation()[0].getName())
                .withTenantId(hrMasterDesignationCreateRequest.getDesignation()[0].getTenantId())
                .withCode(hrMasterDesignationCreateRequest.getDesignation()[0].getCode())
                .withDescription("Modified Description")
                .withChartOfAccounts(hrMasterDesignationCreateRequest.getDesignation()[0].getChartOfAccounts())
                .withActive(hrMasterDesignationCreateRequest.getDesignation()[0].getActive())
                .build();

        HRMasterDesignationCreateRequest hrMasterDesignationUpdateRequest =
                new HRMasterDesignationCreateRequestBuilder()
                        .withRequestInfo(requestInfo)
                        .withDesignation(designation)
                        .build();

        Response response = new HRMasterDesignationResource()
                .updateDesignationResource(RequestHelper.getJsonString(hrMasterDesignationUpdateRequest), id);
        HRMasterDesignationCreateSearchAndUpdateResponse hrMasterDesignationUpdateResponse = (HRMasterDesignationCreateSearchAndUpdateResponse)
                ResponseHelper.getResponseAsObject(response.asString(), HRMasterDesignationCreateSearchAndUpdateResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(hrMasterDesignationUpdateResponse.getDesignation()[0].getDescription(), "Modified Description");
        new APILogger().log("HR Master Designation Update Test is Completed --");
        searchDesignationAfterUpdate(hrMasterDesignationUpdateResponse.getDesignation()[0].getName()); // Search Designation After Update
    }

    private void searchDesignationAfterUpdate(String name) throws IOException {
        RequestInfo requestInfo = new RequestInfoBuilder().build();
        HRMasterDesignationSearchRequest hrMasterDesignationSearchRequest =
                new HRMasterDesignationSearchRequestBuilder()
                        .withRequestInfo(requestInfo)
                        .build();

        Response response = new HRMasterDesignationResource()
                .searchDesignationResource(RequestHelper.getJsonString(hrMasterDesignationSearchRequest), name);
        HRMasterDesignationCreateSearchAndUpdateResponse hrMasterDesignationSearchAfterUpdateResponse = (HRMasterDesignationCreateSearchAndUpdateResponse)
                ResponseHelper.getResponseAsObject(response.asString(), HRMasterDesignationCreateSearchAndUpdateResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(hrMasterDesignationSearchAfterUpdateResponse.getDesignation()[0].getDescription(), "Modified Description");
        new APILogger().log("HR Master Designation Search After Update Test is Completed --");
    }
}
