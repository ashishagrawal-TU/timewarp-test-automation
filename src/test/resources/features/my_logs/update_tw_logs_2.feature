@Regression @function=update_tw_logs @function=update_tw_logs_2
Feature: Verify the update activity log functionality for activities beyond seventy two hours

  Background:
    Given an activity log beyond seventy two hours before now already exists for the user

  @Update_TW_Logs_QA_05_TWE
  Scenario:To be able to verify that when Save button of the "Update Activity Log" form is clicked, a warning message will be prompted (activity logs being updated is beyond 72 hours)

    When User access the Time Warp Editor Home page
    Then A team mate user logs in
    And User goes to the date that is beyond seventy two hours from today
    And User clicks on one of the existing activity that is beyond seventy two hours from now
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click the Save button on the activity log form
    And Verify confirmation message "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval." displays

  @Update_TW_Logs_QA_06_TWE
  Scenario:To be able to verify that users can still edit logs within 72 hours if the first edited logs is beyond 72 hours

    When User access the Time Warp Editor Home page
    Then A team mate user logs in
    And User goes to the date that is beyond seventy two hours from today
    Then User clicks on one of the existing activity that is beyond seventy two hours from now
    And Verify update activity log modal form is displayed
    Then Select an action reason
    And Click the Save button on the activity log form
    Then Verify confirmation message "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval." displays
    And Click OK button on Warning message modal
    Then Click Yes on Save Activity Log modal
    And User clicks on second activity that is beyond seventy two hours from now
    And Verify update activity log modal form is displayed
    Then Select an action reason
    And Click the Save button on the activity log form
    Then Verify confirmation message "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval." displays
    And Click OK button on Warning message modal
    Then Click Yes on Save Activity Log modal
    And Verify no error message displays

  @Update_TW_Logs_QA_07_TWE
  Scenario:To be able to verify that users cannot edit logs beyond 72 hours if the first edited logs is within 72 hours

    And an activity log within seventy two hours from now already exists for the user
    When User access the Time Warp Editor Home page
    And A team mate user logs in
    Then User goes to the date that is beyond seventy two hours from today
    And User clicks on the Zoom In button
    Then User clicks on one of the existing activity that is within seventy two hours from now
    And Verify update activity log modal form is displayed
    Then User updates the values of the selected activity
    And User clicks on one of the existing activity that is beyond seventy two hours from now
    Then Verify update activity log modal form is displayed
    And Select an action reason
    Then Click the Save button on the activity log form
    And Verify confirmation message "You’re trying to submit a request with edited logs beyond and within 72 hours approval period, kindly submit a separate ticket for each coverage." displays
