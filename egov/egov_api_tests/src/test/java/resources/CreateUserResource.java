package resources;

        import com.jayway.restassured.http.ContentType;
        import com.jayway.restassured.response.Header;
        import com.jayway.restassured.response.Response;

        import static com.jayway.restassured.RestAssured.given;

public class CreateUserResource {
    private Header Authorization = new Header("Authorization", "Basic YWVpb3U6YWVpb3U=");

    public Response createUser(String jsonString) {
        return given().request().with()
                .contentType(ContentType.JSON)
                .header(Authorization)
                .body(jsonString)
                .post("https://peaceful-headland-36194.herokuapp.com/v1/mSevaAndLA/users?tenant_id=kul.am");
    }
}