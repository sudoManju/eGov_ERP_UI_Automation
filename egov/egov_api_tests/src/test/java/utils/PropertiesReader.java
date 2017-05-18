package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties prop = new Properties();
    private String PGRReceivingModesUrl;

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
    public String getUserCreateUrl() {
        return prop.getProperty("userCreateUrl");
    }
    public String getCreateOtpUrl() {
        return prop.getProperty("createOtpUrl");
    }
    public String getValidateOtpUrl() {
        return prop.getProperty("validateOtpUrl");
    }
    public String getCreateCitizenUrl(){return prop.getProperty("createCitizenUrl");}
    public String getUserUpdateUrl() {
        return prop.getProperty("userUpdateUrl");
    }
    public String getLoginUserDetailsUrl() {
        return prop.getProperty("loginUserDetailsUrl");
    }
    public String getSearchOtpUrl() {
        return prop.getProperty("searchOtpUrl");
    }

    //      PGR Module Url's      //
    public String getPGRComplaintUrl() {
        return prop.getProperty("getPGRComplaintUrl");
    }

    public String getComplaintUrl() {
        return prop.getProperty("complaintUrl");
    }

    public String getFetchComplaintsUrl() {
        return prop.getProperty("fetchComplaintsUrl");
    }

    public String getFetchComplaintsByIdComplaintstUrl() {
        return prop.getProperty("fetchComplaintsByIdUrl");
    }

    public String getPGRReceivingCenter() {
        return prop.getProperty("pgrReceivingCenter");
    }

    public String getUpdateComplaintUrl() {
        return prop.getProperty("updateComplaintUrl");
    }

    public String getComplaintTypeByServiceCodeUrl() {
        return prop.getProperty("complaintTypeByServiceCodeUrl");
    }

    public String getComplaintTypeCategoriesUrl() {
        return prop.getProperty("complaintTypeCategoriesUrl");
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
    public String getSearchEmployeeTypeUrl() { return prop.getProperty("searchEmployeeTypeUrl"); }
    public String getSearchDesignationTypeUrl() { return prop.getProperty("searchDesignationTypeUrl"); }
    public String getSearchPositionUrl() { return prop.getProperty("searchPositionUrl"); }
    public String getSearchPositionHierarchyUrl() { return prop.getProperty("searchPositionHierarchyUrl"); }
    public String getSearchGradeUrl() { return prop.getProperty("searchGradeUrl"); }
    public String getSearchEmployeeGroupUrl() { return prop.getProperty("searchEmployeeGroupUrl"); }
    public String getSearchRecruitmentQuotaUrl() { return prop.getProperty("searchRecruitmentQuotaUrl"); }
    public String getSearchRecruitmentModesUrl() { return prop.getProperty("searchRecruitmentModesUrl"); }
    public String getSearchHrConfigurationsUrl() { return prop.getProperty("searchHrConfigurations"); }
    public String getSearchHrStatusesUrl() { return prop.getProperty("searchHRStatusesUrl"); }
    public String getSearchLeaveApplicationsUrl() { return prop.getProperty("searchLeaveApplicationsUrl"); }
    public String getSearchLeaveOpeningbalancesUrl() { return prop.getProperty("searchLeaveOpeningbalancesUrl"); }
    public String getSearchLeaveAllotmentsUrl() { return prop.getProperty("searchLeaveAllotmentsUrl"); }
    public String getCreateOpeningBalanceUrl() { return prop.getProperty("createOpeningBalanceUrl"); }

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
    public String getCMCreateHolidayUrl() {return prop.getProperty("cmCreateHolidayUrl");}

    public String getPGRReceivingModesUrl() {
        return prop.getProperty("pgrReceivingModes");
    }
    public String getSearchEmployeeLeaveUrl() {
        return prop.getProperty("searchEmployeeLeaveUrl");
    }
}

