Feature: Create a Agreement based on created asset service from the asset module

  As a registered user able to create the Agreement based on Asset Service

  Scenario Outline: Create the Agreement

#    //users for creating asset and agreement:
#    //kakinadJA:
#    //nelloreJA:
#    //tirupatiJA:

    Given pilotJA logs in
    And user will select the required screen as "Create Asset"
    And user will enter the details as <headerDetails> and <locationDetails>
    And user will enter the category details as <categoryDetails> and with asset summary status as <assetStatus>
    And user will be notified the success page with an asset application number
    And user will select the required screen as "Create Agreement"
    And user will select the required asset service application to create the agreement based on <categoryDetails> with action as "Create"
    And user will enter the allottee details as <allotteeDetails> and agreement details as <agreementDetails>
    And user will enter the approval details of <approvalOfficer1>
    And user will select the required screen as "Search Agreement"
    And user will select the required asset service application to create the agreement based on <categoryDetails> with action as "Collect Tax"
    And user will collect the fee and save the agreement application number
    And current user logs out

#  //kakinadRO:
#  //nelloreRO:
#  //tirupatiRO:

#    Given adoniRO logs in
#    And he chooses to act upon above application number
#    And user will enter the approval details of <approvalOfficer2>
#    And current user logs out

#  //kakinadComm:
#  //nelloreComm:
#  //tirupatiComm:

#    Given commissioner logs in
#    And he chooses to act upon above application number
#    And user will approve the agreement application
#    And current user logs out

    Examples:
      | headerDetails | locationDetails | assetStatus | categoryDetails          | allotteeDetails  | agreementDetails  | approvalOfficer1 | approvalOfficer2 |
      | header1       | location1       | CREATED     | Land                     | allotteeDetails1 | agreementDetails1 | veeraswamy       | commissionerLAMS |
      | header2       | location1       | CREATED     | Shop                     | allotteeDetails1 | agreementDetails1 | veeraswamy       | commissionerLAMS |
      | header3       | location1       | CREATED     | Market                   | allotteeDetails1 | agreementDetails1 | veeraswamy       | commissionerLAMS |
      | header4       | location1       | CREATED     | Kalyana_Mandapam         | allotteeDetails1 | agreementDetails1 | veeraswamy       | commissionerLAMS |
      | header5       | location1       | CREATED     | Usufruct                 | allotteeDetails1 | agreementDetails1 | veeraswamy       | commissionerLAMS |

   Scenario Outline: Create Data Entry Agreement

    Given pilotJA logs in
    And user will select the required screen as "Create Asset"
    And user will enter the details as <headerDetails> and <locationDetails>
    And user will enter the category details as <categoryDetails> and with asset summary status as <assetStatus>
    And user will be notified the success page with an asset application number
    And user will select the required screen as "Create Agreement"
    And user will select the required asset service application to create the agreement based on <categoryDetails> with action as "Data Entry"
    And user will enter the allottee details as <allotteeDetails> and agreement details as <agreementDetails>

   Examples:
     | headerDetails | locationDetails | assetStatus | categoryDetails          | allotteeDetails  | agreementDetails|
     | header1       | location1       | CREATED     | Land                     | allotteeDetails1 | agreementDetails1 |
     | header2       | location2       | CREATED     | Shop                     | allotteeDetails1 | agreementDetails1 |
     | header3       | location3       | CREATED     | Market                   | allotteeDetails1 | agreementDetails1 |
     | header4       | location4       | CREATED     | Kalyana_Mandapam         | allotteeDetails1 | agreementDetails1 |
     | header5       | location5       | CREATED     | Usufruct                 | allotteeDetails1 | agreementDetails1 |
