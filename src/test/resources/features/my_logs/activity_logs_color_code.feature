@Regression @function=activity_logs_color_code
Feature: Verify the different activity types and their correct color code

  Background:
    Given an activity log already exists for the user

  @Activity_Logs_Color_Code_QA_01_TWE
  Scenario: To be able to verify that Shift terminals have blue color

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User finds the first "Start Shift" activity on the screen
    And  Verify that the activity color is "blue"
    Then User finds the first "End Shift" activity on the screen
    And  Verify that the activity color is "blue"

  @Activity_Logs_Color_Code_QA_02_TWE
  Scenario: To be able to verify that Avail activities have green color

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User finds the first "Productive Time" activity on the screen
    And  Verify that the activity color is "green"
    Then User finds the first "Idle Time" activity on the screen
    And  Verify that the activity color is "green"
    Then User finds the first "Overtime" activity on the screen
    And  Verify that the activity color is "green"

  @Activity_Logs_Color_Code_QA_03_TWE
  Scenario: To be able to verify that not Avail activities have orange color

    Given User access the Time Warp Editor Home page
    When A team mate user logs in

    Then User clicks on the calendar icon
    And User clicks on the < on the calendar if enabled

    Then User finds the first "Break (15 minutes)" activity on the screen
    And  Verify that the activity color is "orange"
    Then User finds the first "Lunch (60 minutes)" activity on the screen
    And  Verify that the activity color is "orange"
    Then User finds the first "Team Meetings" activity on the screen
    And  Verify that the activity color is "orange"
    Then User finds the first "Coaching and Development" activity on the screen
    And  Verify that the activity color is "orange"

  @Activity_Logs_Color_Code_QA_04_TWE
  Scenario: To be able to verify that inserted/edited activities have tangerine color

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    Then User clicks on the first "Start Shift" activity
    And  Verify update activity log modal form is displayed
    Then Select an action reason
    And  Click the Save button on the activity log form
    Then Click Yes on Save Activity Log modal
    And User finds the first "Inserted" activity on the screen
    Then  Verify that the activity color is "tangerine"

  @Activity_Logs_Color_Code_QA_05_TWE
  Scenario: To be able to verify that existing activity affected by the change has yellow color

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User finds the first "Start Shift" activity on the screen
    Then User clicks on the first "Start Shift" activity
    And  Verify update activity log modal form is displayed
    Then Add one hour to the existing end time field value
    Then Select an action reason
    And  Click the Save button on the activity log form
    Then Click Yes on Save Activity Log modal
    And  User finds the next activity from the edited activity
    Then Verify that the activity color is "yellow"


  @Activity_Logs_Color_Code_QA_06_TWE
  Scenario: To be able to verify that invalid tagging has tangerine-transparent color

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User clicks on the Zoom In button
    Then User clicks on the first "Productive Time" activity
    And  Verify update activity log modal form is displayed
    Then Select an action reason
    And  Click on trash icon
    Then Click Yes on Delete Activity Log modal
    And  User finds the first "Deleted" activity on the screen
    Then Verify that the activity color is "tangerine-transparent"
