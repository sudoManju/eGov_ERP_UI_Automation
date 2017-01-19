Feature: create preamble

  As a Registered user of the system
  i want to create Preamble, create agenda, create meeting invitation
  enter the attendance for the meeting and create MOM


  # Create Preamble #

  @Sanity
  Scenario Outline: Register user choose to create Preamble

    Given councilCreator logs in
    When he choose to create preamble
    And he enters create preamble details as <details>
    And he will enter the approval details as <approval officer2>
    And he copies preamble number and closes the acknowledgement
    And current user logs out

    When commissioner logs in
    And he chooses to act upon the above preamble number
    And he approves the preamble number
    And user will be notified by "APPROVED"
    And current user logs out

    # Create Agenda #

    Given councilClerk logs in
    When he choose to create agenda
    And he choose to create agenda for the above preamble
    And he enters create agenda details as <committee>
    And he copies agenda number and closes the acknowledgement
    And user will be notified by "APPROVED"

    # Create Meeting #

    When he choose to create meeting
    And he choose to create meeting invitation for the above agenda
    And he enters meeting details as <meetingDetails>
    And he copies meeting number and closes the acknowledgement

    # Enter Attendance #

    When he choose to enter attendance
    And he enters above meeting number to enter attendance
    And he choose to edit attendance details
    And he finalize attendance details and comes to home page

    


  Examples:
  |details| approval officer2 | committee    |meetingDetails |
  |abc    | commissioner1     | createAgenda |councilMeeting |
