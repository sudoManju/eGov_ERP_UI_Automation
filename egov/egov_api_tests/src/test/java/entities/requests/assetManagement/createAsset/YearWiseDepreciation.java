package entities.requests.assetManagement.createAsset;

public class YearWiseDepreciation
{
    private String depreciationRate;

    private String financialYear;

    private String usefulLifeInYears;

    public String getDepreciationRate ()
    {
        return depreciationRate;
    }

    public void setDepreciationRate (String depreciationRate)
    {
        this.depreciationRate = depreciationRate;
    }

    public String getFinancialYear ()
    {
        return financialYear;
    }

    public void setFinancialYear (String financialYear)
    {
        this.financialYear = financialYear;
    }

    public String getUsefulLifeInYears ()
    {
        return usefulLifeInYears;
    }

    public void setUsefulLifeInYears (String usefulLifeInYears)
    {
        this.usefulLifeInYears = usefulLifeInYears;
    }
}
