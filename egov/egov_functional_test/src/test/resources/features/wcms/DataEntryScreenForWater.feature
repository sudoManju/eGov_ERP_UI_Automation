Feature: To create a data entry screen for the water charge management services as well as logo uploading

  As a registered user of the system
  I want to create a data entry screen
  So that the connection records are up to date.

  Background:It will run the data entry screen of property tax

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

  @Sanity
  Scenario: To create a data entry screen for the water charges

    Given admin logs in
    And user search to data entry screen for water
    And user will enter the details of data entry screen for water charges
    And user will notify the successfull creation of data entry screen as "Successfully"
    And user will be notified by "Successfully"
    And current user logs out

