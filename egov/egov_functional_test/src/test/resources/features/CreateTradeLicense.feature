Feature: Create New Property

  As a register user of the system
  I want to be able to Create New Trade License
  So that the TL records are up to date.

  # CREATE NEW LICENSE #

  @WIP
  Scenario Outline: Registered user creating a new license in the system
    Given creator logs in
    When he chooses to create new License
    And he enters trade owner details of new license <tradeDetailsData>
#    And he enters trade location details of new license
#    And he enters trade details of new license
#    And he enters Agreement Details of new license
#    Then he saved new trade license successfully

  Examples:
    |   tradeDetailsData         |
    |   ownerDetailsTradeLicense |



