package resources;

        import com.jayway.restassured.http.ContentType;
        import com.jayway.restassured.response.Header;
        import com.jayway.restassured.response.Response;

        import static com.jayway.restassured.RestAssured.given;

        public class ServiceTypeResource {

                public Response serviceTypeValidation(String jsonString) {
                return given().request().with()
                                .contentType(ContentType.JSON)
                                .get("http://phoenix-qa.egovernments.org");
            }
    }