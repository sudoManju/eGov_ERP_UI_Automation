package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class CommonMasterResource {

    public Response searchLanguageTest(String json) {
        new APILogger().log("Search Language Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.cmLanguageUrl);

        return response;
    }

    public Response searchDepartmentTest(String jsonString) {
        new APILogger().log("Search Department Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.cmDepartmentUrl);

        return response;
    }

    public Response searchCommunityTest(String jsonString) {

        new APILogger().log("Search Community Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.cmCommunityUrl);

        return response;
    }

    public Response searchReligionTest(String jsonString) {

        new APILogger().log("Search Religion Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.cmReligionUrl);

        return response;
    }

    public Response searchHolidayTest(String jsonString) {

        new APILogger().log("Search Holiday Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.cmHolidayUrl);

        return response;
    }

    public Response searchCategoryTest(String jsonString) {

        new APILogger().log("Search Category Test is started --");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post(Properties.cmCategoryUrl);

        return response;
    }
}
