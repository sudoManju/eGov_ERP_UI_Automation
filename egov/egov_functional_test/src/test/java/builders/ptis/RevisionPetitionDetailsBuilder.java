package builders.ptis;

import entities.ptis.RevisionPetitionDetails;

/**
 * Created by bimal on 25/1/17.
 */
public class RevisionPetitionDetailsBuilder {
    RevisionPetitionDetails revisionPetitionDetails = new RevisionPetitionDetails();

    public RevisionPetitionDetailsBuilder(){

    }

    public RevisionPetitionDetailsBuilder withRevisionPetitionDetail(String revisionPetitionDetail) {
        revisionPetitionDetails.setRevisionPetitionDetail(revisionPetitionDetail);
        return this;

    }

    public RevisionPetitionDetails build(){
        return revisionPetitionDetails;
    }
}
