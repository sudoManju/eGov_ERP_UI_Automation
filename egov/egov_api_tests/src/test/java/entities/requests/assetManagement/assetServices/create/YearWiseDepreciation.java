package entities.requests.assetManagement.assetServices.create;
public class YearWiseDepreciation {
    private int depreciationRate;

    private String financialYear;

    public int getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(int depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public int getUsefulLifeInYears() {
        return usefulLifeInYears;
    }

    public void setUsefulLifeInYears(int usefulLifeInYears) {
        this.usefulLifeInYears = usefulLifeInYears;
    }

    private int usefulLifeInYears;


}


