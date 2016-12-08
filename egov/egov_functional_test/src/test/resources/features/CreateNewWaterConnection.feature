Feature: To create a new water connection

  As a registered user of the system
  I want to be able to create a new water connection
  So that the property records are up to date.

  Scenario: applying for new water connection

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    And current user closes acknowledgement
    And current user logs out

  Scenario Outline: applying for a additional new water connection

    Given juniorAssistant logs in
    And user chooses to apply for new additional water connection
    And user will enter the consumer number as <consumer number>
    And user will enter the details of the new additional water connection
    Then user will get the application number and closes the form
    And current user logs out

    Examples:
      |consumer number|
      |1016000019     |


  Scenario Outline: assistant
    When assistantEngineer logs in
    And chooses to act upon the above application
    And user will enter the field inspection details as <inspection details>
    Examples:
    |inspection details|
    |inspectionInfo    |




