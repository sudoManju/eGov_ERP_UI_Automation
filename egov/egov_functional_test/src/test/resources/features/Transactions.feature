Feature: To apply for a new connection

  As a register user of the system
  I want to be able to apply for new water connection
  So that the property records are up to date.

Scenario: applying for new connection

  Given juniorAssistant logs in
  And he chooses to apply for new connection

  And he enter all the credential details

  And current user closes acknowledgement
  And current user logs out
