package builders.ptis;

import entities.ptis.FeeDetails;

/**
 * Created by karthik on 22/11/16.
 */
public class FeeDetailsBuilder {

    FeeDetails feeDetails = new FeeDetails();

    public FeeDetailsBuilder(){}

    public FeeDetailsBuilder withMonthlyFee(String monthlyFee){
        feeDetails.setMonthlyFee(monthlyFee);
        return this;
    }

    public FeeDetailsBuilder withDonationCharges(String donationCharges){
        feeDetails.setDonationCharges(donationCharges);
        return this;
    }

    public FeeDetails build(){
        return feeDetails;
    }
}
