package resources;

        import com.jayway.restassured.http.ContentType;
        import com.jayway.restassured.response.Response;

        import static com.jayway.restassured.RestAssured.given;

        public class ServiceRequestForAnonymousResource {

                public Response serviceTypeValidation(String jsonString) {
                return given().request().with()
                                .contentType(ContentType.JSON)
                                .post("http://localhost:8080/pgrrest/requests?jurisdictionId=kurnool");
            }
    }