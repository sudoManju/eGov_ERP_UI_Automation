package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties prop = new Properties();

    public PropertiesReader() {
        try {
            String env = System.getProperty("env");
            String propertiesFilePath = null;
            switch (env) {
                case "null":
                    propertiesFilePath = "endPoints" + ".properties";
                    break;

                case "dev":
                    propertiesFilePath = "dev" + ".properties";
                    break;

                case "qa":
                    propertiesFilePath = "qa" + ".properties";
                    break;
            }

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath);

            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //      Login And Logout Url's      //
    public String getLoginUrl() {
        return prop.getProperty("loginUrl");
    }

    public String getLogoutUrl() {
        return prop.getProperty("logoutUrl");
    }

    //      Server Url's      //
    public String getServerUrl() {
        return prop.getProperty("qaServerUrl");
    }

    public String getDevServerUrl() {
        return prop.getProperty("devServerUrl");
    }

    //      User Url's      //
    public String getUserUrl() {
        return prop.getProperty("userUrl");
    }

    //      PGR Module Url's      //
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

    public String getPGRStatusUrl() {
        return prop.getProperty("pgrStatusUrl");
    }

    public String getPGRReceivingCenter() {
        return prop.getProperty("pgrReceivingCenter");
    }

    public String getPGRSearchCitizenComplaint() {
        return prop.getProperty("pgrSearchCitizenComplaint");
    }


    //      Asset Module Url's      //
    public String getSearchAssetServiceUrl() {
        return prop.getProperty("searchAssetService");
    }

    public String getAssetCategoryCreateUrl() {
        return prop.getProperty("assetCategoryCreateUrl");
    }

    public String getCreateAssetServiceUrl() {
        return prop.getProperty("createAssetServiceUrl");
    }

    public String getAssetCategorySearchUrl() {
        return prop.getProperty("assetCategorySearchUrl");
    }

    //      eGovEIS Module Url's      //
    public String getSearchAttendanceUrl() {
        return prop.getProperty("searchAttendanceUrl");
    }
    public String getSearchEmployeeUrl() {
        return prop.getProperty("searchEmployeeUrl");
    }
    public String getCreateAttendanceUrl() {
        return prop.getProperty("createAttendanceURL");
    }
    public String getCreateEmployeeUrl() { return prop.getProperty("createEmployeeUrl"); }

    //      Lease And Agreement Module Url's      //
    public String getLAMSServiceSearchUrl() {
        return prop.getProperty("lamsServiceSearchUrl");
    }

    //      Common Master Module Url's      //
    public String getCMLanguageUrl() {
        return prop.getProperty("cmLanguageUrl");
    }

    public String getCMDepartmentUrl() {
        return prop.getProperty("cmDepartmentUrl");
    }

    public String getCMCommunityUrl() {
        return prop.getProperty("cmCommunityUrl");
    }

    public String getCMReligionUrl() {
        return prop.getProperty("cmReligionUrl");
    }

    public String getCMHolidayUrl() {
        return prop.getProperty("cmHolidayUrl");
    }

    public String getCMCategoryUrl() {
        return prop.getProperty("cmCategoryUrl");
    }

    public String getUserCreateUrl() {
        return prop.getProperty("userCreateUrl");
    }
}

