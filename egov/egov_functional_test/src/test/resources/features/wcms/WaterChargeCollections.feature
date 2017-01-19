Feature: To collect water charges in different mode payments

  @WIP
  Scenario Outline: System Should be able to collect water Charges

    Given juniorAssistant logs in
    When he chosses to collect water charges
    And he chooses to collect water charge for <hscNumber>
    And he chooses to pay water charge
    And he pay using <paymentMethod>
    And he pays using cheque with details as defaultChequeDetails
    And he closes the payment acknowledgement
    And current user logs out

    Examples:
    |paymentMethod| hscNumber  |
    |cash         | 1016042128 |
    |cheque       | 1016042140 |
    |dd           | 1016042146 |
