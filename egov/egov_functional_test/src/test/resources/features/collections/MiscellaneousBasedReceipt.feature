Feature: Create/Collect/Remit/Cancel Miscellaneous Receipt
   As a regeistered user of the system
  I am able to Create/Collect/Remit/Cancel Miscellaneous Receipt




@Sanity

   Scenario Outline: System should be able to create Miscellaneous receipt

   Given creator logs in
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
  |directBank       |


@Sanity

  Scenario: System should be able to cancel receipt

    Given creator logs in
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

