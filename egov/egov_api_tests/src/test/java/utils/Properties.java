package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String loginUrl = propertiesReader.getLoginUrl();

    public static final String serverUrl = propertiesReader.getLoginUrl();

}
