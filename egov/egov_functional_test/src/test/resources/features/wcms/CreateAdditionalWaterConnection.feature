Feature: To create a additional water connection

  As a registered user of the system
  I want to create a additional water connection
  So that the connection records are up to date.

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
  And user closes the search application page
  And current user logs out

  And commissioner logs in
  And chooses to act upon the above application
  And user will approve the application with sanction number
  And chooses to act upon the above application
  And user will provide the digital signature
  And current user logs out

  And seniorAssistant1 logs in
  And chooses to act upon the above application
  And the user will generate the work order
  And current user logs out

  Then assistantEngineer logs in
  And chooses to act upon the above application
  And user will perform the execution of tap
  And current user logs out

  When juniorAssistant logs in
  Then user will search for the recent application
  And user will filter the application based upon the connection details as <connection details>
  And user chooses to act upon the above application in search applications
  And user will click on collect charges and collect the money form the customer & closes it
  And user closes the search application page
  And current user logs out

  Examples:
  |consumer number| inspection details | connection details    |
  |1016016712     | inspectionInfo     | Additional_connection |

