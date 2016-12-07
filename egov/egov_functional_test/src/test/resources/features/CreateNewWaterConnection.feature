Feature: To create a new water connection

  As a registered user of the system
  I want to be able to create a new water connection
  So that the property records are up to date.

  Scenario: applying for new connection

    Given juniorAssistant logs in
    And he chooses to apply for new water connection

    And he enter all the water connection details

    And current user closes acknowledgement
    And current user logs out