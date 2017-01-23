package builders.grievances;

import entities.grievances.CreateComplaintDetails;

/**
 * Created by tester1 on 1/23/2017.
 */
public class CreateComplaintDetailsBuilder {

    CreateComplaintDetails createComplaintDetails= new CreateComplaintDetails();

    public CreateComplaintDetailsBuilder withCitizenName(String citizenName) {
         createComplaintDetails.setCitizenname(citizenName);
         return this;
    }

    public CreateComplaintDetailsBuilder withCitizenMobNo(String citizenMobNo) {
        createComplaintDetails.setCitizenMobNo(citizenMobNo);
        return this;
    }

    public CreateComplaintDetailsBuilder withEmailId(String emailId) {
        createComplaintDetails.setEmailId(emailId);
        return this;
    }

    public CreateComplaintDetailsBuilder withCitizenAddress(String citizenAddress) {
        createComplaintDetails.setCitizenAddress(citizenAddress);
        return this;
    }

    public CreateComplaintDetails build() {
        return createComplaintDetails;
    }
}
