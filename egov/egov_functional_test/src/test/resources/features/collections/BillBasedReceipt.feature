Feature: Create/Collect Bill Based Receipt
As a registered user of the system
I am able to Create/Collect Bill Based Receipt



Background:It will run the data entry screen of property tax

    Given commissioner logs in
    When he chooses to create data entry
    And he creates a new assessment for a private residential property
    Then dataEntry Details saved successfully
    And he choose to add edit DCB
    And he choose to close the dataentry acknowledgement screen
    And current user logs out

@Sanity

  Scenario Outline: System should be able to collect taxes

      Given juniorAssistant logs in
      When he chooses to collect taxes
      And he chooses to collect tax for above assessment number

      And he chooses to pay tax
      And he collect tax using <paymentMode>
      And he closes the acknowledgement

      And he clicks on drafts
      And he open application from drafts items
      And he submit all collections
      Then user will be notified by "Successfully"
      And he closes the acknowledgement
      And current user logs out

      When adm_manager_1 logs in
      And he chooses to act upon on receipt
      And he approves all collections
      Then user will be notified by "Approved"
      And he closes the acknowledgement
      And current user logs out

  Examples:
   |paymentMode|
   |cash       |
   |cheque     |
   |dd         |
   |directBank1|