package builders.assetManagement.assetService;

import entities.assetManagement.assetService.HeaderDetails;

public final class HeaderDetailsBuilder {

    HeaderDetails headerDetails = new HeaderDetails();

    public HeaderDetailsBuilder withDepartment(String department) {
        headerDetails.setDepartment(department);
        return this;
    }

    public HeaderDetailsBuilder withAssetCategory(String assetCategory) {
        headerDetails.setAssetCategory(assetCategory);
        return this;
    }

    public HeaderDetailsBuilder withModeOfAcquisition(String modeOfAcquisition) {
        headerDetails.setModeOfAcquisition(modeOfAcquisition);
        return this;
    }

    public HeaderDetailsBuilder withDepreciationRate(String depreciationRate){
        headerDetails.setDepreciationRate(depreciationRate);
        return this;
    }

    public HeaderDetails build() {
        return headerDetails;
    }
}
