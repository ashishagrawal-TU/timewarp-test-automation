@Regression @function=select_date
Feature: Verify the different functionalities of the calendar on the My Logs page

   @Select_Date_QA_01_TWE
   Scenario: To be able to verify that calendar will be displayed when calendar icon is clicked on Select Date field
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And  User clicks on the calendar icon
    Then The calendar should be displayed

   @Select_Date_QA_02_TWE
   Scenario: To be able to verify that previous month will be displayed when "<" is clicked
     Given User access the Time Warp Editor Home page
     When A team mate user logs in
     And User clicks on the calendar icon
     And User clicks on the < on the calendar if enabled
     Then Previous month should be displayed on the calendar

    @Select_Date_QA_03_TWE
    Scenario: To be able to verify that next month will be displayed when ">" is clicked
      Given User access the Time Warp Editor Home page
      When A team mate user logs in
      And User clicks on the calendar icon
      And User clicks on the > on the calendar
      Then Next month should be displayed on the calendar

  @Select_Date_QA_05_TWE
    Scenario: To be able to verify that the current week will be displayed when Today is clicked
      Given User access the Time Warp Editor Home page
      When A team mate user logs in
      And User clicks on the calendar icon
      And User clicks on the previous day date
      And User clicks on the calendar icon
      And User clicks on today's date
      Then Current day should be displayed as the first day of the calendar week

   @Select_Date_QA_07_TWE
    Scenario: To be able to verify that the week where selected date is included will be displayed
      Given User access the Time Warp Editor Home page
      When A team mate user logs in
      And User clicks on the calendar icon
      And User clicks on the previous day date
      Then Week where selected date is included should be displayed

  @Select_Date_QA_08_TWE
   Scenario: To be able to verify the available dates on the calendar
     Given User access the Time Warp Editor Home page
     When A team mate user logs in
     Then User clicks on the calendar icon
     And  User goes to the day fourty five day previous today
     Then Verify that only the dates forty two days prior today are enabled