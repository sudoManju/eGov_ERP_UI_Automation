Feature: To create a Financial Transactions

  @WIP
  Scenario Outline: To create the financial journal vouchers

    Given accountsOfficer logs in
    And search for the create journal voucher
    And user will enter the journal voucher details as <voucher type>
    Examples:
    |voucher type|
    |General     |


