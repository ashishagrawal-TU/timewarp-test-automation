package steps;

import common.APIHelper;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.WebElement;
import pageobjects.EmployeePage;
import testdataobjects.UserAuthenticate;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

public class EmployeeSteps {

    EmployeePage employeePage;
    APIHelper apiHelper = new APIHelper();
    private EnvironmentVariables environmentVariables;
    String selectedEmpFullName;
    String selectedEmpEmployeeId;

    @Step("Verify Employee landing page is displayed")
    public void verifyEmployeeLandingPage() {
        assert (employeePage.doesPageHeaderContainsText("My Bucket") || employeePage.doesPageHeaderContainsText("Employee"));
    }

    @Step("Verify that the list displayed is the same with that returned by the API")
    public void verifyEmployeeListDisplayedForTheLoggedInUserIsCorrect(){

        UserAuthenticate userAuthenticate
                = Serenity.sessionVariableCalled("userAuthenticate");
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");

        List<Map<Object, String>> emplistFromScreen =  employeePage.getSearchResults();
        List<Map<Object, String>> emplistFromAPI = apiHelper.getEmployeesUnderASupervisor(webserviceEndpoint,
                userAuthenticate.getAuthenticationToken(), userAuthenticate.getEmployeeID());

          assert emplistFromScreen.equals(emplistFromAPI);

    }

    @Step("Click on any of the Edit Logs button on the list")
    public void clickOnAnyOfTheEditLogsButtonInTheList(){
        List<WebElement> editLogs = employeePage.getListOfEditLogs();
        Assume.assumeTrue(editLogs.size()>0);
        Random rand = new Random();
        int int_random = 0;
        if(editLogs.size()>1) {
            int upperbound = editLogs.size() - 1;
            int_random = rand.nextInt(upperbound);
        }
        List<Map<Object, String>> dataList = rowsFrom(employeePage.getResultTable());
        Map<Object, String> content = dataList.get(int_random);
        selectedEmpEmployeeId = content.get(2);
        selectedEmpFullName = content.get(3);
        editLogs.get(int_random).click();
        employeePage.waitForAngularRequestsToFinish();
    }

    @Step("Verify that title above the page displays the name of the selected employee")
    public void verifyPageTitleContainsTheEmployeeName(){
        Assert.assertTrue(employeePage.doesPageHeaderContainsText(selectedEmpFullName));
     }

    public String getSelectedEmpEmployeeId() {
        return selectedEmpEmployeeId;
    }

}
