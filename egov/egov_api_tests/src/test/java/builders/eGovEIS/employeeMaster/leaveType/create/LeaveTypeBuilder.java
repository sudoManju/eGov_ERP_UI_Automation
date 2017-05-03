package builders.eGovEIS.employeeMaster.leaveType.create;

import entities.requests.eGovEIS.employeeMaster.leaveType.create.LeaveType1;

public final class LeaveType1Builder {

    LeaveType1 leaveType1 = new LeaveType1();

    private LeaveType1Builder() {
    }

    public LeaveType1Builder withEncashable(boolean encashable) {
        leaveType1.setEncashable(encashable);
        return this;
    }

    public LeaveType1Builder withCreatedDate(String createdDate) {
        leaveType1.setCreatedDate(createdDate);
        return this;
    }

    public LeaveType1Builder withAccumulative(boolean accumulative) {
        leaveType1.setAccumulative(accumulative);
        return this;
    }

    public LeaveType1Builder withCreatedBy(String createdBy) {
        leaveType1.setCreatedBy(createdBy);
        return this;
    }

    public LeaveType1Builder withLastModifiedDate(String lastModifiedDate) {
        leaveType1.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public LeaveType1Builder withLastModifiedBy(String lastModifiedBy) {
        leaveType1.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public LeaveType1Builder withName(String name) {
        leaveType1.setName(name);
        return this;
    }

    public LeaveType1Builder withTenantId(String tenantId) {
        leaveType1.setTenantId(tenantId);
        return this;
    }

    public LeaveType1Builder withDescription(String description) {
        leaveType1.setDescription(description);
        return this;
    }

    public LeaveType1Builder withPayEligible(boolean payEligible) {
        leaveType1.setPayEligible(payEligible);
        return this;
    }

    public LeaveType1Builder withActive(boolean active) {
        leaveType1.setActive(active);
        return this;
    }

    public LeaveType1Builder withHalfdayAllowed(boolean halfdayAllowed) {
        leaveType1.setHalfdayAllowed(halfdayAllowed);
        return this;
    }

    public LeaveType1 build() {
        return leaveType1;
    }
}
