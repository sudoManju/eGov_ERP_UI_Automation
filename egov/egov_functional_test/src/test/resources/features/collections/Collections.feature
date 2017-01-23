Feature: Collection

  As a registered user of the system
  I want to be able to collect taxes
  So that the property records are up to date.

  @WIP
  Scenario: System should be able to collect taxes

    Given juniorAssistant logs in
    When he chooses to collect taxes
    And he chooses to collect tax for "1016000083"

    And he chooses to pay tax
    And he pays using cheque with details as defaultChequeDetails

    And he clicks on drafts
    And he open application from drafts items
    And he submit all collections
    And current user logs out

    When adm_manager logs in
    And he chooses to act upon on receipt
    And he approves the receipt
    And current user logs out


@Sanity

  Scenario Outline: System should be able to create Miscellaneous receipt

      Given juniorAssistant logs in
      When he chooses to create Miscellaneous receipt
      And he enters Miscellaneous header

      And he pays using <paymentMethod>
      And current user closes acknowledgement
      And current user logs out


  Examples:
    |paymentMethod    |
    |cash             |
    |cheque           |
    |dd               |


@Sanity

  Scenario: System should be able to cancel receipt

  Given juniorAssistant logs in
  And he clicks on drafts
  And he chooses to act upon the above receipt in drafts
  And he submit all collections
  Then user will be notified by "Submitted"
  And user closes the acknowledgement
  And current user logs out

  And adm_manager_1 logs in
  And he chooses to act upon the above receipt in inbox
  And he approves all collections
  Then user will be notified by "Approved"
  And user closes the acknowledgement

  And he chooses to search receipts
  And he search for required receipt
  And he selects the required receipt
  And he cancel the receipt
  Then user will be notified by "Cancelled"
  And user closes the acknowledgement
  And current user logs out

@Sanity

  Scenario: Remittance of receipt

  Given adm_manager_1 logs in
  And he chooses to bank remittance
  And he select the required file with bank details
  Then user will be notified by "successfully"
  And user closes the acknowledgement
  And current user logs out

  @WIP
  Scenario Outline: System should be able to Create Challan

   Given juniorAssistant logs in
   And he chooses to create Challan
   And he enters challan details
   And he create challan and closes acknowledgement
   Then user will be notified by "successfully"
   And current user logs out

   Given seniorAssistant logs in
   And chooses to act upon the above challan
   And he validate the challan
   Then user will be notified by "Validated"

   And he search for challan receipt
   And he search for challan number
   And he pay using <paymentMethod>
   And he closes the acknowledgement
   And current user logs out

 Examples:
  |paymentMethod|
  |cash         |
  |cheque       |
  |dd           |







#  @WIP
#    Scenario: System Should be able to collect water Charges
#
#      Given juniorAssistant logs in
#      When he chosses to collect water charges
#      And he chooses to collect water charge for "1016000989"
##      And he chooses to pay water charge
#      And he pays using cheque with details as defaultChequeDetails


   @WIPax
    Scenario: online payment for prperty tax
#     Given commissioner logs in
#     When he chooses to create data entry
#     And he creates a new assessment for a private residential property
#     Then dataEntry Details saved successfully
#     And he choose to add edit DCB
#     And he choose to close the dataentry acknowledgement screen
#     And current user logs out
     Given User will Visit Property Tax onlinepayent link
     And User will enter Assessment Number and click on search button
     And user will fill amount and select the AXIS Bank Payment Gateway and click on PayOnline
     And user will select the card, enter all the details and click on pay now button



