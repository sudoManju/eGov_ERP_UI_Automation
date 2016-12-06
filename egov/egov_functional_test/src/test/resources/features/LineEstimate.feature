Feature: Create New Line Estimate
  As a registered user of the system
  I want to be able to create a spillover estimate
  So that the estimate for work created be recorded

  @Sanity
  Scenario Outline: Registered user can create spillover estimation in the system
    Given assistantEngineer logs in

    When he chooses to create new spillover estimate
    And he enters estimate header details as <estimateHeaderDetails>
#    to be continued...
    And he enters financial header details as <financialHeaderDetails>
    And he enters work header details as <workHeaderDetails>
    And he enters administration sanction header details as <adminSanctionHeaderDetails>
    Then spillover estimate created successfully

    Examples:
      | estimateHeaderDetails | financialHeaderDetails | workHeaderDetails | adminSanctionHeaderDetails |
      |       all             |      financial         |  estimateAmount   |      adminDetail           |

