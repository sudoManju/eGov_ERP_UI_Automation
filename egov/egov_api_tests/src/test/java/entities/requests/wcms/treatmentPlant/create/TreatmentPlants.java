package entities.requests.wcms.treatmentPlant.create;

public class TreatmentPlants {
    private String storageReservoirName;
    private String wardName;
    private String locationName;
    private String name;
    private String tenantId;
    private String description;
    private String zoneName;
    private String plantType;
    private String capacity;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStorageReservoirName() {
        return this.storageReservoirName;
    }

    public void setStorageReservoirName(String storageReservoirName) {
        this.storageReservoirName = storageReservoirName;
    }

    public String getWardName() {
        return this.wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getPlantType() {
        return this.plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
