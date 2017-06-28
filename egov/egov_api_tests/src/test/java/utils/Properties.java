package utils;

public class Properties {
    private static final PropertiesReader propertiesReader = new PropertiesReader();

    //  Login And Logout Url's  //
    public static final String loginUrl = propertiesReader.getEndPointUrl("loginUrl");
    public static final String logoutUrl = propertiesReader.getEndPointUrl("logoutUrl");
    public static final String pilotLoginUrl = propertiesReader.getEndPointUrl("pilotLoginUrl");
    public static final String pilotLogoutUrl = propertiesReader.getEndPointUrl("pilotLogoutUrl");

    //  User Url's  //
    public static final String userUrl = propertiesReader.getEndPointUrl("userUrl");
    public static final String userCreateUrl = propertiesReader.getEndPointUrl("userCreateUrl");
    public static final String createOtpUrl = propertiesReader.getEndPointUrl("createOtpUrl");
    public static final String validateOtpUrl = propertiesReader.getEndPointUrl("validateOtpUrl");
    public static final String createCitizenUrl = propertiesReader.getEndPointUrl("createCitizenUrl");

    //  PGR Module Url's  //
    public static final String getPGRComplaintUrl = propertiesReader.getEndPointUrl("getPGRComplaintUrl");
    public static final String complaintUrl = propertiesReader.getEndPointUrl("complaintUrl");
    public static final String fetchComplaintsUrl = propertiesReader.getEndPointUrl("fetchComplaintsUrl");
    public static final String fetchComplaintsByIdUrl = propertiesReader.getEndPointUrl("fetchComplaintsByIdUrl");
    public static final String pgrReceivingCenterUrl = propertiesReader.getEndPointUrl("pgrReceivingCenter");
    public static final String updateComplaintUrl = propertiesReader.getEndPointUrl("updateComplaintUrl");
    public static final String getComplaintTypeByServiceCodeUrl = propertiesReader.getEndPointUrl("complaintTypeByServiceCodeUrl");
    public static final String getComplaintTypeCategoriesUrl = propertiesReader.getEndPointUrl("complaintTypeCategoriesUrl");

    //  Asset Module Url's  //
    public static final String searchAssetServiceUrl = propertiesReader.getEndPointUrl("searchAssetService");
    public static final String assetCategoryCreateUrl = propertiesReader.getEndPointUrl("assetCategoryCreateUrl");
    public static final String createAssetServiceUrl = propertiesReader.getEndPointUrl("createAssetServiceUrl");
    public static final String assetCategorySearchUrl = propertiesReader.getEndPointUrl("assetCategorySearchUrl");

    //  eGov EIS Module Url's  //
    public static final String searchAttendanceUrl = propertiesReader.getEndPointUrl("searchAttendanceUrl");
    public static final String createAttendanceURL = propertiesReader.getEndPointUrl("createAttendanceURL");
    public static final String searchEmployeeURL = propertiesReader.getEndPointUrl("searchEmployeeUrl");
    public static final String createEmployeeUrl = propertiesReader.getEndPointUrl("createEmployeeUrl");
    public static final String eisSearchEmployeeTypeUrl = propertiesReader.getEndPointUrl("searchEmployeeTypeUrl");
    public static final String eisSearchDesignationTypeUrl = propertiesReader.getEndPointUrl("searchDesignationTypeUrl");
    public static final String eisCreateDesignationTypeUrl = propertiesReader.getEndPointUrl("createDesignationTypeUrl");
    public static final String eisUpdateDesignationTypeUrl = propertiesReader.getEndPointUrl("updateDesignationTypeUrl");
    public static final String eisSearchPositionUrl = propertiesReader.getEndPointUrl("searchPositionUrl");
    public static final String eisCreatePositionUrl = propertiesReader.getEndPointUrl("createPositionUrl");
    public static final String eisUpdatePositionUrl = propertiesReader.getEndPointUrl("updatePositionUrl");
    public static final String eisSearchPositionHierarchyUrl = propertiesReader.getEndPointUrl("searchPositionHierarchyUrl");
    public static final String eisSearchGradeUrl = propertiesReader.getEndPointUrl("searchGradeUrl");
    public static final String eisSearchEmployeeGroupUrl = propertiesReader.getEndPointUrl("searchEmployeeGroupUrl");
    public static final String eisSearchRecruitmentQuotaUrl = propertiesReader.getEndPointUrl("searchRecruitmentQuotaUrl");
    public static final String eisSearchRecruitmentModesUrl = propertiesReader.getEndPointUrl("searchRecruitmentModesUrl");
    public static final String eisSearchHrConfigurationsUrl = propertiesReader.getEndPointUrl("searchHrConfigurations");
    public static final String eisSearchHrStatusesUrl = propertiesReader.getEndPointUrl("searchHRStatusesUrl");
    public static final String eisSearchLeaveApplicationUrl = propertiesReader.getEndPointUrl("searchLeaveApplicationUrl");
    public static final String eisCreateLeaveApplicationUrl = propertiesReader.getEndPointUrl("createLeaveApplicationUrl");
    public static final String eisSearchLeaveOpeningBalanceUrl = propertiesReader.getEndPointUrl("searchLeaveOpeningBalanceUrl");
    public static final String eisSearchLeaveAllotmentsUrl = propertiesReader.getEndPointUrl("searchLeaveAllotmentsUrl");
    public static final String createOpeningBalanceUrlUrl = propertiesReader.getEndPointUrl("createOpeningBalanceUrl");

