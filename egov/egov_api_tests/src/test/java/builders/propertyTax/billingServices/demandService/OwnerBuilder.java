package builders.propertyTax.billingServices.demandService;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.billingServices.demandService.Owner;
import entities.requests.propertyTax.billingServices.demandService.Roles;
import entities.requests.propertyTax.billingServices.demandService.UserDetails;

import static data.ConstantData.tenantId;

public class OwnerBuilder {

    Owner owner = new Owner();

    Roles[] roles = new Roles[1];
    UserDetails userDetails = new UserDetailsBuilder().build();
    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public OwnerBuilder() {
        owner.setTenantId(tenantId);
        owner.setSalutation("MR");
        owner.setLocale("en_IN");
        owner.setType("CITIZEN");
        owner.setActive(true);
        owner.setAccountLocked(false);
        roles[0] = new RolesBuilder().build();
        owner.setRoles(roles);
        owner.setAuditDetails(auditDetails);
        owner.setUserDetails(userDetails);
        owner.setGender("MALE");
        owner.setMobileNumber("9999999999");
    }

    public OwnerBuilder withId(int id) {
        owner.setId(id);
        return this;
    }

    public OwnerBuilder withName(String name) {
        owner.setName(name);
        return this;
    }

    public OwnerBuilder withUserName(String userName) {
        owner.setUserName(userName);
        return this;
    }

    public OwnerBuilder withAuthToken(String authToken){
        owner.setAuthToken(authToken);
        return this;
    }

    public OwnerBuilder withMobileNumber(String num){
        owner.setMobileNumber(num);
        return this;
    }

    public Owner build(){
        return owner;
    }
}