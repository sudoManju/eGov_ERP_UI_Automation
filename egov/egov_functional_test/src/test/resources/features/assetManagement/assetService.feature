Feature: Create/Search for Asset Service

  As a registered user of the system
  I should able to create/search a asset service

  Scenario Outline: Create a Asset Service

    Given assistant logs in
    And user will select the required screen as "Create Asset"
    And user will enter the details <headerDetails> and <locationDetails> with summary status <assetStatus>

    Examples:
      | headerDetails | locationDetails | assetStatus |
      | headerDetails | locationDetails | SOLD        |

