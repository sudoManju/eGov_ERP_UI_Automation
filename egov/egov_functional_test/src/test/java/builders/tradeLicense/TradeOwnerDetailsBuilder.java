package builders.tradeLicense;

import entities.tradeLicense.TradeOwnerDetails;

/**
 * Created by bimal on 23/12/16.
 */
public class TradeOwnerDetailsBuilder {

    TradeOwnerDetails tradeOwnerDetails = new TradeOwnerDetails();

    public TradeOwnerDetailsBuilder() {
    }
        public TradeOwnerDetailsBuilder withAadhaarNumber(String aadhaarNumber){
            tradeOwnerDetails.setAadhaarNumber(aadhaarNumber);
            return this;
        }

      public TradeOwnerDetails build() {
          return tradeOwnerDetails;
      }

}


