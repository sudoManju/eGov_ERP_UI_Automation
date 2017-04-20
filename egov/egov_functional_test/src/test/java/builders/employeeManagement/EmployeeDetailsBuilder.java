package builders.employeeManagement;

import entities.employeeManagement.EmployeeDetails;

public final class EmployeeDetailsBuilder {

    EmployeeDetails details = new EmployeeDetails();

    public EmployeeDetailsBuilder() {
        details.setDateOfBirth("29/07/1994");
        details.setMobileNumber("9999988888");
        details.setEmailId("testing@gmail.com");

    }

    public EmployeeDetailsBuilder withMartialStatus(String status){
        details.setMartialStatus(status);
        return this;
    }

    public EmployeeDetailsBuilder withGender(String gender){
        details.setGender(gender);
        return this;
    }

    public EmployeeDetailsBuilder withEmployeeType(String employeeType){
        details.setEmployeeType(employeeType);
        return this;
    }

    public EmployeeDetailsBuilder withEmployeeGroup(String employeeGroup){
        details.setEmployeeGroup(employeeGroup);
        return this;
    }

    public EmployeeDetailsBuilder withStatus(String status){
        details.setStatus(status);
        return this;
    }

    public EmployeeDetailsBuilder withPermanantAddress(String permanantAddress){
        details.setPermanantAddress(permanantAddress);
        return this;
    }

    public EmployeeDetailsBuilder withPermanantCity(String permanantCity){
        details.setPermanantCity(permanantCity);
        return this;
    }

    public EmployeeDetailsBuilder withPermanantPincode(String permanantPincode){
        details.setPermananyPincode(permanantPincode);
        return this;
    }

    public EmployeeDetailsBuilder withDateOfAppointment(String dateOfAppointment){
        details.setDateOfAppointment(dateOfAppointment);
        return this;
    }

    public EmployeeDetails build() {
        return details;
    }
}
