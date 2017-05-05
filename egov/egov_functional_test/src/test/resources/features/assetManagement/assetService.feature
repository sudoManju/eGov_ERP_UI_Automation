Feature: Create/Search for Asset Service

  As a registered user of the system
  I should able to create/search a asset service

  Scenario Outline: Create a Asset Service

    Given admin logs in
    And user will select the required screen as "Create Asset"
    And user will enter the details as <headerDetails> and <locationDetails>
    And user will enter the category details as <categoryDetails> and with asset summary status as <assetStatus>
    Examples:
      | headerDetails | locationDetails | assetStatus | categoryDetails |
      | header1       | location1       | CREATED     | land            |