package entities.requests.propertyTax.services.create;

public class VacantLand
{
    private int resdPlotArea;

    private String surveyNumber;

    private int nonResdPlotArea;

    private String pattaNumber;

    private String layoutPermissionNo;

    private int marketValue;

    private int capitalValue;

    private AuditDetails auditDetails;

    private String layoutPermissionDate;

    private String layoutApprovedAuth;

    public int getResdPlotArea() {
        return resdPlotArea;
    }

    public void setResdPlotArea(int resdPlotArea) {
        this.resdPlotArea = resdPlotArea;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public int getNonResdPlotArea() {
        return nonResdPlotArea;
    }

    public void setNonResdPlotArea(int nonResdPlotArea) {
        this.nonResdPlotArea = nonResdPlotArea;
    }

    public String getPattaNumber() {
        return pattaNumber;
    }

    public void setPattaNumber(String pattaNumber) {
        this.pattaNumber = pattaNumber;
    }

    public String getLayoutPermissionNo() {
        return layoutPermissionNo;
    }

    public void setLayoutPermissionNo(String layoutPermissionNo) {
        this.layoutPermissionNo = layoutPermissionNo;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public int getCapitalValue() {
        return capitalValue;
    }

    public void setCapitalValue(int capitalValue) {
        this.capitalValue = capitalValue;
    }

    public AuditDetails getAuditDetails() {
        return auditDetails;
    }

    public void setAuditDetails(AuditDetails auditDetails) {
        this.auditDetails = auditDetails;
    }

    public String getLayoutPermissionDate() {
        return layoutPermissionDate;
    }

    public void setLayoutPermissionDate(String layoutPermissionDate) {
        this.layoutPermissionDate = layoutPermissionDate;
    }

    public String getLayoutApprovedAuth() {
        return layoutApprovedAuth;
    }

    public void setLayoutApprovedAuth(String layoutApprovedAuth) {
        this.layoutApprovedAuth = layoutApprovedAuth;
    }
}
