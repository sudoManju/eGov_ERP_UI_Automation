package utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String estimateBookingUrl = propertiesReader.getEstimateBookingUrl();
    public static final String createBookingUrl = propertiesReader.getCreateBookingUrl();
    public static final String driverLoginUrl = propertiesReader.getDriverLoginUrl();
    public static final String customerLoginUrl = propertiesReader.getCustomerLoginUrl();
    public static final String goPayBalanceUrl = propertiesReader.getGoPayBalanceUrl();
    public static final String driverPingUrl = propertiesReader.getDriverPingUrl();
    public static final String geoFenceUrl = propertiesReader.getGeoFenceUrl();

    public static final String bookingStatusUrl = propertiesReader.getBookingStatusUrl();
    public static final String customerCancellationUrl = propertiesReader.getCustomerCancellationUrl();
    public static final String driverCancellationUrl = propertiesReader.getDriverCancellationUrl();
    public static final String orderStatusUrl = propertiesReader.getOrderStatusUrl();
    public static final String rateDriverUrl = propertiesReader.getRateDriverUrl();
    public static final String kilatEcomCreateBookingUrl = propertiesReader.getkilatEcomCreateBookingUrl();
    public static final String bookingHistoryUrl = propertiesReader.getBookingHistoryUrl();
}
