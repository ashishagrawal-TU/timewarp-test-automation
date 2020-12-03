@Regression @function=insert_tw_logs
Feature: Verify the insert activity log functionality

  @Insert_TW_Logs_QA_01_TWE
  Scenario:To be able to verify that "Insert Activity Log" form will be displayed when an area which has no activity yet is clicked

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed

  @Insert_TW_Logs_QA_02_TWE
  Scenario:To be able to verify the default values on the "Insert Activity Log" form

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Verify the Campaign field display should have the campaign where the user belongs
    And Verify the default values for all the other fields

  @Insert_TW_Logs_QA_03_TWE
  Scenario:To be able to verify the editable fields on "Insert Activity Log" form

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    And Verify the editable fields on the Insert Activity Log form

  @Insert_TW_Logs_QA_04_TWE
  Scenario:To be able to verify that when Save button of the "Insert Activity Log" form is clicked, inserted activity log will be saved temporarily (activity logs being inserted is within 72 hours)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Insert an activity log with date within seventy two hours from today
    And User hovers on the recently created activity
    Then Verify the details displayed on the pop up box is correct
    And User refresh the page
    Then Verify inserted activity is gone

  @Insert_TW_Logs_QA_05_TWE
  Scenario:To be able to verify that when Save button of the "Insert Activity Log" form is clicked, a warning message will be prompted (activity logs being inserted is beyond 72 hours)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Insert an activity log with date beyond seventy two hours
    And Verify error message "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval." displays

  @Insert_TW_Logs_QA_06_TWE
  Scenario:To be able to verify that users can still insert logs within 72 hours if the first inserted logs is beyond 72 hours

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Insert an activity log with date within seventy two hours from today
    And Verify the inserted activity log displays on the grid

  @Insert_TW_Logs_QA_07_TWE
  Scenario:To be able to verify that users can insert logs beyond 72 hours if the first inserted logs is within 72 hours

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And User goes to the date that is beyond seventy two hours from today
    Then User clicks on any area where there's no activity
    And Verify insert activity log modal form is displayed
    Then Insert an activity log with date beyond seventy two hours
    And Verify error message "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval." displays
    Then Click OK button on Warning message modal
    And Click Yes on Save Activity Log modal
    Then Verify the inserted activity log displays on the grid
    Then User goes to the date that is within seventy two hours from today
    And User clicks on any area where there's no activity
    Then Verify insert activity log modal form is displayed
    And Insert an activity log with date within seventy two hours from today
    Then Verify insert activity logs is more than one

  @Insert_TW_Logs_QA_08_TWE
  Scenario:To be able to verify that when NO is selected on confirmation message " Submit Ticket? YES/NO", a ticket will not be submitted

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And Insert an activity log within nine hours from now
    Then User clicks the Save Changes on my log page
    And Verify confirmation message "Submit Ticket?" displays
    #And Verify confirmation message submit ticket displays
    Then Click No on the submit ticket confirmation message
    And Verify Save Changes button on the page is enabled

  @Insert_TW_Logs_QA_09_TWE
  Scenario:To be able to verify that when YES is selected on confirmation message "Submit Ticket? YES/NO", a ticket will be submitted

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    And Insert an activity log within nine hours from now
    Then User clicks the Save Changes on my log page
    #And Verify confirmation message submit ticket displays
    And Verify confirmation message "Submit Ticket?" displays
    Then Click Yes on confirmation message
    And Click OK button
    Then Verify Save Changes button on the page is not enabled