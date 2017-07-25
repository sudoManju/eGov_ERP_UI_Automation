package entities.requests.assetManagement.assetServices.create;

import entities.requests.assetManagement.RequestInfo;

public class CreateAssetRequest
{
    private Asset Asset;

    private RequestInfo RequestInfo;

    public Asset getAsset ()
    {
        return Asset;
    }

    public void setAsset (Asset Asset)
    {
        this.Asset = Asset;
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

