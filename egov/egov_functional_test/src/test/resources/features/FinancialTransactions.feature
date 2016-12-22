Feature: To create a Financial Transactions

  @WIP
  Scenario Outline: To create the financial journal vouchers

    Given accountsOfficer logs in
    And search for the create journal voucher
    And user will enter the journal voucher details as <voucher type> & <account code>
    And user will enter the approval details as <approval officer1>
    And current user logs out

#    And assistantExaminer logs in
#    Then the officer will click on the voucher number
#    And officer will approve and transfer to <approval officer>


    Examples:
    |voucher type| account code    | approval officer1 | approval officer  |
    |General     | 2101001_3501001 | accountOfficer    | accounts          |


