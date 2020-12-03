package steps;

import common.CSVReader;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import pageobjects.LoginPage;
import testdataobjects.EmployeeProfile;
import testdataobjects.UserAuthenticate;

import java.util.HashMap;

public class LoginSteps {

    LoginPage loginpage;
    EmployeeProfile omuser;

    HashMap<String, EmployeeProfile> employeeList;

    @Step("Open the Time Warp Editor login page")
    public void theTimeWarpEditorHomePage() {
        if(employeeList==null){
            employeeList = CSVReader.readAndLoadCSVData();
        }
        loginpage.getDriver().manage().deleteAllCookies();
        loginpage.open();
        loginpage.getDriver().navigate().refresh();
    }

    @Step("Enter username and password")
    public void enterUsernameAndPassword(String username, String password) {
        loginpage.enterUserName(username);
        loginpage.enterPassword(password);
    }

    @Step("Click the Sign In button")
    public void clickTheSignInBtn() {
        loginpage.clickSignInBtn();
        //loginpage.getDriver().navigate().refresh();
    }

    public EmployeeProfile UserLogsIn(String user){
        omuser = employeeList.get(user);
        Serenity.setSessionVariable("omuser").to(omuser);
        Serenity.setSessionVariable("EmpNo").to(omuser.getEmpno());
        UserAuthenticate userAuthenticate = loginpage.getUserAuthenticate(omuser.getUsername(), omuser.getPassword());
        Serenity.setSessionVariable("userAuthenticate").to(userAuthenticate);
        String timezone = userAuthenticate.getTimeZone();
        Serenity.setSessionVariable("TimeZone").to(timezone);

        enterUsernameAndPassword( omuser.getUsername(), omuser.getPassword());
        clickTheSignInBtn();
        return omuser;
   }

}
