Feature: Create Spillover Estimate/Create LOA for Spillover
  As a registered user of the system
  I want to be able to create Spillover estimate

  @Works  @Sanity
  Scenario Outline: Create Spillover Line Estimate

    Given assis_Engineer logs in
    When he chooses to create new spillover estimate
    And he enters estimate header details as <estimateHeaderDetails>
    And he enters financial details as <financialDetails>
    And he enters work details as <workDetails>
    And he enters administration sanction details as <adminSanctionDetails>
    And he enters technical sanction details as <technicalSanctionDetails>
    And he saves the file and closes the acknowledgement
    Then user will be notified by "successfully."
    And current user logs out

    Examples:
      | estimateHeaderDetails |financialDetails     |workDetails|adminSanctionDetails|technicalSanctionDetails|
      |SpilloverEstimate_1    |SpillOverFinancial_1 |SpillWork_1|admin               |technical               |
      |SpilloverEstimate_2    |SpillOverFinancial_2 |SpillWork_2|admin               |technical               |
      |SpilloverEstimate_3    |SpillOverFinancial_3 |SpillWork_3|admin               |technical               |


   @WIP
    Scenario: Letter of Acceptance for Spillover work

     Given assis_Engineer logs in
     And he choose to create letter of acceptance
     And he select the required spillover estimate from search results
     And he enters the mandatory details for creating LOA
     Then he save the file and view the LOA pdf

     And he choose to view Letter of Acceptance
     And he search for LOA

     And he choose to modify letter of acceptance
     And he search for LOA for modify
     Then user will be notified by "successfully"
     And current user logs out