    //  Land Estate Module Url's  //
    public static final String lAMSServiceSearchUrl = propertiesReader.getEndPointUrl("lamsServiceSearchUrl");

    //  Common Master's Module Url's  //
    public static final String cmLanguageUrl = propertiesReader.getEndPointUrl("cmLanguageUrl");
    public static final String cmDepartmentUrl = propertiesReader.getEndPointUrl("cmDepartmentUrl");
    public static final String cmCommunityUrl = propertiesReader.getEndPointUrl("cmCommunityUrl");
    public static final String cmReligionUrl = propertiesReader.getEndPointUrl("cmReligionUrl");
    public static final String cmHolidayUrl = propertiesReader.getEndPointUrl("cmHolidayUrl");
    public static final String cmCategoryUrl = propertiesReader.getEndPointUrl("cmCategoryUrl");
    public static final String cmCreateHolidayUrl = propertiesReader.getEndPointUrl("cmCreateHolidayUrl");

    public static final String pgrReceivingModesUrl = propertiesReader.getEndPointUrl("pgrReceivingModes");
    public static final String searchLeaveTypeUrl = propertiesReader.getEndPointUrl("searchLeaveTypeUrl");
    public static final String createLeaveTypeUrl = propertiesReader.getEndPointUrl("createLeaveTypeUrl");
    public static final String userUpdateUrl = propertiesReader.getEndPointUrl("userUpdateUrl");
    public static final String loginUserDetailsUrl = propertiesReader.getEndPointUrl("loginUserDetailsUrl");
    public static final String searchOtpUrl = propertiesReader.getEndPointUrl("searchOtpUrl");

    // Property Tax Master's Module Url's //
    public static final String createUsageMasterUrl = propertiesReader.getEndPointUrl("usageMasterCreateUrl");
    public static final String searchUsageMasterUrl = propertiesReader.getEndPointUrl("usageMasterSearchUrl");
    public static final String createStructureClassUrl = propertiesReader.getEndPointUrl("structureClassCreateUrl");
    public static final String searchStructureClassUrl = propertiesReader.getEndPointUrl("structureClassSearchUrl");
    public static final String updateStructureClassUrl = propertiesReader.getEndPointUrl("structureClassUpdateUrl");
    public static final String createFloorTypesUrl = propertiesReader.getEndPointUrl("floorTypesCreateUrl");
    public static final String searchFloorTypesUrl = propertiesReader.getEndPointUrl("floorTypesSearchUrl");
    public static final String updateFloorTypesUrl = propertiesReader.getEndPointUrl("floorTypesUpdateUrl");
    public static final String createWoodTypeUrl = propertiesReader.getEndPointUrl("woodTypesCreateUrl");
    public static final String searchWoodTypeUrl = propertiesReader.getEndPointUrl("woodTypesSearchUrl");
    public static final String createPropertyTypeUrl = propertiesReader.getEndPointUrl("propertyTypeCreateUrl");
    public static final String searchPropertyTypeUrl = propertiesReader.getEndPointUrl("propertyTypeSearchUrl");
    public static final String createDepartmentsMasterUrl = propertiesReader.getEndPointUrl("departmentMasterCreateUrl");
    public static final String searchDepartmentsMasterUrl = propertiesReader.getEndPointUrl("departmentMasterSearchUrl");
    public static final String createOccupancyMasterUrl = propertiesReader.getEndPointUrl("occupancyMasterCreateUrl");
    public static final String searchOccupancyMasterUrl = propertiesReader.getEndPointUrl("occupancyMasterSearchUrl");
    public static final String createWallTypeMasterUrl = propertiesReader.getEndPointUrl("wallTypeMasterCreateUrl");
    public static final String searchWallTypeMasterUrl = propertiesReader.getEndPointUrl("wallTypeMasterSearchUrl");
    public static final String updateWallTypeMasterUrl = propertiesReader.getEndPointUrl("wallTypeMasterUpdateUrl");

    // Water Charge Management Url's //
    public static final String wcmsCreateCategoryTypeUrl = propertiesReader.getEndPointUrl("wcmsCreateCategoryTypeUrl");
    public static final String wcmsSearchCategoryTypeUrl = propertiesReader.getEndPointUrl("wcmsSearchCategoryTypeUrl");
    public static final String wcmsCreatePipeSizeUrl = propertiesReader.getEndPointUrl("wcmsCreatePipeSizeUrl");
    public static final String wcmsSearchPipeSizeUrl = propertiesReader.getEndPointUrl("wcmsSearchPipeSizeUrl");
    public static final String wcmsCreateDocumentTypeUrl = propertiesReader.getEndPointUrl("wcmsCreateDocumentTypeUrl");
    public static final String wcmsSearchDocumentTypeUrl = propertiesReader.getEndPointUrl("wcmsSearchDocumentTypeUrl");
    public static final String wcmsCreateSourceTypeUrl = propertiesReader.getEndPointUrl("wcmsCreateSourceTypeUrl");
    public static final String wcmsSearchSourceTypeUrl = propertiesReader.getEndPointUrl("wcmsSearchSourceTypeUrl");
    public static final String wcmsCreateSupplyTypeUrl = propertiesReader.getEndPointUrl("wcmsCreateSupplyTypeUrl");
    public static final String wcmsSearchSupplyTypeUrl = propertiesReader.getEndPointUrl("wcmsSearchSupplyTypeUrl");

}