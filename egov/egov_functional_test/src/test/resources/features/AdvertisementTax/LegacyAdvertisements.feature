Feature: Create/Update LegacyAdvertisements
  As a registered user of system
  I am able to create/update legacyAdvertisements


@Sanity

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


@Sanity

Scenario: Create/Renewal LegacyAdvertisements

  Given creator logs in
  And he chooses to create legacy advertisements
  And he enters details for legacy advertisement creation
  And he submit the application and closes the acknowledgement
  Then user will be notified by "successfully"

  And he chooses to renewal advertisement
  And he search for required file by application number for renewal
  And he request for renewal and forward to commissioner
  Then user will be notified by "forwarded"
  And current user logs out

  And commissioner logs in
  And he opens the required application
  And he approves the advertisement application
  Then user will be notified by "Successful"
  And current user logs out
