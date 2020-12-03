@Regression @function=edit_logs
Feature: Verify a TL or an OM can edit the logs of employees under them

  Background:
    Given an activity log already exists for the user

  @Employee_Edit_Logs_QA_01_06_TWE
  Scenario: To be able to verify that TL/OM can update activity/activities of his direct reports
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Click on any of the Edit Logs button displayed for an employee
    And User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And User updates the values of the selected activity
    And  User hover on the activity that was updated
    And Verify the details displayed on the pop up box is correct
    Then User clicks the Save Changes on my log page
    And Verify confirmation message "Save Changes?" displays
    Then Click Yes on confirmation message
    And Click OK button
    And Verify Save Changes button on the page is not enabled

  @Employee_Edit_Logs_QA_02_TWE
  Scenario: To be able to verify that TL/OM can delete activity/activities of his direct reports
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Click on any of the Edit Logs button displayed for an employee
    Then User clicks on the Zoom In button
    And User gets all the log entries displayed initially
    And  User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Verify "Are you sure on deleting this Activity Log?" is displayed on the dialog message
    Then Click Yes on Delete Activity Log modal
    And Verify the log entries displayed on the screen is not the same

  @Employee_Edit_Logs_QA_03_TWE
  Scenario: To be able to verify that TL/OM can insert activity/activities of his direct reports
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Click on any of the Edit Logs button displayed for an employee
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Insert an activity log with date within seventy two hours from today
    And Verify the inserted activity log displays on the grid

  @Employee_Edit_Logs_QA_04_TWE
  Scenario: To be able to verify that TL/OM cannot save changes when there is invalid tagging
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Click on any of the Edit Logs button displayed for an employee
    Then User clicks on the Zoom In button
    Then User clicks on an area before the first "Start Shift" activity
    And  Verify insert activity log modal form is displayed
    Then User creates a new "Start Shift" activity
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify "No End Shift" tag displays on the invalid tagging list
    Then Close the invalid tagging modal
    And  Verify Save Changes button on the page is not enabled

  @Employee_Edit_Logs_QA_08_TWE
  Scenario: To be able to verify that Immediate Supervisor can submit a ticket with logs within 72 hours in behalf of the Employee (without valid Final Approver)
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Click on any of the Edit Logs button displayed for an employee
    Then User clicks on the Zoom In button
    And User clicks on one of the existing activity that is within seventy two hours from now
    Then Verify update activity log modal form is displayed
    And User updates the values of the selected activity
    Then User clicks the Save Changes on my log page
    And Verify confirmation message "Save Changes?" displays
    Then Click Yes on confirmation message
    And Click OK button
    And Verify Save Changes button on the page is not enabled

