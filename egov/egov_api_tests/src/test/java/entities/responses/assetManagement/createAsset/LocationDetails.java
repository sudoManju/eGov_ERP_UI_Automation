package entities.responses.assetManagement.createAsset;

public class LocationDetails {
    private Object zone;
    private Object street;
    private Object revenueWard;
    private Object pinCode;
    private int locality;
    private Object block;
    private String doorNo;
    private Object electionWard;

    public Object getZone() {
        return this.zone;
    }

    public void setZone(Object zone) {
        this.zone = zone;
    }

    public Object getStreet() {
        return this.street;
    }

    public void setStreet(Object street) {
        this.street = street;
    }

    public Object getRevenueWard() {
        return this.revenueWard;
    }

    public void setRevenueWard(Object revenueWard) {
        this.revenueWard = revenueWard;
    }

    public Object getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(Object pinCode) {
        this.pinCode = pinCode;
    }

    public int getLocality() {
        return this.locality;
    }

    public void setLocality(int locality) {
        this.locality = locality;
    }

    public Object getBlock() {
        return this.block;
    }

    public void setBlock(Object block) {
        this.block = block;
    }

    public String getDoorNo() {
        return this.doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public Object getElectionWard() {
        return this.electionWard;
    }

    public void setElectionWard(Object electionWard) {
        this.electionWard = electionWard;
    }
}
