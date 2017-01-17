Feature: To create a new water connection

  As a registered user of the system
  I want to create a new water connection
  So that the connection records are up to date.

  Background:It will run the data entry screen of property tax

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

  @WIP
  Scenario Outline: applying for new water connection

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    And user enter the water management approval details as <approvalOfficer1>
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
#    And chooses to act upon the above application
#    And user will enter the field inspection details as <inspectionDetails>
#    And user will closes the acknowledgement form
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
#    And chooses to act upon the above application
#    And user will click on the generate estimation notice
#    Then user will search for the recent application
    And user will search the recent application based on connection details as <connectionDetails> and collects money
#    Then user will filter the application based upon the connection details as <connectionDetails>
#    And user chooses to act upon the above application in search applications
#    And user will click on collect charges and collect the money form the customer & closes it
#    And user closes the search application page
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer2>
#    And chooses to act upon the above application
#    And user enter the water management approval details as <approvalOfficer2>
#    And user will closes the acknowledgement form
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer3>
#    And chooses to act upon the above application
#    And user enter the water management approval details as <approvalOfficer3>
#    And user will closes the acknowledgement form
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
#    And chooses to act upon the above application
#    And user will approve the application with sanction number
#    And chooses to act upon the above application
#    And user will provide the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
#    And chooses to act upon the above application
#    And the user will generate the work order
    And current user logs out

    Then assistantEngineer logs in
    And user will choose the above application and click on to perform the execution of tap
#    And chooses to act upon the above application
#    And user will perform the execution of tap
    And current user logs out

    When juniorAssistant logs in
#    Then user will search for the recent application
    And user will search the recent application based on connection details as <connectionDetails> and collects money
#    And user will filter the application based upon the connection details as <connectionDetails>
#    And user chooses to act upon the above application in search applications
#    And user will click on collect charges and collect the money form the customer & closes it
#    And user closes the search application page
    And current user logs out


    ###########################################################
            # Creating a Additional Water Connection #
    ###########################################################

    Given juniorAssistant logs in
    And user chooses to apply for new additional water connection
    And user will enter the consumer number
    And user will enter the details of the new additional water connection
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
#    And chooses to act upon the above application
#    And user will enter the field inspection details as <inspectionDetails>
#    And user closes acknowledgement form
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
#    And chooses to act upon the above application
#    And user will click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails1> and collects money
#    Then user will search for the recent application
#    And user will filter the application based upon the connection details as <connectionDetails>
#    And user chooses to act upon the above application in search applications
#    And user will click on collect charges and collect the money form the customer & closes it
#    And user closes the search application page
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer4>
#    And chooses to act upon the above application
#    And user enter the water management approval details as <approvalOfficer2>
#    And user will closes the acknowledgement form
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer5>
#    And chooses to act upon the above application
#    And user enter the water management approval details as <approvalOfficer3>
#    And user will closes the acknowledgement form
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
#    And chooses to act upon the above application
#    And user will approve the application with sanction number
#    And chooses to act upon the above application
#    And user will provide the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
#    And chooses to act upon the above application
#    And the user will generate the work order
    And current user logs out

    Then assistantEngineer logs in
#    And chooses to act upon the above application
#    And user will perform the execution of tap
    And current user logs out

    When juniorAssistant logs in
    And user will search the recent application based on connection details as <connectionDetails1> and collects money
#    Then user will search for the recent application
#    And user will filter the application based upon the connection details as <connection details>
#    And user chooses to act upon the above application in search applications
#    And user will click on collect charges and collect the money form the customer & closes it
#    And user closes the search application page
    And current user logs out

    Examples:
      | connectionDetails |connectionDetails1   | inspectionDetails | approvalOfficer1 | approvalOfficer2        | approvalOfficer3 |approvalOfficer4        | approvalOfficer5 |
      | New_connection    |Additional_connection| inspectionInfo    | engineer         | deputyExecutiveEngineer | commissioner1    |deputyExecutiveEngineer | commissioner1    |







