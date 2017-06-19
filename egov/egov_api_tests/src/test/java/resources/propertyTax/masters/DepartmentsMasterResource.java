package resources.propertyTax.masters;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class DepartmentsMasterResource {

    public Response createDepartment(String jsonString) {

        new APILogger().log("Create Department Master request is started as --"+jsonString);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(jsonString)
                .when()
                .post(Properties.createDepartmentsMasterUrl);

        new APILogger().log("Create Department Master response is generated as --"+response.asString());

        return response;
    }

    public Response searchDepartment(String json,String s){

       new APILogger().log("Search Department Master request is started as --"+json);

        Response response = given().request().with()
                .header("Content-Type","application/json")
                .body(json)
                .when()
                .post(Properties.searchDepartmentsMasterUrl+s);

        new APILogger().log("Search Department Master response is generated as --"+response.asString());

        return response;
    }
}
