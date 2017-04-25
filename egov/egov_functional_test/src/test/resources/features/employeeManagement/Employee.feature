Feature: Create/View/Update

  As a registered user of a system
  i should able to create/view/update employee


  Scenario Outline: Create an employee

    Given admin logs in
    And user will select the required screen as "Create" with condition as "/employee"
#    And user enters the employee details as <employeeDetails>
    And user will enter the assignment details as <assignmentDetails>

    Examples:
    |employeeDetails| assignmentDetails |
    |employee1      | assignment1       |