Feature: To create a Financial Transactions

  @Sanity
  Scenario Outline: To create the financial journal voucher with type General

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucher type> & <account code> <department> <function>
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
    |voucher type| account code    | approval officer1 | approval officer2 |  approval officer3 | department |function               |
    |General     | 2101001_3501001 | accountOfficer1   | accountOfficer2   |  commissioner      | ACCOUNTS   |12th_Finance_Commission|



  @Sanity
  Scenario Outline: To create the financial journal voucher with type expense

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the journal voucher details as <voucher type> & <account code> <department> <function>
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
      |voucher type| account code    | approval officer1 | approval officer2 |  approval officer3 | department                    |function      |
      |Expense     | 2101001_3501003 | accountOfficer1   | accountOfficer2   |  commissioner      | PUBLIC_HEALTH_AND_SANITATION   |Public_Health |



  @WIP
  Scenario: To perform a bill payment for journal voucher

    Given accountsOfficer logs in
    And officer search for the bill payment
    Then officer will modify the results depending upon the fund


