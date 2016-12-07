package builders.ptis;

import entities.ptis.FeeInfo;

/**
 * Created by vinaykumar on 22/11/16.
 */
public class FeeInfoBuilder {

    FeeInfo feeInfo = new FeeInfo();

    public FeeInfoBuilder withMonthlyfees(String monthlyFees) {
        feeInfo.setMonthlyFees(monthlyFees);
        return this;
    }

    public FeeInfoBuilder withDoantionCharges(String doantionCharges) {
        feeInfo.setDonationCharges(doantionCharges);
        return this;
    }

    public FeeInfoBuilder withMeterCost(String meterCost) {
        feeInfo.setMeterCost(meterCost);
        return this;
    }

    public FeeInfoBuilder withMeterName(String meterName) {
        feeInfo.setMeterName(meterName);
        return this;
    }

    public FeeInfoBuilder withMeterSINo(String meterSINo) {
        feeInfo.setMeterSINo(meterSINo);
        return this;
    }

    public FeeInfoBuilder withPreviousReading(String previousReading) {
        feeInfo.setPreviousReading(previousReading);
        return this;
    }

    public FeeInfoBuilder withLastReadingDate(String lastReadingDate) {
        feeInfo.setLastReadingDate(lastReadingDate);
        return this;
    }

    public FeeInfoBuilder withCurrentReading(String currentReading) {
        feeInfo.setCurrentReading(currentReading);
        return this;
    }

    public FeeInfo build(){
        return feeInfo;
    }
}

