package steps;

import common.TextMatcher;
import common.TimeCalendar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageobjects.CommonPageObjectsHelper;
import pageobjects.InvalidTaggingModalPage;
import pageobjects.ModalPage;
import pageobjects.MyLogsPage;
import testdataobjects.UserAuthenticate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MyLogSteps {

        MyLogsPage myLogsPage;
        ModalPage modalPage;
        InvalidTaggingModalPage invalidTaggingModalPage;
        CommonPageObjectsHelper commonPageObjectsHelper;

        HashMap<String, String> retMap;
        private EnvironmentVariables environmentVariables;
        public String updateActivityDetails;
        public String initialActivityDetails;
        String activityDetails;
        public String startTime;
        int indexOfFirstActBeyondSeventyTwoHours = -1;
        int indexOfFirstActWithinSeventyTwoHours = -1;
        int indexOfUpdatedActivity = -1;


       public String getActivityDetails() {
            return activityDetails;
        }

        @Step("Verify My Logs page is displayed")
        public void verifyMyLogsLandingPage() {
            myLogsPage.verifyMyLogsPageHeaderTitle();
        }

        @Step("Click on calendar icon")
        public void clickOnCalendarIcon() {
            myLogsPage.clickOnCalendarIcon();
        }

        @Step("Check if calendar is displayed")
        public void checkIfCalendarIsDisplayed() {
            assert myLogsPage.isCalendarDisplayed();
        }

        @Step("Click on the < on calendar")
        public void clickOnPreviousIconOnCalendar(){
            //if(myLogsPage.isPreviousCaretEnabled()){
                myLogsPage.clickOnPreviousCaretOnCalendar();
                //System.out.println("Previous caret was clicked");}
        }

        @Step("Check that previous month is displayed at the top of the calendar or today's month if < is disabled")
        public void checkThatPreviousMonthYearIsDisplayedOnTop(){
        String txtDisplay = myLogsPage.getCalendarHeaderDisplay();
        TimeCalendar timeCalendar = new TimeCalendar();
        String monthYearDisplay;
        if(myLogsPage.isPreviousCaretEnabled()) {
            monthYearDisplay = timeCalendar.getPreviousMonthYear();
        }else{
            monthYearDisplay = timeCalendar.getToDaysMonthYear();
        }
        assert monthYearDisplay.equalsIgnoreCase(txtDisplay);
    }

    @Step("Click on the > on calendar")
    public void clickOnNextIconOnCalendar(){
        System.out.println("isNextCaretEnabled: " + String.valueOf(myLogsPage.isNextCaretEnabled()));
        if(myLogsPage.isNextCaretEnabled())
            myLogsPage.clickOnNextCaretOnCalendar();
    }

    @Step("Check that next month is displayed at the top of the calendar  or todays month if > is disabled")
    public void checkThatNextMonthYearIsDisplayedOnTop(){
        String txtDisplay = myLogsPage.getCalendarHeaderDisplay();
         String monthYearDisplay;
        if(myLogsPage.isNextCaretEnabled()) {
            monthYearDisplay = TimeCalendar.getNextMonthYear();
        }else{
            monthYearDisplay = TimeCalendar.getToDaysMonthYear();
        }
       assert monthYearDisplay.equalsIgnoreCase(txtDisplay);
    }

    @Step("Click on the previous day date on the calendar")
    public void clickOnPreviousDayOnCalendar(){
       UserAuthenticate userAuthenticate =
               Serenity.sessionVariableCalled("userAuthenticate");
       String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
       myLogsPage.getCalendarHeaderDisplay();
       myLogsPage.clickYesterdayDateOnCalendar(timeZoneIANA);
    }

    @Step("Verify selected day is the first day in the calendar week")
    public void checkThatSelectedDayIsTheFirsDayOfTheCalendarWeek(){
        String textDisplayOnCenter = myLogsPage.getDateDisplayedOnCenter();
        String selectedDate = myLogsPage.getDateFromCalendarTxtBox();
        String weekTxt = TimeCalendar.getMyLogsCenterTextGivenDate(selectedDate);
        assert TextMatcher.compareStringArrayValSkipRow2(textDisplayOnCenter.split(" "), weekTxt.split(" "));
    }

    @Step("Click on the todays date on the calendar")
    public void clickOnTodaysDayOnCalendar(){
        UserAuthenticate userAuthenticate = Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        myLogsPage.clickTodaysDateOnCalendar(timeZoneIANA);
    }

    @Step("Verify today's date is the first day in the calendar week")
    public void checkThatTodaysDateIsTheFirsDayOfTheCalendarWeek(){
       String textDisplayOnCenter = myLogsPage.getDateDisplayedOnCenter();
        UserAuthenticate userAuthenticate = Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
       LocalDate todayLocalDate =   TimeCalendar.getLocalDateOfTodaysDateGivenZoneId(timeZoneIANA);
       String selectedDate = TimeCalendar.getMyLogsCenterTextGivenDate(todayLocalDate.toString());
       assert TextMatcher.compareStringArrayValSkipRow2(textDisplayOnCenter.split(" "),
               selectedDate.split(" "));
    }

    @Step("Get all enabled dates from calendar")
    public void getAllEnabledDatesOnTheCalendar(){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        myLogsPage.clickTheDayFortyFivePreviousTodayOnTheCalendar(timeZoneIANA);
   }

   @Step("Check that only days 42 days prior today are enabled")
    public void checkThatOnlyDaysFortyTwoDaysPriorAreEnabled(){
        UserAuthenticate userAuthenticate =
               Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        List<String> allEnabledDates = myLogsPage.getAllEnabledDates();
        List<String> datestBetNowAndFortyTwoDaysPrior =
                TimeCalendar.getListOfDateFromTodayAndFourtyDaysPrior(timeZoneIANA);
        Collections.sort(allEnabledDates);
        Collections.sort(datestBetNowAndFortyTwoDaysPrior);
       assert allEnabledDates.equals(datestBetNowAndFortyTwoDaysPrior);
   }

    @Step("Verify date displayed on the center starts three days from today")
    public void verifyDateDisplayedOnTheCenterStartsThreeDaysFromToday(){
        String headerDisplayCtr = myLogsPage.getDateDisplayedOnCenter();
        String dayThreeDaysPriorToday = TimeCalendar.whatShouldBeTheDefaultDateDisplayUponLoginToMyLogs();
        assert TextMatcher.compareStringArrayValSkipRow2(headerDisplayCtr.split(" "),
                dayThreeDaysPriorToday.split(" "));
    }

    @Step("Verify the entries displayed against the entries in the API")
    public void verifyTheEntriesDisplayedAgainstTheAPI(String webserviceEndpoint, UserAuthenticate userAuthenticate){
          List<String> entriesFromAPI =
                myLogsPage.getAllActivityLogsForEditGivenTheDisplayedWeekForScreenCompare
                        (webserviceEndpoint, userAuthenticate);

       //get an ArrayList of the Values on the screen
        List<String> entriesFromScreen = myLogsPage.getAllLogEntriesFromScreen();

        Collections.sort(entriesFromAPI);
        Collections.sort(entriesFromScreen);

        assert entriesFromAPI.equals(entriesFromScreen);
    }

    @Step("Hover on one of the activities")
    public void hoverOnAnActivity(){
        myLogsPage.hoverOnOneOfTheActivity();
    }

    @Step("Verify pop-up box displays ")
    public void verifyPopUpBox(){
        assert myLogsPage.verifyToolTipBoxIsDisplayed();
    }

    @Step("Click seven days from  todays date on the calendar")
    public void clickOnSevenDaysOnCalendar(){
        myLogsPage.getCalendarHeaderDisplay();
        myLogsPage.clickSevenDaysFromTodayOnCalendar();
    }

    @Step("Create a new start shift activity")
    public void createANewActivity(){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();

        myLogsPage.scrollToTopOfMyLogGrid();
        Actions action = new Actions(myLogsPage.getDriver());
        String centerDisplay = myLogsPage.getDateDisplayedOnCenter();
        //String today = TimeCalendar.getTodaysDateInEEEMddFormat(timeZoneIANA);
        //WebElement element = myLogsPage.find(By.xpath("//span[text()='" + today + "']"));
        String firstday = TimeCalendar.getFirstDayGivenTextDisplayedonCenterOnMyLogs(centerDisplay);
        WebElement element = myLogsPage.find(By.xpath("//span[text()='" + firstday + "']"));

        action.moveToElement(element).perform();
        //action.moveToElement(element, 0, 200).click().build().perform();
        action.moveToElement(element, 0, 20).click().build().perform();
        myLogsPage.waitForAngularRequestsToFinish();

        //int yOffset = 200;
        int yOffset = 20;
        while(!modalPage.isInsertActivityModalVisible()){
            yOffset = yOffset + 200;
            action.moveToElement(element, 0, yOffset).click().build().perform();
            modalPage.waitForAngularRequestsToFinish();
        };
        activityDetails = modalPage.enterValuesAndSaveModalAndClickPromptMessageAfter("Start Shift", "", "",
                "Forgot to log-in", "Sample remarks", "Insert");
    }

    @Step("Click on any area where there's no activity")
    public void clickOnAnyAreaWithNoActivity(){
        myLogsPage.clickOnAnyAreaOnTheGridWithNoActivity(modalPage);
     }

   @Step("Hover on the inserted activity")
   public void hoverOnTheInsertedActivity(){
       myLogsPage.hoverOnTheRecentlyCreatedActivity();
   }

    @Step("Verify the details of the pop-up box is the same with what was entered on the modal")
    public void verifyDetailsOfPopupIsSameWithModal(){
        String contentfromModal = null;
        contentfromModal = getActivityDetails();
        if(updateActivityDetails!=null){
            contentfromModal = updateActivityDetails;
        }else {
            if (contentfromModal == null && initialActivityDetails != null) {
                contentfromModal = initialActivityDetails;
            }
        }
        String popupContent = myLogsPage.getPopupContents();
        assert contentfromModal.equalsIgnoreCase(popupContent);
    }

    @Step("User clicks on the created activity")
    public void clickOnTheInsertedActivity(){
        myLogsPage.clickOnTheRecentlyCreatedActivity();
    }

    @Step("Update the value of the created activity")
    public void updateTheValueOfInsertedActivity(){
       updateActivityDetails =  modalPage.enterValuesAndSaveModalAndClickPromptMessageAfter
               ("Start Shift", "", "", "Forgot to log-in", "Updated remarks", "Update");
    }

    @Step("Create a new activity log within seventy two hours")
    public void createACompleteActivityLogForTheDayWithinSeventyTwoHours(){

        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
         String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
         String stTime = TimeCalendar.getDateFourtyEightHoursFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
         createLogActivities(stTime);
    }

    @Step("Clicks on the previous button")
    public void clicksOnThePreviousBtn(){
        myLogsPage.clickPreviousBtn();
    }

    @Step("Clicks on the next button")
    public void clicksOnTheNextBtn(){
        myLogsPage.clickNextBtn();
    }

    @Step("Verify that the start day and end day moved by one day less")
    public void checkThatTheWeekDateChangedToStartAtOneDayLess(String textDisplayOnCenter){
        String[] split = textDisplayOnCenter.split(" ");
        String[] split2 = textDisplayOnCenter.split(",");
        String firstDay = split[0] + " " + split[1] + " " + split2[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate startDateNow = LocalDate.parse(firstDay, formatter);

        LocalDate previousday = startDateNow.minusDays(1);
        String selectedDate = TimeCalendar.getMyLogsCenterTextGivenDate(previousday.toString());
        String textDisplayOnCenterNow = myLogsPage.getDateDisplayedOnCenter();
         assert TextMatcher.compareStringArrayValSkipRow2(textDisplayOnCenterNow.split(" "),
                selectedDate.split(" "));
    }

    @Step("Verify that the start day and end day moved by one day more")
    public void checkThatTheWeekDateChangedToStartAtOneDayMore(String textDisplayOnCenter){

       LocalDate startDate = TimeCalendar
                .getFirstDayInLocalDateGivenTextDisplayedonCenterOnMyLogs(textDisplayOnCenter);

        LocalDate nextDay = startDate.plusDays(1);
        String selectedDate = TimeCalendar.getMyLogsCenterTextGivenDate(nextDay.toString());
        String textDisplayOnCenterNow = myLogsPage.getDateDisplayedOnCenter();
        assert TextMatcher.compareStringArrayValSkipRow2(textDisplayOnCenterNow.split(" "),
                selectedDate.split(" "));
    }


    @Step("Click on the first activity displayed on the screen")
    public void clickOnFirstActivityOnTheScreen(){
       myLogsPage.clickOnFirstActivityForEdit();
       modalPage.waitFor(modalPage.getModal());
       initialActivityDetails = modalPage.keepActivityDetails("No Changes");
    }

    @Step("Check that Update Activity Log modal displays")
    public void checkThatUpdateActivityLogModalDisplays(){
        assert modalPage.isUpdateActivityModalDisplayed();
     }

    @Step("Check that Insert Activity Log modal displays")
    public void checkThatInsertActivityLogModalDisplays(){
        assert modalPage.isInsertActivityModalDisplayed();
    }

    @Step("Check that title displayed is correct")
    public void checkActivityLogModalTitle(String title){
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(
                modalPage.getActivityModalTitle().equalsIgnoreCase(title));
   }


    @Step("Get all the contents of the pop up")
    public String getTheContentsOfPopup(){
       return myLogsPage.getPopupContents();
    }

    @Step("Parse the contents of the popup")
    public void parseTheContentsOfPopup(String popupContent){
        retMap = myLogsPage.parsePopupContentsToGetActivityDetails(popupContent);
    }

     @Step("Check that the details displayed on modal is same with the popup")
     public void checkTheContentsOfModalIsSameWithPopup(){
         SoftAssertions softly = new SoftAssertions();
         softly.assertThat(modalPage.getActivity()).isEqualTo(retMap.get("Activity Type"));
         softly.assertThat(modalPage.getStartTime()).isEqualTo(retMap.get("Start Time"));
         softly.assertThat(modalPage.getEndTime()).isEqualTo(retMap.get("End Time"));
         if(retMap.get("Action Reason") != null) {
             softly.assertThat(modalPage.getActionReason()).isEqualTo(retMap.get("Action Reason"));
         }
         softly.assertAll();
     }

   @Step("Check if activities within seventy two hours exist")
    public HashMap<String, String> getAllActivitiesWithinSeventyTwoHours(){
       String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");

       UserAuthenticate userAuthenticate =
               Serenity.sessionVariableCalled("userAuthenticate");

       HashMap<String, String> allActivitiesForEdit =
                myLogsPage.getAllActivityLogsForEditSeventyTwoHoursFromNow(webserviceEndpoint, userAuthenticate);

       Serenity.setSessionVariable("allActivitiesForEditWithinSeventyTwoHours").to(allActivitiesForEdit);

        return allActivitiesForEdit;
    }

    @Step("Check if activities for edit exist")
    public HashMap<String, String> getAllActivitiesForEdit(){
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");

        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");

        HashMap<String, String> allActivitiesForEdit =
                myLogsPage.getAllActivityLogsForEditGivenTheDisplayedWeek(webserviceEndpoint, userAuthenticate);

        Serenity.setSessionVariable("allActivitiesForEdit").to(allActivitiesForEdit);

        return allActivitiesForEdit;
    }

    @Step("Refresh the page")
    public void userRefreshThePage(){
        myLogsPage.getDriver().navigate().refresh();
        myLogsPage.waitForAngularRequestsToFinish();
    }

    @Step("Check that save button on modal can still be clicked")
    public void verifyIfSaveBtnOnLogFormCanStillBeClicked(){
       assert modalPage.isSaveBtnClickable();
    }

    /*@Step("Check that confirmation message {string} displays")
    public void verifyConfirmationMessageSubmitTicketYesNoDisplays(String arg0){
        assert modalPage.getDialogMessage().equalsIgnoreCase(arg0);
    }*/

    @Step("Click Yes on the confirmation message")
    public void clickYesOnConfirmationModal(){
       modalPage.clickYesOnModal();
       //commonPageObjectsHelper.waitForOvelayToBeGone();
     }

    @Step("Check that Ticket Submitted message displays")
    public void checkThatTicketSubmittedMessageDisplays(){
        assert modalPage.isTicketSubmittedModalPresent();
    }

    @Step("Click OK on displayed message")
    public void clickOKOnDisplayedMessage(){
        modalPage.clickOnModalOKBtn();
    }

    @Step("Check that Save Changes button on the page is not enabled")
    public void checkThatSaveChangesBtnIsNotEnabled(){
        assert !myLogsPage.isSaveChangesBtnEnabled();
    }

    @Step("Check that Save Changes button on the page enabled")
    public void checkThatSaveChangesBtnIsEnabled(){
        assert myLogsPage.isSaveChangesBtnEnabled();
    }

    @Step("Click on Save Changes button")
    public void clickOnSaveChangesButton(){
        myLogsPage.clickOnSaveChangesBtn();
    }

    @Step("Verify campaign displayed is where the user belongs")
    public void checkCampaignFieldOnModalIsWhereTheUserBelong(){
        String fromModal = modalPage.getCampaign();
        UserAuthenticate userAuthenticate = Serenity.sessionVariableCalled("userAuthenticate");
      assert fromModal.equalsIgnoreCase(userAuthenticate.getCampaignName());
    }

    @Step("Click on Activity Type dropdown")
    public void clickOnActivityTypeDropDown(){
        modalPage.clickActivityTypeDrpDwn();
    }

    @Step("Check that no more inserted activity log exists")
    public void checkThatNoMoreInsertedActivityLogDisplays(){
       assert !myLogsPage.isRecentlyInsertedActivityPresent();
    }

    @Step("Create a new activity within seventy two hours")
    public void createANewActivityWithinSeventyTwoHours(){

      UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();

        if(startTime == null || startTime.length()==0) {
            startTime = TimeCalendar.getDateFourtyEightHoursFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
        }
        String endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "add",
                "Hour", "yyyy-MM-dd hh:mm:ss a");

        activityDetails = modalPage.enterValuesAndSaveModalAndClickPromptMessageAfter("Productive Time", startTime, endTime,
                "Forgot to log-in", "Sample remarks", "Insert");
    }


    @Step("Create a new activity beyond seventy two hours")
    public void createANewActivityBeyondSeventyTwoHours(){
           UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        if(startTime == null || startTime.length()==0){
            startTime = TimeCalendar.getDateNinetySixFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);}
        String endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "add",
                "Hour", "yyyy-MM-dd hh:mm:ss a");

        activityDetails = modalPage.enterValuesAndSaveModal("Productive Time", startTime, endTime,
                "Forgot to log-in", "Sample remarks", "Insert");
    }

    @Step("Check that the inserted activity log displays")
    public void checkThatInsertedActivityLogDisplays(){
        assert myLogsPage.isRecentlyInsertedActivityDisplays();
    }

    @Step("Check that the inserted activity logs is more than one ")
    public void checkThatInsertedActivityLogsIsMoreThanOne(){
        assert myLogsPage.getRecentlyAddedActivitiesCount()>1;
    }

    @Step("Click on previous button until start time displays on the grid")
    public void clickOnPreviousButtonUntilStartTimeDisplays(String startTime){
        myLogsPage.displayTheStartTimeOnTheGrid(startTime);
    }

    @Step("Insert a complete activity log with date within nine hours from today")
    public void insertACompleteActivityWithinNineHoursFromNow(){

        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        startTime = TimeCalendar.getDateNineHoursBeforeNowInyyyyMMddhhmmssaFormat(timeZoneIANA);

        clickOnAnyAreaWithNoActivity();
        String endTime =
                modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues(
                        "Start Shift", startTime, "");

        clickOnAnyAreaWithNoActivity();
        endTime =
                modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                        ("Productive Time", "", endTime);

        clickOnAnyAreaWithNoActivity();
        modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                ("End Shift", "", endTime);

     }

    @Step("Click on the first activity that is beyond seventy two hours displayed on the screen")
    public void clickOnFirstBeyondSeventyTwoHoursActivityOnTheScreen(){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();

        indexOfFirstActBeyondSeventyTwoHours =
                myLogsPage.getFirstActivityLogOnTheScreenThatIsBeyondSeventyTwoHoursFromToday(timeZoneIANA);
        System.out.println("indexOfFirstActBeyondSeventyTwoHours: " + indexOfFirstActBeyondSeventyTwoHours);
        if(indexOfFirstActBeyondSeventyTwoHours != -1) {
            myLogsPage.clickOnActivityForEditGivenIndex(indexOfFirstActBeyondSeventyTwoHours);
            modalPage.waitFor(modalPage.getModal());
            initialActivityDetails = modalPage.keepActivityDetails("No Changes");
        }
    }

    @Step("Click on the second activity that is beyond seventy two hours displayed on the screen")
    public void clickOnSecondActivityBeyondSeventyTwoHoursOnTheScreen(){
        indexOfFirstActBeyondSeventyTwoHours++;
        myLogsPage.clickOnActivityForEditGivenIndex(indexOfFirstActBeyondSeventyTwoHours);
    }

    @Step("Click on the first activity that is within seventy two hours displayed on the screen")
    public void clickOnFirstActivityThatIsWithinSeventyTwoHoursOnTheScreen(){
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();

        String dateTimeSeventyTwoHoursFromNow =
                TimeCalendar.getDateSeventyTwoHoursFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
        indexOfFirstActWithinSeventyTwoHours =
                myLogsPage.getFirstActivityLogOnTheScreenThatIsWithinSeventyTwoHoursFromToday(timeZoneIANA);

        if(indexOfFirstActWithinSeventyTwoHours != -1) {
            myLogsPage.clickOnActivityForEditGivenIndex(indexOfFirstActWithinSeventyTwoHours);
            String startTime = modalPage.getStartTime();
            String endTime = modalPage.getEndTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
            LocalDateTime lStartDateTime = LocalDateTime.parse(startTime.trim(), formatter);
            LocalDateTime lEndDateTime = LocalDateTime.parse(endTime.trim(), formatter);
            LocalDateTime lDateToCompare =
                    LocalDateTime.parse(dateTimeSeventyTwoHoursFromNow, formatter);

            if(!(lStartDateTime.compareTo(lDateToCompare)>=0 && lEndDateTime.compareTo(lDateToCompare)>=0)){
                modalPage.clickCloseBtn();
                indexOfFirstActWithinSeventyTwoHours++;
                myLogsPage.clickOnActivityForEditGivenIndex(indexOfFirstActWithinSeventyTwoHours);
                modalPage.waitFor(modalPage.getModal());
            }
            indexOfUpdatedActivity = indexOfFirstActWithinSeventyTwoHours;
         }
    }

    @Step("Check if activities beyond seventy two hours exist")
    public HashMap<String, String> getAllActivitiesBeyondSeventyTwoHours(){
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");

        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        HashMap<String, String> allActivitiesForEdit =
                myLogsPage.getAllActivityLogsForEditNinetySixHoursFromNow(webserviceEndpoint, userAuthenticate);

        return allActivitiesForEdit;
    }

    @Step("Hover on the updated activity")
    public void hoverOnTheUpdatedActivity(){
        myLogsPage.hoverOnTheActivity(indexOfUpdatedActivity);
    }

    @Step("Hover on first activity that is within seventy two hours")
    public void hoverOnTheFirstActivityThatIsWithinSeventyTwoHours(){
        myLogsPage.hoverOnTheActivity(indexOfFirstActWithinSeventyTwoHours);
    }

    @Step("Create a new activity log beyond seventy two hours")
    public void createACompleteActivityLogForTheDayBeyondSeventyTwoHours(){

        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        String stTime = TimeCalendar.getDateNinetySixFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
        createLogActivities(stTime);
    }

    @Step ("Get the values of the pop up box")
    public void getActivityDetailsFromPopUp(){
        activityDetails = myLogsPage.getPopupContents();
    }

    @Step ("Clicks on activity on the screen")
    public void clicksOnFirstActivityWithType(String activityType){
        int index = myLogsPage.getTheFirstActivityFromScreenGivenType(activityType);
        myLogsPage.clickOnActivityForEditGivenIndex(index);
    }

    @Step ("Clicks on invalid tagging button")
    public void clickOnInvalidTaggingBtn(){
           myLogsPage.clickOnInvalidTaggingBtn();
    }

    @Step ("Clicks on first productive time activity")
    public void clicksOnFirstProductiveTimeActivity(){
        int index = myLogsPage.getTheFirstActivityFromScreenGivenType("Productive Time");
        myLogsPage.clickOnActivityForEditGivenIndex(index);
    }

    @Step ("Hover on the deleted activity")
    public void hoverOnTheDeletedActivityOnScreen(){
           myLogsPage.hoverOnDeletedActivityLog();
    }

    @Step("Get the count of Invalid Tagging notifications")
    public int getTheCountOfInvalidTaggingNotifications(){
         return myLogsPage.getInvalidTaggingNotificationCount();
    }

    @Step("Get the number of rows in the invalid tagging list")
    public int getTheNumberOfRowsInInvalidTaggingList(){
           return myLogsPage.getInvalidTaggingNotificationCount();
    }

    @Step("Click on any activity displayed on the screen")
    public void clickOnRandomActivityOnTheScreen(){
        indexOfUpdatedActivity = myLogsPage.clickOnRandomActivityForEdit();
        modalPage.waitFor(modalPage.getModal());
        initialActivityDetails = modalPage.keepActivityDetails("No Changes");
    }

    @Step("Get all the log entries displayed on the screen")
    public List getAllActivityLogEntriesFromTheScreen(){
        return myLogsPage.getAllLogEntriesFromScreen();
    }

    @Step("Click on View Deleted Btn on My Edit Logs")
    public void clickViewDeletedBtn(){
       myLogsPage.clickOnViewDeletedBtn();
    }

    @Step("Get the count of View Deleted notifications")
    public int getTheCountOfViewDeletedNotifications(){
        return myLogsPage.getViewDeletedNotificationCount();
    }

    @Step ("Finds the first activity of type on the screen")
    public int findsFirstActivityWithType(String activityType){
        return myLogsPage.getTheFirstActivityFromScreenGivenType(activityType);
    }

    @Step ("Check the activity color code")
    public void verifyActivityColorCode(WebElement activityToFind, String expectedColor ){
        String backGroundColor =
                myLogsPage.getTheActivityBackgroundColor(activityToFind);
        String borderColor =
                myLogsPage.getTheActivityBorderColor(activityToFind);
        String rgbBackgroundColor = "";
        String rgbBorderColor = "";

        switch (expectedColor) {
            case "blue": rgbBackgroundColor = "rgb(55, 136, 217);";
                         rgbBorderColor = "rgb(55, 136, 217);";
                         break;
            case "green": rgbBackgroundColor = "rgb(0, 153, 51);";
                          rgbBorderColor = "rgb(0, 153, 51);";
                          break;
            case "orange":  rgbBackgroundColor = "rgb(230, 138, 0);";
                            rgbBorderColor = "rgb(230, 138, 0);";
                            break;
            case "tangerine": rgbBackgroundColor = "rgb(255, 159, 128);";
                              rgbBorderColor = "rgb(255, 159, 128);";
                              break;
            case "yellow":  rgbBackgroundColor = "rgb(255, 255, 179);";
                            rgbBorderColor = "rgb(255, 255, 77);";
                            break;
            case "tangerine-transparent":
                            rgbBackgroundColor = "rgb(255, 159, 137);";
                            rgbBorderColor = "rgb(255, 159, 137);";
                            break;
           default: rgbBackgroundColor = "";
                     break;
        }

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(
                backGroundColor.equalsIgnoreCase(rgbBackgroundColor));
        softly.assertThat(
                borderColor.equalsIgnoreCase(rgbBorderColor));
        softly.assertAll();
    }

    @Step ("Get the first inserted activity on the screen")
    public WebElement getTheFirstInsertedActivity(){
           return myLogsPage.getTheFirstInsertedActivity();
    }

    @Step ("Get the first deleted activity on the screen")
    public WebElement getTheFirstDeletedActivity(){
        return myLogsPage.getTheFirstDeletedActivity();
    }

     private void createLogActivities(String stTime){
         clickOnAnyAreaWithNoActivity();
         String endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues(
                         "Start Shift", stTime, "");

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Productive Time 1", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Break (15 minutes)", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Team Meetings", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("TaskUs Training", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Lunch (60 minutes)", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Client Training", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Productive Time 2", "", endTime);

         clickOnAnyAreaWithNoActivity();
         endTime =
                 modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                         ("Idle Time", "", endTime);

         clickOnAnyAreaWithNoActivity();
         modalPage.fillUpModalFieldValuesGivenActivityAndTimeValues
                 ("End Shift", "", endTime);

         clickOnSaveChangesButton();
         clickYesOnConfirmationModal();
         clickOKOnDisplayedMessage();
     }

     @Step ("Click on Zoom In button")
     public void clickOnZoomInBtn(){
        myLogsPage.clickOnZoomInBtn();
     }
}
