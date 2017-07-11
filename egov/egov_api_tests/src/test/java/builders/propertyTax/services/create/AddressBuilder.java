package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.Address;
import entities.requests.propertyTax.services.create.AuditDetails;

public class AddressBuilder {

    Address address = new Address();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public AddressBuilder(){
        address.setTenantId("default");
        address.setLatitude(11);
        address.setLongitude(20);
        address.setAddressNumber("Test");
        address.setAddressLine1("Testing line 1");
        address.setAddressLine2("Testing line 2");
        address.setLandmark("Mark");
        address.setCity("City");
        address.setPincode("123456");
        address.setDetail("testing");
        address.setAuditDetails(auditDetails);
    }

    public Address build(){
        return address;
    }
}
