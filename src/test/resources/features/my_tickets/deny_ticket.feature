@Regression @function=deny_ticket
Feature: Verify a TL or an OM can deny a submitted ticket

  Background:
    Given an activity log within seventy two hours from now not yet approved already exists for the user

  @Deny_Ticket_QA_01_TWE
  Scenario: To be able to verify that when NO is selected on Denial Ticket confirmation, ticket will not be denied

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Tickets tab
    And Click on the first View button in the list
    Then Verify that the selected ticket details page is displayed
    And Click the Deny button
    Then Click No on Deny Ticket? message
    And Click the Back to Tickets link
    Then Search the ticket
    And Verify that the selected ticket status is "For Supervisor Approval"

  @Deny_Ticket_QA_03_05_TWE
  Scenario: To be able to verify that when the ticket is denied, it will be listed in the 'Denied' tickets

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Tickets tab
    And Click on the first View button in the list
    Then Verify that the selected ticket details page is displayed
    And Click the Deny button
    Then Click Yes on Deny Ticket? message
    And Click OK button
    And Search the ticket
    Then Verify that the selected ticket status is "Denied"






