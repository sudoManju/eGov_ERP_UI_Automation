package builders.wcms.storageReservoir.create;

import entities.requests.wcms.storageReservoir.create.StorageReservoir;
import tests.BaseAPITest;

import static data.SearchParameterData.TENANT_DEFAULT;

public class StorageReservoirBuilder {
    StorageReservoir storageReservoir = new StorageReservoir();

    public StorageReservoirBuilder() {
        storageReservoir.setName("Storage Reservoir " + String.valueOf(new BaseAPITest().getRandomIntFromRange(100, 9999)));
        storageReservoir.setTenantId(TENANT_DEFAULT);
        storageReservoir.setNoOfSubLines(String.valueOf(new BaseAPITest().getRandomIntFromRange(0, 99)));
        storageReservoir.setNoOfConnection(String.valueOf(new BaseAPITest().getRandomIntFromRange(0, 99)));
        storageReservoir.setNoOfMainDistributionLines(String.valueOf(new BaseAPITest().getRandomIntFromRange(0, 99)));
        storageReservoir.setCapacity(String.valueOf(new BaseAPITest().getRandomIntFromRange(0, 99)));
    }

    public StorageReservoirBuilder withReservoirType(String reservoirType) {
        storageReservoir.setReservoirType(reservoirType);
        return this;
    }

    public StorageReservoirBuilder withWardName(String wardName) {
        storageReservoir.setWardName(wardName);
        return this;
    }

    public StorageReservoirBuilder withNoOfConnection(String noOfConnection) {
        storageReservoir.setNoOfConnection(noOfConnection);
        return this;
    }

    public StorageReservoirBuilder withLocationName(String locationName) {
        storageReservoir.setLocationName(locationName);
        return this;
    }

    public StorageReservoirBuilder withNoOfSubLines(String noOfSubLines) {
        storageReservoir.setNoOfSubLines(noOfSubLines);
        return this;
    }

    public StorageReservoirBuilder withName(String name) {
        storageReservoir.setName(name);
        return this;
    }

    public StorageReservoirBuilder withTenantId(String tenantId) {
        storageReservoir.setTenantId(tenantId);
        return this;
    }

    public StorageReservoirBuilder withZoneName(String zoneName) {
        storageReservoir.setZoneName(zoneName);
        return this;
    }

    public StorageReservoirBuilder withNoOfMainDistributionLines(String noOfMainDistributionLines) {
        storageReservoir.setNoOfMainDistributionLines(noOfMainDistributionLines);
        return this;
    }

    public StorageReservoirBuilder withCapacity(String capacity) {
        storageReservoir.setCapacity(capacity);
        return this;
    }

    public StorageReservoir build() {
        return storageReservoir;
    }
}
