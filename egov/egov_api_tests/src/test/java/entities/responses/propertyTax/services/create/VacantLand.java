package entities.responses.propertyTax.services.create;

public class VacantLand {
    private String layoutApprovedAuth;
    private AuditDetails auditDetails;
    private double resdPlotArea;
    private double marketValue;
    private String layoutPermissionDate;
    private String surveyNumber;
    private String layoutPermissionNo;
    private Object id;
    private double nonResdPlotArea;
    private double capitalValue;
    private String pattaNumber;

    public String getLayoutApprovedAuth() {
        return this.layoutApprovedAuth;
    }

    public void setLayoutApprovedAuth(String layoutApprovedAuth) {
        this.layoutApprovedAuth = layoutApprovedAuth;
    }

    public AuditDetails getAuditDetails() {
        return this.auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public double getResdPlotArea() {
        return this.resdPlotArea;
    }

    public void setResdPlotArea(double resdPlotArea) {
        this.resdPlotArea = resdPlotArea;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public String getLayoutPermissionDate() {
        return this.layoutPermissionDate;
    }

    public void setLayoutPermissionDate(String layoutPermissionDate) {
        this.layoutPermissionDate = layoutPermissionDate;
    }

    public String getSurveyNumber() {
        return this.surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public String getLayoutPermissionNo() {
        return this.layoutPermissionNo;
    }

    public void setLayoutPermissionNo(String layoutPermissionNo) {
        this.layoutPermissionNo = layoutPermissionNo;
    }

    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public double getNonResdPlotArea() {
        return this.nonResdPlotArea;
    }

    public void setNonResdPlotArea(double nonResdPlotArea) {
        this.nonResdPlotArea = nonResdPlotArea;
    }

    public double getCapitalValue() {
        return this.capitalValue;
    }

    public void setCapitalValue(double capitalValue) {
        this.capitalValue = capitalValue;
    }

    public String getPattaNumber() {
        return this.pattaNumber;
    }

    public void setPattaNumber(String pattaNumber) {
        this.pattaNumber = pattaNumber;
    }
}
