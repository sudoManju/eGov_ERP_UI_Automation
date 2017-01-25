package builders.collections;

import entities.collections.PaymentMethod;

/**
 * Created by karthik on 18/1/17.
 */
public class PaymentMethodBuilder {

    PaymentMethod paymentMethod = new PaymentMethod();

    public PaymentMethodBuilder withChequeNumber(String chequeNumber){
        paymentMethod.setChequeNumber(chequeNumber);
        return this;
    }

    public PaymentMethodBuilder withBankName(String bankName){
        paymentMethod.setBankName(bankName);
        return this;
    }

    public PaymentMethodBuilder withAccountNumber(String accountNumber){
        paymentMethod.setAccountNumber(accountNumber);
        return this;
    }

    public PaymentMethod build(){
        return paymentMethod;
    }
}
