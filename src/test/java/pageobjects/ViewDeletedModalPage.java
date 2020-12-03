package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;

import java.util.List;

public class ViewDeletedModalPage extends PageObject {

    @FindBy(css="#tableDeletedLogs")
    public WebElementFacade viewDeletedModal;

    @FindAll({ @org.openqa.selenium.support.FindBy(css="tr.odd"),})
    List<WebElement> viewDeletedLists;

    public int getViewDeletedListRowCount(){
        int cnt = viewDeletedLists.size();
        if(cnt == 1){
            String rowText = viewDeletedLists.get(0).getText();
            if(rowText.equalsIgnoreCase("No data available in table")){
                cnt = 0;
            }
        }
        return cnt;
    }

}
