package entities.ptis;

/**
 * Created by karthik on 22/11/16.
 */
public class FeeDetails {

   private String monthlyFee;
   private String donationCharges;

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getDonationCharges() {
        return donationCharges;
    }

    public void setDonationCharges(String donationCharges) {
        this.donationCharges = donationCharges;
    }
}
