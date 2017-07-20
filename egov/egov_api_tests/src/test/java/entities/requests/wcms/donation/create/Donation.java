package entities.requests.wcms.donation.create;

public class Donation {
    private String fromDate;
    private String propertyType;
    private String toDate;
    private String tenantId;
    private String minPipeSize;
    private boolean active;
    private String maxPipeSize;
    private String category;
    private String donationAmount;
    private String usageType;

    public String getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getToDate() {
        return this.toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getMinPipeSize() {
        return this.minPipeSize;
    }

    public void setMinPipeSize(String minPipeSize) {
        this.minPipeSize = minPipeSize;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMaxPipeSize() {
        return this.maxPipeSize;
    }

    public void setMaxPipeSize(String maxPipeSize) {
        this.maxPipeSize = maxPipeSize;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDonationAmount() {
        return this.donationAmount;
    }

    public void setDonationAmount(String donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getUsageType() {
        return this.usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }
}
