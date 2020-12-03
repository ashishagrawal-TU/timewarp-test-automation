@Regression @function=pop_up_box @demo
Feature: Verify the pop up box on an activity on the My Logs page

  @Pop_up_box_on_activity_QA_01_TWE
  Scenario: To be able to verify that pop-up box will appear upon hover on the activity
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User hover on an activity
    Then Pop-up box with the details of the activity should be displayed

  @Pop_up_box_on_activity_QA_03_TWE
  Scenario: To be able to verify the contents of the pop-up box (activity is not inserted nor updated)
    Given User access the Time Warp Editor Home page
    And A team mate user logs in
    When User clicks on the Zoom In button
    And an activity log already exists for the user
    Then User hover on an activity
    And Verify the details displayed on the pop up box is the same as the database

  @Pop_up_box_on_activity_QA_04_TWE
  Scenario: To be able to verify the contents of the pop-up box (activity is inserted)
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User creates a new activity
    And User hovers on the recently created activity
    Then Verify the details displayed on the pop up box is correct

  @Pop_up_box_on_activity_QA_05_TWE
  Scenario: To be able to verify the contents of the pop-up box (activity is updated)
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User creates a new activity
    And User clicks on the created activity
    And User updates the values from the created activity
    And User hovers on the recently created activity
    Then Verify the details displayed on the pop up box is correct


