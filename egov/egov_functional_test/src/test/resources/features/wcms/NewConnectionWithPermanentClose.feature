Feature: To create a flow of water management connections and applying a permanent closure

  This feature includes the creation of following connections
            1) New Connection
            2) Additional Connection
            3) Change Of Use Connection
            4) Closing a Connection Permanently

  Background:It will run the data entry screen of property tax

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

  @Sanity
  Scenario Outline: This Scenario Includon of New Connection , Additional Connection , Change Of Use Connection ,
                    Closing a Connection Permanently

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    And user enter the water management approval details as <approvalOfficer1>
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails> and collects money
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer2>
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer3>
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
    And current user logs out

    Then assistantEngineer logs in
    And user will choose the above application and click on to perform the execution of tap
    And current user logs out

    When juniorAssistant logs in
    And user will search the recent application based on connection details as <connectionDetails> and collects money
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
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails1> and collects money
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer4>
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer5>
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
    And current user logs out

    Then assistantEngineer logs in
    And user will choose the above application and click on to perform the execution of tap
    And current user logs out

    When juniorAssistant logs in
    And user will search the recent application based on connection details as <connectionDetails1> and collects money
    And current user logs out

    ###########################################################
            # Change of use for existing connection #
    ###########################################################

    Given juniorAssistant logs in
    And user search to apply for change of use for existing connection
    And user will enter the consumer number
    And user will enter the details of the change of use water connection
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails2> and collects money
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer4>
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer5>
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
    And current user logs out

    Then assistantEngineer logs in
    And user will choose the above application and click on to perform the execution of tap
    And current user logs out

    When juniorAssistant logs in
    And user will search the recent application based on connection details as <connectionDetails2> and collects money
    And current user logs out

    ###########################################################
            # Closing a Connection Permanently #
    ###########################################################

    Given juniorAssistant logs in
    And user search to apply for closure of connection
    And user will enter the consumer number
    And user will enter the closure connection details as <closureType1>
    And user closes acknowledgement form
    And current user logs out

    Given assistantEngineer logs in
    And chooses to act upon the above closure application as "deputyExecutiveEngineer"
    And user closes acknowledgement form
    And current user logs out

    And deputyExecutiveEngineer logs in
    And chooses to act upon the above closure application as "commissioner1"
    And user closes acknowledgement form
    And current user logs out

    And commissioner logs in
    And user will choose the above closure application to approve
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above closure application and click on generate acknowledgement
    And current user logs out


    Examples:
      | connectionDetails |connectionDetails1   |connectionDetails2| inspectionDetails | approvalOfficer1 | approvalOfficer2        | approvalOfficer3 |approvalOfficer4        | approvalOfficer5 | closureType1|
      | New_connection    |Additional_connection|Change_of_use     | inspectionInfo    | engineer         | deputyExecutiveEngineer | commissioner1    |deputyExecutiveEngineer | commissioner1    | Permanent   |







