package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import spec.*;

import java.util.ArrayList;

public class BaseAPITest {


    protected StartingPointSpec startingPoint;
    protected ScenarioContext scenarioContext;


//    @BeforeMethod(alwaysRun = true)
//    public void setUp() {
//        RestAssured.baseURI = getBaseURI(System.getProperty("env"));
//        scenarioContext = new ScenarioContext();
//        startingPoint = new StartingPointSpec(scenarioContext);
//
//    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown(ITestResult result) {
//        System.out.println("Test output was for --> " + result.getMethod().getMethodName());
//    }

//    private String getBaseURI(String env) {
//        if (env.equals("staging"))
//            return "https://phoenix-qa.egovernments.org";
//        throw new RuntimeException("not a valid environment");
//    }

    public boolean isGoodResponse(Response response) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(200);
        arrayList.add(201);
        arrayList.add(202);
        arrayList.add(203);
        return arrayList.contains(response.getStatusCode());
    }



//    protected <T extends StartingPointSpec> T given(T dsl) {
//        return dsl;
//    }
//
//    protected <T extends StartingPointSpec> T and(T dsl) {
//        return dsl;
//    }

}