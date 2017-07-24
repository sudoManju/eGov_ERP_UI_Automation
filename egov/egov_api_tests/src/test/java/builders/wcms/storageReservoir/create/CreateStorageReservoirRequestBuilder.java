package builders.wcms.storageReservoir.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.storageReservoir.create.CreateStorageReservoirRequest;
import entities.requests.wcms.storageReservoir.create.StorageReservoir;

public class CreateStorageReservoirRequestBuilder {
    CreateStorageReservoirRequest createStorageReservoirRequest = new CreateStorageReservoirRequest();

    public CreateStorageReservoirRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        createStorageReservoirRequest.setRequestInfo(requestInfo);
        return this;
    }

    public CreateStorageReservoirRequestBuilder withStorageReservoir(StorageReservoir[] storageReservoir) {
        createStorageReservoirRequest.setStorageReservoir(storageReservoir);
        return this;
    }

    public CreateStorageReservoirRequest build() {
        return createStorageReservoirRequest;
    }
}
