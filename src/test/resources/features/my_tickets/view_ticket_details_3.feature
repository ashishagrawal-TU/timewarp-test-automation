@Regression @function=deny_ticket  @function=deny_ticket_3
Feature: Verify the ticket details for approved, denied and expired tickets

  @View_Ticket_Details_QA_05_TWE
  Scenario: To be able to verify details of 'For Supervisor then VP Approval' tickets of employee submitted by immediate superior

    Given TL submit a log beyond seventy two hours for the TM
    When User access the Time Warp Editor Home page
    And A vice president user logs in
    Then User clicks the Tickets tab
    And Select "For Supervisor then VP Approval" from the Ticket status dropdown
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Verify that the ticket details with status "For Supervisor then VP Approval" is correct

  @View_Ticket_Details_QA_06_TWE
  Scenario: To be able to verify the details of 'Approved' tickets

    Given Tickets with "Approved" status exists
    When User access the Time Warp Editor Home page
    And A team leader user logs in
    Then User clicks the Tickets tab
    And Select "Approved" from the Ticket status dropdown
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Verify that the ticket details with status "Approved" is correct

  @View_Ticket_Details_QA_07_TWE
  Scenario: To be able to verify the details of 'Denied' tickets

    Given Tickets with "Denied" status exists
    When User access the Time Warp Editor Home page
    And A team leader user logs in
    Then User clicks the Tickets tab
    And Select "Denied" from the Ticket status dropdown
    Then Click on the first View button in the list
    And Verify that the selected ticket details page is displayed
    Then Verify that the ticket details with status "Denied" is correct

  @View_Ticket_Details_QA_08_TWE
  Scenario: To be able to verify details of 'Ticket Expired' tickets

    Given User access the Time Warp Editor Home page
    When A team leader user logs in
    Then User clicks the Tickets tab
    And Select "Ticket Expired" from the Ticket status dropdown
    And Click on the first View button in the list
    Then Verify that the selected ticket details page is displayed
    And Verify that the ticket details with status "Ticket Expired" is correct