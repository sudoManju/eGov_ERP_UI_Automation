Feature: Create a Line Estimates
  As a registered user of the system
  I want to be able to create a Regular/Spillover estimates

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



  @Sanity
  Scenario Outline: Create Regular Estimate

   Given assis_Engineer logs in
   When he chooses to create new estimate
   And he enters estimate header details as <estimateHeaderDetails>
   And he enters financial details as <financialDetails>
   And he enters work details as for <workDetails>
   And he enters approver details as <approverDetails1>
   And he forwards to DEE and closes the acknowledgement
   And user will notifies that "successfully"
   And current user logs out

   Given deputyExecutiveEngineer logs in
   And he clicks on estimate and opens the application
   And he enters approver details as <approverDetails2>
   And he submit the application to superIntendent
   And user will notifies that "forwarded"
   And current user logs out

   Given superIntendent logs in
   And he clicks on estimate and opens the application
   And he enters approver details as <approverDetails3>
   And he submit the application to commissioner
   And user will notifies that "forwarded"
   And current user logs out

   Given commissioner logs in
   And he clicks on estimate and opens the application
   And he enters the AdminSanctionNumber
   And he enters approver details as <approverDetails4>
   And he submit the application to assis_Engineer
   And user will notifies that "forwarded"
   And current user logs out

   Given assis_Engineer logs in
   And he clicks on estimate and opens the application
   And he enters the details for approve
   And he approves the application
   And user will notifies that "done"
   And current user logs out

   Examples:
   |estimateHeaderDetails|financialDetails    |workDetails     |approverDetails1         |approverDetails2|approverDetails3|approverDetails4|
   |Estimate_1           |EstimateFinancial_1 |EstimateWork_1  |deputyExecutiveEngineer  |SuperIntendent  |commissioner    |assis_Engineer  |

