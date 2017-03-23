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

    public String getFetchComplaintsUrl() {
        return prop.getProperty("fetchComplaintsUrl");
    }

    public String getFrequentlyFilledComplaintstUrl() {
        return prop.getProperty("frequentlyFilledComplaintsUrl");
    }

    public String getSearchAssetServiceUrl() {
        return prop.getProperty("searchAssetService");
    }

    public String getDevServerUrl() {
        return prop.getProperty("devServerUrl");
    }

    public String getAssetCategoryCreateUrl() {
        return prop.getProperty("assetCategoryCreateUrl");
    }

    public String getSearchAttendanceUrl() {
        return prop.getProperty("searchAttendanceUrl");
    }

    public String getCreateAssetServiceUrl() {
        return prop.getProperty("createAssetServiceUrl");
    }

    public String getAssetCategorySearchUrl() {
        return prop.getProperty("assetCategorySearchUrl");
    }

    public String getSearchEmployeeUrl() {
        return prop.getProperty("searchEmployeeUrl");
    }

    public String getCreateAttendanceUrl() {
        return prop.getProperty("createAttendanceURL");
    }

    public String getLamsServiceSearchUrl() {
        return prop.getProperty("lamsServiceSearchUrl");
    }
}

