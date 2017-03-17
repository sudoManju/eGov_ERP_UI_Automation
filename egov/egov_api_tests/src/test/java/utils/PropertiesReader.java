package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties prop = new Properties();

    public PropertiesReader() {
        try {
            String propertiesFilePath = "endPoints.properties";
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath);

            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getServiceListUrl() {
        return prop.getProperty("serviceListUrl");
    }

    public String getLoginUrl() {
        return prop.getProperty("loginUrl");
    }

    public String getServerUrl() {
        return prop.getProperty("serverUrl");
    }

    public String getLogoutUrl() {
        return prop.getProperty("logoutUrl");
    }

    public String getPGRComplaintUrl() {
        return prop.getProperty("getPGRComplaintUrl");
    }

    public String getComplaintUrl() {
        return prop.getProperty("complaintUrl");
    }

    public String getLocationNameUrl() {
        return prop.getProperty("locationNameUrl");
    }
}

