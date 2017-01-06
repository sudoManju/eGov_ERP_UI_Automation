package builders.councilManagement;
import entities.councilManagement.CreatePreambleDetails;

/**
 * Created by tester1 on 1/4/2017.
 */
public class PreambleDetailsBuilder {

    CreatePreambleDetails createPreambleDetails=new CreatePreambleDetails();

    public PreambleDetailsBuilder withPreambleDepartment(String preambleDepartment) {
        createPreambleDetails.setPreambleDepartment(preambleDepartment);
        return this;
    }

    public PreambleDetailsBuilder withSanctionAmount(String amount) {
        createPreambleDetails.setAmount(amount);
        return this;
    }

    public PreambleDetailsBuilder withGistOfPreamble(String gistOfPreamble) {
        createPreambleDetails.setGistOfPreamble(gistOfPreamble);
        return this;
    }

    public CreatePreambleDetails build() {
        return createPreambleDetails;
    }
}
