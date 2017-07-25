package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.LocationDetails;

public class LocationDetailsBuilder {

  LocationDetails locationDetails = new LocationDetails();

  public LocationDetailsBuilder(){
      locationDetails.setLocality("4");
      locationDetails.setDoorNo("Door no");
  }

  public LocationDetails build(){
      return locationDetails;
  }
}


