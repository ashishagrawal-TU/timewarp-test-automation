@Regression @function=invalid_tagging
Feature: Verify when an invalid tagging happens

  Background:
    Given an activity log already exists for the user

  @Invalid_Tagging_QA_01_TWE
  Scenario: To be able to verify that no Start Shift will result to invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then User keeps the date displayed on Start Time field
    Then User creates a new "End Shift" activity
    Then Click on the Invalid Tagging button on the My Logs page
    And Verify "No Start Shift" tag displays on the invalid tagging list

  @Invalid_Tagging_QA_02_TWE
  Scenario: To be able to verify that gaps between activities will result to invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And  User clicks on the first "Productive Time" activity
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Click Yes on Delete Activity Log modal
    Then Click on the Invalid Tagging button on the My Logs page
    And Verify the details of the deleted activity appears on the invalid tagging list
    Then Close the invalid tagging modal
    And  Hover on the deleted activity log on the screen
    Then Verify "Gap Between Activity Logs" displays on the popup

  @Invalid_Tagging_QA_03_04_TWE
  Scenario: To be able to verify that no End Shift will result to invalid tagging and that Save button will not be clickable when there is invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on an area before the first "Start Shift" activity
    And  Verify insert activity log modal form is displayed
    Then User keeps the date displayed on End Time field
    Then User creates a new "Start Shift" activity
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify "No End Shift" tag displays on the invalid tagging list
    Then Close the invalid tagging modal
    And  Verify Save Changes button on the page is not enabled

  @Invalid_Tagging_QA_06_TWE
  Scenario: To be able to verify the Invalid notification when there is invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And  User keep the count of the invalid tagging notification
    Then User clicks on the first "Productive Time" activity
    And  Verify update activity log modal form is displayed
    Then Select an action reason
    And  Click on trash icon
    Then Click Yes on Delete Activity Log modal
    And  Verify the number of invalid tagging notification count was added by "1"
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify the number of rows in the invalid tagging list is the same as those on notifications

  @Invalid_Tagging_QA_07_TWE
  Scenario: To be able to verify that inserting Start Shift will fix 'No Start Shift' invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    Then User clicks on the first "Start Shift" activity
    And  Verify update activity log modal form is displayed
    Then Select an action reason
    And  Click on trash icon
    Then Click Yes on Delete Activity Log modal
    And  User keep the count of the invalid tagging notification
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify the details of the deleted activity appears on the invalid tagging list
    Then Click on action button corresponding to the deleted activity
    And  Verify insert activity log modal form is displayed
    Then Select an action reason
    And  Click the Save button on the activity log form
    Then Click OK on warning message if displayed
    And Click Yes on Save Activity Log modal
    Then  Verify the number of invalid tagging notification count was decreased by "1"

  @Invalid_Tagging_QA_08_TWE
  Scenario: To be able to verify that Inserting activity on gaps will fix 'Gap Between Activity Logs' invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And  User clicks on the first "Productive Time" activity
    Then Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Click Yes on Delete Activity Log modal
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify the details of the deleted activity appears on the invalid tagging list
    Then Close the invalid tagging modal
    And  User keep the count of the invalid tagging notification
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify the details of the deleted activity appears on the invalid tagging list
    Then Click on action button corresponding to the deleted activity
    And  Verify insert activity log modal form is displayed
    Then Select "Productive Time" from the activity type dropdown
    Then Select an action reason
    And  Click the Save button on the activity log form
    Then Click OK on warning message if displayed
    And Click Yes on Save Activity Log modal
    Then  Verify the number of invalid tagging notification count was decreased by "1"

  @Invalid_Tagging_QA_09_TWE
  Scenario: To be able to verify that Inserting End Shift will fix 'No End Shift' invalid tagging

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on an area before the first "Start Shift" activity
    And  Verify insert activity log modal form is displayed
    Then User keeps the date displayed on End Time field
    And  User creates a new "Start Shift" activity
    And  User keep the count of the invalid tagging notification
    Then Click on the Invalid Tagging button on the My Logs page
    And  Verify "No End Shift" tag displays on the invalid tagging list
    Then Click on action button corresponding to the deleted activity
    And  Verify insert activity log modal form is displayed
    And  Select an action reason
    Then Click the Save button on the activity log form
    And Click OK on warning message if displayed
    Then  Click Yes on Save Activity Log modal
    And Verify the number of invalid tagging notification count was decreased by "1"

  @Invalid_Tagging_QA_10_TWE
  Scenario: To be able to verify that no Start Shift will not result to invalid tagging (Start Shift is in the previous day that is not displayed)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User clicks on the Zoom In button
    Then User keep the count of the invalid tagging notification
    Then User clicks on the first "Start Shift" activity
    And  Verify update activity log modal form is displayed
    And  Select an action reason
    Then Click on trash icon
    And  Click Yes on Delete Activity Log modal
    And  Verify the number of invalid tagging notification count was added by "0"