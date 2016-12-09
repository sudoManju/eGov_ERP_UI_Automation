Feature: To find the daily collection reports

  As a registered user of the system
  I want to be able to search the daily collection reports

  Scenario: To find the daily collection vlt reports

    Given juniorAssistant logs in
    And user chooses to find the daily collection vlt reports
    And user need to enter the date and get the report details
    And current user logs out
