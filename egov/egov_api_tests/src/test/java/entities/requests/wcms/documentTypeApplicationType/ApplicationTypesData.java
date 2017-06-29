package entities.requests.wcms.documentTypeApplicationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApplicationTypesData {
    private List<String> applicationTypes;
    private Random randomGenerator;

    public ApplicationTypesData() {
        applicationTypes = new ArrayList<>();
        randomGenerator = new Random();
        applicationTypes.add("CHANGEOFUSE"); //
        applicationTypes.add("HOLDINGCONNECTION");
        applicationTypes.add("REGULARIZATIONCONNECTION");
        applicationTypes.add("ADDITIONALCONNECTION");
        applicationTypes.add("CLOSINGCONNECTION");
        applicationTypes.add("NEWCONNECTION");
        applicationTypes.add("TITTLETRANSFER");
    }

    public String randomApplicationType() {
        return applicationTypes.get(randomGenerator.nextInt(applicationTypes.size()));
    }
}
