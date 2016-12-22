Feature: To create a Financial Transactions

  @WIP
  Scenario Outline: To create the financial journal vouchers

    Given accountsOfficer logs in
    And search for the create journal voucher
    And user will enter the journal voucher details as <voucher type> & <account code>
    And user will enter the approval details as <approval officer>
    And current user logs out

    Examples:
    |voucher type| account code    | approval officer |
    |General     | 2101001_3501001 | accountOfficer   |


