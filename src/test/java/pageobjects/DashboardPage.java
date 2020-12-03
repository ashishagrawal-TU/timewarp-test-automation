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

public class DashboardPage extends PageObject {

    @FindBy(css = "b.ng-binding")
    private WebElementFacade dashboardTabHeader;

    @FindBy(css = "h1.page-header.ng-scope")
    private WebElementFacade dashboardPageHeader;

    @FindBy(css = "b.ng-binding")
    private WebElementFacade dashboardUserNameHeader;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div[1]/div[2]/div[1]/div/div[1]/span/span[1]")
    private WebElementFacade campaignDropDown;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div[1]/div[2]/div[2]/div/div[1]/span/span[1]")
    private WebElementFacade teamLeaderDropDown;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div[1]/div[2]/div[3]/div/div[1]/span/span[1]")
    private WebElementFacade teamMateDropDown;

    @FindBy(xpath="//button[@title='Refresh']")
    private WebElementFacade refreshButton;

    @FindBy(xpath="//*[@id=\"pads\"]/div/ui-view/div/div[2]/ul/li[1]/a")
    private WebElementFacade activeUsersTab;

    @FindBy(xpath="//*[@id=\"DataTables_Table_10_wrapper\"]/div[2]/div[1]/div/table")
    private WebElementFacade activeUserResultTable;

    @FindBy(xpath="//*[@id=\"pads\"]/div/ui-view/div/div[2]/ul/li[2]/a")
    private WebElementFacade activeLogsTab;

    @FindBy(xpath="//*[@id=\"DataTables_Table_11_wrapper\"]/div[2]/div[1]/div/table")
    private WebElementFacade activeLogsResultTable;




    @FindBy(css = "h1.page-header.ng-scope")
    private WebElementFacade pageHeader;

    @FindBy(css = "a.dropdown-toggle")
    private WebElementFacade userNameHeader;

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
