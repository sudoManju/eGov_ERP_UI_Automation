package builders.financial;

import builders.LoginDetailsBuilder;
import entities.LoginDetails;
import entities.financial.FinancialJournalVoucherDetails;

/**
 * Created by vinaykumar on 26/12/16.
 */
public class FinancialJournalVoucherDetailsBuilder {

    FinancialJournalVoucherDetails financialJournalVoucherDetails = new FinancialJournalVoucherDetails();

    public FinancialJournalVoucherDetailsBuilder withDate(String date){
        financialJournalVoucherDetails.setDate(date);
        return this;
    }

    public FinancialJournalVoucherDetailsBuilder withVoucherType(String voucherType){
        financialJournalVoucherDetails.setVoucherType(voucherType);
        return this;
    }

    public FinancialJournalVoucherDetailsBuilder withAccountCode1(String accountCode1){
        financialJournalVoucherDetails.setAccountCode1(accountCode1);
        return this;
    }

    public FinancialJournalVoucherDetailsBuilder withAccountCode2(String accountCode2){
        financialJournalVoucherDetails.setAccountCode2(accountCode2);
        return this;
    }

    public FinancialJournalVoucherDetailsBuilder withDepartment(String department){
        financialJournalVoucherDetails.setDepartment(department);
        return this;
    }

    public FinancialJournalVoucherDetailsBuilder withFunction(String function){
        financialJournalVoucherDetails.setFunction(function);
        return this;
    }

    public FinancialJournalVoucherDetails build(){
        return financialJournalVoucherDetails;
    }
}
