Feature: Create/view/modify Milestone template
  As a registered user of the system
  I want to create/view/modify Milestone template

  #Creating a milestone template

  @Sanity

  Scenario: Create milestone template

    Given assis_Engineer logs in
    And he chooses to create milestone template
    And he enters the milestone template creation details
    And he save the file and closes the acknowledgement
    And current user logs out




  #View a milestone template

  @Sanity

  Scenario: view milestone template

    Given assis_Engineer logs in
    And he chooses to view milestone template
    And he enters the details for search
    And he selects the required template
    And he views and closes the acknowledgement
    And current user logs out



  #Modify a milestone template

  @Sanity

  Scenario: modify milestone template

    Given assis_Engineer logs in
    And he chooses to modify milestone template
    And he enters the details for search
    And he select the required template for modification
    And he modifies the template and closes the acknowledgement
    And current user logs out

