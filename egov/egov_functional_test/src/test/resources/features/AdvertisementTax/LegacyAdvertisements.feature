Feature: Create/Update LegacyAdvertisements
  As a registered user of system
  I am able to create/update legacyAdvertisements


@WIP

Scenario: Create/Update LegacyAdvertisements

  Given creator logs in
  And he chooses to create legacy advertisements
  And he enters details for legacy advertisement creation
  And he submit the application and closes the acknowledgement
  Then user will be notified by "successfully"

  And he chooses to update legacy advertisements
  And he search for required file by application number
  And he update the legacy advertisement and close the acknowledgement
  Then user will be notified by "updated"
  And current user logs out

