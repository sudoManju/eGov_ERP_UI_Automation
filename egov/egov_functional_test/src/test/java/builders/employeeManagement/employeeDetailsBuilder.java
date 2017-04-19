package builders.employeeManagement;

import entities.employeeManagement.employeeDetails;

public final class employeeDetailsBuilder {

    employeeDetails details = new employeeDetails();

    public employeeDetailsBuilder() {
        details.setDateOfBirth("29/07/1994");
        details.setMobileNumber("9999988888");
        details.setEmailId("testing@gmail.com");

    }

    public employeeDetailsBuilder withMartialStatus(String status){
        details.setMartialStatus(status);
        return this;
    }

    public employeeDetailsBuilder withGender(String gender){
        details.setGender(gender);
        return this;
    }

    public employeeDetailsBuilder withEmployeeType(String employeeType){
        details.setEmployeeType(employeeType);
        return this;
    }

    public employeeDetailsBuilder withEmployeeGroup(String employeeGroup){
        details.setEmployeeGroup(employeeGroup);
        return this;
    }

    public employeeDetailsBuilder withStatus(String status){
        details.setStatus(status);
        return this;
    }

    public employeeDetailsBuilder withPermanantAddress(String permanantAddress){
        details.setPermanantAddress(permanantAddress);
        return this;
    }

    public employeeDetailsBuilder withPermanantCity(String permanantCity){
        details.setPermanantCity(permanantCity);
        return this;
    }

    public employeeDetailsBuilder withPermanantPincode(String permanantPincode){
        details.setPermananyPincode(permanantPincode);
        return this;
    }

    public employeeDetailsBuilder withDateOfAppointment(String dateOfAppointment){
        details.setDateOfAppointment(dateOfAppointment);
        return this;
    }

    public employeeDetails build() {
        return details;
    }
}
