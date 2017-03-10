package resources;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class LoginAUserResource {
	  public Response serviceTypeValidation(String jsonString) {
	        return given().request().with()
	                        .contentType(ContentType.JSON)
	                        .post("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/users/_login?tenant_id=848834");
	    }

	    public Response post(String jsonString){
			return given().request().with()
					.header("Content-type", "application/json; charset=utf-8")
					.body(jsonString)
					.when()
					.post("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/users?tenant_id");
		}

		public Response logout(String jsonString){
			return given().request().with()
					.header("Content-type", "application/json; charset=utf-8")
					.body(jsonString)
					.when()
					.post("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/users/_logout}?tenant_id");
		}
	}
