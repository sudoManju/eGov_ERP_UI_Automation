package entities.responses.pgrCollections;

public class ReceivingCenterResponse {

    private String id;

    private String orderNo;

    private String crnRequired;

    private String name;

    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCrnRequired() {
        return crnRequired;
    }

    public void setCrnRequired(String crnRequired) {
        this.crnRequired = crnRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
