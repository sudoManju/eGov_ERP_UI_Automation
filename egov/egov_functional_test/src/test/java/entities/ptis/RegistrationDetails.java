package entities.ptis;

/**
 * Created by bimal on 18/1/17.
 */
public class RegistrationDetails {

        private String sellerExecutantName;
        private String buyerClaimantName;
        private String doorNo;
        private String propertyAddress;


    public String getSellerExecutantName() {
        return sellerExecutantName;
    }

    public void setSellerExecutantName(String sellerExecutantName) {
        this.sellerExecutantName = sellerExecutantName;
    }

    public String getBuyerClaimantName() {
        return buyerClaimantName;
    }

    public void setBuyerClaimantName(String buyerClaimantName) {
        this.buyerClaimantName = buyerClaimantName;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    }
