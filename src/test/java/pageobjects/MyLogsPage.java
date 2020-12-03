package pageobjects;

import common.APIHelper;
import common.DBHelper;
import common.StringFormatters;
import common.TimeCalendar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import testdataobjects.UserAuthenticate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyLogsPage extends PageObject {

    @FindBy(css = "h1.page-header.ng-binding")
    private WebElementFacade pageHeader;

    @FindBy(css = "span.input-group-addon")
    private WebElementFacade calendarIcon;

    @FindBy(xpath = "//div[contains(@class, 'bootstrap-datetimepicker-widget')]")
    private WebElementFacade calendar;

    @FindBy(xpath = "//th[@class='picker-switch' and @title='Select Month']")
    private WebElementFacade calendarMonthYear;

    @FindBy(xpath = "//th[@class='prev']")
    private WebElementFacade calendarPreviousCaretChk;

    @FindBy(xpath = "//span[contains(@class, 'glyphicon-chevron-left') and @title='Previous Month']")
    private WebElementFacade calendarPreviousCaret;

    @FindBy(xpath = "//th[@class='next'])[0]")
    private WebElementFacade calendarNextCaretChk;

    @FindBy(xpath = "//span[contains(@class, 'glyphicon-chevron-right') and @title='Next Month']")
    private WebElementFacade calendarNextCaret;

   @FindBy(xpath="//span[contains(@class, 'glyphicon-screenshot')]")
    private WebElementFacade calendarTodayBtn;

    @FindBy(css="div.fc-center>h2")
    private WebElementFacade dateDisplayedOnCenter;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//td[@class='day' or @class='day weekend']"),})
    List<WebElement> enabledDates;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//th[contains(@class,'fc-day-header')]"),})
    List<WebElement> dateHeaders;

    @FindAll({ @org.openqa.selenium.support.FindBy(css = "a.fc-time-grid-event"),})
    List<WebElement> activities;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//td[@class='fc-axis fc-time fc-widget-content']/span"),})
    List<WebElement> timeIntervals;

    @FindBy(css="div.tooltipster-box")
    private WebElementFacade toolTipBox;

    @FindBy(css="div.tooltipster-content")
    private WebElementFacade toolTipBoxContent;

    @FindBy(xpath="//a[contains(@style, 'background-color: rgb(255, 159, 128)')]")
    private WebElementFacade recentlyInsertedActivity;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath="//a[contains(@style, 'background-color: rgb(255, 159, 128)')]"),})
    List<WebElement> recentlyInsertedActivities;

    @FindBy(css="input#dateTimePicker")
    private WebElementFacade txtCalendar;

    @FindBy(xpath="//button[@title='Zoom In']")
    private WebElementFacade zoomInBtn;

    @FindBy(xpath="//button[@title='Zoom Out']")
    private WebElementFacade zoomOutBtn;

    @FindBy(xpath="//button[@title='Previous Day']")
    private WebElementFacade previousBtn;

    @FindBy(xpath="//button[@title='Next Day']")
    private WebElementFacade nextBtn;

    @FindBy(css ="div.fc-scroller.fc-time-grid-container")
    private WebElementFacade scrollerGrid;

    @FindBy(xpath="//button[@title='Save Changes']")
    private WebElementFacade saveChangesBtn;

    @FindBy(xpath="//button[contains(@class, 'fc-buttonViewInvalid-button')]")
    private WebElementFacade invalidTaggingBtn;

    @FindBy(xpath="//button[contains(@class, 'fc-buttonViewDeleted-button')]")
    private WebElementFacade viewDeletedBtn;

    @FindBy(css="div.fc-bgevent.tooltipstered")
    private WebElementFacade deletedActivityLog;

    @FindAll({ @org.openqa.selenium.support.FindBy(css="div.fc-bgevent.tooltipstered"),})
    List<WebElement> deletedActivities;


    private String calendarTextHeaderDisplay;

    List<String> allEnabledDates = new ArrayList<>();

    public String getCalendarTextHeaderDisplay() {
        return calendarTextHeaderDisplay;
    }

    private WebElementFacade dayOnCalendar(String dayVal){
        return find(By.xpath("//td[contains(@class, 'day') and text()='" + dayVal + "']"));
    }

    public void verifyMyLogsPageHeaderTitle(){
        assert pageHeader.getText().contains("Edit My Log(s)");
    }

    public void clickOnCalendarIcon(){
        if(calendarIcon.isCurrentlyVisible() && calendarIcon.isClickable()){
            calendarIcon.click();
        }
    }

    public boolean isCalendarDisplayed(){
        return calendar.isDisplayed();
    }

    public boolean isPreviousCaretEnabled(){
        return calendarPreviousCaretChk.isPresent();
    }

    public boolean isNextCaretEnabled(){
        return calendarNextCaretChk.isPresent();
     }

    public void clickOnPreviousCaretOnCalendar(){
        calendarPreviousCaret.click();
    }

    public void clickOnNextCaretOnCalendar() {
        calendarNextCaret.click();
    }

    public String getCalendarHeaderDisplay(){
        calendarTextHeaderDisplay = calendarMonthYear.getText();
        return calendarTextHeaderDisplay;
    }

    public void clickYesterdayDateOnCalendar(String timeZoneIANA){
        String dayVal = TimeCalendar.getYesterDaysDayValue(timeZoneIANA);
        dayOnCalendar(dayVal).waitUntilClickable();
        dayOnCalendar(dayVal).click();
        if(!dateDisplayedOnCenter.isCurrentlyVisible())
            waitForAngularRequestsToFinish();
    }

    public void clickTodaysDateOnCalendar(String timeZoneIANA){
        String dayVal = TimeCalendar.getTodaysDayValue(timeZoneIANA);
        dayOnCalendar(dayVal).waitUntilClickable();
        dayOnCalendar(dayVal).click();
        if(!dateDisplayedOnCenter.isCurrentlyVisible())
            waitForAngularRequestsToFinish();
    }

    public void clickSevenDaysFromTodayOnCalendar(){
        String dayVal = TimeCalendar.getSevenDaysFromToday();
        dayOnCalendar(dayVal).waitUntilClickable();
        dayOnCalendar(dayVal).click();
        if(!dateDisplayedOnCenter.isCurrentlyVisible())
            waitForAngularRequestsToFinish();
    }

    public String getDateDisplayedOnCenter() {
        return dateDisplayedOnCenter.getText();
    }

    public void clickTheDayFortyFivePreviousTodayOnTheCalendar(String timezoneIANA){
        String fortyFirstDayPreviousToday = TimeCalendar.getFourtyFirstDayPreviousToday(timezoneIANA);
        String[] splitText = fortyFirstDayPreviousToday.split(" ");
        String monthIncludingFortyFiveDayPrevious = splitText[0];

         //Get the header month from Calendar
        String calendarHeaderText = getCalendarHeaderDisplay();
        String[] splitText2 = calendarHeaderText.split(" ");

        //get all enabled dates and transform to a new list
        String headerMonth = splitText2[0];
        String headerYear =  splitText2[1];
        String day;
        String strStartDate;
        Date dStartDate;
        String formattedDate;
        SimpleDateFormat originalFormat = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            for (WebElement enabledDate : enabledDates) {
                day = enabledDate.getText();
                strStartDate = headerMonth + " " + day + ", " + headerYear;
                dStartDate = originalFormat.parse(strStartDate);
                formattedDate = targetFormat.format(dStartDate);
                allEnabledDates.add(formattedDate);
            }
        }catch(ParseException pe){
            pe.printStackTrace();
        }

        String[] headerText;
       while(!monthIncludingFortyFiveDayPrevious.equalsIgnoreCase(headerMonth)){
            clickOnPreviousCaretOnCalendar();
            //Get the header month from Calendar
            calendarHeaderText = getCalendarHeaderDisplay();
            headerText = calendarHeaderText.split(" ");

            //get all enabled dates and transform to a new list
            headerMonth = headerText[0];
            headerYear =  headerText[1];

            try{
                for (WebElement enabledDate : enabledDates) {
                    day = enabledDate.getText();
                    strStartDate = headerMonth + " " + day + ", " + headerYear;
                    dStartDate = originalFormat.parse(strStartDate);
                    formattedDate = targetFormat.format(dStartDate);
                    allEnabledDates.add(formattedDate);
                }
            }catch(ParseException pe){
                pe.printStackTrace();
            }
        }
    }

    public List<String> getAllEnabledDates() {
        return allEnabledDates;
    }

    public List<String> getAllLogEntriesFromScreen() {
        String time;
        String activity;
        String[] splitTime;
        String finalTxt;
        String startTime;
        String endTime;


        List<String> logEntries = new ArrayList<>();
        List<WebElement> times;
        List<WebElement> activities;
        StringFormatters matcher = new StringFormatters();


        List<String> dataDates = new ArrayList<>();
        for (WebElement dateHeader : dateHeaders) {
            dataDates.add(dateHeader.getAttribute("data-date"));
        }

        ArrayList<WebElement> timeLogs =    (ArrayList<WebElement>) evaluateJavascript(
                        "return document.getElementsByClassName(\"fc-content-col\")");
           int l;
            for (l=0; l < timeLogs.size(); l++) {
                try {
                    times = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-time']"));
                    activities = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-title']"));

                   if (times.size()!=0 && activities.size()!=0) {
                       for(int j=0; j<times.size();j++) {
                           time = times.get(j).getAttribute("data-full");
                           activity = activities.get(j).getAttribute("textContent");
                           splitTime = time.split("-");
                           startTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[0].trim());

                           if (splitTime.length > 1) {
                               endTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[1].trim());
                           } else {
                               endTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[0].trim());
                           }

                           finalTxt = startTime + "|" + endTime + "|" + activity;
                           logEntries.add(finalTxt);

                       }

                   }
                }catch(NoSuchElementException ne){
                        //System.out.println("No Element at: " + l);
                        continue;
                }
            }

        return logEntries;
    }

    public HashMap<String, String> getAllLogEntriesFromScreenIndexOnActivityAndTime() {
        String time;
        String activity;

        HashMap<String, String> logEntries = new HashMap<>();
        List<WebElement> times;
        List<WebElement> activities;

        List<String> dataDates = new ArrayList<>();
        for (WebElement dateHeader : dateHeaders) {
            dataDates.add(dateHeader.getAttribute("data-date"));
        }

        ArrayList<WebElement> timeLogs =    (ArrayList<WebElement>) evaluateJavascript(
                "return document.getElementsByClassName(\"fc-content-col\")");
        int l;
        for (l=0; l < timeLogs.size(); l++) {
            try {
                times = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-time']"));
                activities = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-title']"));

                if (times.size()!=0 && activities.size()!=0) {
                    for(int j=0; j<times.size();j++) {
                        time = times.get(j).getAttribute("data-full");
                        activity = activities.get(j).getAttribute("textContent");

                        logEntries.put(activity + "+" + time, dataDates.get(l));
                    }

                }
            }catch(NoSuchElementException ne){
                continue;
            }
        }

         return logEntries;
    }

    public void hoverOnOneOfTheActivity(){
        Actions builder = new Actions(getDriver());
        builder.moveToElement(activities.get(0)).build().perform();
    }

    public void hoverOnTheActivity(int index){
        Actions builder = new Actions(getDriver());
        builder.moveToElement(activities.get(index));
        builder.moveToElement(activities.get(index)).build().perform();
        //waitFor(ExpectedConditions.visibilityOf(toolTipBoxContent));
    }

   public boolean verifyToolTipBoxIsDisplayed(){
        return toolTipBox.isPresent();
   }

   public String getActivityDetailsFromDBOfSelectedActivity(){
        DBHelper helper = new DBHelper();

        String timeRange = activities.get(0).findElement(By.xpath(".//div[@class='fc-time']")).getAttribute("data-full");
        String activity =  activities.get(0).findElement(By.xpath(".//div[@class='fc-title']")).getAttribute("textContent");

        HashMap<String, String> allActivitiesFromScreen = getAllLogEntriesFromScreenIndexOnActivityAndTime();
        String sDate = allActivitiesFromScreen.get(activity + "+" + timeRange);
        String[] splitTimeRange = timeRange.split("-");
        String startT = splitTimeRange[0];
        String endT = splitTimeRange[1];
        if(startT.trim().length()<8){
            startT = "0" + startT.trim();
        }
       if(endT.trim().length()<8){
           endT = "0" + endT.trim();
       }

        String username = Serenity.sessionVariableCalled("EmpNo").toString();
       String startTime = sDate + " " + startT.trim();
        String endTime = sDate + " " + endT.trim();

        String activityFromDB = helper.getActivityLogEntryGivenActivityDateStartTimeAndEndTime(username,
                sDate, activity, startTime, endTime);

        return activityFromDB;
   }

   public String getPopupContents(){
       return toolTipBoxContent.getTextContent();
   }

    public void hoverOnTheRecentlyCreatedActivity(){
        Actions builder = new Actions(getDriver());
        builder.moveToElement(recentlyInsertedActivity).build().perform();
    }

    public void clickOnTheRecentlyCreatedActivity(){
        Actions action = new Actions(getDriver());
        scrollToTopOfMyLogGrid();
        action.moveToElement(recentlyInsertedActivity).click().build().perform();
        waitForAngularRequestsToFinish();
    }

    public String getDateFromCalendarTxtBox(){
        return txtCalendar.getValue();
    }

    public int getTheHeightOfTheFirstActivityDisplayed(){
        return activities.get(0).getSize().height;
    }

    public void clickOnZoomInBtn(){
        zoomInBtn.click();
    }

    public void clickOnZoomOutBtn(){
        zoomOutBtn.click();
    }

    public List<String> getTimeIntervals(){
        List<String> intervals = new ArrayList<>();
        for (WebElement timeInterval : timeIntervals) {
            String txt = timeInterval.getText();
            intervals.add(txt);
        }
        return intervals;
    }

    public boolean isZoomInBtnClickable(){
        boolean ret = false;
        if(zoomInBtn.isEnabled()) {
            ret = zoomInBtn.isClickable();
        }
        return ret;
    }

    public boolean isZoomOutBtnClickable(){
        boolean ret = false;
        if(zoomOutBtn.isEnabled()) {
            ret = zoomOutBtn.isClickable();
        }
        return ret;
    }

    public List<WebElement> getActivities() {
        return activities;
    }

    public void clickPreviousBtn(){
        previousBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickNextBtn(){
        nextBtn.click();
        waitForAngularRequestsToFinish();
    }

    public boolean isNextBtnClickable(){
        boolean ret = false;
        if(nextBtn.isEnabled()){
            ret = nextBtn.isClickable();
        }
        return ret;
   }

   public void scrollToTopOfMyLogGrid(){
       evaluateJavascript("$('div.fc-scroller.fc-time-grid-container').animate({ scrollTop: 0 }, 'fast')");
   }

    public String getActivityDetailsForSelectedActivity(){

         String timeRange = activities.get(0).findElement(By.xpath(".//div[@class='fc-time']")).getAttribute("data-full");
        String activity =  activities.get(0).findElement(By.xpath(".//div[@class='fc-title']")).getAttribute("textContent");

        HashMap<String, String> allActivitiesFromScreen = getAllLogEntriesFromScreenIndexOnActivityAndTime();
        String sDate = allActivitiesFromScreen.get(activity + "+" + timeRange);
        String[] splitTimeRange = timeRange.split("-");
        String startT = splitTimeRange[0];
        if(startT.trim().length()<8){
            startT = "0" + startT.trim();
        }

        String startTime = sDate + " " + startT.trim();
        String keyToFind = activity.trim() + startTime.trim();

        HashMap<String, String> allActivitiesForEdit =
                Serenity.sessionVariableCalled("allActivitiesForEdit");

        String ret = allActivitiesForEdit.get(keyToFind);

        return ret;
    }

    public HashMap<String, String> getAllActivityLogsForEditGivenTheDisplayedWeek(
            String webserviceEndpoint, UserAuthenticate userAuthenticate){

        APIHelper apiHelper = new APIHelper();
        String centerDisplay = getDateDisplayedOnCenter();
        LocalDate startDate = TimeCalendar.getFirstDayInLocalDateGivenTextDisplayedonCenterOnMyLogs(centerDisplay);
        LocalDate endDate = startDate.plusDays(6);
        String fromDate = startDate.toString().trim() + " 00:00:00";
        String toDate = endDate.toString().trim() + " 23:59:59";

        HashMap<String, String> allActivitiesForEdit = apiHelper.getActivityLogSelectForEdit(webserviceEndpoint,
                userAuthenticate.getEmployeeID(),
                fromDate, userAuthenticate.getTimeZone(), userAuthenticate.getTimeZoneIANA(), toDate,
                userAuthenticate.getAuthenticationToken());

        return allActivitiesForEdit;
    }

    public List<String> getAllActivityLogsForEditGivenTheDisplayedWeekForScreenCompare(
            String webserviceEndpoint, UserAuthenticate userAuthenticate){

        APIHelper apiHelper = new APIHelper();
        String centerDisplay = getDateDisplayedOnCenter();
        LocalDate startDate = TimeCalendar.getFirstDayInLocalDateGivenTextDisplayedonCenterOnMyLogs(centerDisplay);
        LocalDate endDate = startDate.plusDays(6);
        String fromDate = startDate.toString().trim() + " 00:00:00";
        String toDate = endDate.toString().trim() + " 23:59:59";

        List<String> allActivitiesForEdit =
                apiHelper.getActivityLogSelectForEditForScreenLogsCompare(webserviceEndpoint,
                userAuthenticate.getEmployeeID(),
                fromDate, userAuthenticate.getTimeZone(), userAuthenticate.getTimeZoneIANA(), toDate,
                userAuthenticate.getAuthenticationToken());

        return allActivitiesForEdit;
    }

    public void clickOnFirstActivityForEdit(){
        activities.get(0).click();
    }

    public void clickOnActivityForEditGivenIndex(int index){
        activities.get(index).click();
        waitForAngularRequestsToFinish();
     }

    public int clickOnRandomActivityForEdit(){
        Random rand = new Random();
        int int_random = 0;
        if(activities.size()>1) {
            int upperbound = activities.size() - 1;
            int_random = rand.nextInt(upperbound);
        }
        activities.get(int_random).click();
        return int_random;
   }

    public HashMap<String, String> parsePopupContentsToGetActivityDetails(String popupContents){
        int j = -1;
        String value;
        String activity;
        String startTime;
        String endTime;
        String duration;
        String timeZone;
        String actionType;
        String actionReason;

        HashMap<String, String> retMap = new HashMap<>();

        String[] splitContent = popupContents.split(" : ");

       for(int i=0; i<splitContent.length; i++){
           if(i == 1){
                value = splitContent[1];
                j = value.indexOf("Start Time");
                activity = value.substring(0,j);
                retMap.put("Activity Type", activity.trim());
            }
            if(i==2){
                value = splitContent[2];
                j = value.indexOf("End Time");
                startTime = value.substring(0,j);
                retMap.put("Start Time", startTime.trim());
            }
            if(i==3){
                value = splitContent[3];
                j = value.indexOf("Duration");
                endTime = value.substring(0,j);
                retMap.put("End Time", endTime.trim());
            }
            if(i==4){
                value = splitContent[4];
                j = value.indexOf("TimeZone");
                duration = value.substring(0,j);
                retMap.put("Duration", duration.trim());
            }
            if(i==5){
                value = splitContent[5];
                j = value.indexOf("Action Type");
                timeZone = value.substring(0,j);
                retMap.put("TimeZone", timeZone.trim());
            }
            if(i==6){
                value = splitContent[6];
                j = value.indexOf("Action Reason");
                if(j != -1) {
                    actionType = value.substring(0, j);
                }else{
                    actionType = value.trim();
                }
                retMap.put("Action Type", actionType.trim());
            }
            if(i==7){
                actionReason = splitContent[7];
                retMap.put("Action Reason", actionReason.trim());
            }
        }

     return retMap;
    }

    public HashMap<String, String> getAllActivityLogsForEditSeventyTwoHoursFromNow(
            String webserviceEndpoint, UserAuthenticate userAuthenticate){

        APIHelper apiHelper = new APIHelper();
        //String fromDate = TimeCalendar.getDateFourtyEightHoursFromNow(userAuthenticate.getTimeZoneIANA());
        String fromDate = TimeCalendar.getDateSeventyTwoHoursBeforeNow(userAuthenticate.getTimeZoneIANA());
        String toDate = TimeCalendar.getDateTimeNow(userAuthenticate.getTimeZoneIANA());

        HashMap<String, String> allActivitiesForEdit = apiHelper.getActivityLogSelectForEdit(webserviceEndpoint,
                userAuthenticate.getEmployeeID(),
                fromDate, userAuthenticate.getTimeZone(), userAuthenticate.getTimeZoneIANA(), toDate,
                userAuthenticate.getAuthenticationToken());

        return allActivitiesForEdit;
    }

    public boolean isSaveChangesBtnEnabled(){
        return saveChangesBtn.isEnabled();
    }

    public void clickOnSaveChangesBtn(){
        saveChangesBtn.click();
        waitForAngularRequestsToFinish();
    }

    public boolean isRecentlyInsertedActivityDisplays(){
        boolean ret =  recentlyInsertedActivity.isDisplayed();
        if(ret){
            Actions action = new Actions(getDriver());
            action.moveToElement(recentlyInsertedActivity).perform();
        }
        return ret;
    }

    public boolean isRecentlyInsertedActivityPresent(){
       return recentlyInsertedActivity.isPresent();
     }

    public int getRecentlyAddedActivitiesCount(){
        return recentlyInsertedActivities.size();
    }

    public boolean checkIfDateIsDisplayedOnTheGrid(String givenDate){
        String centerDisplay = getDateDisplayedOnCenter();
        LocalDate startDate = TimeCalendar.getFirstDayInLocalDateGivenTextDisplayedonCenterOnMyLogs(centerDisplay);
        LocalDate endDate = startDate.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDate givenLocDate = LocalDate.parse(givenDate, formatter);
        boolean withinRange = ( ! givenLocDate.isBefore( startDate ) ) && ( givenLocDate.isBefore( endDate ) ) ;
        return withinRange;
    }

    public void displayTheStartTimeOnTheGrid(String startTime){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        if(!checkIfDateIsDisplayedOnTheGrid(startTime)){
            while(!checkIfDateIsDisplayedOnTheGrid(startTime)) {
                clickPreviousBtn();
            }
        }
    }

    public void clickOnAnyAreaOnTheGridWithNoActivity(ModalPage modalPage){
        scrollToTopOfMyLogGrid();
        Actions action = new Actions(getDriver());
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();

        String today = TimeCalendar.getTodaysDateInEEEMddFormat(timeZoneIANA);
        WebElement element = find(By.xpath("//span[text()='" + today + "']"));

        action.moveToElement(element).perform();
        action.moveToElement(element, 0, 20).click().build().perform();
        waitForAngularRequestsToFinish();

        int yOffset = 20;
        while(!modalPage.isInsertActivityModalVisible()){
            yOffset = yOffset + 200;
            action.moveToElement(element, 0, yOffset).click().build().perform();
            waitForAngularRequestsToFinish();
        }
    }

    public int getFirstActivityLogOnTheScreenThatIsBeyondSeventyTwoHoursFromToday(String timeZoneIANA) {

        String time;
        String[] splitTime;
        String endTime;
        int ret = -1;

        List<WebElement> times;
        List<WebElement> activities;
        StringFormatters matcher = new StringFormatters();
        String dateTimeBeyondSeventyTwoHours =
                TimeCalendar.getDateNinetySixFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);

        List<String> dataDates = new ArrayList<>();
        for (int k=0; k< dateHeaders.size();k++) {
            dataDates.add(dateHeaders.get(k).getAttribute("data-date"));
        }

        ArrayList<WebElement> timeLogs =    (ArrayList<WebElement>) evaluateJavascript(
                "return document.getElementsByClassName(\"fc-content-col\")");
        int l = 0;
        for (l=0; l < timeLogs.size(); l++) {
            try {
                times = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-time']"));
                activities = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-title']"));

                if (times.size()!=0 && activities.size()!=0) {
                    for(int j=0; j<times.size();j++) {
                        time = times.get(j).getAttribute("data-full");
                        //activity = activities.get(j).getAttribute("textContent");
                        splitTime = time.split("-");
                        //startTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[0].trim());

                        if (splitTime.length > 1) {
                            endTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[1].trim());
                        } else {
                            endTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[0].trim());
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
                        LocalDateTime lEndDateTime = LocalDateTime.parse(endTime, formatter2);
                        LocalDateTime lDateToCompare =
                                LocalDateTime.parse(dateTimeBeyondSeventyTwoHours, formatter);

                        if(lEndDateTime.isBefore(lDateToCompare) || lEndDateTime.isEqual(lDateToCompare)){
                            ret = l;
                            break;
                        }

                    }

                }
            }catch(NoSuchElementException ne){
                //System.out.println("No Element at: " + l);
                continue;
            }
        }

        return ret;
    }

    public int getFirstActivityLogOnTheScreenThatIsWithinSeventyTwoHoursFromToday(String timeZoneIANA) {

        String time;
        String[] splitTime;
        String endTime;
        String startTime;
        int actCounter = -1;

        List<WebElement> times;
        List<WebElement> activities;
        StringFormatters matcher = new StringFormatters();
        String dateTimeSeventyTwoHoursFromNow =
                TimeCalendar.getDateSeventyTwoHoursFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);

        List<String> dataDates = new ArrayList<>();
        for (WebElement dateHeader : dateHeaders) {
            dataDates.add(dateHeader.getAttribute("data-date"));
        }

        ArrayList<WebElement> timeLogs =    (ArrayList<WebElement>) evaluateJavascript(
                "return document.getElementsByClassName(\"fc-content-col\")");
        int l;
        for (l=0; l < timeLogs.size(); l++) {
            try {
                times = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-time']"));
                activities = timeLogs.get(l).findElements(By.xpath(".//div[@class='fc-title']"));

                if (times.size()!=0 && activities.size()!=0) {
                    for(int j=0; j<times.size();j++) {
                        time = times.get(j).getAttribute("data-full");
                        //activity = activities.get(j).getAttribute("textContent");
                        splitTime = time.split("-");
                        startTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[0].trim());

                        if (splitTime.length > 1) {
                            endTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[1].trim());
                        } else {
                            endTime = dataDates.get(l) + " " + matcher.formatTimeFromGrid(splitTime[0].trim());
                        }

                        actCounter++;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
                        LocalDateTime lStartDateTime = LocalDateTime.parse(startTime, formatter2);
                        LocalDateTime lEndDateTime = LocalDateTime.parse(endTime, formatter2);
                        LocalDateTime lDateToCompare =
                                LocalDateTime.parse(dateTimeSeventyTwoHoursFromNow, formatter);

                        if(lStartDateTime.compareTo(lDateToCompare)>=0 && lEndDateTime.compareTo(lDateToCompare)>=0){
                           System.out.println("Breaking at index: [" + actCounter + "]");
                            return actCounter;
                        }

                    }

                }
            }catch(NoSuchElementException ne){
                //System.out.println("No Element at: " + l);
                continue;
            }
        }

        return actCounter;
    }

    public HashMap<String, String> getAllActivityLogsForEditNinetySixHoursFromNow(
            String webserviceEndpoint, UserAuthenticate userAuthenticate){

        APIHelper apiHelper = new APIHelper();
        String fromDate = TimeCalendar.getDateNinetySixHoursFromNow(userAuthenticate.getTimeZoneIANA());
        String toDate = TimeCalendar.getDateTimeNow(userAuthenticate.getTimeZoneIANA());

        HashMap<String, String> allActivitiesForEdit = apiHelper.getActivityLogSelectForEdit(webserviceEndpoint,
                userAuthenticate.getEmployeeID(),
                fromDate, userAuthenticate.getTimeZone(), userAuthenticate.getTimeZoneIANA(), toDate,
                userAuthenticate.getAuthenticationToken());

        return allActivitiesForEdit;
    }

    public int getTheFirstActivityFromScreenGivenType(String type){
        int index = -1;
        String text;
        for(int i=0; i < activities.size(); i++){
           text = activities.get(i).getText().trim();
            if(text.contains(type)){
                index = i;
                break;
            }
        }
        return index;
    }

    public void clickOnInvalidTaggingBtn(){
        invalidTaggingBtn.click();
    }

    public void hoverOnDeletedActivityLog(){
        Actions builder = new Actions(getDriver());
        builder.moveToElement(deletedActivityLog);
        builder.moveToElement(deletedActivityLog).build().perform();
    }

    public int getInvalidTaggingNotificationCount(){
        String cnt = invalidTaggingBtn.getText().trim();
        cnt = cnt.replace("(", "");
        cnt = cnt.replace(")", "");
        return Integer.parseInt(cnt);
    }

    public void clickOnViewDeletedBtn(){
        viewDeletedBtn.click();
    }

    public int getViewDeletedNotificationCount(){
        String cnt = viewDeletedBtn.getText().trim();
        cnt = cnt.replace("(", "");
        cnt = cnt.replace(")", "");
        return Integer.parseInt(cnt);
    }

    public void clickOnAnAreaBeforeFirstStartActivity(){
        int index = getTheFirstActivityFromScreenGivenType("Start Shift");
        System.out.println("index of Start Shift: " + index);
        WebElement element = getActivities().get(index);
        Long return_value = (Long) evaluateJavascript("return $('div.fc-scroller.fc-time-grid-container').scrollTop()");
        System.out.println("return_value: " + return_value);
        long scrollToValue = Long.valueOf(return_value) - 350;
        evaluateJavascript("$('div.fc-scroller.fc-time-grid-container').scrollTop(" + scrollToValue + ")");
        Actions action = new Actions(getDriver());
        action.moveToElement(element).perform();
        action.moveToElement(element, 0, -100).click().build().perform();
        waitForAngularRequestsToFinish();
    }

    public String getTheActivityBackgroundColor(WebElement element){
        return element.getCssValue("background-color");
    }

    public String getTheActivityBorderColor(WebElement element){
        return element.getCssValue("border-color");
    }

    public WebElement getTheFirstInsertedActivity(){
        if(recentlyInsertedActivities.size()!=0){
            return recentlyInsertedActivities.get(0);
        }
        return null;
    }

    public WebElement getTheFirstDeletedActivity(){
        if(deletedActivities.size()!=0){
            return deletedActivities.get(0);
        }
        return null;
    }

    @FindBy(xpath = "//td[@data-day='03/07/2020']")
    private WebElementFacade mar072020;

    public void clickOnFeb22(){
        mar072020.click();
        waitForAngularRequestsToFinish();
    }

}
