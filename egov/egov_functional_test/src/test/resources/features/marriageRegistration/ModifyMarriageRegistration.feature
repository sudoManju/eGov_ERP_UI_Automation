Feature: Marriage Registration
  An valid system user can Modify Marriage Registration

  @Marriage @Sanity
  Scenario Outline: Modify Marriage Registration

    When commissioner logs in
    And user will select the required screen as "Data Entry Screen" with condition as "mrs"
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
#    And he choose to modify marriage registration
    And user will select the required screen as "Modify Marriage Registration"
    And he search the marrige application
    And he modify application and update it
    Then user will be notified by "successfully."
    And he closes the acknowledgements
    And current user logs out

    Examples:
      |generalInformation     | bridegroomInformation|brideInformation|
      |generalInfo            |    bridegroomInfo    | brideInfo|


