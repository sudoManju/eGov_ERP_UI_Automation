
Feature: To collect water charges in different mode payments

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
  Scenario Outline: This Scenario Includes creation of New Connection and collecting the charges with different mode of payment

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    And user enter the water management approval details as <approvalOfficer1>
    Then user will get the application number and closes the form
    And current user logs out

    And assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails> and collects money
    And current user logs out

    And assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer2>
    And current user logs out

    And deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer3>
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
    And current user logs out

    And assistantEngineer logs in
    And user will choose the above application and click on to perform the execution of tap
    And current user logs out

    #################################################################################
           # Collecting the water charges with different modes of payment #
    #################################################################################

    Given juniorAssistant logs in
    And user search for the collect water charges
    And user will enter the consumer number
    And user will pay the water charges with mode as <paymentMode>
    And he closes the payment acknowledgement
    And current user logs out

    Examples:
      | connectionDetails | inspectionDetails | approvalOfficer1 | approvalOfficer2        |approvalOfficer3| paymentMode  |
      | New_connection    | inspectionInfo    | engineer         | deputyExecutiveEngineer |commissioner1   | cash         |
      | New_connection    | inspectionInfo    | engineer         | deputyExecutiveEngineer |commissioner1   | cheque       |
      | New_connection    | inspectionInfo    | engineer         | deputyExecutiveEngineer |commissioner1   | dd           |


  @Sanity
  Scenario Outline: This Scenario Includes creation of New Connection and collecting the charges through online link

    Given juniorAssistant logs in
    And user chooses to apply for new water connection
    And user will enter the details of the new water connection
    And user enter the water management approval details as <approvalOfficer1>
    Then user will get the application number and closes the form
    And current user logs out

    And assistantEngineer logs in
    And user will choose the above application and enter the field inspection details as <inspectionDetails> and closes the acknowledgement form
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on the generate estimation notice
    And user will search the recent application based on connection details as <connectionDetails> and collects money
    And current user logs out

    And assistantEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer2>
    And current user logs out

    And deputyExecutiveEngineer logs in
    And user will choose the above application and enter the approval details as <approvalOfficer3>
    And current user logs out

    And commissioner logs in
    And user will choose the above application to approve and provides the digital signature
    And current user logs out

    And juniorAssistant logs in
    And user will choose the above application and click on generate the work order
    And current user logs out

    And assistantEngineer logs in
    And user will choose the above application and click on to perform the execution of tap
    And current user logs out

    #################################################################################
                  # Collecting the water charges through online #
    #################################################################################

    Given user will visit the online payment link
    And user will enter the consumer number and click on pay in online website
    And user will select the bank to pay the charges
    And user will enter the card details
    And user will get the successful online payment acknowledgement form
    Then user will be notified by "received."
    And user will click on the generate receipt

    Examples:
      | connectionDetails | inspectionDetails | approvalOfficer1 | approvalOfficer2        |approvalOfficer3|
      | New_connection    | inspectionInfo    | engineer         | deputyExecutiveEngineer |commissioner1   |