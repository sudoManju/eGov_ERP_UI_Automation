package entities.responses.propertyTax.masters.occupancy.create;

import entities.responses.propertyTax.masters.ResponseInfo;

public class OccupancyMasterResponse {
    private OccuapancyMasters[] occuapancyMasters;
    private ResponseInfo responseInfo;

    public OccuapancyMasters[] getOccuapancyMasters() {
        return this.occuapancyMasters;
    }

    public void setOccuapancyMasters(OccuapancyMasters[] occuapancyMasters) {
        this.occuapancyMasters = occuapancyMasters;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
