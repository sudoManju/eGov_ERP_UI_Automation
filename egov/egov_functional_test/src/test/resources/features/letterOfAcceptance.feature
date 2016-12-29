Feature: Creating Work Order for the Line estimate
  As a registered user of the system
  I want to create/search/modify Letter of Acceptance

  @Sanity
  Scenario: Create Letter of Acceptance

#  Creating Letter of Acceptance

    When assis_Engineer logs in
    And he choose to create letter of acceptance
    And he enters the mandatory details
    Then he save the file and view the LOA pdf

    #    View Letter of Acceptance

    And he choose to view Letter of Acceptance
    And he search for LOA

#    Modify Letter of Acceptance

    And he choose to modify letter of acceptance
    And he search for LOA for modify
    And current user logs out