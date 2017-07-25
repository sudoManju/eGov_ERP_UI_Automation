package builders.wcms.treatmentPlant.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.treatmentPlant.create.CreateTreatmentPlantRequest;
import entities.requests.wcms.treatmentPlant.create.TreatmentPlants;

public class CreateTreatmentPlantRequestBuilder {

    CreateTreatmentPlantRequest createTreatmentPlantRequest = new CreateTreatmentPlantRequest();

    public CreateTreatmentPlantRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        createTreatmentPlantRequest.setRequestInfo(requestInfo);
        return this;
    }

    public CreateTreatmentPlantRequestBuilder withTreatmentPlants(TreatmentPlants[] treatmentPlants) {
        createTreatmentPlantRequest.setTreatmentPlants(treatmentPlants);
        return this;
    }

    public CreateTreatmentPlantRequest build() {
        return createTreatmentPlantRequest;
    }
}
