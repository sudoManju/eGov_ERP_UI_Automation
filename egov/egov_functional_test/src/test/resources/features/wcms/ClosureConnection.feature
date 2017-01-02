Feature: To close a water connection either Temporary or Permanent

  As a registered user of the system
  I want to close a new water connection
  So that the connection records are up to date.

  @WIP
  Scenario Outline: To Apply for Closure of Connection Temporarily

    Given juniorAssistant logs in
    And user search to apply for closure of connection
    And user will enter the consumer number as <consumer number>
    And user will enter the closure connection details as <closure type>
    And user closes acknowledgement form
    And current user logs out

    Given assistantEngineer logs in
    And chooses to act upon the above closure application
    And user closes acknowledgement form
    And current user logs out

    And commissioner logs in
    And chooses to act upon the above application
    And user will approve the closure application
    And chooses to act upon the above application
    And user will provide the digital signature
    And current user logs out

    And seniorAssistant1 logs in
    And chooses to act upon the above application
    And user will click on the generate acknowledge ment
    And current user logs out


    Examples:
    | closure type | consumer number |
    | Temporary    | 1016000074      |


  @WIP
  Scenario Outline: To Apply for Closure of Connection Permanent

    Given juniorAssistant logs in
    And user search to apply for closure of connection
    And user will enter the consumer number as <consumer number>
    And user will enter the closure connection details as <closure type>
    And user closes acknowledgement form
    And current user logs out

    Given assistantEngineer logs in
    And chooses to act upon the above closure application
    And user closes acknowledgement form
    And current user logs out

    And commissioner logs in
    And chooses to act upon the above application
    And user will approve the closure application
    And chooses to act upon the above application
    And user will provide the digital signature
    And current user logs out

    And seniorAssistant1 logs in
    And chooses to act upon the above application
    And user will click on the generate acknowledge ment
    And current user logs out


    Examples:
      | closure type | consumer number |
      | Permanent    | 1016000046      |
