package entities.requests.wcms.storageReservoir.create;

import entities.requests.wcms.RequestInfo;

public class CreateStorageReservoirRequest {
    private RequestInfo requestInfo;
    private StorageReservoir[] storageReservoir;

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public StorageReservoir[] getStorageReservoir() {
        return this.storageReservoir;
    }

    public void setStorageReservoir(StorageReservoir[] storageReservoir) {
        this.storageReservoir = storageReservoir;
    }
}
