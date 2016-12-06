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






