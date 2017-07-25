package entities.responses.wcms.treatmentPlant;

import entities.responses.wcms.ResponseInfo;

public class CreateTreatmentPlantResponse {
    private ResponseInfo responseInfo;
    private TreatmentPlants[] treatmentPlants;

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public TreatmentPlants[] getTreatmentPlants() {
        return this.treatmentPlants;
    }

    public void setTreatmentPlants(TreatmentPlants[] treatmentPlants) {
        this.treatmentPlants = treatmentPlants;
    }
}
