Feature: To create a new Journal voucher according to the budget check

  @WIP
  Scenario Outline: To create the financial journal voucher with type expense and budget check

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucher details>
    And officer will enter the approval details as <approval officer1>
    And officer will get successful BAN NUMBER created and closes it
    Then user will be notified by "BANo"
    And current user logs out

    And accountsOfficer logs in
    And he clicks on drafts
    And the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And accountOfficer logs in
    And he clicks on drafts
    And the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page
    Then user will be notified by "forwarded"
    And current user logs out

    And commissioner logs in
    And the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    Then user will be notified by "approved"
    And current user logs out

    Examples:
      |voucher details   | approval officer1 | approval officer2 |  approval officer3 |
      |budgetCheck       |  accountOfficer1a  | accountOfficer2   |  commissioner      |


