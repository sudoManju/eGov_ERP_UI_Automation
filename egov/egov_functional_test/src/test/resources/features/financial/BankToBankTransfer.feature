Feature: To transfer payments from one bank to other bank

  @Sanity
  Scenario: To make payment transfers from one bank to other bank

    Given accountsOfficer logs in
    And officer will choose the bank to bank transfers
    And officer will enter the bank to bank transfer details
    And officer will close the successful creation page
    Then user will be notified by "Successful"
    And current user logs out