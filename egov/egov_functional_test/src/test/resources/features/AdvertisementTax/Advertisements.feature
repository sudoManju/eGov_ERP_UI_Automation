Feature: Create/search Advertisement
  As a registered user of the system
  I am able to create/search Advertisements


@Sanity

Scenario: Create/Search/Collect Tax Agency wise

    # Create Agency

    Given admin logs in
    And he chooses to create advertisement agency
    And he enter details for agency creation
    And he submit the details and closes acknowledgement
    Then user will be notified by "created"

    And he chooses to search advertisement agency
    And he enter details for search agency
    And he view and closes the acknowledgement
    And current user logs out

   # Create Advertisement

    And creator logs in
    And he chooses to create advertisement
    And he enters details for advertisement creation with agency
    And he forwards and closes the acknowledgement
    Then user will be notified by "successfully"
    And current user logs out

    And commissioner logs in
    And he clicks on advertisement and opens the application
    And he approves the advertisement application
    Then user will be notified by "approved"
    And current user logs out

  #  Collect Advertisement Tax by Agency wise

    And creator logs in
    And he choose to collect advertisement tax by advertisement wise
    And he choose to collect advertisement tax by agency wise
    And he selects the agency for Tax/Fees collection
    And he choose to collect advertisement tax
    And current user logs out




@Sanity

Scenario: Create/Search/Collect Tax AdvertisementWise

#  Create Advertisements

   Given creator logs in
   And he chooses to create advertisement
   And he enters details for advertisement creation
   And he forwards and closes the acknowledgement
   Then user will be notified by "successfully"
   And current user logs out

   And commissioner logs in
   And he clicks on advertisement and opens the application
   And he approves the advertisement application
   Then user will be notified by "approved"
   And current user logs out

#  Search Advertisements

  And creator logs in
  And he chooses to search advertisement
  And he search and select the required advertisement
  And he view and close the acknowledgement

# Collect Advertisement Tax

  And he choose to collect advertisement tax by advertisement wise
  And he search advertisement by advertisement number
  And he choose advertisement for collecting advertisement tax
  And current user logs out




  @WIP
  Scenario: Deactivate Advertisement

    Given admin logs in
    And he choose to deactivate the advertisement
    And he search for advertisement for deactivate
    And he deactivates the advertisement with remarks and date
    Then user will be notified by "Deactivated"
    And user closes the acknowledgement pages
    And current user logs out
