package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String url = propertiesReader.getUrl();

    public static final long waitTime = 10;

}
