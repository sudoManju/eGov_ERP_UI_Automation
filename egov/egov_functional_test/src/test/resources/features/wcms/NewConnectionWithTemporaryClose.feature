Feature: To create a flow of water management connections and applying a temporary closure

  This feature includes the creation of following connections
            1) New Connection
            2) Additional Connection
            3) Change Of Use Connection
            4) Closing a Connection Temporarily
            5) Applying for the Re-Connection

  Background:It will run the data entry screen of property tax as well as logo uploading

    #######################################################
            # Egovernments logo uploading #
    #######################################################
    Given admin logs in
    And user will search for city setup
    And user will select the municipality info to upload the image
    And current user logs out

    #########################################################################
          # It will run the data entry screen of property tax #
    #########################################################################

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

  @Sanity
  Scenario Outline: This Scenario Includes New Connection , Additional Connection , Change Of Use Connection , Closing a Connection Temporarily and Applying for the Re-Connection

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    And user enter the water management approval details as <approvalOfficer1>
    And user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
    Then user will be notified by "forwarded"
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails> and collects money
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer2>
    Then user will be notified by "forwarded"
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer3>
    Then user will be notified by "forwarded"
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
    Then user will be notified by "forwarded"
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails1> and collects money
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer4>
    Then user will be notified by "forwarded"
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer5>
    Then user will be notified by "forwarded"
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

    ##########################################################
            # Change of use for existing connection #
    ##########################################################

    Given juniorAssistant logs in
    And user search to apply for change of use for existing connection
    And user will enter the consumer number
    And user will enter the details of the change of use water connection
    Then user will get the application number and closes the form
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
    Then user will be notified by "forwarded"
    And current user logs out

    When juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails2> and collects money
    And current user logs out

    When assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer4>
    Then user will be notified by "forwarded"
    And current user logs out

    When deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer5>
    Then user will be notified by "forwarded"
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

    ##########################################################
              # Closing a Connection temporarily #
    ##########################################################

    Given juniorAssistant logs in
    And user search to apply for closure of connection
    And user will enter the consumer number
    And user will enter the closure connection details as <closureType>
    And user closes acknowledgement form
    And current user logs out

    And assistantEngineer logs in
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

    ###########################################################
                # Requesting for a Re-Connection #
    ###########################################################

    Given juniorAssistant logs in
    And user search to apply for re-connection
    And user will enter the consumer number
    And user will enter the re-connection details
    And user closes acknowledgement form
    And current user logs out

    And assistantEngineer logs in
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
    And user will choose the above re-connection application and click on generate acknowledgement
    And current user logs out

    Examples:
      | connectionDetails |connectionDetails1   |connectionDetails2| inspectionDetails | approvalOfficer1 | approvalOfficer2        | approvalOfficer3 |approvalOfficer4        | approvalOfficer5 | closureType |
      | New_connection    |Additional_connection|Change_of_use     | inspectionInfo    | engineer         | deputyExecutiveEngineer | commissioner1    |deputyExecutiveEngineer | commissioner1    | Temporary   |







