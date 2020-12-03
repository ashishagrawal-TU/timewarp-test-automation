package stepdefinitions;

import common.TimeCalendar;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebElement;
import pageobjects.MyLogsPage;
import pageobjects.TicketsPage;
import steps.*;
import testdataobjects.UserAuthenticate;

import java.util.List;

public class TicketStepDefinitions {
    TicketsPage ticketsPage;
    MyLogsPage myLogsPage;

    @Steps
    LoginSteps loginSteps;

    @Steps
    NavigationBarSteps navigationBarSteps;

    @Steps
    MyLogSteps myLogSteps;

    @Steps
    TicketsSteps ticketsSteps;

    @Steps
    EmployeeSteps employeeSteps;

    @Steps
    ModalSteps modalSteps;

    @And("Click on the first View button in the list")
    public void clickOnAnyOfTheViewButtonWithStatusOr() {
        ticketsSteps.clickOnTheFirstOfTheViewButtonInTheList();
    }

    @Then("Click the Approve button")
    public void clickTheApproveButton() {
        ticketsSteps.approveTheTicket();
    }

    @And("Click No on Deny Ticket? message")
    public void clickNoOnApproveTicketMessage() {
        ticketsSteps.clickNoOnDenial();
    }

    @Then("Click the Deny button")
    public void clickTheDenyButton() {
        ticketsSteps.denyTheTicket();
    }

    @Then("Click the Back to Tickets link")
    public void clickTheBackToTicketsLink() {
        ticketsSteps.clickBackToTicketsLink();
    }

    @Then("Verify that the selected ticket details page is displayed")
    public void verifyThatTheSelectedTicketDetailsPageIsDisplayed() {
        ticketsSteps.checkThatCorrectPageIsDisplayedForSelectedTicket();
    }

    @And("Click Yes on Deny Ticket? message")
    public void clickYesOnDenyTicketMessage() {
        ticketsSteps.clickYesOnDenial();
    }

    @Then("Select {string} from the Ticket status dropdown")
    public void selectFromTheTicketStatusDropdown(String arg0) {
        ticketsSteps.selectAValueFromTicketStatus(arg0);
    }

    @Then("Search the ticket")
    public void searchTheTicket() {
        ticketsSteps.searchTheTicket();
    }

    @And("Verify that the selected ticket status is {string}")
    public void verifyThatTheSelectedTicketStatusIs(String arg0) {
        ticketsSteps.checkThatTicketStatusIsSameAsExpected(arg0);
    }

    @Then("Verify that the ticket details with status {string} is correct")
    public void verifyThatTheTicketDetailsWithStatusIsCorrect(String arg0) {
        ticketsSteps.checkThatTicketDetailsDisplayedIsCorrect(arg0);
        ticketsSteps.checkThatTicketLogEntriesAreCorrect();
    }

    @Then("Click Yes on Approval confirmation message")
    public void clickYesOnApprovalConfirmationMessage() {
        ticketsSteps.clickYesOnApproval();
    }

    @And("Click OK on ticket approved message")
    public void clickOKOnTicketApprovedMessage() {
        ticketsSteps.clickOKOnTicketApprovedMessage();
    }

    @And("Keep the approver name and update date")
    public void keepTheApproverNameAndUpdateDate() {
        ticketsSteps.keepTheApproverNameAndDate();
    }

    @Given("Tickets with {string} status exists")
    public void ticketsWithStatusExists(String arg0) {
        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TL");
        navigationBarSteps.clickOnTickets();
        ticketsSteps.selectAValueFromTicketStatus(arg0);
        List<WebElement> editLogs = ticketsPage.getViewTickets();
        if(editLogs.size() == 0){
            ticketsSteps.selectAValueFromTicketStatus("For Supervisor Approval");
            editLogs = ticketsPage.getViewTickets();
            if (editLogs.size() <= 0) {
                navigationBarSteps.logOut();
                loginSteps.UserLogsIn("TM");
                myLogSteps.createACompleteActivityLogForTheDayWithinSeventyTwoHours();
                navigationBarSteps.logOut();
                loginSteps.UserLogsIn("TL");
                navigationBarSteps.clickOnTickets();
                ticketsSteps.selectAValueFromTicketStatus("For Supervisor Approval");
            }
            ticketsSteps.clickFirstValueInList();
            if(arg0.equalsIgnoreCase("Approved"))
                ticketsSteps.approveTheTicket();
            else
                ticketsSteps.denyTheTicket();
            ticketsSteps.clickYesOnApproval();
            ticketsSteps.clickOKOnTicketApprovedMessage();
        }
        navigationBarSteps.logOut();
    }

    @Given("TL submit a log beyond seventy two hours for the TM")
    public void tlSubmitATicketForTheTM() {
        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TL");

        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();

        navigationBarSteps.clickOnEmployeeBtn();
        employeeSteps.clickOnAnyOfTheEditLogsButtonInTheList();
        myLogSteps.startTime = TimeCalendar.getDateNinetySixFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
        myLogSteps.clickOnPreviousButtonUntilStartTimeDisplays(myLogSteps.startTime);
        myLogSteps.clickOnZoomInBtn();

        int indexOfFirstActBeyondSeventyTwoHours =
                myLogsPage.getFirstActivityLogOnTheScreenThatIsBeyondSeventyTwoHoursFromToday(timeZoneIANA);

       if(indexOfFirstActBeyondSeventyTwoHours == -1){
            myLogSteps.createACompleteActivityLogForTheDayBeyondSeventyTwoHours();
        }else {
           myLogSteps.clickOnFirstBeyondSeventyTwoHoursActivityOnTheScreen();
            myLogSteps.checkThatUpdateActivityLogModalDisplays();
            modalSteps.selectActionReason("Forgot to log-in");
            modalSteps.addRemarks("This is a sample additional information.");
            modalSteps.clickSaveOnModal();
            modalSteps.clickOKOnWarningMessage();
            modalSteps.clickYesOnConfirmationWindow();
            myLogSteps.clickOnSaveChangesButton();
            myLogSteps.clickYesOnConfirmationModal();
            myLogSteps.clickOKOnDisplayedMessage();
        }
        navigationBarSteps.logOut();
    }
}
