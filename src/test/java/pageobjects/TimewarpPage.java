package pageobjects;

import com.paulhammant.ngwebdriver.ByAngular;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import steps.StepsLogin;
import testdataobjects.EmployeeProfile;

import java.util.HashMap;

public class TimewarpPage extends PageObject {

    @FindBy(className = "btn btn-default")
    public WebElementFacade timewarpPopupMessageCloseButton;

    @FindBy(xpath = "//div[contains(text(),'All activities are being monitored and are being l')]")
    private WebElementFacade timewarpPopupMessageText;

    @FindBy(className = "bootstrap-dialog-header")
    private WebElementFacade timewarpRecentEndShiftDialogBox;

    @FindBy(xpath = "//button[@title='Yes']")
    private WebElementFacade timewarpRecentEndShiftDialogYesButton;

    @FindBy(xpath = "//button[@title='No']")
    private WebElementFacade timewarpRecentEndShiftDialogNoButton;

    @FindBy(xpath = "//b[contains(text(),'Time Warp')]")
    private WebElementFacade timewarpPageHeader;

    @FindBy(xpath = "//h1[@class='page-header ng-scope']")
    private WebElementFacade pageHeader;

    @FindBy(css = "b.ng-binding")
    private WebElementFacade userNameHeader;

    @FindBy(css = "h2.panel-title")
    private WebElementFacade timePanelHeader;

    @FindBy(css = "timer.ng-binding.ng-isolate-scope")
    private WebElementFacade timerClock;

    @FindBy(xpath = "//*[@id=\"activityTypeBox\"]/tbody/tr[1]/td")
    private WebElementFacade activityTypeProductivity;

    @FindBy(xpath = "//*[@id=\"activityTypeBox\"]/tbody/tr[17]/td/span")
    private WebElementFacade activityTypeEndShift;

    @FindBy(xpath = "//button[@id='submitActivity']")
    private WebElementFacade startActivityButton;

    @FindBy(xpath = "//a[@class='ng-binding']")
    private WebElementFacade activityPageTableHeader;

    @FindBy(xpath = "//td[@class='warning'][contains(text(),'Start Shift')]")
    private WebElementFacade activityPageStartShift;

    @FindBy(xpath = "//body//div[@id='AuxTool']//div//div//div//div//div//tr[2]//td[1]")
    private WebElementFacade lastActivityEndShift;

    @FindBy(xpath = "//table[@id='activityTypeBox']//tr[17]//td[1]")
    private WebElementFacade selectTheEndShiftActivity;

    @FindBy(xpath = "//*[@id=\"AuxTool\"]/div[2]/div/div[1]")
    private WebElementFacade resultTable;

    @FindBy(xpath = "//table[@id='activityTypeBox']//tr[17]//td[1]")
    private WebElementFacade selectTheEndShiftActivityTL;

    @FindBy(xpath = "//button[@id='submitActivity']")
    private WebElementFacade startActivityButtonTL;

    @FindBy(xpath = "//td[@class='warning'][contains(text(),'Start Shift')]")
    private WebElementFacade activityPageStartShiftTL;

    @FindBy(xpath = "//td[contains(text(),'End Shift')]")
    private WebElementFacade lastActivityEndShiftTL;

    TWLoginPage twLoginPage;
    HashMap<String, EmployeeProfile> employeeList;
    StepsLogin loginSteps;
    EmployeeProfile omuser;

    public String verifyTimewarpPopupMessageButton() {
        String actualTextCompare = timewarpPopupMessageText.getText();
        return actualTextCompare;
    }

    public void verifyActivityPageHeader() {
        find(ByAngular.buttonText("Close")).click();
        if (find(ByAngular.buttonText("YES")).isVisible()){
            find(ByAngular.buttonText("YES")).click();
        }
        Assert.assertEquals("Verify Timewarp Activity Page", "Time Warp", timewarpPageHeader.getText());
        Assert.assertEquals("Verify Timewarp Activity Page", "Time Warp", pageHeader.getText());
    }

    public void verifyCurrentActivityTable() {
        Assert.assertEquals("Verify first activity is Start Shift ", "Start Shift", activityPageStartShift.getText());
        Assert.assertEquals("Verify Last Activity was End Shift ", "End Shift", lastActivityEndShift.getText());
        selectTheEndShiftActivity.click();
        startActivityButton.click();
        if (find(ByAngular.buttonText("NO")).isVisible()) {
            find(ByAngular.buttonText("NO")).click();
        }
    }

    public void verifyStartShiftAndCurrentActivityTable() {
        Assert.assertEquals("Verify first activity is Start Shift ", "Start Shift", activityPageStartShift.getText());
        Assert.assertEquals("Verify Last Activity was End Shift ", "End Shift", lastActivityEndShift.getText());
        selectTheEndShiftActivityTL.click();
        startActivityButtonTL.click();
        if (find(ByAngular.buttonText("NO")).isVisible()) {
            find(ByAngular.buttonText("NO")).click();
        }
    }

}