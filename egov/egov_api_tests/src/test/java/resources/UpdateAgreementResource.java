package resources;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class UpdateAgreementResource {
	   public Response serviceTypeValidation(String jsonString) {
	        return given().request().with()
	                        .contentType(ContentType.JSON)
	                        .put("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/agreements/858899?tenant_id=7484");
	    }
	}
