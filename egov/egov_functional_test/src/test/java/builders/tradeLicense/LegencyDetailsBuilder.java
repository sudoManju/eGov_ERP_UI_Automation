package builders.tradeLicense;

import entities.tradeLicense.LegencyDetails;

/**
 * Created by bimal on 9/1/17.
 */
public class LegencyDetailsBuilder {

    LegencyDetails legencyDetails = new LegencyDetails();

    public LegencyDetailsBuilder() {
    }
    public LegencyDetailsBuilder withAmount1(String amount1){
        legencyDetails.setAmount1(amount1);
        return this;
    }

    public LegencyDetailsBuilder withAmount2(String amount2) {
        legencyDetails.setAmount2(amount2);
        return this;
    }

    public LegencyDetailsBuilder withAmount3(String amount3) {
        legencyDetails.setAmount3(amount3);
        return this;
    }

    public LegencyDetailsBuilder withAmount4(String amount4) {
        legencyDetails.setAmount4(amount4);
        return this;
    }

    public LegencyDetailsBuilder withAmount5(String amount5) {
        legencyDetails.setAmount5(amount5);
        return this;
    }

    public LegencyDetailsBuilder withAmount6(String amount6) {
        legencyDetails.setAmount6(amount6);
        return this;
    }
    public LegencyDetails build(){
        return legencyDetails;
    }


}
