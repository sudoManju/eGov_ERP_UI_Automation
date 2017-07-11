package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.AuditDetails;
import entities.requests.propertyTax.services.create.VacantLand;

public class VacantLandBuilder {

    VacantLand vacantLand = new VacantLand();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public VacantLandBuilder(){
        vacantLand.setSurveyNumber("surveyNumber2");
        vacantLand.setPattaNumber("pn2");
        vacantLand.setMarketValue(10748);
        vacantLand.setLayoutApprovedAuth("laa2");
        vacantLand.setLayoutPermissionNo("lpn2");
        vacantLand.setLayoutPermissionDate("10/05/2017");
        vacantLand.setCapitalValue(452200);
        vacantLand.setResdPlotArea(475);
        vacantLand.setNonResdPlotArea(658);
        vacantLand.setAuditDetails(auditDetails);
    }

    public VacantLand build(){
        return vacantLand;
    }
}
