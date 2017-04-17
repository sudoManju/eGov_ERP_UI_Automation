package builders.eGovEIS.hrLeave;

import entities.requests.eGovEIS.hrLeave.openingBalance.LeaveOpeningBalance;
import entities.requests.eGovEIS.hrLeave.openingBalance.LeaveType;

public final class LeaveOpeningBalanceBuilder {

    LeaveOpeningBalance leaveOpeningBalance = new LeaveOpeningBalance();
    LeaveType leaveType = new LeaveType();

    public LeaveOpeningBalanceBuilder() {
        leaveOpeningBalance.setLeaveType(leaveType);
        leaveOpeningBalance.setId(null);
        leaveOpeningBalance.setEmployee(1);
        leaveOpeningBalance.setCalendarYear(2017);
        leaveOpeningBalance.setNoOfDays(2017);
        leaveOpeningBalance.setTenantId("1");
    }

    public LeaveOpeningBalanceBuilder withLeaveType(LeaveType leaveType) {
        leaveOpeningBalance.setLeaveType(leaveType);
        return this;
    }

    public LeaveOpeningBalanceBuilder withCalendarYear(int calendarYear) {
        leaveOpeningBalance.setCalendarYear(calendarYear);
        return this;
    }

    public LeaveOpeningBalanceBuilder withTenantId(String tenantId) {
        leaveOpeningBalance.setTenantId(tenantId);
        return this;
    }

    public LeaveOpeningBalanceBuilder withId(String id) {
        leaveOpeningBalance.setId(id);
        return this;
    }

    public LeaveOpeningBalanceBuilder withEmployee(int employee) {
        leaveOpeningBalance.setEmployee(employee);
        return this;
    }

    public LeaveOpeningBalanceBuilder withNoOfDays(int noOfDays) {
        leaveOpeningBalance.setNoOfDays(noOfDays);
        return this;
    }

    public LeaveOpeningBalance build() {
        return leaveOpeningBalance;
    }
}
