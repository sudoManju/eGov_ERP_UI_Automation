Feature: Marriage Registration
  An valid system user can create marriage registration

  @WIP
  Scenario Outline: Create Marriage Registration
    When juniorAssistant logs in
    And he chooses to create marriage registration
    And he enters the applicants details as <applicantsInformation> <applicantsInformation1>

    Examples:
    |applicantsInformation     | applicantsInformation1|
    |generalInfo               | bridegroomInfo        |