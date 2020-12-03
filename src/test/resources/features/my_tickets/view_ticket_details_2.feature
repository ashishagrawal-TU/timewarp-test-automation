@Regression @function=deny_ticket  @function=deny_ticket_2
Feature: Verify the submitted ticket details for logs beyond seventy two hours

  Background:
    Given an activity log beyond seventy two hours before now not yet approved already exists for the user

  @View_Ticket_Details_QA_03_TWE
  Scenario: To be able to verify details of 'For Supervisor then VP Approval' tickets (Requestor's view)

    Given User access the Time Warp Editor Home page
    When A team mate user logs in
    Then User clicks the Tickets tab
    And Select "For Supervisor then VP Approval" from the Ticket status dropdown
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Verify that the ticket details with status "For Supervisor then VP Approval" is correct

  @View_Ticket_Details_QA_04_TWE
  Scenario: To be able to verify details of 'For Supervisor Approval' tickets (Approver's view)

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Tickets tab
    And Select "For Supervisor then VP Approval" from the Ticket status dropdown
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Verify that the ticket details with status "For Supervisor then VP Approval" is correct
    And Click the Approve button
    Then Click Yes on Approval confirmation message
    And Click OK on ticket approved message
    Then Search the ticket
    And Keep the approver name and update date
    Then User logs out
    When A vice president user logs in
    Then User clicks the Tickets tab
    And Search the ticket
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Verify that the ticket details with status "For Supervisor then VP Approval" is correct






