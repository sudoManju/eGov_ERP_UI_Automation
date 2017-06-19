package resources;

import com.jayway.restassured.response.Response;
import utils.APILogger;
import utils.Properties;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class LoginResource extends Resource{

    public Response login(Map json) {
        new APILogger().log("Login Request is started with-- " + json.toString());
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0")
                .params(json)
                .when()
                .post(Properties.loginUrl);

        new APILogger().log("Login Response is generated as-- " + response.asString());
        return response;
    }

    public Response logout(String json, String accessToken) {
        new APILogger().log("Logout request started for-- " + json);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(Properties.logoutUrl + accessToken);

        new APILogger().log("Logout response generated as-- " + response.asString());
        return response;
    }

    public Response inValidLogout(String accessToken) {
        new APILogger().log("In-Valid logout request started for-- " + accessToken);
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("auth-token", accessToken)
                .when()
                .post(Properties.logoutUrl + accessToken);

        new APILogger().log("In-Valid logout response generated as-- " + response.asString());
        return response;
    }

    public Response getSessionIdFromPilotBaseAPI() {
        new APILogger().log("Get The SESSION ID From Base API Test Is Started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .when()
                .get("http://kurnool-pilot-services.egovernments.org/egi");
        scenarioContext.setSessionId(response.getCookie("SESSIONID"));
        new APILogger().log("Get The SESSION ID From Base API Test Is Completed -- ");
        return response;
    }

    public Response loginFromPilotService(String sessionId, Map map) {
        new APILogger().log("Get The SESSION ID From LOGIN API Test Is Started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic Og==")
                .header("SESSIONID", sessionId)
                .params(map)
                .when()
                .post(Properties.pilotLoginUrl);

        scenarioContext.setSessionId(response.getCookie("SESSIONID"));
        new APILogger().log("Get The SESSION ID From LOGIN API Test Is Completed -- ");
        return response;
    }

    public Response logoutFromPilotService(String sessionIdFromLoginAPI) {
        new APILogger().log("LOGOUT Test Is Started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("SESSIONID", sessionIdFromLoginAPI)
                .when()
                .get(Properties.pilotLogoutUrl);

        new APILogger().log("LOGOUT Test Is Completed -- ");
        return response;
    }

    //
    public Response getSessionIdFromPilotBaseAPI1() {
        new APILogger().log("Get The SESSION ID From Base API Test Is Started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .when()
                .get("http://kurnool-pilot-services.egovernments.org/egi");
        scenarioContext.setSessionId(response.getCookie("SESSIONID"));
        new APILogger().log("Get The SESSION ID From Base API Test Is Completed -- ");
        return response;
    }

    public Response loginFromPilotService1(Map map) {
        new APILogger().log("Get The SESSION ID From LOGIN API Test Is Started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("Content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic Og==")
                .header("SESSIONID", scenarioContext.getSessionId())
                .params(map)
                .when()
                .post(Properties.pilotLoginUrl);

        scenarioContext.setSessionId(response.getCookie("SESSIONID"));
        new APILogger().log("Get The SESSION ID From LOGIN API Test Is Completed -- ");
        return response;
    }

    public Response logoutFromPilotService1() {
        new APILogger().log("LOGOUT Test Is Started -- ");
        Response response = given().request().with()
                .urlEncodingEnabled(false)
                .header("SESSIONID", scenarioContext.getSessionId())
                .when()
                .get(Properties.pilotLogoutUrl);

        new APILogger().log("LOGOUT Test Is Completed -- ");
        return response;
    }
}
