package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.NavigationBarSteps;

public class NavigationBarStepDefinitions {


    @Steps
    NavigationBarSteps navigationBarSteps;

    @And("My Tickets tab is available")
    public void myTicketsTabIsAvailable() {
        navigationBarSteps.checkTicketsBtnIsAvailable();
    }

    @And("My Logs tab is available")
    public void myLogsTabIsAvailable() {
        navigationBarSteps.checkMyLogsBtnIsAvailable();
    }

    @And("Employee tab is available")
    public void employeeTabIsAvailable() {
        navigationBarSteps.checkEmployeeBtnIsAvailable();
    }

    @Then("User clicks the Employee tab")
    public void userClicksTheEmployeeTab() {
        navigationBarSteps.clickOnEmployeeBtn();
    }

    @Then("User clicks the Tickets tab")
    public void userClicksTheTicketsTab() {
        navigationBarSteps.clickOnTickets();
    }

}
