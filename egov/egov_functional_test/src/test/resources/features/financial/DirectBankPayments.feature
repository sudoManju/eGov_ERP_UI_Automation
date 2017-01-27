Feature: To create a voucher through direct bank payments

  @Sanity
  Scenario Outline: To create a voucher through direct bank payments with different modes

    Given accountsOfficer logs in
    And officer will search for the direct bank payments
    And officer will enter the direct bank payment details with <paymentMode>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "Successful"
    And current user logs out

#    And examiner logs in
    And the next user will be logged in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "forwarded"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "approved"
    And current user logs out

    Examples:
    |paymentMode | approvalOfficer1  | approval officer2 |
    |cheque      | accountOfficer2   | commissioner      |
    |cash        | accountOfficer2   | commissioner      |
    |RTGS        | accountOfficer2   | commissioner      |

