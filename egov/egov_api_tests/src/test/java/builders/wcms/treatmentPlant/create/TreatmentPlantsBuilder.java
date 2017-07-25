package builders.wcms.treatmentPlant.create;

import entities.requests.wcms.treatmentPlant.create.TreatmentPlants;
import tests.BaseAPITest;

import static data.SearchParameterData.TENANT_DEFAULT;

public class TreatmentPlantsBuilder {

    TreatmentPlants treatmentPlants = new TreatmentPlants();

    public TreatmentPlantsBuilder() {
        treatmentPlants.setCode("");
        treatmentPlants.setName("TreatmentPlant " + String.valueOf(new BaseAPITest().getRandomIntFromRange(100, 9999)));
        treatmentPlants.setTenantId(TENANT_DEFAULT);
        treatmentPlants.setCapacity(String.valueOf(new BaseAPITest().getRandomIntFromRange(1, 99)));
        treatmentPlants.setDescription("Treatment Plant Description");
    }

    public TreatmentPlantsBuilder withStorageReservoirName(String storageReservoirName) {
        treatmentPlants.setStorageReservoirName(storageReservoirName);
        return this;
    }

    public TreatmentPlantsBuilder withWardName(String wardName) {
        treatmentPlants.setWardName(wardName);
        return this;
    }

    public TreatmentPlantsBuilder withLocationName(String locationName) {
        treatmentPlants.setLocationName(locationName);
        return this;
    }

    public TreatmentPlantsBuilder withName(String name) {
        treatmentPlants.setName(name);
        return this;
    }

    public TreatmentPlantsBuilder withCode(String code) {
        treatmentPlants.setCode(code);
        return this;
    }

    public TreatmentPlantsBuilder withTenantId(String tenantId) {
        treatmentPlants.setTenantId(tenantId);
        return this;
    }

    public TreatmentPlantsBuilder withDescription(String description) {
        treatmentPlants.setDescription(description);
        return this;
    }

    public TreatmentPlantsBuilder withZoneName(String zoneName) {
        treatmentPlants.setZoneName(zoneName);
        return this;
    }

    public TreatmentPlantsBuilder withPlantType(String plantType) {
        treatmentPlants.setPlantType(plantType);
        return this;
    }

    public TreatmentPlantsBuilder withCapacity(String capacity) {
        treatmentPlants.setCapacity(capacity);
        return this;
    }

    public TreatmentPlants build() {
        return treatmentPlants;
    }
}
