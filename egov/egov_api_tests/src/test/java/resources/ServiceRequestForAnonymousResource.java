package resources;

        import com.jayway.restassured.http.ContentType;
        import com.jayway.restassured.response.Header;
        import com.jayway.restassured.response.Response;

        import static com.jayway.restassured.RestAssured.given;

        public class ServiceRequestForAnonymousResource {

                public Response serviceTypeValidation(String jsonString) {
                return given().request().with()
                                .contentType(ContentType.JSON)
                                .post("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/users?tenant_id=kul.am");
            }
    }