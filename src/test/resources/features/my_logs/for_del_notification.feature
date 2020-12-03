@Regression @function=delete_notification
Feature: Verify the delete notification functionality

  Background:
    Given an activity log already exists for the user

  @For_Del_Notification_QA_01_TWE
  Scenario: To be able to verify that when NO is selected on Delete Activity Log confirmation, activity will not be removed

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User gets all the log entries displayed initially
    And  User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Verify "Are you sure on deleting this Activity Log?" is displayed on the dialog message
    Then Click No on Delete Activity Log modal
    And  Click on the activity log form Close button
    And Verify the log entries displayed on the screen is still the same

  @For_Del_Notification_QA_02_TWE
  Scenario: To be able to verify that when YES is selected on Delete Activity Log confirmation, activity will be removed

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User gets all the log entries displayed initially
    And  User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Verify "Are you sure on deleting this Activity Log?" is displayed on the dialog message
    Then Click Yes on Delete Activity Log modal
    And Verify the log entries displayed on the screen is not the same

  @For_Del_Notification_QA_03_TWE
  Scenario: To be able to verify 'For Del' notification when there is no deleted activity

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User gets all the log entries displayed initially
    And User keep the count of the view deleted notification
    And  User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And  Click on the activity log form Close button
    Then Verify the number of view deleted notification count was increased by "0"
    And Click on the view deleted notification button
    Then Verify the view deleted modal screen does not have any data
    And Click on the activity log form Close button
    Then Verify the log entries displayed on the screen is still the same

  @For_Del_Notification_QA_04_TWE
  Scenario: To be able to verify 'For Del' notification when there is deleted activity

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User gets all the log entries displayed initially
    And User keep the count of the view deleted notification
    And  User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Click Yes on Delete Activity Log modal
    Then Verify the number of view deleted notification count was increased by "1"
    And Click on the view deleted notification button
    Then Verify the view deleted modal screen have data
    And Click on the activity log form Close button
    Then Verify the log entries displayed on the screen is not the same