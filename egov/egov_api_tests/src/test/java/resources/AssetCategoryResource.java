package resources;

import com.jayway.restassured.response.Response;
import utils.Properties;

import static com.jayway.restassured.RestAssured.given;

public class AssetCategoryResource {

    public Response create(String json){

        Response response = given().request().with()
                           .header("Content-Type", "application/json")
                           .header("auth-token", "bedb20fb-7d74-445e-94cc-6a64e825d509")
                           .body(json)
                           .when()
                           .post(Properties.devServerUrl+Properties.assetCategoryCreateUrl);

        return response;
    }
}
