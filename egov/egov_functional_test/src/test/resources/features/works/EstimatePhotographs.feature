Feature: Upload/View estimate photographs
  As a registered user of the system
  I am able to upload and view photos of estimate progress

  @Works  @Sanity

  Scenario: Upload/View estimate photographs

    Given assis_Engineer logs in
    And he choose to upload estimate photo
    And he search for estimate in estimate search result
    And he upload the estimate photos for physical progress track
    And he close the acknowledgement page
    And current user logs out