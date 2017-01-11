Feature: Create/search Advertisement
  As a registered user of the system
  I am able to create/search Advertisements

@WIP

Scenario: Create/search advertisments

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