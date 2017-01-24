Feature: Marriage Registration
  An valid system user can create marriage registration

  Scenario Outline: Create Marriage Registration
    When juniorAssistant logs in
    And he chooses to create marriage registration
    And he enters the applicants details as <applicantsInformation>

    Examples:
    |applicantsInformation|
    |generalInfo          |