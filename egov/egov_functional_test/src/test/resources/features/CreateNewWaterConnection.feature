Feature: To create a new water connection

  As a registered user of the system
  I want to create a new as well as additional water connection
  So that the connection records are up to date.

  @WIP
  Scenario Outline: applying for new water connection

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And chooses to act upon the above application
    And user will enter the field inspection details as <inspection details>
    And user closes acknowledgement form
    And current user logs out

    When juniorAssistant logs in
    And chooses to act upon the above application
    And user will click on the generate estimation notice
    Then user will search for the recent application
    Then user will filter the application based upon the connection details as <connection details>
    And user chooses to act upon the above application in search applications
    And user will click on collect charges and collect the money form the customer & closes it
    And current user logs out

    Examples:
      | connection details | inspection details |
      | New_connection     | inspectionInfo     |

  @WIP
  Scenario Outline: applying for a additional new water connection

    Given juniorAssistant logs in
    And user chooses to apply for new additional water connection
    And user will enter the consumer number as <consumer number>
    And user will enter the details of the new additional water connection
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And chooses to act upon the above application
    And user will enter the field inspection details as <inspection details>
    And user closes acknowledgement form
    And current user logs out

    When juniorAssistant logs in
    And chooses to act upon the above application
    And user will click on the generate estimation notice
    Then user will search for the recent application
    And user will filter the application based upon the connection details as <connection details>
    And user chooses to act upon the above application in search applications
    And user will click on collect charges and collect the money form the customer & closes it
    And current user logs out

    Examples:
      |consumer number| inspection details | connection details    |
      |1016000063     | inspectionInfo     | Additional_connection |





