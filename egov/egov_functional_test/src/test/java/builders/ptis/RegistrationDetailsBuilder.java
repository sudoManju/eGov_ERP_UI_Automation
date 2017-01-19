package builders.ptis;

import entities.ptis.RegistrationDetails;



/**
 * Created by bimal on 19/1/17.
 */
public class RegistrationDetailsBuilder {

    RegistrationDetails registrationDetails = new RegistrationDetails();

    public RegistrationDetailsBuilder() {
    }
        public RegistrationDetailsBuilder withSellerExecutantName(String  sellerExecutantName) {
          registrationDetails.setSellerExecutantName(sellerExecutantName);
            return this;
        }

        public RegistrationDetailsBuilder withBuyerClaimantName(String buyerClaimantName){
            registrationDetails.setBuyerClaimantName(buyerClaimantName);
            return this;
        }

        public RegistrationDetailsBuilder withDoorNo(String doorNo){
            registrationDetails.setDoorNo(doorNo);
            return this;
        }

        public RegistrationDetailsBuilder withPropertyAddress (String propertyAddress){
            registrationDetails.setPropertyAddress(propertyAddress);
            return this;
        }


        public RegistrationDetails build(){
            return registrationDetails;
        }

}
