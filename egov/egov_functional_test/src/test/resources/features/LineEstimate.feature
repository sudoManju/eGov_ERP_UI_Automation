Feature: Create Spillover Line Estimate
  As a registered user of the system
  I want to be able to create a spillover estimate


 @Sanity
Scenario Outline: Create Spillover Line Estimate

    Given assis_Engineer logs in
    When he chooses to create new spillover estimate
    And he enters estimate header details as <estimateHeaderDetails>
    And he enters financial details as <financialDetails>
    And he enters work details as <workDetails>
    And he enters administration sanction details as <adminSanctionDetails>
    And he enters technical sanction details as <technicalSanctionDetails>
    And he saves the file and closes the acknowledgement
    And current user logs out
    Examples:
      | estimateHeaderDetails |financialDetails|workDetails|adminSanctionDetails|technicalSanctionDetails|
      | header                |financial       |work       |admin               |technical               |