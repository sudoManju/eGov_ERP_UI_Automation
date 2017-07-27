package entities.responses.propertyTax.billingServices.taHeadMaster;

public class TaxHeadMasters {
    private long validTill;
    private String code;
    private long validFrom;
    private boolean isActualDemand;
    private GlCodes[] glCodes;
    private String service;
    private String tenantId;
    private String name;
    private String id;
    private boolean isDebit;
    private String category;
    private Object auditDetail;
    private Object order;

    public long getValidTill() {
        return this.validTill;
    }

    public void setValidTill(long validTill) {
        this.validTill = validTill;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getValidFrom() {
        return this.validFrom;
    }

    public void setValidFrom(long validFrom) {
        this.validFrom = validFrom;
    }

    public boolean getIsActualDemand() {
        return this.isActualDemand;
    }

    public void setIsActualDemand(boolean isActualDemand) {
        this.isActualDemand = isActualDemand;
    }

    public GlCodes[] getGlCodes() {
        return this.glCodes;
    }

    public void setGlCodes(GlCodes[] glCodes) {
        this.glCodes = glCodes;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsDebit() {
        return this.isDebit;
    }

    public void setIsDebit(boolean isDebit) {
        this.isDebit = isDebit;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getAuditDetail() {
        return this.auditDetail;
    }

    public void setAuditDetail(Object auditDetail) {
        this.auditDetail = auditDetail;
    }

    public Object getOrder() {
        return this.order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }
}
