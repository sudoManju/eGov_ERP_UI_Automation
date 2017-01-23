Feature: Create Trade License

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
    And he copy trade application number
    And current user logs out

  Examples:
    |   tradeDetailsData         |       tradeLocationData           |            tradeDetailsData1    |
    |   ownerDetailsTradeLicense |       locationDetailsTradeLicense |          tradeDetailsTradeLicense |


    # Create Trade License with work flow #

    @Sanity
    Scenario Outline: Register User create trade license with work flow
      Given creator logs in
      When he chooses to create new License
      And he enters trade owner details of new license <tradeDetailsData>
      And he enters trade location details of new license <tradeLocationData>
      And he enters trade details of new license <tradeDetailsData1>
      And he copy trade application number

      And he choose to search trade license
      And he search existing application number
      And he choose to collectfees
      And he choose to payTax of applicationNumber
      And he choose to act upon the above application number
      And he forwards for approver sanitaryInspector
      And he closes the acknowledgement
      And current user logs out

      When sanitaryInspector logs in
      And he choose to act upon the above application number
      And he forwards for approver commissioner
      And he closes the acknowledgement
      And current user logs out

      When commissioner logs in
      And he choose to act upon the above application number
      And he approves the closure
      And he closes the acknowledgement
      And current user logs out

      When creator logs in
      And he choose to act upon the above application number
      And he generates the license certificate




      Examples:
        |   tradeDetailsData         |       tradeLocationData           |            tradeDetailsData1    |
        |   ownerDetailsTradeLicense |       locationDetailsTradeLicense |          tradeDetailsTradeLicense |


       # CREATE LEGENCY TRADE LICENSE #

      @WIP
        Scenario Outline: Register user create legacy trade license
        Given creator logs in
        When he choose to create legency trade license
        And he enters old license number
        And he enters trade owner details of new license <tradeDetailsData>
        And he enters trade location details of new license <tradeLocationData>
        And he enters trade details of new license <tradeDetailsData1>
        And he enters fee details of legency trade license <legencyDetailsData>


        Examples:
          |   tradeDetailsData         |       tradeLocationData           |            tradeDetailsData1    |   legencyDetailsData|
          |   ownerDetailsTradeLicense |       locationDetailsTradeLicense |        tradeDetailsTradeLicense |     legencyTrade    |


   # Trade License Closure #


    @Sanity
    Scenario Outline: Registered user choose for trade license closure
    Given creator logs in
    When he choose to do trade license closure
    And he choose a trade license for closure as <closure>
    And he forwards for approver sanitaryInspector
    And he closes the acknowledgement page
    And current user logs out

    When sanitaryInspector logs in
    And he choose to act upon the above licence number
    And he forwards for approver commissioner
    And he closes the acknowledgement
    And current user logs out

    When commissioner logs in
    And he choose to act upon the above licence number
    And he approves the closure
    And he closes the acknowledgement
    And current user logs out


        Examples:
        |closure          |
        |licenceForClosure|


