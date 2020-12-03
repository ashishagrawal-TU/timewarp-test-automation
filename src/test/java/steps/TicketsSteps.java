package steps;

import common.APIHelper;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assume;
import org.openqa.selenium.WebElement;
import pageobjects.CommonPageObjectsHelper;
import pageobjects.MyLogsPage;
import pageobjects.TicketsPage;
import testdataobjects.UserAuthenticate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

public class TicketsSteps {

    TicketsPage ticketsPage;
    CommonPageObjectsHelper commonPageObjectsHelper;
    String selectedTicketNumber;
    String selectedRequestorName;
    String firstApprover;
    String updatedDate;
    private EnvironmentVariables environmentVariables;
    MyLogsPage myLogsPage;
    APIHelper apiHelper = new APIHelper();

    @Step("Click the first ticket in the list")
    public void clickFirstValueInList(){
        ticketsPage.clickFirstTicketInViewTicketList();
    }

    @Step("Approve the ticket")
    public void approveTheTicket(){
        ticketsPage.clickApproveBtn();
    }

    @Step("Click Yes on Approve Ticket? confirmation message")
    public void clickYesOnApproval(){
        ticketsPage.clickYesOnMessageModal();
    }

    @Step("Click No on Approve Ticket? confirmation message")
    public void clickNoOnApproval(){
        ticketsPage.clickNoOnMessageModal();
    }

    @Step("Click OK on the ticket approved message")
    public void clickOKOnTicketApprovedMessage(){
        ticketsPage.clickOnTicketApprovedModalOKBtn();
    }

    @Step("Select a value from ticket status")
    public void selectAValueFromTicketStatus(String value){
        ticketsPage.selectFromTicketStatusDrpdwn(value);
        commonPageObjectsHelper.waitForOvelayToBeGone();
    }

    @Step("Click on the first of the View button on the list")
    public void clickOnTheFirstOfTheViewButtonInTheList(){
        List<WebElement> editLogs = ticketsPage.getViewTickets();
        Assume.assumeTrue(editLogs.size()>0);
        List<Map<Object, String>> dataList = rowsFrom(ticketsPage.getResultTable());
        Map<Object, String> content = dataList.get(0);
        selectedTicketNumber = content.get("Ticket Number");
        selectedRequestorName = content.get("Requestor Name");
        editLogs.get(0).click();
        commonPageObjectsHelper.waitForOvelayToBeGone();
    }

    @Step("Deny the ticket")
    public void denyTheTicket(){
        ticketsPage.clickDenyBtn();
    }

    @Step("Click No on Deny Ticket? confirmation message")
    public void clickNoOnDenial(){
        ticketsPage.clickNoOnMessageModal();
    }

    @Step("Click Yes on Deny Ticket? confirmation message")
    public void clickYesOnDenial(){
        ticketsPage.clickYesOnMessageModal();
    }

    @Step("Click Back to Tickets link")
    public void clickBackToTicketsLink(){
        ticketsPage.clickBackToTickets();
        commonPageObjectsHelper.waitForOvelayToBeGone();
    }

    @Step("Verify displayed page is of the selected ticket")
    public void checkThatCorrectPageIsDisplayedForSelectedTicket(){
        assert ticketsPage.getPageHeaderTextDisplay().contains(selectedTicketNumber);
    }

    @Step("Search the ticket")
    public void searchTheTicket(){
       ticketsPage.searchTheTicket(selectedTicketNumber);
        commonPageObjectsHelper.waitForOvelayToBeGone();
    }

    @Step("Verify ticket status")
    public void checkThatTicketStatusIsSameAsExpected(String status){
        assert ticketsPage.getTheTicketStatus().equalsIgnoreCase(status);
    }

    @Step("Verify ticket details")
    public void checkThatTicketDetailsDisplayedIsCorrect(String status){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ticketsPage.getTicketDetailsStatus()
                .equalsIgnoreCase(status));

        if(status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Denied")){
            softly.assertThat(!ticketsPage.getTicketDetailsUpdatedBy().trim().isEmpty());
        }else{
            if(firstApprover != null){
                softly.assertThat(ticketsPage.getTicketDetailsUpdatedBy().equalsIgnoreCase(firstApprover));
            }else if(!status.equalsIgnoreCase("Ticket Expired")){
                softly.assertThat(ticketsPage.getTicketDetailsUpdatedBy().trim().isEmpty());
            }
        }

        if(status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Denied")){
            softly.assertThat(!ticketsPage.getTicketDetailsUpdatedDate().trim().isEmpty());
        }else{
            if(updatedDate != null){
                softly.assertThat(ticketsPage.getTicketDetailsUpdatedDate().equalsIgnoreCase(updatedDate));
            }else if(!status.equalsIgnoreCase("Ticket Expired")){
                softly.assertThat(ticketsPage.getTicketDetailsUpdatedDate().trim().isEmpty());
            }
        }

        if(userAuthenticate.getEmployeeLevel() > 1 ) {

            softly.assertThat(ticketsPage.isApproveBtnDisplayed());
            softly.assertThat(ticketsPage.isDenyBtnDisplayed());
        }else{
            if(status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Denied") ||
                    status.equalsIgnoreCase("Ticket Expired")){
                softly.assertThat(!ticketsPage.isApproveBtnDisplayed());
                softly.assertThat(!ticketsPage.isDenyBtnDisplayed());

            }
        }
        softly.assertAll();
    }

    @Step("Check that the logs displayed for the ticket are correct")
    public void checkThatTicketLogEntriesAreCorrect() {
        String webserviceEndpoint = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");

        List<String> entriesFromAPI =
                apiHelper.getTicketDetailsGivenTicketNumber
                        (webserviceEndpoint, selectedTicketNumber, userAuthenticate.getAuthenticationToken());

        //get an ArrayList of the Values on the screen
        List<String> entriesFromScreen = myLogsPage.getAllLogEntriesFromScreen();

        Collections.sort(entriesFromAPI);
        Collections.sort(entriesFromScreen);
        assert entriesFromAPI.equals(entriesFromScreen);

    }

    @Step("Keep the approver name and date")
    public void keepTheApproverNameAndDate(){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        firstApprover = userAuthenticate.getFullName();
        updatedDate = ticketsPage.getUpdatedDateOfFirstTicketInTheList();
   }

}
