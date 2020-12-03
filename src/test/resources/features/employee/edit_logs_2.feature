@Regression @function=edit_logs @function=edit_logs_2
Feature: Verify a TL or an OM can edit the logs with date before 72 hours from now of employees under them

  Background:
    Given an activity log beyond seventy two hours before now already exists for the user

  @Employee_Edit_Logs_QA_10_TWE
  Scenario: To be able to verify that Immediate Supervisor can submit a ticket with logs beyond 72 hours in behalf of the Employee (with valid FInal Approver)

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Employee tab
    And Click on any of the Edit Logs button displayed for an employee
    And User goes to the date that is beyond seventy two hours from today
    Then User clicks on the Zoom In button
    And User clicks on one of the existing activity that is beyond seventy two hours from now
    Then Verify update activity log modal form is displayed
    And User updates the values of the selected activity
    Then User clicks the Save Changes on my log page
    And Verify confirmation message "Ticket will route to VP Approval once you submit, do you want to proceed?" displays
    Then Click Yes on confirmation message
    And Verify Ticket Submitted message displays
    Then Click OK button
    And Verify Save Changes button on the page is not enabled
