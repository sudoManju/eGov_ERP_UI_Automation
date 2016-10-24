Feature: Create New Property

  As a register user of the system
  I want to be able to create a new property
  So that the property records are up to date.


  @Sanity
  Scenario Outline: Registered user creating a new property in the system
    Given user is logged in with details of <loginAs>
    When he chooses to create new property
    And he enters property header details as <propertyHeaderDetails>
    And he enters owner details for the first owner as <ownerDetails>
    And he enters property address details as <propertyAddressDetails>
    And he enters assessment details as <assessmentDetails>
    And he enters amenities as <amenitiesDetails>
    And he enters construction type details as <constructionTypeDetails>
    And he enters floor details as <floorDetails>
    And he enters approval details as <approvalDetails>
    And he forwards the details

    Then the property gets registered

    Examples:
      | loginAs         | propertyHeaderDetails | ownerDetails | propertyAddressDetails | assessmentDetails     | amenitiesDetails | constructionTypeDetails | floorDetails | approvalDetails |
      | juniorAssistant | residentialPrivate    | bimal        | addressOne             | assessmentNewProperty | all              | defaultConstructionType | firstFloor   | defaultApprover |



