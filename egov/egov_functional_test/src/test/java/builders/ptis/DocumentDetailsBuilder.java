package builders.ptis;

import entities.ptis.DocumentTypeValue;

/**
 * Created by bimal on 13/2/17.
 */
public class DocumentDetailsBuilder {
    DocumentTypeValue documentDetails = new DocumentTypeValue();

    public DocumentDetailsBuilder() {
    }

    public DocumentDetailsBuilder withdocumentType (String documentType){
        documentDetails.setDocumentType(documentType);
        return this;
    }

    public DocumentDetailsBuilder withDeedNo(String deedNo){
        documentDetails.setDeedNo(deedNo);
        return this;
    }

    public DocumentDetailsBuilder withDeedDate(String deedDate){
        documentDetails.setDeedDate(deedDate);
        return this;
    }

    public DocumentTypeValue build() {
        return documentDetails;
    }

}
