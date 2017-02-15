Feature: To find the daily collection reports

  As a registered user of the system
  I want to be able to search the daily collection reports

  @Sanity @DailyCollectionReports
  Scenario Outline: To find the daily collection vlt reports

    Given juniorAssistant logs in
    And user will select the required screen as "Daily Collection Report(VLT)"
#    And user chooses to find the daily collection vlt reports
    And user need to enter the date to get the vlt report details
    And current user logs out

    Examples:
    |     screenName             |
    |Daily_collection_report     |

  @Sanity @DailyCollectionReports
  Scenario: To find the daily collection pt reports

    Given juniorAssistant logs in
    And user chooses to find the daily collection pt reports
    And user need to enter the date to get the pt report details
    And current user logs out


