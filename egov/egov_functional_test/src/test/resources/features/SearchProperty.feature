Feature: search property

  As a register user of the system
  I want to be able to search for a property
  So that the property records are up to date.


Scenario: Registered user searching a property in the system with assessment number

  Given Admin logs in

  When he chooses to search property
  And he search property with assessment number
  And he check total number of records found

  And current user closes acknowledgement
  And current user logs out

Scenario:  Registered user searching a property in the system with door number

  Given Admin logs in

  When he chooses to search property
  And he search property with door number
  And he check total number of records found

  And current user closes acknowledgement
  And current user logs out

Scenario: Registered user searching a property in the system with mobile number

  Given Admin logs in

  When he chooses to search property
  And he search property with mobile number
  And he check total number of records found

  And current user closes acknowledgement
  And current user logs out

Scenario: Registered user searching a property in the system with zone and ward number

  Given Admin logs in

  When he chooses to search property
  And he search property with zone and ward number
  And he check total number of records found

  And current user closes acknowledgement
  And current user logs out