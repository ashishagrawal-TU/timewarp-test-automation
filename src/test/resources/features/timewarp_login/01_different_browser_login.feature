@Regression @function=TWlogin
Feature: Verify that different browser login for TimeWarp

  @QA_01_TW
  Scenario: To be able to verify login of TM user level
    Given User access the Time Warp Login page
    When A team mate user logs in
    Then User lands on the Home page
    #And Name displayed on the navigation bar is of the user

  @QA_02_TW @Ignored
  Scenario: Verify that the user should not be able to login at TW using Google Chrome Incognito Mode
    Given User instantiate Google Chrome Incognito mode
    When Time Warp Login page gets Open
    Then Verify Timewarp page is not accessible and show Error message

  @QA_03_TW @Ignored
  Scenario: Verify that the user should not be able to login at TW using other browsers
    Given User instantiate other than chrome browser
    When Time Warp Login page gets Open
    Then Verify Timewarp page is not accessible and show Error message for other browsers