Feature: To create a voucher through direct bank payments

  @WIP
  Scenario Outline: To create a voucher through direct bank payments with different modes

    Given accountsOfficer logs in
    And officer will search for the direct bank payments
    And officer will enter the direct bank payment details with <paymentMode>

    Examples:
    |paymentMode |
    |cheque      |
    |cash        |
    |RTGS        |

