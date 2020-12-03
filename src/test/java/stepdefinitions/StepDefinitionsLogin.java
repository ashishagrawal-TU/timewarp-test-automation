package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import steps.NavigationBarSteps;
import steps.StepsLogin;
import testdataobjects.EmployeeProfile;
import testdataobjects.UserAuthenticate;

import java.util.HashMap;

public class StepDefinitionsLogin {
   HashMap<String, EmployeeProfile> employeeList;
    EmployeeProfile omuser;

    @Steps
    StepsLogin loginSteps;

    @Steps
    NavigationBarSteps navigationBarSteps;

    @Given("User access the Time Warp Login page")
    public void userAccessTheTimeWarpLoginPage() {
        loginSteps.theTimeWarpLoginPage();
    }

    @Given("User instantiate Google Chrome Incognito mode")
    public void userInstantiateGoogleChromeIncognitoMode() {
        loginSteps.OpenChromeIncognitoMode();
    }

    @When("Time Warp Login page gets Open")
    public void timeWarpLoginPageGetsOpen() throws InterruptedException {
        loginSteps.OpenTimewarpInChromeIncognito();
    }

    @Then("Verify Timewarp page is not accessible and show Error message")
    public void VerifyTimewarpPageNotAccessible(){
        loginSteps.VerifyTimewarpInChromeIncognito();
    }

    @Given("User instantiate other than chrome browser")
    public void userInstantiateOtherThanChromeBrowser() {
        loginSteps.OpenOtherBrowser();
    }

    @Then("Verify Timewarp page is not accessible and show Error message for other browsers")
    public void VerifyTimewarpPageNotAccessibleOtherBrowser(){
        loginSteps.VerifyTimewarpInOtherBrowser();
    }

    @When("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginWithAnd(String username, String password) throws Throwable {
        loginSteps.enterUsernameAndPassword(username, password);
        loginSteps.clickTheSignInBtn();
    }

    @When("^An operations manager user logs in$")
    public void anOMUserLogsIn() throws InterruptedException {
        omuser = loginSteps.UserLogsIn("OM");
   }

    @When("A team mate user logs in")
    public void aTeamMateUserLogsIn() throws InterruptedException {
        omuser = loginSteps.UserLogsIn("TM");
     }

    @When("A team Leader user logs in")
    public void aTeamLeaderUserLogsIn() throws InterruptedException {
        omuser = loginSteps.UserLogsIn("TL");
    }

    @And("^Name displayed on the navigation bar is of the user$")
    public void nameDisplayedOnTheNavigationBar() {
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String namedisplay = userAuthenticate.getFullName();
        navigationBarSteps.verifyNameDisplayed(namedisplay);
    }

}
