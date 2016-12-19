Feature: To close a water connection either Temporary or Permanent

  As a registered user of the system
  I want to close a new water connection
  So that the connection records are up to date.

  @WIP
  Scenario Outline: To Apply for Closure of Connection Temporarily

#    Given juniorAssistant logs in
#    And user search to apply for closure of connection
#    And user will enter the consumer number as <consumer number>
#    And user will enter the closure connection detials as <closure type>
#    And user closes acknowledgement form
#    And current user logs out

    Given assistantEngineer logs in
    And chooses to act upon the application number as <consumer number>
    And user closes acknowledgement form
    And current user logs out


    Examples:
    |closure type | consumer number |
    |Temporary    | 1016000009      |
