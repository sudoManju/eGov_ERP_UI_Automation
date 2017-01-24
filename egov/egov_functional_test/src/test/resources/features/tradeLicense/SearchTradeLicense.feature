Feature: Search Trade License

  As a register user of the system
  I want to be able to search for a trade license
  So that the license records are up to date.

  Scenario: Registered user search trade license with application number

    Given creator logs in
    When he choose to search trade license
    And he search trade license with application number