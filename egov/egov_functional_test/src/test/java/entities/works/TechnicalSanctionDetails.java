package entities.works;

/**
 * Created by karthik on 20/12/16.
 */
public class TechnicalSanctionDetails {

    String technicalSanctionNumber;
    String technicalSanctionDate;
    String technicalSanctionAuthority;

    public String getTechnicalSanctionNumber() {
        return technicalSanctionNumber;
    }

    public void setTechnicalSanctionNumber(String technicalSanctionNumber) {
        this.technicalSanctionNumber = technicalSanctionNumber;
    }

    public String getTechnicalSanctionDate() {
        return technicalSanctionDate;
    }

    public void setTechnicalSanctionDate(String technicalSanctionDate) {
        this.technicalSanctionDate = technicalSanctionDate;
    }

    public String getTechnicalSanctionAuthority() {
        return technicalSanctionAuthority;
    }

    public void setTechnicalSanctionAuthority(String technicalSanctionAuthority) {
        this.technicalSanctionAuthority = technicalSanctionAuthority;
    }
}
