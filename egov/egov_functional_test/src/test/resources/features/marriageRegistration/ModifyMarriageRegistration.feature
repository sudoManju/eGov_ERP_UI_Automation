Feature: Marriage Registration
  An valid system user can Modify Marriage Registration

  Background: It will run the Data Entry Screen Of Marriage Registration



  Scenario Outline: Modify Marriage Registration

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

    When commissioner logs in
    And he choose to modify marriage registration
    And he search the marrige application
    And he modify application and update it
    Then user will be notified by "successfully."
    And he closes the acknowledgements
    And current user logs out

    Examples:
      |generalInformation     | bridegroomInformation|brideInformation|
      |generalInfo            |    bridegroomInfo    | brideInfo|


