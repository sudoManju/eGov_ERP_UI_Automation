package builders.userDetails;

import entities.requests.userDetails.Roles;
import entities.requests.userDetails.User;

public final class UserBuilder {
    User user = new User();

    Roles[] roles = new Roles[2];

    Roles role1 = new RolesBuilder().withName("TL").withDescription("Technical Lead for HR & Other Modules")
                     .withCreatedBy("1").withCreatedDate("01/01/2017")
                     .withLastModifiedBy("1").withLastModifiedDate("01/01/2017").build();

    Roles role2 = new RolesBuilder().withName("Manager").withDescription("Manager for HR & Other Modules")
                      .withCreatedBy("1").withCreatedDate("01/01/2017")
                      .withLastModifiedBy("1").withLastModifiedDate("01/01/2017").build();

    public UserBuilder() {
        user.setUserName("");
        user.setPassword("eGov@123");
        user.setSalutation("Mr");
        user.setName("ABCD");
        user.setGender("male");
        user.setMobileNumber("9999999999");
        user.setEmailId("abcd@eGovernments.com");
        user.setAltContactNumber(null);
        user.setPan(null);
        user.setAadhaarNumber(null);
        user.setPermanentAddress(null);
        user.setPermanentCity(null);
        user.setPermanentPincode(null);
        user.setCorrespondenceAddress(null);
        user.setCorrespondenceCity(null);
        user.setCorrespondencePincode(null);
        user.setActive(true);
        user.setDob("01/01/1990");
        user.setPwdExpiryDate("01-01-2018 00:00:00");
        user.setLocale("en_IN");
        user.setType("EMPLOYEE");
        user.setAccountLocked(false);
        user.setFatherOrHusbandName(null);
        user.setBloodGroup(null);
        user.setIdentificationMark(null);
        user.setPhoto(null);
        user.setCreatedBy("1");
        user.setCreatedDate("01-01-2017 00:00:00");
        user.setLastModifiedBy("1");
        user.setLastModifiedDate("01-01-2017 00:00:00");
        user.setTenantId("1");
        roles[0] = role1;
        roles[1] = role2;
        user.setRoles(roles);
    }

    public UserBuilder withUsername(String username){
        user.setUserName(username);
        return this;
    }


    public User build() {
        return user;
    }
}
