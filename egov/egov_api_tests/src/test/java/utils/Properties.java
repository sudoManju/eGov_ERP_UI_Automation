package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String loginUrl = propertiesReader.getLoginUrl();

    public static final String serverUrl = propertiesReader.getServerUrl();

    public static final String logoutUrl = propertiesReader.getLogoutUrl();

    public static final String getPGRComplaintUrl = propertiesReader.getPGRComplaintUrl();

    public static final String complaintUrl = propertiesReader.getComplaintUrl();

}