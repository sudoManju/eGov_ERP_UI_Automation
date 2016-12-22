Feature: To create a Financial Transactions

  @Sanity
  Scenario Outline: To create the financial journal vouchers type General

    Given accountsOfficer logs in
    And search for the create journal voucher
    And officer will enter the journal voucher details as <voucher type> & <account code>
    And officer will enter the approval details as <approval officer1>
    And officer will get the voucher number and closes it
    And current user logs out

    And assistantExaminer logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page
    And current user logs out

    And examiner logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page
    And current user logs out

    And commissioner logs in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    And current user logs out

    Examples:
    |voucher type| account code    | approval officer1 | approval officer2 |  approval officer3 |
    |General     | 2101001_3501001 | accountOfficer1   | accountOfficer2   |  commissioner      |


