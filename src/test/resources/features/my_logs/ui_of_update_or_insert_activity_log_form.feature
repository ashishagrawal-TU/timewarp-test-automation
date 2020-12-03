@Regression @function=ui_log_activity_form
Feature: Verify the user interface displays of the update or insert activity log form

  Background:
    Given an activity log within seventy two hours from now already exists for the user

  @UI_log_activty_form_QA_01_TWE
  Scenario: To be able to verify that the Campaign field of Insert Activity Log form is the campaign where the user belongs

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Verify the Campaign field display should have the campaign where the user belongs

  @UI_log_activty_form_QA_02_TWE
  Scenario: To be able to verify that the list of Activity Types are the same with what is in Time Warp

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click on the activity dropdown
    And Verify the displayed activities are those for the campaign

  @UI_log_activty_form_QA_03_TWE
  Scenario: To be able to verify that the Activity Type selected will be the data on Activity Type field

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Select "Productive Time" from the activity type dropdown
    And Verify "Productive Time" displays on the activity type field

  @UI_log_activty_form_QA_04_TWE
  Scenario: To be able to verify that a calendar will be displayed when Calendar icon is clicked on the Start Time field

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the start time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed

  @UI_log_activty_form_QA_06_TWE
  Scenario: To be able to verify that the data on Start TIme will be removed when Clear on calendar display is clicked

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the start time field
    And  Verify calendar is displayed
    Then Click the clear button on the calendar
    And  Verify the date on the start time field is removed

  @UI_log_activty_form_QA_11_TWE
  Scenario: To be able to verify that seconds, minutes and hour will increase when "^" is clicked (Start Time field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the start time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed
    Then Get the value of the hour
    Then Get the value of the minute
    Then Get the value of the second
    Then Click the up icon on the hour
    And  Verify the hour increased by 1
    Then Click the up icon on the minute
    And  Verify the minute increased by 1
    Then Click the up icon on the second
    And  Verify the second increased by 1

  @UI_log_activty_form_QA_12_TWE
  Scenario: To be able to verify that seconds, minutes and hour will decrease when "v" is clicked (Start Time field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the start time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed
    Then Get the value of the hour
    And  Get the value of the minute
    Then Get the value of the second
    And  Click the down icon on the hour
    Then Verify the hour decreased by 1
    And  Click the down icon on the minute
    Then Verify the minute decresed by 1
    And  Click the down icon on the second
    Then Verify the second decreased by 1

  @UI_log_activty_form_QA_13_TWE
  Scenario: To be able to verify that when AM is clicked, PM will be displayed and vice versa (Start TIme field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the start time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed
    Then Get the value of the AM or PM button
    Then Click the AM or PM button
    And Verify it changes to the other value other than displayed
    And  Get the value of the AM or PM button again
    Then Click the AM or PM button
    And  Verify it changes to the other value other than displayed

  @UI_log_activty_form_QA_19_TWE
  Scenario: To be able to verify that a calendar will be displayed when clock icon is clicked on the End Time field

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the end time field
    And  Verify calendar is displayed

  @UI_log_activty_form_QA_21_TWE
  Scenario: To be able to verify that the data on End Time will be removed when Clear on calendar display is clicked

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the end time field
    And  Verify calendar is displayed
    Then Click the clear button on the calendar
    And  Verify the date on the end time field is removed

  @UI_log_activty_form_QA_24_TWE
  Scenario: To be able to verify that time selection will be prompted when Time is clicked (End Time field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the end time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed

  @UI_log_activty_form_QA_26_TWE
  Scenario: To be able to verify that seconds, minutes and hour will increase when "^" is clicked (End Time field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the start time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed
    Then Get the value of the hour
    Then Get the value of the minute
    Then Get the value of the second
    Then Click the up icon on the hour
    And  Verify the hour increased by 1
    Then Click the up icon on the minute
    And  Verify the minute increased by 1
    Then Click the up icon on the second
    And  Verify the second increased by 1

  @UI_log_activty_form_QA_27_TWE
  Scenario: To be able to verify that seconds, minutes and hour will decrease when "v" is clicked (End Time field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Click the calendar icon on the end time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed
    Then Get the value of the hour
    And  Get the value of the minute
    Then Get the value of the second
    And  Click the down icon on the hour
    Then Verify the hour decreased by 1
    And  Click the down icon on the minute
    Then Verify the minute decresed by 1
    And  Click the down icon on the second
    Then Verify the second decreased by 1

  @UI_log_activty_form_QA_28_TWE
  Scenario: To be able to verify that when AM is clicked, PM will be displayed and vice versa (End Time field)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Click the calendar icon on the end time field
    And  Verify calendar is displayed
    Then Click the clock icon on the calendar
    And  Verify time page is displayed
    Then Get the value of the AM or PM button
    And  Click the AM or PM button
    Then Verify it changes to the other value other than displayed
    And  Get the value of the AM or PM button again
    Then Click the AM or PM button
    And  Verify it changes to the other value other than displayed

  @UI_log_activty_form_QA_34_TWE
  Scenario: To be able to verify the list of the Action Reason

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Click on the action reason dropdown
    And  Verify the displayed action reasons choices are complete

  @UI_log_activty_form_QA_35_TWE
  Scenario: To be able to verify that Start Time greater than End Time will not be allowed

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    And  Set an end time that is before the start time
    And  Select an action reason
    Then Click the Save button on the activity log form
    And  Click OK on warning message if displayed
    Then Verify confirmation message "Start Time cannot be greater than End Time." displays

  @UI_log_activty_form_QA_36_TWE
  Scenario: To be able to verify that the selected reason will be the data on the Action Reason field

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Select "No Time Warp profile" an action reason
    And  Verify "No Time Warp profile" is displayed on the action reason field

  @UI_log_activty_form_QA_37_TWE
  Scenario: To be able to verify that Activity Log Form will be closed when "X" button is clicked

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Click on the activity log form Close button
    And  Verify that the Update Activity log form is gone
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then  Click on the activity log form Close button
    And Verify that the Insert Activity log form is gone

  @UI_log_activty_form_QA_38_TWE
  Scenario: To be able to verify that Trash icon is present on Update Activity Log form and not in Insert Activity Log form

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Verify the trash icon is present in the upper right corner of the form
    And Click on the activity log form Close button
    Then User creates a new activity
    And User clicks on the created activity
    Then Verify update activity log modal form is displayed
    And Verify the trash icon is present in the upper right corner of the form

  @UI_log_activty_form_QA_39_TWE
  Scenario: To verify that AM and PM indicators in Start Time field will accept any case character

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Get the value of the start time field
    And  Change the PM or AM value of start time field to a value with first letter on big cap and second on small cap
    Then Verify the value of the start time field is the same as original value
    Then Get the value of the start time field
    And  Change the PM or AM value of start time field to a value with first letter on small cap and second on big cap
    Then Verify the value of the start time field is the same as original value
    Then Get the value of the start time field
    And  Change the PM or AM value of start time field to a value to both small cap
    Then Verify the value of the start time field is the same as original value

  @UI_log_activty_form_QA_40_TWE
  Scenario: To verify that AM and PM indicators in End Time field will accept any case character

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Get the value of the end time field
    And  Change the PM or AM value of end time field to a value with first letter on big cap and second on small cap
    Then Verify the value of the end time field is the same as original value
    Then Get the value of the end time field
    And  Change the PM or AM value of end time field to a value with first letter on small cap and second on big cap
    Then Verify the value of the end time field is the same as original value
    Then Get the value of the end time field
    And  Change the PM or AM value of end time field to a value to both small cap
    Then Verify the value of the end time field is the same as original value

  @UI_log_activty_form_QA_41_42_TWE
  Scenario Outline: To be able to verify ITSD reference number field when Action Reason=Network Issue Or Time Warp Site Inaccessible

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Select "Productive Time" from the activity type dropdown
    Then Select "<reason>" an action reason
    And  Verify "<reason>" is displayed on the action reason field
    Then Verify ITSD reference number field is displayed

    Examples:
      |reason                                      |
      |Network Issue / Time Warp Site Inaccessible |
      |Machine Malfunction           |


  @UI_log_activty_form_QA_43_TWE
  Scenario Outline: To be able to verify ITSD reference number field when Action Reason is neither Machine Malfunction nor Network Issue / Time Warp Site Inaccessible

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Select "Productive Time" from the activity type dropdown
    Then Select "<reason>" an action reason
    And  Verify "<reason>" is displayed on the action reason field
    Then Verify ITSD reference number field is not displayed

    Examples:
      |reason                            |
      |Forgot to log-in                  |
      |Forgot to switch correct activity |
      |No Time Warp profile              |
      |No work station available         |
      |Incorrect Activity Selected       |
      |Account Locked Out / Disabled     |
      |Security, Parking, Elevator Issue |
      |Working Offsite                   |

  @UI_log_activty_form_QA_44_TWE
  Scenario Outline:To be able to verify that ITSD reference number field is required when Action Reason is either Machine Malfunction or Network Issue / Time Warp Site Inaccessible

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And  Verify update activity log modal form is displayed
    Then Select "<reason>" an action reason
    And Click the Save button on the activity log form
    Then Click OK on warning message if displayed
    And Verify that field error message "ITSD Reference Number is required" is displayed

    Examples:
      |reason                                      |
      |Network Issue / Time Warp Site Inaccessible |
      |Machine Malfunction           |

