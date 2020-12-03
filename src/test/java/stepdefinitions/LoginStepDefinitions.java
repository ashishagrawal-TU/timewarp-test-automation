package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import steps.LoginSteps;
import steps.NavigationBarSteps;
import testdataobjects.EmployeeProfile;
import testdataobjects.UserAuthenticate;

import java.util.HashMap;

public class LoginStepDefinitions {
   HashMap<String, EmployeeProfile> employeeList;
    EmployeeProfile omuser;

    @Steps
    LoginSteps loginSteps;

    @Steps
    NavigationBarSteps navigationBarSteps;

    @Given("User access the Time Warp Editor Home page")
    public void userAccessTheTimeWarpEditorHomePage() {
        loginSteps.theTimeWarpEditorHomePage();
    }

    @When("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginWithAnd(String username, String password) throws Throwable {
        loginSteps.enterUsernameAndPassword(username, password);
        loginSteps.clickTheSignInBtn();
    }

    @When("^An operations manager user logs in$")
    public void anOMUserLogsIn() {
        omuser = loginSteps.UserLogsIn("OM");
   }


    @When("A team mate user logs in")
    public void aTeamMateUserLogsIn() {
        omuser = loginSteps.UserLogsIn("TM");
     }

    @And("^Name displayed on the navigation bar is of the user$")
    public void nameDisplayedOnTheNavigationBar() {
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String namedisplay = userAuthenticate.getFullName();
        navigationBarSteps.verifyNameDisplayed(namedisplay);
    }

    @When("A team leader user logs in")
    public void aTeamLeaderUserLogsIn() {
        omuser = loginSteps.UserLogsIn("TL");
    }

    @When("A director user logs in")
    public void aDirectorUserLogsIn() {
        omuser = loginSteps.UserLogsIn("TD");
    }

    @When("A vice president user logs in")
    public void aVicePresidentUserLogsIn() {
        omuser = loginSteps.UserLogsIn("VP");
    }
}
