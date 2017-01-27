Feature: Create new sewerage connection
  As a registered user of the system
  User should be able to create new sewerage connection

  Background:It will run the data entry screen of property tax

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

@WIP

  Scenario: create new sewerage connection

      Given creator logs in
      And he chooses to create new sewage connection
      And he create new sewerage connection for above assessment number
      And he forward to engineer and closes the acknowledgement

      And he chooses to collect sewerage tax for above application number
      And he search for above application number to collect
      And he collect the charges and closes the acknowledgement
      And current user logs out

      When assistantEngineer logs in
      And he chooses to act upon above sewerage connection

