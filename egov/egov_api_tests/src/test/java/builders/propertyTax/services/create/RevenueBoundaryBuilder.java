package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.RevenueBoundary;

public class RevenueBoundaryBuilder {

    RevenueBoundary revenueBoundary = new RevenueBoundary();

    public RevenueBoundaryBuilder(){
        revenueBoundary.setId(13);
        revenueBoundary.setName("Block No 1");
    }

    public RevenueBoundary build(){
        return revenueBoundary;
    }
}
