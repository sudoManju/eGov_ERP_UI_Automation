package entities.requests.assetManagement.assetCategory;


public class AssetCategotyCreateRequest
{
    private AssetCategory AssetCategory;

    private RequestInfo RequestInfo;

    public AssetCategory getAssetCategory ()
    {
        return AssetCategory;
    }

    public void setAssetCategory (AssetCategory AssetCategory)
    {
        this.AssetCategory = AssetCategory;
    }

    public RequestInfo getRequestInfo ()
    {
        return RequestInfo;
    }

    public void setRequestInfo (RequestInfo RequestInfo)
    {
        this.RequestInfo = RequestInfo;
    }

}