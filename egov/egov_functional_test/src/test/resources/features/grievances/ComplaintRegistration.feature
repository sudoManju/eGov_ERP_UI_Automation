Feature: Register Complaint

  As a citizen register complaint directly in website


  @WIP

  Scenario Outline: Register Complaint anonymously

    Given user log on to the website
    When he choose to register a complaint
    And he choose to enter contact information as <contactDetails>


    Examples:
    |contactDetails |
    |contactInfo    |