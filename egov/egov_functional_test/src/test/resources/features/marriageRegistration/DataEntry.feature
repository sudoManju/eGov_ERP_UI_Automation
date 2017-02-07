Feature: Create an Data Entry for the Marriage registration
  An commissioner can make data entry
  the Marriage registration in the system

  @WIP
  Scenario Outline: Data Entry for the Marriage registration
    When commissioner logs in
    And he chooses data entry in marriage registration
    And he enters the applicants details as <generalInformation>
    And he enters the bridegroom information as <bridegroomInformation> <brideInformation>
    And he enters the serial and page number
    And he enters the Witnesses Information
    And he enters the checklist
    And he submit the data entry
    Then user will be notified by "registered"
    And he closes the acknowledgement
    And current user logs out


    Examples:
      |generalInformation     | bridegroomInformation|brideInformation|
      |generalInfo            |    bridegroomInfo    | brideInfo|