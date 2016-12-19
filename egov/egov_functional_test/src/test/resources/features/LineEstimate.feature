Feature: Create Spillover Line Estimate
  As a registered user of the system
  I want to be able to create a spillover estimate


 @WIP
Scenario Outline: Create Spillover Line Estimate

    Given assis_Engineer logs in
    When he chooses to create new spillover estimate
    And he enters estimate header details as <estimateHeaderDetails>
#    And he enters financial header details as <financialHeaderDetails>
#    And he enters work header details as <workHeaderDetails>
#    And he enters administration sanction header details as <adminSanctionHeaderDetails>
#    Then spillover estimate created successfully
    Examples:
      | estimateHeaderDetails |
      | header                |