Feature: Create/search Advertisement
  As a registered user of the system
  I am able to create/search Advertisements

@WIP

Scenario: Advertisements Create/Search/Collect Tax

#  Create Advertisements
   Given creator logs in
   And he chooses to create advertisement
   And he enters details for advertisement creation
   And he forwards and closes the acknowledgement
   Then user will be notified by "successfully"
   And current user logs out

  Given commissioner logs in
  And he clicks on advertisement and opens the application
  And he approves the advertisement application
  Then user will be notified by "approved"
  And current user logs out

  #  Search Advertisements
  Given creator logs in
  And he chooses to search advertisement
  And he search and select the required advertisement
  And he view and close the acknowledgement
  And current user logs out

# Collect Advertisement Tax
  Given creator logs in
  And he choose to collect advertisement tax by advertisement wise
  And he search advertisement by advertisement number
  And he choose advertisement for collecting advertisement tax
  And current user logs out

#  Collect Advertisement Tax by Agency wise
  Given creator logs in
  And he choose to collect advertisement tax by advertisement wise
  And he choose to collect advertisement tax by agency wise
  And he selects the agency for Tax/Fees collection
  And he choose to collect advertisement tax
  And current user logs out


  @WIP

Scenario: Create/Search agency

   Given admin logs in
   And he chooses to create advertisement agency
   And he enter details for agency creation



