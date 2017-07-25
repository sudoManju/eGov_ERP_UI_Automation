Feature: Create/Search for Asset Service

  As a registered user of the system
  I should able to create/search a asset service

  Scenario Outline: Create a Asset Service

    Given pilotJA logs in
    And user will select the required screen as "Create Asset"
    And user will enter the details as <headerDetails>
    And user will enter the category details as <categoryDetails> and with asset summary status as <assetStatus>
    And user will be notified the success page with an asset application number

    And user will select the required screen as "Modify Asset"
    And user will search the asset application based on category details
    And user will update the details in asset modify screen based on <categoryDetails>
    Then user will be notified by "Updated"
    And current user logs out

    Examples:
      | headerDetails | assetStatus | categoryDetails  |
      | header1       | CREATED     | Land             |
      | header2       | CAPITALIZED | Shop             |
      | header3       | CAPITALIZED | Market           |
      | header4       | CAPITALIZED | Kalyana_Mandapam |
      | header5       | CAPITALIZED | Usufruct         |
      | header6       | CAPITALIZED | Shopping_Complex |
      | header7       | CREATED     | Lakes_and_Ponds  |
      | header8       | CREATED     | Roads            |