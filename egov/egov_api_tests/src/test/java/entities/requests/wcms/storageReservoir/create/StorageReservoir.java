package entities.requests.wcms.storageReservoir.create;

public class StorageReservoir {
    private String reservoirType;
    private String wardName;
    private String noOfConnection;
    private String locationName;
    private String noOfSubLines;
    private String name;
    private String tenantId;
    private String zoneName;
    private String noOfMainDistributionLines;
    private String capacity;

    public String getReservoirType() {
        return this.reservoirType;
    }

    public void setReservoirType(String reservoirType) {
        this.reservoirType = reservoirType;
    }

    public String getWardName() {
        return this.wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getNoOfConnection() {
        return this.noOfConnection;
    }

    public void setNoOfConnection(String noOfConnection) {
        this.noOfConnection = noOfConnection;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getNoOfSubLines() {
        return this.noOfSubLines;
    }

    public void setNoOfSubLines(String noOfSubLines) {
        this.noOfSubLines = noOfSubLines;
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

    public String getZoneName() {
        return this.zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getNoOfMainDistributionLines() {
        return this.noOfMainDistributionLines;
    }

    public void setNoOfMainDistributionLines(String noOfMainDistributionLines) {
        this.noOfMainDistributionLines = noOfMainDistributionLines;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
