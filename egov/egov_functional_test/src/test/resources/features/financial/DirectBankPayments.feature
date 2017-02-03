Feature: To create a voucher through direct bank payments

  @Sanity @Finance
  Scenario Outline: To create a voucher through direct bank payments with cheque and rtgs modes and also applying the check assignment

    Given accountsOfficer logs in
    And officer will search for the direct bank payments
    And officer will enter the direct bank payment details with <paymentMode>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "Successful"
    And current user logs out

#    And examiner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer2>
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "forwarded"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "approved"
    And current user logs out

    And accountsOfficer logs in
    And officer search for the assignment mode as <assignment>
    And officer will filter the payment cheque assignment bill
    And officer will select the bill and enter the details <assignment>
    And officer will close the successfull assignment page
    Then user will be notified by "successfully"
    And current user logs out

    Examples:
    |paymentMode | approvalOfficer1  | approvalOfficer2  | assignment |
    |cheque      | accountOfficer2   | commissioner      | cheque     |
    |RTGS        | accountOfficer2   | commissioner      | RTGS       |


    @Sanity @Finance
    Scenario Outline: To create a voucher through direct bank payments with cash mode

    Given accountsOfficer logs in
    And officer will search for the direct bank payments
    And officer will enter the direct bank payment details with <paymentMode>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "Successful"
    And current user logs out

#    And examiner logs in
    And the next user will be logged in
      And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer2>
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "forwarded"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
      And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will see the successful voucher creation page and closes it
    Then user will be notified by "approved"
    And current user logs out

    Examples:
    |paymentMode | approvalOfficer1  | approvalOfficer2  |
    |cash        | accountOfficer2   | commissioner      |