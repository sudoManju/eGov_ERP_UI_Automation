package builders.collections;

import entities.collections.ChequeDetails;

public class ChequeDetailsBuilder {

    ChequeDetails chequeDetails = new ChequeDetails();

    public ChequeDetailsBuilder() {

        chequeDetails.setPaidBy("Nadir");
        chequeDetails.setChequeNumber("123456");
        chequeDetails.setBankName("102");
    }


    public ChequeDetails build() {
        return chequeDetails;
    }

    public ChequeDetailsBuilder withChequeDate(String date) {
        chequeDetails.setChequeDate(date);
        return this;
    }

    public ChequeDetailsBuilder withChequeNumber(String chequeNumber) {
        chequeDetails.setChequeNumber(chequeNumber);
        return this;
    }

    public ChequeDetailsBuilder withBankName(String bankName) {
        chequeDetails.setBankName(bankName);
        return this;
    }

    public ChequeDetailsBuilder withPaidBy(String paidBy) {
        chequeDetails.setPaidBy(paidBy);
        return this;
    }
}
