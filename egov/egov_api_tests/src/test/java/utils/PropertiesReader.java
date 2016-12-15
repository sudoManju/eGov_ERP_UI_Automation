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


    public String getEstimateBookingUrl() {
        return prop.getProperty("estimateBookingUrl");
    }

    public String getCreateBookingUrl() {
        return prop.getProperty("createBookingUrl");
    }

    public String getDriverLoginUrl() {
        return prop.getProperty("driverLoginUrl");
    }

    public String getCustomerLoginUrl() {
        return prop.getProperty("customerLoginUrl");
    }

    public String getGoPayBalanceUrl() {
        return prop.getProperty("goPayBalanceUrl");
    }

    public String getDriverPingUrl() {
        return prop.getProperty("driverPingUrl");
    }

    public String getGeoFenceUrl() {
        return prop.getProperty("geoFenceUrl");
    }


    public String getBookingStatusUrl() {
        return prop.getProperty("bookingStatusUrl");
    }

    public String getCustomerCancellationUrl() {
        return prop.getProperty("customerCancellationUrl");
    }

    public String getDriverCancellationUrl() {
        return prop.getProperty("driverCancellationUrl");
    }

    public String getOrderStatusUrl() {
        return prop.getProperty("orderStatusUrl");

    }

    public String getRateDriverUrl() {
        return prop.getProperty("rateDriverUrl");
    }

    public String getkilatEcomCreateBookingUrl() {
        return prop.getProperty("kilatEcomCreateBookingUrl");
    }

    public String getBookingHistoryUrl() {
        return prop.getProperty("bookingHistoryUrl");
    }
}

