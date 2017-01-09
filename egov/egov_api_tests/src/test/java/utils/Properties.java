package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String estimateBookingUrl = propertiesReader.getEstimateBookingUrl();
    public static final String createBookingUrl = propertiesReader.getCreateBookingUrl();
    }
