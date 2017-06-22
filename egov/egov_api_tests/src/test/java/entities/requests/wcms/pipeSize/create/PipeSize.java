package entities.requests.wcms.pipeSize.create;

public class PipeSize {
    private String code;
    private int sizeInInch;
    private String tenantId;
    private String sizeInMilimeter;
    private boolean active;
    private String description;
    private int id;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSizeInInch() {
        return this.sizeInInch;
    }

    public void setSizeInInch(int sizeInInch) {
        this.sizeInInch = sizeInInch;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSizeInMilimeter() {
        return this.sizeInMilimeter;
    }

    public void setSizeInMilimeter(String sizeInMilimeter) {
        this.sizeInMilimeter = sizeInMilimeter;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
