package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.Roles;
import entities.requests.eGovEIS.emp.User;

public class UserBuilder {

    User user = new User();

    Roles[] roles = new Roles[1];

    Roles roles1 = new RolesBuilder().build();

    public UserBuilder(){
       user.setName("Tester");
       user.setGender("MALE");
       user.setMobileNumber("9999999999");
       user.setEmailId("Test@Testing.com");
       user.setAltContactNumber("9090909090");
       user.setPan("ABCDE1234F");
       user.setAadhaarNumber("123456789012");
       user.setPermanentAddress("asdfgh");
       user.setPermanentCity("asdfgh");
       user.setPermanentPinCode("asdfgh");
       user.setActive(true);
       user.setDob("01/02/1990");
       user.setPwdExpiryDate("01-12-2045 00:00:00");
       user.setLocale("");
       user.setType("EMPLOYEE");
       user.setBloodGroup("A_POSITIVE");
       user.setIdentificationMark("asdfgh");
       roles[0] = roles1;
       user.setRoles(roles);
    }

    public UserBuilder withUserName(String userName){
        user.setUserName(userName);
        return this;
    }

    public User build(){
        return user;
    }
}
