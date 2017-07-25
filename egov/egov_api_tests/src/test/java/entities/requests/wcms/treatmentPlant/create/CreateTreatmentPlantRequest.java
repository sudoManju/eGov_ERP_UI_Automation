package entities.requests.wcms.treatmentPlant.create;

import entities.requests.wcms.RequestInfo;

public class CreateTreatmentPlantRequest {
    private RequestInfo requestInfo;
    private TreatmentPlants[] treatmentPlants;

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public TreatmentPlants[] getTreatmentPlants() {
        return this.treatmentPlants;
    }

    public void setTreatmentPlants(TreatmentPlants[] treatmentPlants) {
        this.treatmentPlants = treatmentPlants;
    }
}
