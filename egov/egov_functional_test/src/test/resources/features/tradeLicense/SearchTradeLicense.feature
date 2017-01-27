Feature: Search Trade License

  As a register user of the system
  I want to be able to search for a trade license
  So that the license records are up to date.

  @Sanity
  Scenario: Registered user search trade license with application number

    Given creator logs in
    When he choose to search trade license
    And he search trade license with application number
    And he checks total number of records
    And current user logs out

  @Sanity
  Scenario: Registered user search trade license with license number

    Given creator logs in
    When he choose to search trade license
    And he search trade license with license number
    And he checks total number of records
    And current user logs out

  @Sanity
  Scenario: Registered user search trade license with status

    Given creator logs in
    When he choose to search trade license
    And he search trade license with status "Cancelled"
    And he checks total number of records
    And current user logs out

  @Sanity
  Scenario: Registered user search trade license with status

    Given creator logs in
    When he choose to search trade license
    And he search trade license with status "Rejected"
    And he checks total number of records
    And current user logs out