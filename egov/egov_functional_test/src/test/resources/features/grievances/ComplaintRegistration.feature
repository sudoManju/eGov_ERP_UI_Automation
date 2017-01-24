Feature: Register Complaint

  As a citizen register complaint directly in website


  @WIP

  Scenario Outline: Register Complaint anonymously

    Given user log on to the website
    When he choose to register a complaint
    And he choose to enter contact information as <contactDetails>
    And he choose to enter grievance details as <grievanceDetails>



    Examples:
    |contactDetails |grievanceDetails |
    |contactInfo    |grievanceDetails |


  @WIP

  Scenario Outline: Register a Complaint with Citizen Login

    Given citizen logs in
    When he choose to register complaint with his login
    And he choose to enter grievance details as <grievanceDetails>


    Examples:
    |grievanceDetails|
    |grievanceDetails|