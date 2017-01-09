Feature: Create regular estimate/Create LOA for estimate/Create-Track milestone/Generate Contractor Bill
  As a registered of the system
  I want to able Create regular estimate, LOA, Milestone/Track Milestone/Generate Contractor Bill


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



  @Sanity

  Scenario: Create Letter of Acceptance

    When assis_Engineer logs in
    And he choose to create letter of acceptance
    And he select the required application
    And he enters the mandatory details
    Then he save the file and view the LOA pdf
    And user will notifies that "successfully"

    And he choose to view Letter of Acceptance
    And he search for LOA

    And he choose to modify letter of acceptance
    And he search for LOA for modify
    And user will notifies that "successfully"
    And current user logs out


  @Sanity

  Scenario Outline: Create/view of Milestone/Track Milestone/Generate Contractor bill

    Given assis_Engineer logs in
    And he chooses to create milestone
    And he search and select the required file
    And he stores the loa number and enters details
    And he save the file and close
    And user will notifies that "successfully"

    And he chooses to track milestone
    And he search application using loa number
    And he select the application
    And he enters the milestone details
    And he save the file and close
    And user will notifies that "successfully"

    And he chooses to create contractor bill
    And he search application using loa number
    And he select the required file
    And he enters contractor details for part bill <approverDetails1>
    And user will notifies that "successfully"
    And current user logs out

    Given deputyExecutiveEngineer logs in
    And he chooses to act upon on contractorBillNumber
    And he approves the bill
    And user will notifies that "approved"
    And current user logs out

    Given assis_Engineer logs in
    And he chooses to create contractor bill
    And he search application using loa number
    And he select the required file
    And he enters contractor details for full bill <approverDetails1>
    And user will notifies that "successfully"
    And current user logs out

    Given deputyExecutiveEngineer logs in
    And he chooses to act upon on contractorBillNumber
    And he approves the bill
    And user will notifies that "approved"
    And current user logs out


    Examples:
      |approverDetails1       |
      |deputyExecutiveEngineer|
