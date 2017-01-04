Feature: To create a new remittance recovery

  @Sanity
  Scenario Outline: To create the remittance recovery with expense type

    Given admin logs in
    And user search for the modify detailed code
    And user will enter the account code to modify as <glCode>
    And user will map the account code to particular
    And current user logs out

    Given accountsOfficer logs in
    And officer search for the create journal voucher
    And officer will enter the remittance details as <voucher details>
    And officer will enter the approval details as <approval officer1>
    And officer will get successful voucher created and closes it
    And user will notifies that "Created"
    And current user logs out

    And assistantExaminer logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page
    And user will notifies that "forwarded"
    And current user logs out

    And examiner logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page
    And user will notifies that "forwarded"
    And current user logs out

    And commissioner logs in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    And user will notifies that "approved"
    And current user logs out

    Given accountsOfficer logs in
    And officer will search for the Create Remittance Recovery
    And officer will search for remittance bills
    And officer will enter the remittance bank details
    And officer will enter the approval details as <approval officer2>
    And officer will closes the acknowledgement page
    And user will notifies that "forwarded"
    And current user logs out

    And examiner logs in
    Then the officer will click on the voucher number
    And officer will enter the approval details as <approval officer3>
    And officer will closes the acknowledgement page
    And user will notifies that "forwarded"
    And current user logs out

    And commissioner logs in
    Then the officer will click on the voucher number
    And officer click on approval of the voucher
    And officer will closes the acknowledgement page
    And user will notifies that "approved"
    And current user logs out

    Examples:
      |voucher details  | approval officer1 | approval officer2 |  approval officer3 |  glCode   |
      |remittance       |  accountOfficer1  | accountOfficer2   |  commissioner      |  3502002  |


