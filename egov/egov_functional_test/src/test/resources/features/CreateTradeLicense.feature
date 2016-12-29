Feature: Create New Property

  As a register user of the system
  I want to be able to Create New Trade License
  So that the TL records are up to date.

  # CREATE NEW LICENSE #

  @Sanity
  Scenario Outline: Registered user creating a new license in the system
    Given creator logs in
    When he chooses to create new License
    And he enters trade owner details of new license <tradeDetailsData>
    And he enters trade location details of new license <tradeLocationData>
    And he enters trade details of new license <tradeDetailsData1>
#    And he enters Agreement Details of new license
    And current user logs out

  Examples:
    |   tradeDetailsData         |       tradeLocationData           |            tradeDetailsData1    |
    |   ownerDetailsTradeLicense |       locationDetailsTradeLicense |          tradeDetailsTradeLicense |


    # SEARCH TRADE LICENSE #

    @Sanity
    Scenario Outline: Register User search application using search trade
      Given creator logs in
      When he chooses to create new License
      And he enters trade owner details of new license <tradeDetailsData>
      And he enters trade location details of new license <tradeLocationData>
      And he enters trade details of new license <tradeDetailsData1>

      And he choose to search trade license
      And he search existing application number
      And he choose to collectfees
      And he choose to payTax of applicationNumber



      Examples:
        |   tradeDetailsData         |       tradeLocationData           |            tradeDetailsData1    |
        |   ownerDetailsTradeLicense |       locationDetailsTradeLicense |          tradeDetailsTradeLicense |





