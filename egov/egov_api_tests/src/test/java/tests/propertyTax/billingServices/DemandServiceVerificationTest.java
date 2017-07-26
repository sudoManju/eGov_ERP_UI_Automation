package tests.propertyTax.billingServices;

import builders.propertyTax.RequestInfoBuilder;
import builders.propertyTax.billingServices.demandService.DemandDetailsBuilder;
import builders.propertyTax.billingServices.demandService.DemandServiceRequestBuilder;
import builders.propertyTax.billingServices.demandService.DemandsBuilder;
import builders.propertyTax.billingServices.demandService.OwnerBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.billingServices.demandService.DemandDetails;
import entities.requests.propertyTax.billingServices.demandService.DemandServiceRequest;
import entities.requests.propertyTax.billingServices.demandService.Demands;
import entities.requests.propertyTax.billingServices.demandService.Owner;
import entities.responses.propertyTax.billingServices.TaxHeadMasterResponse;
import entities.responses.propertyTax.billingServices.businessService.BusinessServiceMasterResponse;
import entities.responses.propertyTax.billingServices.demandService.DemandServiceResponse;
import entities.responses.propertyTax.billingServices.taxPeriodsMaster.TaxPeriodsMasterResponse;
import entities.responses.userServices.createUser.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyTax.billingServices.DemandServiceResource;
import tests.BaseAPITest;
import tests.userServices.CreateUserWithoutValidationTest;
import utils.APILogger;
import utils.LoginAndLogoutHelper;
import utils.RequestHelper;
import utils.ResponseHelper;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class DemandServiceVerificationTest extends BaseAPITest{

    private RequestInfo requestInfo;
    private Demands[] demands;
    private DemandDetails[] demandDetails;
    private Owner owner;
    private UserResponse userInfo;
    private TaxHeadMasterResponse taxHeadDetails;
    private TaxPeriodsMasterResponse taxPeriodDetails;


    public DemandServiceVerificationTest(){
        owner = new Owner();
        demands = new Demands[1];
        demandDetails = new DemandDetails[1];
    }

    @Test
    public void demandServiceTest() throws IOException{
        LoginAndLogoutHelper.login(NARASAPPA);              //Login
        requestInfo = new RequestInfoBuilder().withAuthToken(scenarioContext.getAuthToken()).build();
        createDemandService();                             //Create
    }

    private DemandServiceResponse createDemandService() throws IOException {

        new APILogger().log("Create User is Started --");
        userInfo = new CreateUserWithoutValidationTest().getUserInfo();
        new APILogger().log("Create User is Completed --");

        new APILogger().log("Create BusinessService Master is Started --");
        String service = new BusinessServiceMasterVerificationTest().getBusinessService();
        new APILogger().log("Create BusinessService Master is Completed --");

        new APILogger().log("Create TaxHead Master is Started --");
        taxHeadDetails = new TaxHeadMasterVerificationTest().getTaxHeadDetails(getRandomIntFromRange(0,6),service);
        new APILogger().log("Create TaxHead Master is Completed --");

        new APILogger().log("Create TaxPeriod Master is Started --");
        taxPeriodDetails = new TaxPeriodMasterVerificationTest().getTaxPeriodDetails(service,taxHeadDetails);
        new APILogger().log("Create TaxPeriod Master is Completed --");

        new APILogger().log("Create Demand is Started --");
        owner = new OwnerBuilder().withId(userInfo.getUser()[0].getId()).withName(userInfo.getUser()[0].getName())
                .withUserName(userInfo.getUser()[0].getUserName()).withMobileNumber(userInfo.getUser()[0].getMobileNumber()).build();
        demandDetails[0] = new DemandDetailsBuilder()
                .withTaxHeadMasterCode(taxHeadDetails.getTaxHeadMasters()[0].getCode()).build();
        demands[0] = new DemandsBuilder().withBusinessService(service).withConsumerCode("EMP"+get3DigitRandomInt()).withOwner(owner)
                .withTaxPeriodFrom(String.valueOf(taxPeriodDetails.getTaxPeriods()[0].getFromDate())).withTaxPeriodTo(String.valueOf(taxPeriodDetails.getTaxPeriods()[0].getToDate()))
                .withDemandDetails(demandDetails).build();
        DemandServiceRequest request = new DemandServiceRequestBuilder().withRequestInfo(requestInfo).withDemands(demands).build();

        Response response = new DemandServiceResource().create(RequestHelper.getJsonString(request));
        DemandServiceResponse responseObject = checkAssertsForCreate(request,response);
        new APILogger().log("Create Demand is Completed --");
        return responseObject;
    }

    private DemandServiceResponse checkAssertsForCreate(DemandServiceRequest request, Response response) throws IOException {
        DemandServiceResponse responseObject = (DemandServiceResponse)
                ResponseHelper.getResponseAsObject(response.asString(),DemandServiceResponse.class);

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(responseObject.getResponseInfo().getStatus(),"201");
        Assert.assertEquals(responseObject.getDemands()[0].getConsumerCode(),request.getDemands()[0].getConsumerCode());
        Assert.assertEquals(responseObject.getDemands()[0].getBusinessService(),request.getDemands()[0].getBusinessService());
        Assert.assertEquals(Integer.toString(responseObject.getDemands()[0].getTaxPeriodFrom()),request.getDemands()[0].getTaxPeriodFrom());
        Assert.assertEquals(Integer.toString(responseObject.getDemands()[0].getTaxPeriodTo()),request.getDemands()[0].getTaxPeriodTo());
        Assert.assertEquals(responseObject.getDemands()[0].getOwner().getId(),request.getDemands()[0].getOwner().getId());
        Assert.assertEquals(responseObject.getDemands()[0].getOwner().getName(),request.getDemands()[0].getOwner().getName());
        Assert.assertEquals(responseObject.getDemands()[0].getOwner().getUserName(),request.getDemands()[0].getOwner().getUserName());
        Assert.assertEquals(responseObject.getDemands()[0].getOwner().getMobileNumber(),request.getDemands()[0].getOwner().getMobileNumber());
        Assert.assertEquals(responseObject.getDemands()[0].getDemandDetails()[0].getTaxHeadMasterCode(),request.getDemands()[0].getDemandDetails()[0].getTaxHeadMasterCode());

        return responseObject;
    }
}
