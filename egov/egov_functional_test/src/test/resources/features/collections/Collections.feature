Feature: Collection

  As a registered user of the system
  I want to be able to collect taxes
  So that the property records are up to date.



#  @WIP
#    Scenario: System Should be able to collect water Charges
#
#      Given juniorAssistant logs in
#      When he chosses to collect water charges
#      And he chooses to collect water charge for "1016000989"
##      And he chooses to pay water charge
#      And he pays using cheque with details as defaultChequeDetails


   @WIP
    Scenario: online payment for prperty tax
#     Given commissioner logs in
#     When he chooses to create data entry
#     And he creates a new assessment for a private residential property
#     Then dataEntry Details saved successfully
#     And he choose to add edit DCB
#     And he choose to close the dataentry acknowledgement screen
#     And current user logs out
     Given User will Visit Property Tax onlinepayent link
     And User will enter Assessment Number and click on search button
     And user will fill amount and select the AXIS Bank Payment Gateway and click on PayOnline
     And user will select the card, enter all the details and click on pay now button



