package entities.responses.pgrCollections.CreateComplaint;

public class Values {
    private String complainantAddress;
    private String receivingMode;
    private String receivingCenter;
    private String status;

    public String getComplainantAddress() {
        return this.complainantAddress;
    }

    public void setComplainantAddress(String complainantAddress) {
        this.complainantAddress = complainantAddress;
    }

    public String getReceivingMode() {
        return this.receivingMode;
    }

    public void setReceivingMode(String receivingMode) {
        this.receivingMode = receivingMode;
    }

    public String getReceivingCenter() {
        return this.receivingCenter;
    }

    public void setReceivingCenter(String receivingCenter) {
        this.receivingCenter = receivingCenter;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
