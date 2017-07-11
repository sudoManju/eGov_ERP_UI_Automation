package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.AuditDetails;
import entities.requests.propertyTax.services.create.Owners;
import entities.requests.propertyTax.services.create.Roles;

public class OwnersBuilder {

    Owners owners = new Owners();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    Roles[] roles = new Roles[1];

    Roles role1 = new RolesBuilder().build();

    public OwnersBuilder(){
        owners.setUserName("TestIt");
        owners.setPassword("12345");
        owners.setSalutation("Mr");
        owners.setName("Bimal");
        owners.setGender("male");
        owners.setMobileNumber("9999999999");
        owners.setEmailId("test@testing.com");
        owners.setActive(true);
        owners.setLocale("en_IN");
        owners.setType("CITIZEN");
        owners.setAccountLocked(false);
        owners.setTenantId("default");
        owners.setAuditDetails(auditDetails);
        roles[0] = role1;
        owners.setRoles(roles);
    }

    public OwnersBuilder withUserName(String userName){
        owners.setUserName(userName);
        return this;
    }

    public OwnersBuilder withMobileNumber(String number){
        owners.setMobileNumber(number);
        return this;
    }

    public OwnersBuilder withAadharNumber(String number){
        owners.setAadhaarNumber(number);
        return this;
    }

    public Owners build(){
        return owners;
    }
}
