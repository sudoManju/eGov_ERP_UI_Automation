package builders.ptis;

import entities.ptis.EnclosedDocuments;

/**
 * Created by karthik on 5/12/16.
 */
public class EnclosedDocumentsBuilder {

    EnclosedDocuments enclosedDocuments = new EnclosedDocuments();

    public EnclosedDocumentsBuilder withDocumentNumber1(String documentNumber){
        enclosedDocuments.setDocumentNumber1(documentNumber);
        return this;
    }

    public EnclosedDocumentsBuilder withDocumentDate1(String documentDate){
        enclosedDocuments.setDocumentDate1(documentDate);
        return this;
    }
    public EnclosedDocumentsBuilder withDocumentNumber2(String documentNumber){
        enclosedDocuments.setDocumentNumber2(documentNumber);
        return this;
    }
    public EnclosedDocumentsBuilder withDocumentDate2(String documentDate){
        enclosedDocuments.setDocumentDate2(documentDate);
        return this;
    }
    public EnclosedDocumentsBuilder withDocumentNumber3(String documentNumber){
        enclosedDocuments.setDocumentNumber3(documentNumber);
        return this;
    }
    public EnclosedDocumentsBuilder withDocumentDate3(String documentDate){
        enclosedDocuments.setDocumentDate3(documentDate);
        return this;
    }

    public EnclosedDocuments build(){
        return enclosedDocuments;
    }
}
