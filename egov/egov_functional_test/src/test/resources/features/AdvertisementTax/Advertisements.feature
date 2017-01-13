Feature: Create/search Advertisement
  As a registered user of the system
  I am able to create/search Advertisements

@WIP

Scenario: Advertisments Create/Search/Collect Tax

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

# Collect Advertisement Tax

  Given creator logs in
  And he choose to collect advertisement tax by advertisement wise
  And he search advertisement by advertisement number
  And he choose advertisement for collecting advertisement tax

#  Search Advertisements

#  And he chooses to search advertisement
#  And he search and select the required advertisement
#  And he view and close the acknowledgement