Feature: Create/View/Update

  As a registered user of a system
  i should able to create/view/update employee


  Scenario Outline: Create an employee

    Given assistant logs in
    And user will select the required screen as "Create" with condition as "/employee"
    And user enters the employee details as <employeeDetails>


    Examples:
    |employeeDetails|
    |employee1      |