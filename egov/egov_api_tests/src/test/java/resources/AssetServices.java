package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetServices {

    public Response searchAssetService(String json){

        new APILogger().log("Searching the Asset Service  -- ");
        Response response = given().request().with()
                .header("Content-Type", "application/json")
                .header("auth-token","bedb20fb-7d74-445e-94cc-6a64e825d509")
                .body(json)
                .when()
                .post(Properties.serverUrl + Properties.complaintUrl);

        return response;
    }
}
