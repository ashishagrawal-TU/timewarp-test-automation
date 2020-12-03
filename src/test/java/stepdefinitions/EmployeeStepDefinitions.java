package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import steps.EmployeeSteps;
import steps.MyLogSteps;
import testdataobjects.UserAuthenticate;

public class EmployeeStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Steps
    EmployeeSteps employeeSteps;

    @Steps
    MyLogSteps myLogSteps;

    @Then("Employee Landing page is displayed")
    public void employeeLandingPageIsDisplayed() {
        employeeSteps.verifyEmployeeLandingPage();
    }

    @Then("Verify the list of employees under the logged in user is correct")
    public void verifyTheListOfEmployeesUnderTheLoggedInUserIsCorrect() {
        employeeSteps.verifyEmployeeListDisplayedForTheLoggedInUserIsCorrect();
    }

    @And("Click on any of the Edit Logs button displayed for an employee")
    public void clickOnAnyOfTheEditLogsButtonDisplayedForAnEmployee() {
        employeeSteps.clickOnAnyOfTheEditLogsButtonInTheList();
    }

   @Then("Verify the page title displays the name of the selected employee")
    public void verifyThePageTitleDisplaysTheNameOfTheSelectedEmployee() {
        employeeSteps.verifyPageTitleContainsTheEmployeeName();
    }

    @And("Activity logs displayed for the selected employee is correct")
    public void activityLogsDisplayedForTheSelectedEmployeeIsCorrect() {
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        userAuthenticate.setEmployeeID(employeeSteps.getSelectedEmpEmployeeId());
        myLogSteps.verifyTheEntriesDisplayedAgainstTheAPI(webserviceEndpoint, userAuthenticate);
    }


}
