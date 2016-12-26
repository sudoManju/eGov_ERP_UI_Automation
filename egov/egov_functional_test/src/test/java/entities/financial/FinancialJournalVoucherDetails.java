package entities.financial;

/**
 * Created by vinaykumar on 26/12/16.
 */
public class FinancialJournalVoucherDetails {

    private String voucherType;
    private String accountCode1;
    private String accountCode2;
    private String department;
    private String function;

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getAccountCode1() {
        return accountCode1;
    }

    public void setAccountCode1(String accountCode1) {
        this.accountCode1 = accountCode1;
    }

    public String getAccountCode2() {
        return accountCode2;
    }

    public void setAccountCode2(String accountCode2) {
        this.accountCode2 = accountCode2;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
