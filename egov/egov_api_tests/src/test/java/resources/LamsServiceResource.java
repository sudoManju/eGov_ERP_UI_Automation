package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class LamsServiceResource {

    public Response LamsServiceSearch(String jsonString,String access_token ){

        new APILogger().log("LAMS service search request started -- ");

        Response response =  given().request().with()
                            .header("Content-Type", "application/json")
                            .header("auth-token", access_token)
                            .body(jsonString)
                            .when()
                            .post(Properties.devServerUrl+Properties.lamsServiceSearchUrl);

        return response;
    }
}
