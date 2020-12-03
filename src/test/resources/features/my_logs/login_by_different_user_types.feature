@Regression @function=login @demo
Feature: Verify that users of different levels can login to TimeWarp Editor

  @QA_04_TWE
  Scenario: To be able to verify login of TM user level
    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User lands on the My Logs page
    And Name displayed on the navigation bar is of the user
    And My Tickets tab is available

  @QA_05_TWE
  Scenario: To be able to verify login of TL user level
    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User lands on the My Logs page
    And Name displayed on the navigation bar is of the user
    And Employee tab is available
    And My Logs tab is available

  @QA_06_TWE
  Scenario: To be able to verify login of OM user level
    Given User access the Time Warp Editor Home page
    When An operations manager user logs in
    Then Employee Landing page is displayed
    And Name displayed on the navigation bar is of the user
    And My Tickets tab is available

  @QA_07_TWE
  Scenario: To be able to verify login of Director user level
    Given User access the Time Warp Editor Home page
    When A director user logs in
    Then Employee Landing page is displayed
    And Name displayed on the navigation bar is of the user
    And My Tickets tab is available

  @QA_08_TWE
  Scenario: To be able to verify login of VP user level
    Given User access the Time Warp Editor Home page
    When A vice president user logs in
    Then Employee Landing page is displayed
    And Name displayed on the navigation bar is of the user
    And My Tickets tab is available