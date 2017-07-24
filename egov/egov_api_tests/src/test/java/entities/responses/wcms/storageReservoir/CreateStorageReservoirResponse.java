package entities.responses.wcms.storageReservoir;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateStorageReservoirResponse {
    @JsonProperty("storageReservoirs")
    private StorageReservoirs[] storageReservoirs;
    private ResponseInfo responseInfo;

    public StorageReservoirs[] getStorageReservoirs() {
        return this.storageReservoirs;
    }

    public void setStorageReservoirs(StorageReservoirs[] storageReservoirs) {
        this.storageReservoirs = storageReservoirs;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
