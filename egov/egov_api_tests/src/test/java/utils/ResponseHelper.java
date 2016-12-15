package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ResponseHelper {
    public static Object getResponseAsObject(String responseString, Class responseClass) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseString, responseClass);

    }

}
