package builders.propertyTax.billingServices.demandService;

import entities.requests.propertyTax.billingServices.demandService.UserDetails;

public class UserDetailsBuilder {

    UserDetails userDetails = new UserDetails();

    public UserDetailsBuilder(){
        userDetails.setFirstName("Tester1");
        userDetails.setMiddleName("Tester2");
        userDetails.setLastName("Tester3");
        userDetails.setDob("01/01/2001");
        userDetails.setFatherName("Tester4");
        userDetails.setHusbandName("Tester5");
        userDetails.setBloodGroup("O POSITIVE");
        userDetails.setPan("ABCDE1234F");
    }

    public UserDetails build(){
        return userDetails;
    }
}
