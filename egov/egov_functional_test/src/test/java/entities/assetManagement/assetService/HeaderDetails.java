package entities.assetManagement.assetService;

public class HeaderDetails {

    private String department;

    private String assetCategory;

    private String description;

    private String modeOfAcquisition;

    private String depreciationRate;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModeOfAcquisition() {
        return modeOfAcquisition;
    }

    public void setModeOfAcquisition(String modeOfAcquisition) {
        this.modeOfAcquisition = modeOfAcquisition;
    }

    public void setDepreciationRate(String depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public String getDepreciationRate() {
        return depreciationRate;
    }
}
