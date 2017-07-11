package builders.propertyTax.services.create;

import entities.requests.propertyTax.services.create.LocationBoundary;

public class LocationBoundaryBuilder {

    LocationBoundary locationBoundary = new LocationBoundary();

    public LocationBoundaryBuilder(){
        locationBoundary.setId(19);
        locationBoundary.setName("Kotta Peta Super Structure");
    }

    public LocationBoundary build(){
        return locationBoundary;
    }
}
