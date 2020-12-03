package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import steps.TimewarpSteps;

public class TimewarpStepDefinition {

    @Steps
    TimewarpSteps timewarpSteps;

    @Then("User lands on the Home page")
    public void userLandsOnTheHomePage() {
        timewarpSteps.TimewarpHomePage();
    }

    @And("Verify User is on activity page")
    public void verifyUserIsOnActivityPage() {
        timewarpSteps.verifyActivityPage();
    }

    @And("Verify that Start Shift has been recorded at the Current Activity table")
    public void verifyThatStartShiftHasBeenRecordedAtTheCurrentActivityTable() {
        timewarpSteps.verifyStartShiftHasBeenRecorded();
    }

    @And("Verify that Start Shift should be started and be recorded at the Current Activity table")
    public void verifyThatStartShiftShouldBeStartedAndBeRecordedAtCurrentActivityTable() {
        timewarpSteps.verifyStartShiftIsRecordedAndShow();
    }

    @Given("Verify User click the Yes button")
    public void verifyUserClickTheYesButton() {
        timewarpSteps.verifyYesButtonIsClicked();
    }

}

