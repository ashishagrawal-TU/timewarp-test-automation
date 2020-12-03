package pageobjects;

import com.paulhammant.ngwebdriver.ByAngular;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class MyTicketsPage extends PageObject {

    @FindBy(css = "table#tableResult")
    private WebElementFacade resultTable;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//button[@class='btn btn-primary']"),})
    List<WebElement> viewTickets;

    @FindBy(xpath = "//button[contains(@class,'btn-success')]")
    private WebElementFacade approveBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and text()='YES']")
    private WebElementFacade approveTicketModalYesBtn;

    @FindBy(css = "button.btn.btn-success.btn-lg")
    private WebElementFacade ticketApprovedModalOKBtn;

    @FindBy(css = "select#ticketStatus")
    private WebElementFacade ticketStatusDrpDwn;

    public void verifyMyTicketsPageIsAvailable(){
        assert find(ByAngular.binding("Tickets")).isCurrentlyVisible();
    }

    public void clickFirstTicketInViewTicketList(){
        waitFor(ExpectedConditions.elementToBeClickable(viewTickets.get(0)));
        viewTickets.get(0).click();
        withTimeoutOf(1, MINUTES)
                .waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.overlay__inner")));
    }

    public void clickApproveBtn(){
        approveBtn.click();
    }

    public void clickYesOnApproveTicketModal(){
        approveTicketModalYesBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickOnTicketApprovedModalOKBtn(){
        ticketApprovedModalOKBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void selectFromTicketStatusDrpdwn(String value){
        ticketStatusDrpDwn.click();
        setValueInTicketStatus(value);
    }

    private void setValueInTicketStatus(String value) {
        WebElementFacade dropdownValue =
                find(By.xpath("//select[@id='ticketStatus']/option[@label='" + value + "']"));
        dropdownValue.waitUntilVisible().then().click();
    }
}
