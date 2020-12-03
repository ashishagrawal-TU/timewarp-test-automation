@Regression @function=deny_ticket  @function=deny_ticket_2
Feature: Verify a TL or an OM can deny a submitted ticket for logs beyond seventy two hours

  Background:
    Given an activity log beyond seventy two hours before now not yet approved already exists for the user

  @Deny_Ticket_QA_07_TWE
  Scenario: To be able to verify that Immediate Supervisor can deny ticket with logs beyond 72 hours submitted by Employee (with valid Final Approver)

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Tickets tab
    And Select "For Supervisor then VP Approval" from the Ticket status dropdown
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Click the Deny button
    And Click Yes on Deny Ticket? message
    And Click OK button
    And Search the ticket
    Then Verify that the selected ticket status is "Denied"
