@Regression @function=view_logs
Feature: Verify the date entries displayed for a user are correct

  Background:
   Given an activity log beyond seventy two hours before now already exists for the user

  @View_TW_Logs_QA_01_TWE
  Scenario: To be able to verify activity logs of the current week
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then Start of the week displayed on header is three days from today and ends three days from today
    And  Log entries for the given week is correct

  @View_TW_Logs_QA_02_TWE
  Scenario: To be able to verify activity Logs when a date is selected
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the calendar icon
    And User clicks on the previous day date
    Then Log entries for the given week is correct


