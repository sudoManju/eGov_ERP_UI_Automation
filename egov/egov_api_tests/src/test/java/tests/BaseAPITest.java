package tests;

import com.jayway.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import spec.*;

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



//    protected <T extends StartingPointSpec> T given(T dsl) {
//        return dsl;
//    }
//
//    protected <T extends StartingPointSpec> T and(T dsl) {
//        return dsl;
//    }

    }