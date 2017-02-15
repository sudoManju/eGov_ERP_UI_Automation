Feature: To create a Financial Transactions

  @Sanity @Finance
  Scenario Outline: To create the financial journal voucher with type General

    Given accountsOfficer logs in
    And user will select the required screen as "Create Journal Voucher"
#    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucherDetails>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will get successful voucher created and closes it
    Then user will be notified by "Created"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer2>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    Examples:
    |voucherDetails|  approvalOfficer1 | approvalOfficer2 |  approvalOfficer3 |
    |voucher1      |  accountOfficer1a | accountOfficer2  |  commissioner     |


  @Sanity @Finance
  Scenario Outline: To create the financial journal voucher with type expense

    Given accountsOfficer logs in
    And user will select the required screen as "Create Journal Voucher"
#    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucherDetails>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will get successful voucher created and closes it
    Then user will be notified by "Created"
    And current user logs out

#    And assistantExaminer logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer2>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

#    And examiner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    Examples:
      |voucherDetails| approvalOfficer1 | approvalOfficer2 |  approvalOfficer3 |
      |voucher2      | accountOfficer1a | accountOfficer2  |  commissioner     |


  @Sanity @Finance
  Scenario Outline: To create a voucher of date in june as well paying the bill

    Given accountsOfficer logs in
    And user will select the required screen as "Create Journal Voucher"
#    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucherDetails>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will get successful voucher created and closes it
    Then user will be notified by "Created"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer2>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    And accountsOfficer logs in
    And user will select the required screen as "Bill Payment"
#    And officer search for the bill payment
    And officer will modify the results depending upon the fund and date as <date>
    And officer will act upon the above voucher with payment mode as <paymentMode>

    And officer will enter the bank details
    And officer will enter the approval details as <approvalOfficer2>
    And officer will closes the successfull payment page
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    Examples:
      |voucherDetails     |  approvalOfficer1  | date       | approvalOfficer2  |  approvalOfficer3  | paymentMode  |
      |voucherDateJune    |  accountOfficer1   | 30_06_2016 | accountOfficer2   |  commissioner      | cheque       |
      |voucherDateJune    |  accountOfficer1   | 30_06_2016 | accountOfficer2   |  commissioner      | cash         |
      |voucherDateJune    |  accountOfficer1   | 30_06_2016 | accountOfficer2   |  commissioner      | RTGS         |


  @Sanity @Finance
  Scenario Outline: To create a new expense bill

    Given accountsOfficer logs in
    And user will select the required screen as "New Create Expense Bill"
#    And officer will search for the new expense bill
    And officer will the expense bill details as <billDetails>
    And officer will enter the expense approval details as <approvalOfficer1>
    And officer will closes the expense acknowledgement page
    Then user will be notified by "created"
    And current user logs out

#    Then examiner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the expense approval details as <approvalOfficer2>
    And officer will closes the expense acknowledgement page
    Then user will be notified by "created"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the expense acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    And accountsOfficer logs in
    And user will select the required screen as "Create Voucher"
#    And officer will search for the Create Voucher for expense bill
    And officer will filter the bill according to the type
    And officer will enter the approval details as <approvalOfficer3>
    And officer will set the new expense voucher number and closes it
    Then user will be notified by "forwarded"
    And current user logs out

#    And assistantExaminer logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer4>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

#    And examiner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer5>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    Examples:
    | billDetails  | approvalOfficer1  | approvalOfficer2  | approvalOfficer3  | approvalOfficer4  | approvalOfficer5 |
    | expenseBill  | accountOfficer3   | commissioner1     | accountOfficer1   | accountOfficer2   | commissioner     |


  @Sanity @Finance
  Scenario Outline: To create a voucher of date in june as well paying the bill and checking the assignment

    Given accountsOfficer logs in
    And user will select the required screen as "Create Journal Voucher"
#    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucherDetails>
    And officer will enter the approval details as <approvalOfficer1>
    And officer will get successful voucher created and closes it
    Then user will be notified by "Created"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer2>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    And accountsOfficer logs in
    And user will select the required screen as "Bill Payment"
#    And officer search for the bill payment
    And officer will modify the results depending upon the fund and date as <date>
    And officer will act upon the above voucher with payment mode as <paymentMode>

    And officer will enter the bank details
    And officer will enter the approval details as <approvalOfficer2>
    And officer will closes the successfull payment page
    And current user logs out

#    And examiner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer will enter the approval details as <approvalOfficer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

#    And commissioner logs in
    And the next user will be logged in
    And the officer will click on the above voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
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
      |voucherDetails     |  approvalOfficer1  | date       | approvalOfficer2  |  approvalOfficer3  | paymentMode  | assignment |
      |voucherDateJune    |  accountOfficer1   | 30_06_2016 | accountOfficer2   |  commissioner      | cheque       | cheque     |
      |voucherDateJune    |  accountOfficer1   | 30_06_2016 | accountOfficer2   |  commissioner      | RTGS         | RTGS       |


