package pageobjects;

import com.paulhammant.ngwebdriver.ByAngular;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.components.HtmlTable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;
import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

public class TicketsPage extends PageObject {

    @FindBy(css = "table#tableResult")
    WebElementFacade resultTable;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//button[@class='btn btn-primary']"),})
    List<WebElement> viewTickets;

    @FindBy(xpath = "//button[contains(@class,'btn-success')]")
    private WebElementFacade approveBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and text()='YES']")
    private WebElementFacade messageModalYesBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and text()='NO']")
    private WebElementFacade messageModalNoBtn;

    @FindBy(css = "button.btn.btn-success.btn-lg")
    private WebElementFacade ticketApprovedModalOKBtn;

    @FindBy(css = "select#ticketStatus")
    private WebElementFacade ticketStatusDrpDwn;

    @FindBy(xpath = "//button[contains(@class,'btn-danger')]")
    private WebElementFacade denyBtn;

    @FindBy(xpath = "//a[text()='Back to Tickets']")
    private WebElementFacade backToTickets;

    @FindBy(css = "h3")
    private WebElementFacade pageHeader;

    @FindBy(css = "span.label.label-info")
    private WebElementFacade ticketDetailStatus;

    @FindBy(css = "table#ticketDetails")
    WebElementFacade ticketDetailsTable;


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

    public void clickYesOnMessageModal(){
        messageModalYesBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickNoOnMessageModal(){
        messageModalNoBtn.click();
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
        dropdownValue.waitUntilVisible();
        dropdownValue.click();
        waitForAngularRequestsToFinish();
    }

    public List<WebElement> getViewTickets() {
        return viewTickets;
    }

    public WebElementFacade getResultTable() {
        return resultTable;
    }

    public void clickDenyBtn(){
       denyBtn.click();
    }

    public void clickBackToTickets(){
        backToTickets.click();
    }

    public String getPageHeaderTextDisplay(){
        return pageHeader.getText();
    }

    public void searchTheTicket(String ticketNum){
        find(ByAngular.model("$ctrl.ticketID")).sendKeys(ticketNum, Keys.ENTER);
    }

    public String getTheTicketStatus(){
        List<Map<Object, String>> dataList = rowsFrom(getResultTable());
        Map<Object, String> content = dataList.get(0);
        return content.get("Status");
    }

    public String getTicketDetailsStatus(){
        String retvalue = "";
        List<Map<Object, String>> tableRows =
                HtmlTable.withColumns("Detail","Value")
                        .readRowsFrom(ticketDetailsTable);

        for(int i=0;i<tableRows.size();i++){
            Map<Object, String> rowDtl = tableRows.get(i);
            if(rowDtl.get("Detail").equalsIgnoreCase("Status")){
                retvalue = rowDtl.get("Value");
                break;
            }
        }
        return retvalue;
    }

    public String getTicketDetailsUpdatedBy(){
        String retvalue = "";
        List<Map<Object, String>> tableRows =
                HtmlTable.withColumns("Detail","Value")
                        .readRowsFrom(ticketDetailsTable);

        for(int i=0;i<tableRows.size();i++){
            Map<Object, String> rowDtl = tableRows.get(i);
            if(rowDtl.get("Detail").equalsIgnoreCase("Updated By")){
                retvalue = rowDtl.get("Value");
                break;
            }
        }
        return retvalue;
    }

    public String getTicketDetailsUpdatedDate(){
        String retvalue = "";
        List<Map<Object, String>> tableRows =
                HtmlTable.withColumns("Detail","Value")
                        .readRowsFrom(ticketDetailsTable);

        for(int i=0;i<tableRows.size();i++){
            Map<Object, String> rowDtl = tableRows.get(i);
            if(rowDtl.get("Detail").equalsIgnoreCase("Updated Date")){
                retvalue = rowDtl.get("Value");
                break;
            }
        }
        return retvalue;
    }

    public boolean isApproveBtnDisplayed(){
        if(approveBtn.isPresent() && approveBtn.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isDenyBtnDisplayed(){
        if(denyBtn.isPresent() && denyBtn.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    public String getUpdatedDateOfFirstTicketInTheList(){
        List<Map<Object, String>> dataList = rowsFrom(getResultTable());
        Map<Object, String> rowDtl = dataList.get(0);
            return rowDtl.get("Updated Date");
    }

}
