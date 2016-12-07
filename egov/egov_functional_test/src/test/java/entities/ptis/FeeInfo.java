package entities.ptis;

/**
 * Created by vinaykumar on 22/11/16.
 */
public class FeeInfo {

    private String monthlyFees;
    private String donationCharges;
    private String meterCost;
    private String meterName;
    private String meterSINo;
    private String previousReading;
    private String lastReadingDate;
    private String currentReading;


    public String getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(String monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public String getDonationCharges() {
        return donationCharges;
    }

    public void setDonationCharges(String donationCharges) {
        this.donationCharges = donationCharges;
    }

    public String getMeterCost() {
        return meterCost;
    }

    public void setMeterCost(String meterCost) {
        this.meterCost = meterCost;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getMeterSINo() {
        return meterSINo;
    }

    public void setMeterSINo(String meterSINo) {
        this.meterSINo = meterSINo;
    }

    public String getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(String previousReading) {
        this.previousReading = previousReading;
    }

    public String getLastReadingDate() {
        return lastReadingDate;
    }

    public void setLastReadingDate(String lastReadingDate) {
        this.lastReadingDate = lastReadingDate;
    }

    public String getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(String currentReading) {
        this.currentReading = currentReading;
    }
}
