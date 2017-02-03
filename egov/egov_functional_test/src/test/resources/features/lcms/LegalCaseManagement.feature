Feature: In this feature the following are created as well as edited

    1. Create/Edit Legal Case.
    2. Create/Edit Hearings
    3. Create/Edit Interim Order
    4. Create/Edit Judgment
    5. Create/Edit Judgment Implementation
    6. Case Closure

  @WIP
  Scenario Outline: It includes the creation and edit of Legal Case , Hearings , Interim Order , Judgement ,
            Judgment Implementation and Case Closure\

    ##########################################################
                  # Creating a Legal Case #
    ##########################################################

    Given admin logs in
    And user search for to create the legal case
    And user will enter the legal case details as <legalCaseData>
    And user closes the successful acknowledgement form
    And user will be notified by "successfully."
    And current user logs out

    ##########################################################
                  # Creating a Legal Case #
    ##########################################################

    Given admin logs in
    And user search for the recent legal case file
    And user will enter the case file number to search the file

    Examples:
    | legalCaseData |
    | testData1     |




