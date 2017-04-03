package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.Hod;

public final class HodBuilder {
    Hod hod = new Hod();

    public HodBuilder withDepartment(String department) {
        hod.setDepartment(department);
        return this;
    }

    public Hod build() {
        return hod;
    }
}
