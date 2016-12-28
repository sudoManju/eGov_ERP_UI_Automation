package entities.financial;

/**
 * Created by vinaykumar on 27/12/16.
 */
public class FinancialExpenseBillDetails {

    private String expenseFund;
    private String expenseDeparment;
    private String expenseFunction;
    private String expenseBillSubType;
    private String expenseAccountCodeDebit;
    private String expenseAccountCodeCredit;

    public String getExpenseFund() {
        return expenseFund;
    }

    public void setExpenseFund(String expenseFund) {
        this.expenseFund = expenseFund;
    }

    public String getExpenseDeparment() {
        return expenseDeparment;
    }

    public void setExpenseDeparment(String expenseDeparment) {
        this.expenseDeparment = expenseDeparment;
    }

    public String getExpenseFunction() {
        return expenseFunction;
    }

    public void setExpenseFunction(String expenseFunction) {
        this.expenseFunction = expenseFunction;
    }

    public String getExpenseBillSubType() {
        return expenseBillSubType;
    }

    public void setExpenseBillSubType(String expenseBillSubType) {
        this.expenseBillSubType = expenseBillSubType;
    }

    public String getExpenseAccountCodeDebit() {
        return expenseAccountCodeDebit;
    }

    public void setExpenseAccountCodeDebit(String expenseAccountCodeDebit) {
        this.expenseAccountCodeDebit = expenseAccountCodeDebit;
    }

    public String getExpenseAccountCodeCredit() {
        return expenseAccountCodeCredit;
    }

    public void setExpenseAccountCodeCredit(String expenseAccountCodeCredit) {
        this.expenseAccountCodeCredit = expenseAccountCodeCredit;
    }
}
