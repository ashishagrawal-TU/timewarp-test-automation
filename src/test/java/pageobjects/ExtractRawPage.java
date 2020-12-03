package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ExtractRawPage extends PageObject {

    @FindBy(xpath = "//a[@title='Extract Raw']")
    private WebElementFacade extractRawTabHeader;

    @FindBy(xpath = "//a[@header='Extract Logs']")
    private WebElementFacade extractLogsPageHeader;

    @FindBy(css = "b.ng-binding")
    private WebElementFacade extractLogsUserNameHeader;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div/div[2]/div[1]/p/input")
    private WebElementFacade extractLogsFromDate;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div/div[2]/div[2]/p/input")
    private WebElementFacade extractLogsToDate;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div/div[2]/div[3]/div/div[1]/span/span[1]")
    private WebElementFacade extractLogsCampaignDropDown;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div/div[2]/div[4]/div/div[1]/span/span[1]")
    private WebElementFacade extractLogsTeamLeaderDropDown;

    @FindBy(xpath = "//*[@id=\"pads\"]/div/ui-view/div/div[1]/div/div[2]/div[5]/div/div[1]/span/span[1]")
    private WebElementFacade extractLogsTeamMateDropDown;

    @FindBy(xpath = "//button[@title='Generate']")
    private WebElementFacade generateButton;

    @FindBy(xpath = "//button[@title='CSV']")
    private WebElementFacade CSVButton;

    @FindBy(xpath = "//*[@id=\"DataTables_Table_11_wrapper\"]/div[2]/div[1]/div/table")
    private WebElementFacade activeLogsResultTable;
}

