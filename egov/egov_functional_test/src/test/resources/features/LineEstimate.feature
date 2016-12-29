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
    And user will notifies that "successfully."
    And current user logs out
    Examples:
      | estimateHeaderDetails |financialDetails     |workDetails|adminSanctionDetails|technicalSanctionDetails|
      |SpilloverEstimate_1    |SpillOverFinancial_1 |SpillWork_1|admin               |technical               |
      |SpilloverEstimate_2    |SpillOverFinancial_2 |SpillWork_2|admin               |technical               |
      |SpilloverEstimate_3    |SpillOverFinancial_3 |SpillWork_3|admin               |technical               |



 @WIP
Scenario Outline: Create Estimate

   Given assis_Engineer logs in
   When he chooses to create new estimate
   And he enters estimate header details as <estimateHeaderDetails>
   And he enters financial details as <financialDetails>
   And he enters work details as for <workDetails>
   And he enters approver details as <approverDetails>
   And he forwards to DEE and closes the acknowledgement
   And user will notifies that "successfully"
   And current user logs out

   

   Examples:
   |estimateHeaderDetails|financialDetails    |workDetails     |approverDetails          |
   |Estimate_1           |EstimateFinancial_1 |EstimateWork_1  |deputyExecutiveEngineer  |

