package entities.responses.wcms.searchBoundary;

public class Boundary {
    private Object parent;
    private Object latitude;
    private String name;
    private String tenantId;
    private Object boundaryType;
    private String id;
    private int boundaryNum;
    private Object longitude;

    public Object getParent() {
        return this.parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public Object getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
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

    public Object getBoundaryType() {
        return this.boundaryType;
    }

    public void setBoundaryType(Object boundaryType) {
        this.boundaryType = boundaryType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBoundaryNum() {
        return this.boundaryNum;
    }

    public void setBoundaryNum(int boundaryNum) {
        this.boundaryNum = boundaryNum;
    }

    public Object getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }
}
