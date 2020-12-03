@Regression @function=filter_date
Feature: Verify the previous and next button functionalities of the calendar on the My Logs page

  @Filter_Date_Range_QA_01_TWE
  Scenario: To be able to verify that the start day of the current week will be adjusted to the previous day when previous button is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User gets the week displayed on center
    Then User clicks the previous button
    And Verify that the start and end day of the week displayed at the center moved one day less

  @Filter_Date_Range_QA_02_TWE
  Scenario: To be able to verify that the start day of the current week will not be adjusted to the next day when the next button is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then Verify that the next button can not be clicked

  @Filter_Date_Range_QA_03_TWE
  Scenario: To be able to verify that the start day of the specified week will be adjusted to the previous day when previous button is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the calendar icon
    And User clicks on the previous day date
    Then User gets the week displayed on center
    And User clicks the previous button
    Then Verify that the start and end day of the week displayed at the center moved one day less

  @Filter_Date_Range_QA_04_TWE
  Scenario: To be able to verify that the start day of the specified week will be adjusted to the next day when the next button is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the calendar icon
    And User clicks on the day seven day previous from today
    Then User gets the week displayed on center
    And User clicks the next button
    Then Verify that the start and end day of the week displayed at the center moved one day more