@Regression @function=ActivityPageStartShift
Feature: Verify the Start shift starts and show in Activity table for TimeWarp

  @QA_01_TW
  Scenario: To be able to verify Start Shift will automatically start once logged in
    Given User access the Time Warp Login page
    When A team mate user logs in
    Then User lands on the Home page
    And Verify User is on activity page
    And Verify that Start Shift has been recorded at the Current Activity table

  @QA_02_TW
  Scenario: To be able to verify Start Shift will start if Yes is selected
    Given User access the Time Warp Login page
    When A team mate user logs in
    Then User lands on the Home page
    And Verify User click the Yes button
    And Verify that Start Shift has been recorded at the Current Activity table

  @QA_03_TW
  Scenario: To be able to verify Start Shift recording for TW first-time user #Logged in using TL
    Given User access the Time Warp Login page
    When A team Leader user logs in
    Then User lands on the Home page
    And Verify User is on activity page
    And Verify that Start Shift should be started and be recorded at the Current Activity table
