Feature: Registered user search the complaint with various parameters

  Scenario Outline: Employee search for complaints filed by citizen

    Given citizen logs in
    When he choose to register complaint with his login
    And he choose to enter grievance details as <grievanceDetails>
    And user will be notified by "successfully"
    And he copies CRN and closes the acknowledgement
    And citizen sign out

    Given LightingSuperintendent logs in
    And user will select the required screen as "Search Grievance"
    And he search complaint with all parameters
    And current user logs out

  Examples:
    | grievanceDetails |
    | grievanceDetails |