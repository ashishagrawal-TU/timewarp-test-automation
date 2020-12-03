@Regression @function=update_tw_logs @function=update_tw_logs_1
Feature: Verify the update activity log functionality

  Background:
   Given an activity log within seventy two hours from now already exists for the user

  @Update_TW_Logs_QA_01_TWE
  Scenario:To be able to verify that "Update Activity Log" form will be displayed when an activity is clicked

    When User access the Time Warp Editor Home page
    Then A team mate user logs in
    And User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed

  @Update_TW_Logs_QA_02_TWE
  Scenario:To be able to verify the editable fields on "Update Activity Log" form

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on one of the existing activity
    And Verify update activity log modal form is displayed
    Then Verify the editable fields on the Update Activity Log form

  @Update_TW_Logs_QA_03_TWE
  Scenario:To be able to verify Activity Type, Start Time and End Time of Update Activity Log form

    When User access the Time Warp Editor Home page
    And A team mate user logs in
    Then User clicks on the Zoom In button
    And User hover on an activity
    Then User takes note of the activity details from the popup
    And User clicks on one of the existing activity
    Then Verify update activity log modal form is displayed
    And Verify the activity details displayed on the update activity log is same with the popup

  @Update_TW_Logs_QA_04_TWE
  Scenario:To be able to verify that when Save button of the "Update Activity Log" form is clicked
  changes on the activity logs will be saved temporarily (activity logs being updated is within 72 hours)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    Then User clicks on one of the existing activity that is within seventy two hours from now
    And Verify update activity log modal form is displayed
    Then User updates the values of the selected activity
    And  User hover on the activity that was updated
    And Verify the details displayed on the pop up box is correct
    Then User refresh the page
    Then User clicks on the Zoom In button
    Then User hover on the first activity that is within seventy two hours from now
    And  User gets the details of the pop up content
    Then Verify the details displayed on the pop up box is correct

  @Update_TW_Logs_QA_08_TWE
  Scenario:To be able to verify that when NO is selected on confirmation message "Submit TIcket? YES/NO", a ticket will not be submitted

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User clicks on one of the existing activity that is within seventy two hours from now
    Then Verify update activity log modal form is displayed
    And User updates the values of the selected activity but clicks No on confirmation window
    Then Verify the Save button on the Update Activity Log form can still be clicked

  @Update_TW_Logs_QA_09_TWE
  Scenario:To be able to verify that when YES is selected on confirmation message Submit Ticket? YES/NO, a ticket will be submitted

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on the Zoom In button
    And User clicks on one of the existing activity that is within seventy two hours from now
    Then Verify update activity log modal form is displayed
    And User updates the values of the selected activity
    Then User clicks the Save Changes on my log page
    #And Verify confirmation message submit ticket displays
    And Verify confirmation message "Submit Ticket?" displays
    Then Click Yes on confirmation message
    And Verify Ticket Submitted message displays
    Then Click OK button
    And Verify Save Changes button on the page is not enabled

