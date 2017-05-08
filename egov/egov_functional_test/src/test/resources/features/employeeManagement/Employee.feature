Feature: Create/View/Update

  As a registered user of a system
  i should able to create/view/update employee

  Scenario Outline: Create an employee

    Given admin logs in
    And user will select the required screen as "Create Employee" with condition as "/employee"
    And user enters the employee details as <employeeDetails>
    And user will enter the assignment details as <assignmentDetails>
    And user will enter the jurisdiction details as <jurisdictionDetail>
    Then user clicks on submit button
#    Then user close the employee search
    
#    And user will select the required screen as "Create LeaveBalance"


    Examples:
      | employeeDetails | assignmentDetails | jurisdictionDetail |
      | employee1       | assignment1       | JurisdictionList1  |
#      | employee2       | assignment2       | JurisdictionList2  |