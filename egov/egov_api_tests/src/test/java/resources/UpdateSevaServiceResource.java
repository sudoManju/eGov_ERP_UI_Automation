package resources;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class UpdateSevaServiceResource {
	 public Response serviceTypeValidation(String jsonString) {
	        return given().request().with()
	                        .contentType(ContentType.JSON)
	                        .put("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/seva/363656?jurisdiction_id=6676");
	    }
	}
