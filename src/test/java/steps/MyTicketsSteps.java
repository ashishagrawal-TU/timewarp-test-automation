package steps;

import net.thucydides.core.annotations.Step;
import pageobjects.MyTicketsPage;

public class MyTicketsSteps {

    MyTicketsPage myTicketsPage;

    @Step("Click the first ticket in the list")
    public void clickFirstValueInList(){
        myTicketsPage.clickFirstTicketInViewTicketList();
    }

    @Step("Approve the ticket")
    public void approveTheTicket(){
        myTicketsPage.clickApproveBtn();
    }

    @Step("Click Yes on Approve Ticket? confirmation message")
    public void clickYesOnApproval(){
        myTicketsPage.clickYesOnApproveTicketModal();
    }

    @Step("Click OK on the ticket approved message")
    public void clickOKOnTicketApprovedMessage(){
        myTicketsPage.clickOnTicketApprovedModalOKBtn();
    }

    @Step("Select a value from ticket status")
    public void selectAValueFromTicketStatus(String value){
        myTicketsPage.selectFromTicketStatusDrpdwn(value);
    }

}
