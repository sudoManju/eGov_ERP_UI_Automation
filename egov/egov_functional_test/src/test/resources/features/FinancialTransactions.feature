Feature: To create a Financial Transactions

  @Sanity
  Scenario Outline: To create the financial journal voucher with type General

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucher details>
    And officer will enter the approval details as <approval officer1>
    And officer will get successful voucher created and closes it "Created"
    And he will notifies that "Created"
    And current user logs out

    And assistantExaminer logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page "forwarded"
    And current user logs out

    And examiner logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page "forwarded"
    And current user logs out

    And commissioner logs in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page "approved"
    And current user logs out

    Examples:
    |voucher details|  approval officer1 | approval officer2 |  approval officer3 |
    |voucher1       |  accountOfficer1   | accountOfficer2   |  commissioner      |


  @Sanity
  Scenario Outline: To create the financial journal voucher with type expense

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucher details>
    And officer will enter the approval details as <approval officer1>
    And officer will get successful voucher created and closes it "Created"
    And current user logs out

    And assistantExaminer logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page "forwarded"
    And current user logs out

    And examiner logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page "forwarded"
    And current user logs out

    And commissioner logs in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page "approved"
    And current user logs out

    Examples:
      |voucher details| approval officer1 | approval officer2 |  approval officer3 |
      |voucher2       |  accountOfficer1  | accountOfficer2   |  commissioner      |


  @WIP
  Scenario Outline: To create a voucher of date in june as well paying the bill

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucher details>
    And officer will enter the approval details as <approval officer1>
    And officer will get successful voucher created and closes it "Created"

    And officer search for the bill payment
    Then officer will modify the results depending upon the fund and date as <date>
    And officer will act upon the above voucher

#    And officer will verify the voucher number

    And officer will enter the bank details
    And officer will enter the approval details as <approval officer1>
    And current user logs out

    And assistantExaminer logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page "forwarded"
    And current user logs out

    And examiner logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page "forwarded"
    And current user logs out

    And commissioner logs in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page "approved"
    And current user logs out

    Examples:
      |voucher details    |  approval officer1 | date       | approval officer2 |  approval officer3 |
      |voucherDateJune    |  accountOfficer1   | 30_06_2016 | accountOfficer2   |  commissioner      |


