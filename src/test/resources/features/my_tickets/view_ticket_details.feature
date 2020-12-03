@Regression @function=view_ticket_details
Feature: Verify the submitted ticket details

  Background:
   Given an activity log within seventy two hours from now not yet approved already exists for the user

  @View_Ticket_Details_QA_01_TWE
  Scenario: To be able to verify details of 'For Supervisor Approval' tickets (Requestor's view)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks the Tickets tab
    And Click on the first View button in the list
    Then Verify that the selected ticket details page is displayed
    And Verify that the ticket details with status "For Supervisor Approval" is correct

  @View_Ticket_Details_QA_02_TWE
  Scenario: To be able to verify details of 'For Supervisor Approval' tickets (Approver's view)

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Tickets tab
    And Click on the first View button in the list
    Then Verify that the selected ticket details page is displayed
    And Verify that the ticket details with status "For Supervisor Approval" is correct

