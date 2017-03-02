Feature: Existing Property

  As a register user of the system
  I want to be able to maintain existing property
  So that the property records are up to date.

  @Sanity @PropertyTax
  Scenario: Registered user create property through data entry screen

  Given commissioner logs in
  And user will select the required screen as "Data entry screen" with condition as "ptis"
  And he creates a new assessment for a private residential property
  Then dataEntry Details saved successfully
  And he choose to add edit DCB
  And he choose to close the dataentry acknowledgement screen
  And current user logs out

  Given juniorAssistant logs in
  And user will select the required screen as "collect tax"
  And he searches for assessment with number
  And he chooses to pay tax
  And he pay tax using Cash

  And user will select the required screen as "Tax Exemption"
  And he searches for assessment with number
  And he selects the exemption reason from drop down
  And he forwarding for approval to bill_Collector
  Then user will be notified by "successfully"
  And current user logs out
#
#  When billCollector logs in
#  And he chooses to act upon above assessment number
#  And he forwards for approval to revenueInspector
#  And current user closes acknowledgement
#  And current user logs out
#
#  When revenueInspector logs in
#  And he chooses to act upon above assessment number
#  And he forwards for approval to revenueOfficer
#  And current user closes acknowledgement
#  And current user logs out
