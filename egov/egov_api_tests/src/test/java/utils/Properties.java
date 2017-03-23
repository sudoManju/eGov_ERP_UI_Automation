package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String loginUrl = propertiesReader.getLoginUrl();

    public static final String serverUrl = propertiesReader.getServerUrl();

    public static final String logoutUrl = propertiesReader.getLogoutUrl();

    public static final String getPGRComplaintUrl = propertiesReader.getPGRComplaintUrl();

    public static final String complaintUrl = propertiesReader.getComplaintUrl();

    public static final String locationNameUrl = propertiesReader.getLocationNameUrl();

    public static final String fetchComplaintsUrl = propertiesReader.getFetchComplaintsUrl();

    public static final String frequentlyFilledComplaintsUrl = propertiesReader.getFrequentlyFilledComplaintstUrl();

    public static final String searchAssetServiceUrl = propertiesReader.getSearchAssetServiceUrl();

    public static final String devServerUrl = propertiesReader.getDevServerUrl();

    public static final String assetCategoryCreateUrl = propertiesReader.getAssetCategoryCreateUrl();

    public static final String searchAttendanceUrl = propertiesReader.getSearchAttendanceUrl();

    public static final String createAssetServiceUrl = propertiesReader.getCreateAssetServiceUrl();

    public static final String assetCategorySearchUrl = propertiesReader.getAssetCategorySearchUrl();

    public static final String createAttendanceURL = propertiesReader.getCreateAttendanceUrl();

    public static final String lamsServiceSearchUrl = propertiesReader.getLamsServiceSearchUrl();

    public static final String searchEmployeeURL = propertiesReader.getSearchEmployeeUrl();
}