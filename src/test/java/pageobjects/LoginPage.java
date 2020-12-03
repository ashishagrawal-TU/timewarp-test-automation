package pageobjects;

import com.paulhammant.ngwebdriver.ByAngular;
import common.APIHelper;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testdataobjects.UserAuthenticate;

public class LoginPage extends PageObject{

    private EnvironmentVariables environmentVariables;

     public void enterUserName(String username){
         if(find(ByAngular.model("$ctrl.username")).isCurrentlyVisible())
             find(ByAngular.model("$ctrl.username")).sendKeys(username);

    }

    public void enterPassword(String password){
        if(find(ByAngular.model("$ctrl.password")).isCurrentlyVisible())
            find(ByAngular.model("$ctrl.password")).sendKeys(password);
    }

    public void clickSignInBtn(){
        waitForSignInBtnToBeClickable();
        find(ByAngular.buttonText("Sign in")).click();
        try {
            waitForAngularRequestsToFinish();
        }catch (ScriptTimeoutException ste){
            this.open();
        }
   }

    private void waitForSignInBtnToBeClickable() {
       WebElementFacade signInBtn = find(ByAngular.buttonText("Sign in"));
        waitForCondition().until(
                ExpectedConditions.elementToBeClickable(signInBtn)
        );
    }

    public UserAuthenticate getUserAuthenticate(String username, String password){
        UserAuthenticate userAuthenticate;
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        APIHelper apiHelper = new APIHelper();
        userAuthenticate  = apiHelper.getLoginUserInfo(webserviceEndpoint, username, password);
        return userAuthenticate;
    }

}
