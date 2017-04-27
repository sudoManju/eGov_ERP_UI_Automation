Feature: Create a Agreement based on created asset service from the asset module

  As a registered user able to create the Agreement based on Asset Service

  Scenario Outline: Create the Agreement

    Given admin logs in
    And user will select the required screen as "Create Agreement"
    And user will select the required asset service application to create the agreement
    And user will enter the allottee details as <allotteeDetails> and agreement details as <agreementDetails>

    Examples:
    | allotteeDetails  | agreementDetails  |
    | allotteeDetails1 | agreementDetails1 |