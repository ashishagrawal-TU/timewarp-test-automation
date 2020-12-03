package steps;

import net.thucydides.core.annotations.Step;
import pageobjects.CommonPageObjectsHelper;
import pageobjects.NavigationBarPage;

public class NavigationBarSteps {

    NavigationBarPage navigationBarPage;
    CommonPageObjectsHelper commonPageObjectsHelper;

    @Step("Verify Name displayed on the upper right is correct")
    public void verifyNameDisplayed(String namedisplay) {
        assert navigationBarPage.getNameDisplay().contains(namedisplay);
    }


    @Step("Verify My Logs button is available")
    public void checkMyLogsBtnIsAvailable() {
        navigationBarPage.verifyMyLogsPageIsAvailable();
    }

    @Step("Verify Tickets button is available")
    public void checkTicketsBtnIsAvailable() {
        navigationBarPage.verifyTicketsBtnIsAvailable();
    }

    @Step("Verify Employee button is available")
    public void checkEmployeeBtnIsAvailable() {
        navigationBarPage.verifyEmployeeBtnIsAvailable();
    }

    @Step("Logout")
    public void logOut() {
         navigationBarPage.clickCaret();
         navigationBarPage.clickLogOutBtn();
    }

    @Step("Click on Tickets button")
    public void clickOnTickets() {
        navigationBarPage.clickOnTicketsBtn();
        commonPageObjectsHelper.waitForOvelayToBeGone();
    }

    public boolean checkIfAUserIsLoggedIn(){
        return navigationBarPage.isUserLogin();
    }

    @Step("Click on the Employee button")
    public void clickOnEmployeeBtn() {
        navigationBarPage.clickOnEmployeeBtn();
    }


}
