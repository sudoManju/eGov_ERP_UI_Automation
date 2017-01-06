package entities.councilManagement;

/**
 * Created by tester1 on 1/4/2017.
 */
public class CreatePreambleDetails {


    private String preambleDepartment;
    private String amount;
    private String gistOfPreamble;
    private String createPreambleDetails;

    public void setPreambleDepartment(String preambleDepartment) {
        this.preambleDepartment = preambleDepartment;
    }

    public String getPreambleDepartment() {
        return preambleDepartment;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }


    public String getGistOfPreamble() {
        return gistOfPreamble;
    }

    public void setGistOfPreamble(String gistOfPreamble) {
        this.gistOfPreamble = gistOfPreamble;
    }

    public String build() {
        return createPreambleDetails;
    }

}
