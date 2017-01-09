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

    public LegencyDetails build(){
        return legencyDetails;
    }
}
