@Regression @function=zoom_logs
Feature: Verify the zoom functionality in My Logs page

  @Zoom_TW_Logs_QA_01_TWE
  Scenario: To be able to verify the height of activity when Zoom In (magnifying glass with + icon) is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User gets the height of one activity displayed
    And User clicks on the Zoom In button
    Then User gets the height of same activity again
    And Verify that the activity height is bigger

  @Zoom_TW_Logs_QA_02_TWE
  Scenario: To be able to verify the height of the activity when Zoom Out (magnifying glass with - icon) is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User gets the height of one activity displayed
    And User clicks on the Zoom In button
    Then User clicks on the Zoom Out button
    And User gets the height of same activity again
    Then Verify that the activity height is the same as initial display

  @Zoom_TW_Logs_QA_03_TWE
  Scenario: To be able to verify the time interval when Zoom In (magnifying glass with + icon) is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User clicks on the Zoom In button
    Then User gets the time displayed per row
    Then Verify that the time intervals are 15 minutes

  @Zoom_TW_Logs_QA_04_TWE
  Scenario: To be able to verify the interval when Zoom Out (magnifying glass with - icon) is clicked
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User clicks on the Zoom In button
    Then User gets the time displayed per row
    Then Verify that the time intervals are 15 minutes
    And User clicks on the Zoom Out button
    Then User gets the time displayed per row
    Then Verify that the time intervals are 30 minutes

  @Zoom_TW_Logs_QA_05_TWE
  Scenario: To be able to verify that when Zoom In is clicked, it will not be clickable again
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User clicks on the Zoom In button
    Then Verify that Zoom In button can not be clicked again

  @Zoom_TW_Logs_QA_06_TWE
  Scenario: To be able to verify that when Zoom In is clicked, it will not be clickable again
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User clicks on the Zoom In button
    Then User clicks on the Zoom Out button
    Then Verify that Zoom Out button can not be clicked again