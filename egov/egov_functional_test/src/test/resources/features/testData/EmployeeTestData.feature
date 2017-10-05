Feature: Create Employees for Testing

  Scenario Outline: : Create Positions

    Given admin logs in
    And user will select the required screen as "Create Position" with condition as "/position"
    And user selects the position <department> position <designation> and the position name as <position>
    Then user clicks on create position button, and closes the page
    And current user logs out

    Examples:
      | department     | designation                    | position  |
      | ACCOUNTS       | Accounts Officer               | ACO Test  |
      | ACCOUNTS       | Assistant Examiner of Accounts | AEOA Test |
      | ACCOUNTS       | Examiner of Accounts           | EOA Test  |
      | ADMINISTRATION | Commissioner                   | COMM Test |

  Scenario Outline: Create Employees

    Given admin logs in
    And user will select the required screen as "Create Employee" with condition as "/employee"
    And user enters the employee details as <employeeDetails>
    And user will enter the assignment details as <assignmentDetails>
    And user will enter the jurisdiction details as <jurisdictionDetails>
    And user will enter the service section and other details
    Then user clicks on submit button

    Examples:
      | employeeDetails | assignmentDetails | jurisdictionDetails |
      | employee1       | assignment1       | JurisdictionList1   |
      | employee2       | assignment2       | JurisdictionList2   |
      | employee3       | assignment3       | JurisdictionList3   |
      | employee4       | assignment4       | JurisdictionList4   |