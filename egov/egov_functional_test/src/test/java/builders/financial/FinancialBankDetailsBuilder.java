package builders.financial;

import entities.financial.FinancialBankDetails;

/**
 * Created by vinaykumar on 27/12/16.
 */
public class FinancialBankDetailsBuilder {

    FinancialBankDetails financialBankDetails = new FinancialBankDetails();

    public FinancialBankDetailsBuilder withBankName(String bankName){
        financialBankDetails.setBankName(bankName);
        return this;
    }

    public FinancialBankDetailsBuilder withAccountNumber(String accountNumber){
        financialBankDetails.setAccountNumber(accountNumber);
        return this;
    }

    public FinancialBankDetails build(){
        return financialBankDetails;
    }
}
