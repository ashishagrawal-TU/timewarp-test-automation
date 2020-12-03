package pageobjects;

import com.paulhammant.ngwebdriver.ByAngular;
import common.APIHelper;
import common.DBHelper;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testdataobjects.UserAuthenticate;

public class TWLoginPage extends PageObject{

    String chromeIncognitoErrorMessage = "Time Warp is not accessible in Incognito";
    String otherBrowserErrorMessage = "Invalid browser type, Time Warp is only accessible in Google Chrome.";

    @FindBy(xpath = "//div[@class='ng-scope']")
    private WebElementFacade userGSignIn;

    @FindBy(xpath = "//div[contains(text(),'OTP Sign in')]")
    private WebElementFacade userOTPSignIn;

    @FindBy(xpath = "//input[@placeholder='Enter Task Us Email Address']")
    private WebElementFacade enterEmailAddressInputBox;

    @FindBy(xpath = "//button[@id='otpLogin']//div")
    private WebElementFacade clickEmailAddressButton;

    @FindBy(xpath = "//input[@placeholder='Enter your code here to Login']")
    private WebElementFacade otpInputTextBox;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg btn-block']")
    private WebElementFacade OTPSignInClick;

    @FindBy(xpath = "/html/body/ui-view/google-login/div/div/div/div/form/div[3]/center/strong")
    private WebElementFacade chromeIncognitoModeError;

    private EnvironmentVariables environmentVariables;
    DBHelper dbHelper = new DBHelper();

    public void VerifyUserGSignIn(){
        userGSignIn.isVisible();
        userGSignIn.isEnabled();
    }

    public void VerifyUserOTPSignIn(){
        userOTPSignIn.isVisible();
        userOTPSignIn.isEnabled();
    }

    public void VerifyEnterEmailAddressPage(){
        userOTPSignIn.click();
        waitForEmailIDPage();
        enterEmailAddressInputBox.isVisible();
        enterEmailAddressInputBox.isEnabled();
    }

    public void EnterEmailAddress(String userName){
        enterEmailAddressInputBox.sendKeys(userName);
        clickEmailAddressButton.click();
        waitForOTPInputPage();
    }

    private void waitForEmailIDPage() {
        waitForCondition().until(
                ExpectedConditions.elementToBeClickable(enterEmailAddressInputBox)
        );
    }

    private void waitForOTPInputPage() {
        waitForCondition().until(
                ExpectedConditions.elementToBeClickable(otpInputTextBox)
        );
    }

    public boolean VerifyChromeIncognitoModeError(){
        boolean textCompare;
        return chromeIncognitoErrorMessage.equalsIgnoreCase(chromeIncognitoModeError.getText());
    }

    public boolean VerifyOtherBrowserError(){
        boolean textCompare;
        return otherBrowserErrorMessage.equalsIgnoreCase(chromeIncognitoModeError.getText());
    }

    public void otpSignIn(){
        waitForOTPSignInBtnToBeClickable();
        find(ByAngular.buttonText("OTP Sign in")).click();
        try {
            waitForAngularRequestsToFinish();
            find("").sendKeys("team.mate@taskus.com");
            find("").click();
            Thread.sleep(5000);

            String OTP;
            OTP = dbHelper.getOTPGivenEID("8888001");
            enterOTPSignIn(OTP);

        }catch (ScriptTimeoutException | InterruptedException ste){
            this.open();
        }
    }

    public void enterOTPSignIn(String OTP) throws InterruptedException {
        otpInputTextBox.sendKeys(OTP);
        OTPSignInClick.click();
        Thread.sleep(5000);
    }


    private void waitForOTPSignInBtnToBeClickable() {
        WebElementFacade signInBtn = find(ByAngular.buttonText("OTP Sign in"));
        waitForCondition().until(
                ExpectedConditions.elementToBeClickable(signInBtn)
        );
    }

     public void enterUserName(String username){
         if(find(ByAngular.model("$ctrl.username")).isCurrentlyVisible())
             find(ByAngular.model("$ctrl.username")).sendKeys(username);

    }

    public void enterPassword(String password){
        if(find(ByAngular.model("$ctrl.password")).isCurrentlyVisible())
            find(ByAngular.model("$ctrl.password")).sendKeys(password);
    }

    public void clickSignInBtn(){
        //waitForSignInBtnToBeClickable();
        find(ByAngular.buttonText("Sign in")).click();
        try {
            waitForAngularRequestsToFinish();
        }catch (ScriptTimeoutException ste){
            this.open();
        }
   }

    public UserAuthenticate getUserAuthenticate(String username){
        UserAuthenticate userAuthenticate;
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        APIHelper apiHelper = new APIHelper();
        userAuthenticate  = apiHelper.getLoginUserInfo(webserviceEndpoint, username);
        return userAuthenticate;
    }

}
