package utils;

public class ResourceHelper {

    public String getBaseURI() {
        if (System.getProperty("env").equals("qa"))
            return "http://egov-micro-qa.egovernments.org/";
        if (System.getProperty("env").equals("dev"))
            return "http://egov-micro-dev.egovernments.org/";

        throw new RuntimeException("Not a valid env");
    }

}
