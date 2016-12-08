Feature: Collection

  As a registered user of the system
  I want to be able to collect taxes
  So that the property records are up to date.


  Scenario: System should be able to collect taxes

    Given juniorAssistant logs in
    When he chooses to collect taxes

    And he chooses to collect tax for "1016053938"
    And he chooses to pay tax
    And he pays using cheque with details as defaultChequeDetails


    Scenario: System should be able to create Miscellaneous receipt

      Given juniorAssistant logs in
      When he chooses to create Miscellaneous receipt

      And he enters Miscellaneous header



  Scenario: System should be able to Create Challan

    Given juniorAssistant logs in
    When he chooses to create Challan
    And he enters challan details

    And he stores the challan number and closes acknowledgement
    And current user logs out

 Scenario: check for senior assistant

    When seniorAssistant logs in
    And chooses to act upon the above challan
    And he validate the challan
    And he closes the acknowledgement

    And he search for challan receipt
    And he search for challan number
    And he pay using cash
    And he closes the acknowledgement
    And current user logs out


