package entities.requests.assetManagement.createAsset;

public class Asset
{
    private String tenantId;

    private String depreciationRate;

    private String assetDetails;

    private String status;

    private Department department;

    private String width;

    private YearWiseDepreciation[] yearWiseDepreciation;

    private String modeOfAcquisition;

    private String remarks;

    private String accumulatedDepreciation;

    private String dateOfCreation;

    private LocationDetails locationDetails;

    private String version;

    private String grossValue;

    private String description;

    private String name;

    private AssetAttributes[] assetAttributes;

    private String length;

    private String totalArea;

    private AssetCategory assetCategory;

    private String assetReference;

    private String enableYearWiseDepreciation;

    public String getTenantId ()
    {
        return tenantId;
    }

    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getDepreciationRate ()
    {
        return depreciationRate;
    }

    public void setDepreciationRate (String depreciationRate)
    {
        this.depreciationRate = depreciationRate;
    }

    public String getAssetDetails ()
    {
        return assetDetails;
    }

    public void setAssetDetails (String assetDetails)
    {
        this.assetDetails = assetDetails;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Department getDepartment ()
    {
        return department;
    }

    public void setDepartment (Department department)
    {
        this.department = department;
    }

    public String getWidth ()
{
    return width;
}

    public void setWidth (String width)
    {
        this.width = width;
    }

    public YearWiseDepreciation[] getYearWiseDepreciation ()
    {
        return yearWiseDepreciation;
    }

    public void setYearWiseDepreciation (YearWiseDepreciation[] yearWiseDepreciation)
    {
        this.yearWiseDepreciation = yearWiseDepreciation;
    }

    public String getModeOfAcquisition ()
{
    return modeOfAcquisition;
}

    public void setModeOfAcquisition (String modeOfAcquisition)
    {
        this.modeOfAcquisition = modeOfAcquisition;
    }

    public String getRemarks ()
{
    return remarks;
}

    public void setRemarks (String remarks)
    {
        this.remarks = remarks;
    }

    public String getAccumulatedDepreciation ()
    {
        return accumulatedDepreciation;
    }

    public void setAccumulatedDepreciation (String accumulatedDepreciation)
    {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    public String getDateOfCreation ()
    {
        return dateOfCreation;
    }

    public void setDateOfCreation (String dateOfCreation)
    {
        this.dateOfCreation = dateOfCreation;
    }

    public LocationDetails getLocationDetails ()
    {
        return locationDetails;
    }

    public void setLocationDetails (LocationDetails locationDetails)
    {
        this.locationDetails = locationDetails;
    }

    public String getVersion ()
{
    return version;
}

    public void setVersion (String version)
    {
        this.version = version;
    }

    public String getGrossValue ()
    {
        return grossValue;
    }

    public void setGrossValue (String grossValue)
    {
        this.grossValue = grossValue;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public AssetAttributes[] getAssetAttributes ()
    {
        return assetAttributes;
    }

    public void setAssetAttributes (AssetAttributes[] assetAttributes)
    {
        this.assetAttributes = assetAttributes;
    }

    public String getLength ()
{
    return length;
}

    public void setLength (String length)
    {
        this.length = length;
    }

    public String getTotalArea ()
{
    return totalArea;
}

    public void setTotalArea (String totalArea)
    {
        this.totalArea = totalArea;
    }

    public AssetCategory getAssetCategory ()
    {
        return assetCategory;
    }

    public void setAssetCategory (AssetCategory assetCategory)
    {
        this.assetCategory = assetCategory;
    }

    public String getAssetReference ()
    {
        return assetReference;
    }

    public void setAssetReference (String assetReference)
    {
        this.assetReference = assetReference;
    }

    public String getEnableYearWiseDepreciation ()
    {
        return enableYearWiseDepreciation;
    }

    public void setEnableYearWiseDepreciation (String enableYearWiseDepreciation)
    {
        this.enableYearWiseDepreciation = enableYearWiseDepreciation;
    }
}