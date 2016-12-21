package builders.works;

import entities.works.AdminSanctionDetails;

/**
 * Created by karthik on 20/12/16.
 */
public class AdminSanctionDetailsBuilder {

    AdminSanctionDetails adminSanctionDetails = new AdminSanctionDetails();

    public AdminSanctionDetailsBuilder(){

    }
    public AdminSanctionDetailsBuilder withAdministrationSanctionNumber(String administrationSanctionNumber){
        adminSanctionDetails.setAdministrationSanctionNumber(administrationSanctionNumber);
        return this;
    }

    public AdminSanctionDetailsBuilder withAdminSanctionDate(String sanctionDate) {
        adminSanctionDetails.setAdminSanctionDate(sanctionDate);
        return this;
    }

    public AdminSanctionDetails build(){
        return adminSanctionDetails;
    }
}
