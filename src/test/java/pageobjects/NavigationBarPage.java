package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class NavigationBarPage extends PageObject {

    @FindBy(css = "a.dropdown-toggle>b.ng-binding")
    private WebElementFacade nameDisplay;

    @FindBy(xpath = "//b[@class='ng-binding' and normalize-space(text())='My Logs']")
    private WebElementFacade myLogsBtn;

    @FindBy(xpath = "//b[@class='ng-binding' and normalize-space(text())='Tickets']")
    private WebElementFacade ticketsBtn;

    @FindBy(xpath = "//b[@class='ng-binding' and normalize-space(text())='Employee']")
    private WebElementFacade employeeBtn;

    @FindBy(css = "span.caret")
    private WebElementFacade caret;

    @FindBy(xpath = "//a[@ui-sref='logout']")
    private WebElementFacade logoutBtn;

    @FindBy(css = "table#tableResult")
    private WebElementFacade resultTable;

    public String getNameDisplay() {
        return nameDisplay.getText();
    }

    public void verifyMyLogsPageIsAvailable() {
        assert myLogsBtn.isCurrentlyVisible();
        assert myLogsBtn.isClickable();
    }

    public void verifyTicketsBtnIsAvailable() {
        assert ticketsBtn.isCurrentlyVisible();
        assert ticketsBtn.isClickable();
    }

    public void verifyEmployeeBtnIsAvailable() {
        assert employeeBtn.isCurrentlyVisible();
        assert employeeBtn.isClickable();
    }

    public void clickCaret() {
        caret.click();
    }

    public void clickLogOutBtn() {
        logoutBtn.click();
    }

    public void clickOnTicketsBtn() {
        ticketsBtn.click();
        waitForAngularRequestsToFinish();
    }

    public boolean isUserLogin() {
        if (nameDisplay.isPresent() && nameDisplay.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    public void clickOnEmployeeBtn() {
        employeeBtn.click();
        waitForAngularRequestsToFinish();
    }


}

