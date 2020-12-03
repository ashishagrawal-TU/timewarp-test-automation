@Regression @function=employee_view_logs
Feature: Verify the employee list displayed for a supervisor is correct

  @Employee_View_TW_Logs_QA_01_TWE
  Scenario: To be able to verify the Employee page using TL user level
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Verify the list of employees under the logged in user is correct

  @Employee_View_TW_Logs_QA_02_TWE
  Scenario: To be able to verify the Employee page using OM userl level

    Given User access the Time Warp Editor Home page
    When An operations manager user logs in
    Then Verify the list of employees under the logged in user is correct

  @Employee_View_TW_Logs_QA_03_01_TWE
  Scenario: To be able to verify that Activity logs of employees under OM's bucket can be viewed

    Given User access the Time Warp Editor Home page
    When An operations manager user logs in
    Then Verify the list of employees under the logged in user is correct
    And Click on any of the Edit Logs button displayed for an employee
    Then Verify the page title displays the name of the selected employee
    And Start of the week displayed on header is three days from today and ends three days from today
    Then Activity logs displayed for the selected employee is correct
    And  User clicks on the calendar icon
    Then The calendar should be displayed

  @Employee_View_TW_Logs_QA_03_02_TWE
  Scenario: To be able to verify that Activity logs of employees under TL's bucket can be viewed

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    Then Verify the list of employees under the logged in user is correct
    And Click on any of the Edit Logs button displayed for an employee
    Then Verify the page title displays the name of the selected employee
    And Start of the week displayed on header is three days from today and ends three days from today
    Then Activity logs displayed for the selected employee is correct
    And  User clicks on the calendar icon
    Then The calendar should be displayed