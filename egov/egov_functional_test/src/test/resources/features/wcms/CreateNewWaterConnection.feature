Feature: To create a new water connection

  As a registered user of the system
  I want to create a new water connection
  So that the connection records are up to date.

  @Sanity1
  Scenario Outline: applying for new water connection

#    Given juniorAssistant logs in
#
#    When he chooses to create new property
#    And he enters property header details as <propertyHeaderDetails>
#    And he enters owner details for the first owner as <ownerDetails>
#    And he enters property address details as <propertyAddressDetails>
#    And he enters assessment details as <assessmentDetails>
#
#    And he enters amenities as <amenitiesDetails>
#    And he enters construction type details as <constructionTypeDetails>
#    And he enters floor details as <floorDetails>
#    And he forwards for approval to billCollector
#    Then property details get saved successfully
#
#    And current user closes acknowledgement
#    And current user logs out
#
#    When billCollector logs in
#    And chooses to act upon the above application
#    And he forwards for approval to revenueInspector
#    And current user closes acknowledgement
#    And current user logs out
#
#    When revenueInspector logs in
#    And chooses to act upon the above application
#    And he forwards for approval to revenueOfficer
#    And current user closes acknowledgement
#    And current user logs out
#
#    When revenueOfficer logs in
#    And chooses to act upon the above application
#    And he forwards for approval to commissioner
#    And current user closes acknowledgement
#    And current user logs out
#
#    When commissioner logs in
#    And chooses to act upon the above application
#    And he approved the property with remarks "property approved"
#    And current user closes acknowledgement
#
#    And chooses to act upon the above assessment
#    And he does a digital signature
#
#    Then user will be notified by "Successfully"
#
#    When commissioner closes acknowledgement
#    And current user logs out
#
#    And juniorAssistant logs in
#    And chooses to act upon the above assessment
#    And he generates a notice
#    And current user logs out

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

#
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
      | propertyHeaderDetails | ownerDetails | propertyAddressDetails | assessmentDetails     | amenitiesDetails | constructionTypeDetails | floorDetails | connection details | inspection details |
      | residentialPrivate    | bimal        | addressOne             | assessmentNewProperty | all              | defaultConstructionType | firstFloor   | New_connection     | inspectionInfo     |










