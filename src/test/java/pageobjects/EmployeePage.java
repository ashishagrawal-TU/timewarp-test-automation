package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

public class EmployeePage extends PageObject {

    @FindBy(css = "h1.page-header.ng-binding")
    private WebElementFacade pageHeader;

    @FindBy(css = "table#tableResult")
    private WebElementFacade resultTable;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//button[normalize-space(text())='Edit Logs']"),})
    private List<WebElement> editLogs;

    @FindBy(xpath = "//div[@class='overlay']")
    private WebElementFacade overlay;

    public boolean doesPageHeaderContainsText(String title){
       return pageHeader.getText().contains(title);
    }

    public List<Map<Object, String>> getSearchResults() {
        List<Map<Object, String>> dataList = rowsFrom(resultTable);
        Collections.sort(dataList, new Comparator<Map<Object, String>>() {
            @Override
            public int compare(Map<Object, String> map1, Map<Object, String> map2) {
                return map1.get(2).compareTo(map2.get(2));
            }
        });
        return dataList;
     }

     public List<WebElement> getListOfEditLogs(){
        return editLogs;
     }

    public WebElementFacade getResultTable() {
        return resultTable;
    }

    public void waitForOvelaytoBeInvisible(){
        if(overlay.isPresent() && overlay.isVisible()){
            withTimeoutOf(Duration.ofMinutes(2)).waitFor(ExpectedConditions.invisibilityOf(overlay));
        }
    }


 }
