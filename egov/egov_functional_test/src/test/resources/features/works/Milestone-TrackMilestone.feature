Feature: Create/view of Milestone/Track Milestone
  As a registered user of the system
  I want to able to Create/view of Milestone/Track Milestone


 #create/track milestone

  @Sanity
  Scenario: Create/view of Milestone/Track Milestone

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
    And current user logs out


