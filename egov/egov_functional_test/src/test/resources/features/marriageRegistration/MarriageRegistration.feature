Feature: Marriage Registration
  An valid system user can create marriage registration

  @WIP
  Scenario Outline: Create Marriage Registration
    When juniorAssistant logs in
    And he chooses to create marriage registration
    And he enters the applicants details as <generalInformation>
    And he enters the bridegroom information as <bridegroomInformation> <brideInformation>
    And he enters the Witnesses Information
    And he enters the checklist
    And he forward to commissioner and closes the acknowledgement
    And current user logs out

    When creator logs in
    And he choose to collect marriage registration fee
    And he search for above application number to collect marriage Registration fee
    And he collect the charges and closes the acknowledgement
    And current user logs out


    When commissioner logs in
    And he choose to act upon the above new marriage application number
    And he approve the new marriage application  and close the acknowledgement
    Then user will be notified by "successfully"
    And current user logs out


    Examples:
    |generalInformation     | bridegroomInformation|brideInformation|
    |generalInfo            |    bridegroomInfo    | brideInfo|