package stepdefinitions;

import common.TimeCalendar;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assume;
import org.openqa.selenium.WebElement;
import pageobjects.InvalidTaggingModalPage;
import pageobjects.ModalPage;
import pageobjects.MyLogsPage;
import pageobjects.TicketsPage;
import steps.*;
import testdataobjects.UserAuthenticate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyLogsStepDefintions {

    MyLogsPage myLogsPage;
    ModalPage modalPage;
    InvalidTaggingModalPage invalidTaggingModalPage;
    private EnvironmentVariables environmentVariables;
    TicketsPage ticketsPage;

    int beforeZoomElemHeight = 0;
    int afterZoomElemHeight = 0;
    List<String> timeIntervals;
    String textDisplayOnCenter;
    public String originalStartTime;
    public String originalEndTime;
    String activityToBeDeletedDetails;
    int invalidTagCount;
    String defaultDateDisplayedOnStartTimeFld;
    String defaultDateDisplayedOnEndTimeFld;
    List<String> initialActivityLogs;
    int viewDeletedCount;
    WebElement activityToFind;
    int indexOfActivityToEdiit;

    @Steps
    MyLogSteps myLogSteps;

    @Steps
    ModalSteps modalSteps;

    @Steps
    LoginSteps loginSteps;

    @Steps
    NavigationBarSteps navigationBarSteps;

    @Steps
    TicketsSteps ticketsSteps;

    @Steps
    InvalidTaggingModalSteps invalidTaggingModalSteps;


    @Then("User lands on the My Logs page")
    public void userLandsOnTheMyLogsPage() {
        myLogSteps.verifyMyLogsLandingPage();
    }

   @And("User clicks on the calendar icon")
    public void userClicksOnTheCalendarIcon() {
        myLogSteps.clickOnCalendarIcon();
    }

    @Then("The calendar should be displayed")
    public void theCalendarShouldBeDisplayed() {
        myLogSteps.checkIfCalendarIsDisplayed();
    }

    @And("User clicks on the < on the calendar if enabled")
    public void userClicksOnThePreviousCaretOnTheCalendar() {
        myLogSteps.clickOnPreviousIconOnCalendar();
    }

    @Then("^Previous month should be displayed on the calendar$")
    public void previousMonthShouldBeDisplayedOnTheCalendar() {
        myLogSteps.checkThatPreviousMonthYearIsDisplayedOnTop();
    }

    @And("User clicks on the > on the calendar")
    public void userClicksNextCaretOnTheCalendar() {
        myLogSteps.clickOnNextIconOnCalendar();
    }

    @Then("^Next month should be displayed on the calendar$")
    public void nextMonthShouldBeDisplayedOnTheCalendar() {
        myLogSteps.checkThatNextMonthYearIsDisplayedOnTop();
    }

    @And("User clicks on the previous day date")
    public void userClicksOnThePreviousDayDate() {
       myLogSteps.clickOnPreviousDayOnCalendar();
    }

    @Then("Week where selected date is included should be displayed")
    public void weekWhereSelectedDateIsIncludedShouldBeDisplayed() {
        myLogSteps.checkThatSelectedDayIsTheFirsDayOfTheCalendarWeek();
    }

    @And("User clicks on today's date")
    public void userClicksOnTodaySDate() {
        myLogSteps.clickOnTodaysDayOnCalendar();
    }

    @Then("Current day should be displayed as the first day of the calendar week")
    public void currentDayShouldBeDisplayedAsTheFirstDayOfTheCalendarWeek() {
        myLogSteps.checkThatTodaysDateIsTheFirsDayOfTheCalendarWeek();
    }

    @And("User goes to the day fourty five day previous today")
    public void userGoesToTheDayFourtyFiveDayPreviousToday() {
        myLogSteps.getAllEnabledDatesOnTheCalendar();
    }

    @Then("Verify that only the dates forty two days prior today are enabled")
    public void verifyThatOnlyTheDatesDaysPriorTodayAreEnabled() {
        myLogSteps.checkThatOnlyDaysFortyTwoDaysPriorAreEnabled();
    }

    @Then("Start of the week displayed on header is three days from today and ends three days from today")
    public void startOfTheWeekDisplayedOnHeaderIsThreeDaysFromToday() {
        myLogSteps.verifyDateDisplayedOnTheCenterStartsThreeDaysFromToday();
    }

    @And("Log entries for the given week is correct")
    public void logEntriesForTheGivenWeekIsCorrect() {
        //get an ArrayList of the Values in the API
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        myLogSteps.verifyTheEntriesDisplayedAgainstTheAPI(webserviceEndpoint, userAuthenticate);
    }

    @Then("Pop-up box with the details of the activity should be displayed")
    public void popUpBoxWithTheDetailsOfTheActivityShouldBeDisplayed() {
        myLogSteps.verifyPopUpBox();
    }

    @And("User hover on an activity")
    public void userHoverOnAnActivity() {
        myLogSteps.hoverOnAnActivity();
    }

    @And("Verify the details displayed on the pop up box is the same as the database")
    public void verifyTheDetailsDisplayedonPopupAgainstTheDB(){
        String contentfromScreen = myLogsPage.getActivityDetailsForSelectedActivity();
        String popupContent = myLogsPage.getPopupContents();
         assert contentfromScreen.equalsIgnoreCase(popupContent);
   }

    @And("User clicks on the day seven day previous from today")
    public void userClicksOnTheDaySevenDaysPrevious() {
        myLogSteps.clickOnSevenDaysOnCalendar();
    }


    @Then("User creates a new activity")
    public void userCreatesANewActivity() {
        myLogSteps.createANewActivity();
    }

    @And("User hovers on the recently created activity")
    public void userHoversOnTheRecentlyCreatedActivity() {
        myLogSteps.hoverOnTheInsertedActivity();
    }

    @Then("Verify the details displayed on the pop up box is correct")
    public void verifyTheDetailsDisplayedOnThePopUpBoxIsCorrect() {
        myLogSteps.verifyDetailsOfPopupIsSameWithModal();
    }

    @And("User clicks on the created activity")
    public void userClicksOnTheCreatedActivity() {
        myLogSteps.clickOnTheInsertedActivity();
    }

    @And("User updates the values from the created activity")
    public void userUpdatesTheValuesFromTheCreatedActivity() {
        myLogSteps.updateTheValueOfInsertedActivity();
    }

    @Then("User gets the height of one activity displayed")
    public void userGetsTheHeightOfOneActivityDisplayed() {
        beforeZoomElemHeight = myLogsPage.getTheHeightOfTheFirstActivityDisplayed();
    }

    @And("User clicks on the Zoom In button")
    public void userClicksOnTheZoomInButton() {
       myLogSteps.clickOnZoomInBtn();
    }


    @Then("User gets the height of same activity again")
    public void userGetsTheHeightOfSameActivityAgain() {
        afterZoomElemHeight =  myLogsPage.getTheHeightOfTheFirstActivityDisplayed();
    }

    @And("Verify that the activity height is bigger")
    public void verifyThatTheActivityHeightIsBigger() {
        assert afterZoomElemHeight > beforeZoomElemHeight;
    }

    @And("User clicks on the Zoom Out button")
    public void userClicksOnTheZoomOutButton() {
        myLogsPage.clickOnZoomOutBtn();
    }

    @And("Verify that the activity height is the same as initial display")
    public void verifyThatTheActivityHeightIsTheSameAsInitialDisplay() {
        assert afterZoomElemHeight == beforeZoomElemHeight;
    }

    @Then("User gets the time displayed per row")
    public void userGetsTheTimeIntervalsDisplayed() {
        timeIntervals = myLogsPage.getTimeIntervals();
    }

    @Then("Verify that the time intervals are {int} minutes")
    public void verifyThatTheTimeIntervalsAreMinutes(int arg0) {
        String startDisplay = timeIntervals.get(0);
        String endDisplay = timeIntervals.get(1);

        long mins = TimeCalendar.getDurationBetweenTwoTimeInMinutes(
                startDisplay.toUpperCase(), endDisplay.toUpperCase());
        assert mins == arg0;
    }

    @Then("Verify that Zoom In button can not be clicked again")
    public void verifyThatZoomInButtonCanNotBeClickedAgain() {
        assert !myLogsPage.isZoomInBtnClickable();
    }

    @Then("Verify that Zoom Out button can not be clicked again")
    public void verifyThatZoomOutButtonCanNotBeClickedAgain() {
        assert !myLogsPage.isZoomOutBtnClickable();
    }

    @Then("User clicks the previous button")
    public void userClicksThePreviousButtonOnTheCalendar() {
        myLogSteps.clicksOnThePreviousBtn();
    }

    @And("Verify that the start and end day of the week displayed at the center moved one day less")
    public void verifyThatTheStartAndEndDayOfTheWeekDisplayedAtTheCenterMovedOneDayLess() {
        myLogSteps.checkThatTheWeekDateChangedToStartAtOneDayLess(textDisplayOnCenter);
    }

    @Then("User gets the week displayed on center")
    public void userGetsTheWeekDisplayedOnCenter() {
       textDisplayOnCenter = myLogsPage.getDateDisplayedOnCenter();
    }

    @Then("Verify that the next button can not be clicked")
    public void verifyThatTheNextButtonCanNotBeClicked() {
        assert myLogsPage.isNextBtnClickable() == false;
    }

    @And("User clicks the next button")
    public void userClicksTheNextButton() {
        myLogSteps.clicksOnTheNextBtn();
    }

    @Then("Verify that the start and end day of the week displayed at the center moved one day more")
    public void verifyThatTheStartAndEndDayOfTheWeekDisplayedAtTheCenterMovedOneDayMore() {
        myLogSteps.checkThatTheWeekDateChangedToStartAtOneDayMore(textDisplayOnCenter);
    }

    @Given("an activity log already exists for the user")
    public void anActivityLogAlreadyExistsForTheUser() {

        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TM");
        HashMap<String, String> allActivitiesForEdit = myLogSteps.getAllActivitiesForEdit();

        if(allActivitiesForEdit.size() == 0){
            myLogSteps.createACompleteActivityLogForTheDayWithinSeventyTwoHours();
            navigationBarSteps.logOut();
            loginSteps.UserLogsIn("TL");
            navigationBarSteps.clickOnTickets();
            ticketsSteps.clickFirstValueInList();
            ticketsSteps.approveTheTicket();
            ticketsSteps.clickYesOnApproval();
            ticketsSteps.clickOKOnTicketApprovedMessage();
        }
        navigationBarSteps.logOut();

    }

    @Then("User clicks on one of the existing activity")
    public void userClicksOnOneOfTheExistingActivity() {
        //myLogSteps.clickOnFirstActivityOnTheScreen();
        myLogSteps.clickOnRandomActivityOnTheScreen();
    }



    @And("Verify update activity log modal form is displayed")
    public void verifyUpdateActivityLogModalFormIsDisplayed() {
        myLogSteps.checkThatUpdateActivityLogModalDisplays();
        myLogSteps.checkActivityLogModalTitle("Update Activity Log");
    }

    @And("Verify the editable fields on the Update Activity Log form")
    public void verifyTheEditableFieldsOnTheUpdateActivityLogForm() {
        modalSteps.checkAllFieldsInTheModalAreEditable();
    }

    @And("User takes note of the activity details from the popup")
    public void userTakesNoteOfTheActivityDetailsFromThePopup() {
        String popupContent = myLogSteps.getTheContentsOfPopup();
        myLogSteps.parseTheContentsOfPopup(popupContent);
    }

    @Then("Verify the activity details displayed on the update activity log is same with the popup")
    public void verifyTheActivityDetailsDisplayedOnTheUpdateActivityLogIsSameWithThePopup() {
        myLogSteps.checkTheContentsOfModalIsSameWithPopup();
    }

    @Then("User updates the values of the selected activity")
    public void userUpdatesTheValuesOfTheSelectedActivity() {
        modalSteps.selectActionReason("Forgot to log-in");
        modalSteps.addRemarks("This is a sample additional information.");
        myLogSteps.updateActivityDetails = modalPage.keepActivityDetails("Update");
        modalSteps.clickSaveOnModal();
        modalSteps.clickOKOnWarningMessage();
        modalSteps.clickYesOnConfirmationWindow();
    }

    @Then("User refresh the page")
    public void userRefreshThePage() {
        myLogSteps.userRefreshThePage();
        myLogSteps.updateActivityDetails = null;
   }

    @And("an activity log within seventy two hours from now already exists for the user")
    public void anActivityLogWithinSeventyHoursFromNowAlreadyExistsForTheUser() {
        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TM");
        HashMap<String, String> allActivitiesForEdit = myLogSteps.getAllActivitiesWithinSeventyTwoHours();

        if(allActivitiesForEdit.size() == 0){
            myLogSteps.createACompleteActivityLogForTheDayWithinSeventyTwoHours();
            navigationBarSteps.logOut();
            loginSteps.UserLogsIn("TL");
            navigationBarSteps.clickOnTickets();
            ticketsSteps.clickFirstValueInList();
            ticketsSteps.approveTheTicket();
            ticketsSteps.clickYesOnApproval();
            ticketsSteps.clickOKOnTicketApprovedMessage();
        }
        navigationBarSteps.logOut();
    }

    @Then("User updates the values of the selected activity but clicks No on confirmation window")
    public void userUpdatesTheValuesOfTheSelectedActivityButClicksNoOnConfirmationWindow() {
        modalSteps.selectActionReason("Forgot to log-in");
        modalSteps.addRemarks("This is a sample additional information.");
        myLogSteps.updateActivityDetails = modalPage.keepActivityDetails("Update");
        modalSteps.clickSaveOnModal();
        modalSteps.clickNoOnSaveChangesWindow();
    }

    @And("Verify the Save button on the Update Activity Log form can still be clicked")
    public void verifyTheSaveButtonOnTheUpdateActivityLogFormCanStillBeClicked() {
        myLogSteps.verifyIfSaveBtnOnLogFormCanStillBeClicked();
    }

    /*@And("Verify confirmation message submit ticket displays")
    public void verifyConfirmationMessageSubmitTicketYesNoDisplays() {
        myLogSteps.verifyConfirmationMessageSubmitTicketYesNoDisplays();
    }*/

    @Then("Click Yes on confirmation message")
    public void clickYesOnConfirmationMessage() {
        myLogSteps.clickYesOnConfirmationModal();
    }

    @And("Verify Ticket Submitted message displays")
    public void verifyTicketSubmittedConfirmationMessageDisplays() {
        myLogSteps.checkThatTicketSubmittedMessageDisplays();
    }

    @Then("Click OK button")
    public void clickOKButtonOnTicketSubmittedMessage() {
        myLogSteps.clickOKOnDisplayedMessage();
    }

    @And("Verify Save Changes button on the page is not enabled")
    public void verifySaveChangesButtonOnThePageIsNotEnabled() {
        myLogSteps.checkThatSaveChangesBtnIsNotEnabled();
    }

    @And("Verify Save Changes button on the page is enabled")
    public void verifySaveChangesButtonOnThePageIsEnabled() {
        myLogSteps.checkThatSaveChangesBtnIsEnabled();
    }

    @And("User clicks the Save Changes on my log page")
    public void userClicksTheSaveChangesOnMyLogPage() {
        myLogSteps.clickOnSaveChangesButton();
    }

    @And("Verify the Campaign field display should have the campaign where the user belongs")
    public void verifyTheCampaignFieldDisplayShouldHaveTheCampaignWhereTheUserBelongs() {
        myLogSteps.checkCampaignFieldOnModalIsWhereTheUserBelong();
    }

    @Then("Click on the activity dropdown")
    public void clickOnTheActivityDropdown() {
        myLogSteps.clickOnActivityTypeDropDown();
    }

    @And("Verify the displayed activities are those for the campaign")
    public void verifyTheDisplayedActivitiesAreThoseForTheCampaign() {
        modalSteps.checkActivitiesDisplayedAreForTheCampaign();
    }

    @And("Select {string} from the activity type dropdown")
    public void selectFromTheActivityTypeDropdown(String arg0) {
        modalSteps.selectAnActivityFromDropdown(arg0);
    }

    @Then("Verify {string} displays on the activity type field")
    public void verifyDisplaysOnTheActivityTypeField(String arg0) {
        modalSteps.checkSelectedActivityIsWhatsDisplayed(arg0);
    }

    @Then("Click the calendar icon on the start time field")
    public void clickTheCalendarIconOnTheStartTimeField() {
        modalSteps.clickCalendarOnStartTime();
    }

    @And("Verify calendar is displayed")
    public void verifyCalendarIsDisplayed() {
        modalSteps.checkThatStartTimeCalendarIsDisplayed();
    }

    @Then("Click the clock icon on the calendar")
    public void clickTheClockIconOnTheCalendar() {
        modalSteps.clickSelectTimeBtn();
    }

    @And("Verify time page is displayed")
    public void verifyTimePageIsDisplayed() {
        modalSteps.checkThatTimeSelectorElementsAreDisplayed();
    }

    @Then("Click the clear button on the calendar")
    public void clickTheClearButtonOnTheCalendar() {
        modalSteps.clickTheClearBtn();
    }

    @And("Verify the date on the start time field is removed")
    public void verifyTheDateOnTheStartTimeFieldIsRemoved() {
        modalSteps.checkTheValueOfStartTimeFieldIsNotADate();
    }

    @Then("Click the up icon on the hour")
    public void clickTheUpIconOnTheHour() {
        modalSteps.clickOnUpIconOnHour();
    }

    @And("Verify the hour increased by 1")
    public void verifyTheHourIncreasedBy() {
        modalSteps.checkHourValueWasIncreasedByOne();
    }

    @Then("Get the value of the hour")
    public void getTheValueOfTheHour() {
        modalSteps.getHourValue();
    }

    @Then("Get the value of the minute")
    public void getTheValueOfTheMinute() {
        modalSteps.getMinuteValue();
    }

    @Then("Get the value of the second")
    public void getTheValueOfTheSecond() {
        modalSteps.getSecondValue();
    }

    @Then("Click the up icon on the minute")
    public void clickTheUpIconOnTheMinute() {
        modalSteps.clickOnUpIconOMinute();
    }

    @Then("Click the up icon on the second")
    public void clickTheUpIconOnTheSecond() {
        modalSteps.clickOnUpIconOnSecond();
    }

    @And("Verify the minute increased by 1")
    public void verifyTheMinuteIncreasedBy() {
        modalSteps.checkMinuteValueWasIncreasedByOne();
    }

    @And("Verify the second increased by 1")
    public void verifyTheSecondIncreasedBy() {
        modalSteps.checkSecondValueWasIncreasedByOne();
    }

    @Then("Click the down icon on the hour")
    public void clickTheDownIconOnTheHour() {
        modalSteps.clickOnDownIconOnHour();
    }

    @Then("Click the down icon on the minute")
    public void clickTheDownIconOnTheMinute() {
        modalSteps.clickOnDownIconOMinute();
    }

    @Then("Click the down icon on the second")
    public void clickTheDownIconOnTheSecond() {
        modalSteps.clickOnDownIconOnSecond();
    }

    @And("Verify the hour decreased by 1")
    public void verifyTheHourDecreasedBy() {
        modalSteps.checkHourValueWasDecreasedByOne();
    }

    @And("Verify the minute decresed by 1")
    public void verifyTheMinuteDecresedBy() {
        modalSteps.checkMinuteValueWasDecreasedByOne();
    }

    @And("Verify the second decreased by 1")
    public void verifyTheSecondDecreasedBy() {
        modalSteps.checkSecondValueWasDecreasedByOne();
    }

    @Then("Click the AM or PM button")
    public void clickTheAMOrPMButton() {
        modalSteps.clickOnAmOrPmButton();
    }

    @Then("Get the value of the AM or PM button")
    public void getTheValueOfTheAMOrPMButton() {
        modalSteps.getAMorPMValue();
    }

    @And("Verify it changes to the other value other than displayed")
    public void verifyItChangesToTheOtherValueOtherThanDisplayed() {
        modalSteps.checkAmOrPmValueChanges();
    }

    @Then("Click the calendar icon on the end time field")
    public void clickTheCalendarIconOnTheEndTimeField() {
        modalSteps.clickCalendarOnEndTime();
    }

    @And("Verify the date on the end time field is removed")
    public void verifyTheDateOnTheEndTimeFieldIsRemoved() {
        modalSteps.checkTheValueOfEndTimeFieldIsNotADate();
    }

    @And("Get the value of the AM or PM button again")
    public void getTheValueOfTheAMOrPMButtonAgain() {
        modalSteps.getAMorPMValue();
    }

    @Then("Click on the action reason dropdown")
    public void clickOnTheActionReasonDropdown() {
        modalSteps.clickOnActionReasonDropDown();
    }

    @And("Verify the displayed action reasons choices are complete")
    public void verifyTheDisplayedReasonsAreComplete() {
        modalSteps.checkActionReasonDropDownAreValuesComplete();
    }

    @And("Set an end time that is before the start time")
    public void setAnEndTimeThatIsBeforeTheStartTime() {
        modalSteps.setAnEndTimeBeforeStartTime();
    }

    @And("Select an action reason")
    public void selectAnActionReason() {
        modalSteps.selectActionReason("Forgot to log-in");
    }

    @Then("Click the Save button on the activity log form")
    public void clickTheSaveButtonOnTheActivityLogForm() {
        modalSteps.clickSaveOnModal();
    }

    @And("Verify error message {string} displays")
    public void verifyErrorMessageDisplays(String arg0) {
        modalSteps.checkThatErrorMessageDisplays(arg0);
    }

    @And("Select {string} an action reason")
    public void selectAnActionReason(String arg0) {
        modalSteps.selectActionReason(arg0);
    }

    @And("Verify {string} is displayed on the action reason field")
    public void verifyIsDisplayedOnTheActionReasonField(String arg0) {
        modalSteps.checkThatTheSelectedActionReasonIsDisplayed(arg0);
    }

    @Then("Click on the activity log form Close button")
    public void clickOnTheActivityLogFormCloseButton() {
        modalSteps.clickTheActivityLogFormCloseBtn();
    }

    @And("Verify that the Update Activity log form is gone")
    public void verifyThatTheUpdateActivityLogFormIsGone() {
        modalSteps.checkThatUpdateActivityLogFormIsNotPresent();
    }

    @And("Verify insert activity log modal form is displayed")
    public void verifyInsertActivityLogModalFormIsDisplayed() {
        myLogSteps.checkThatInsertActivityLogModalDisplays();
        myLogSteps.checkActivityLogModalTitle("Insert Activity Log");
    }

    @And("Verify that the Insert Activity log form is gone")
    public void verifyThatThInsertActivityLogFormIsGone() {
        modalSteps.checkThatInsertActivityLogFormIsNotPresent();
    }

    @Then("User clicks on any area where there's no activity")
    public void userClicksOnAnyAreaWhereThereSNoActivity() {
        myLogSteps.clickOnAnyAreaWithNoActivity();
    }

    @And("Verify the default values for all the other fields")
    public void verifyTheDefaultValuesForAllTheOtherFields() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(
                modalPage.getSelectedActivityType().equalsIgnoreCase("--Select Activity Type--"));
        softly.assertThat(
                !modalPage.getStartTime().isEmpty());
        softly.assertThat(
                modalPage.getEndTime().isEmpty());
        softly.assertThat(
                !modalPage.getSelectedActionReason().equalsIgnoreCase("--Select Action Reason--"));
        softly.assertThat(
                modalPage.getRemarks().isEmpty());
        softly.assertAll();
    }

    @And("Verify the editable fields on the Insert Activity Log form")
    public void verifyTheEditableFieldsOnTheInsertActivityLogForm() {
        modalSteps.checkAllFieldsInTheModalAreEditable();
    }

    @Then("Insert an activity log with date within seventy two hours from today")
    public void insertAnActivityLogWithDateWithinSeventyTwoHours() {
        myLogSteps.createANewActivityWithinSeventyTwoHours();
    }

    @Then("Verify inserted activity is gone")
    public void verifyInsertedActivityIsGone() {
        myLogSteps.checkThatNoMoreInsertedActivityLogDisplays();
    }

    @Then("Insert an activity log with date beyond seventy two hours")
    public void insertAnActivityLogWithDateBeyondSeventyTwoHours() {
        myLogSteps.createANewActivityBeyondSeventyTwoHours();
    }

    @And("Verify the inserted activity log displays on the grid")
    public void verifyTheInsertedActivityLogDisplaysOnTheGrid() {
         myLogSteps.checkThatInsertedActivityLogDisplays();
    }

    @And("Verify insert activity logs is more than one")
    public void verifyInsertActivityLogsIsMoreThanOne() {
        myLogSteps.checkThatInsertedActivityLogsIsMoreThanOne();
    }

    @Then("Click OK button on Warning message modal")
    public void clickOKButtonOnWarningMessage() {
        modalSteps.clickTheOKButtonOnWarningMessage();
    }

    @And("Click Yes on Save Activity Log modal")
    public void clickYesOnSaveActivityLogModal() {
        modalSteps.clickYesBtnOnSaveOrUpdateActivityLogModal();
    }

    @Then("User goes to the date that is within seventy two hours from today")
    public void goToDateThatisSeventyTwoHoursFromToday() {
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        myLogSteps.startTime = TimeCalendar.getDateFourtyEightHoursFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
        myLogSteps.clickOnPreviousButtonUntilStartTimeDisplays(myLogSteps.startTime);
    }

    @And("User goes to the date that is beyond seventy two hours from today")
    public void goToDateThatIsBeyondSeventyTwoHoursFromToday() {
        UserAuthenticate userAuthenticate =
                Serenity.sessionVariableCalled("userAuthenticate");
        String timeZoneIANA = userAuthenticate.getTimeZoneIANA();
        myLogSteps.startTime = TimeCalendar.getDateNinetySixFromNowInyyyyMMddhhmmssaFormat(timeZoneIANA);
        myLogSteps.clickOnPreviousButtonUntilStartTimeDisplays(myLogSteps.startTime);
    }

    @Then("Click No on the submit ticket confirmation message")
    public void clickNoOnTheSubmitTicketConfirmationMessage() {
        modalSteps.clickNoOnConfirmationWindow();
    }

    @And("Insert an activity log within nine hours from now")
    public void insertAnActivityLogWithinNineHoursFromNow() {
        myLogSteps.insertACompleteActivityWithinNineHoursFromNow();
    }

    @And("User clicks on one of the existing activity that is beyond seventy two hours from now")
    public void userClicksOnOneOfTheExistingActivityThatIsBeyondSeventyTwoHoursFromNow() {
        myLogSteps.clickOnFirstBeyondSeventyTwoHoursActivityOnTheScreen();
    }

    @And("User clicks on second activity that is beyond seventy two hours from now")
    public void userClicksOnSecondActivityThatIsBeyondSeventyTwoHoursFromNow() {
        myLogSteps.clickOnSecondActivityBeyondSeventyTwoHoursOnTheScreen();
    }

    @And("Verify no error message displays")
    public void verifyErrorMessageDoesNotDisplays() {
        modalSteps.checkThatNoErrorMessageModalIsPresent();
    }

    @Then("User clicks on one of the existing activity that is within seventy two hours from now")
    public void userClicksOnOneOfTheExistingActivityThatIsWithinSeventyTwoHoursFromNow() {
        myLogSteps.clickOnFirstActivityThatIsWithinSeventyTwoHoursOnTheScreen();
    }

    @Given("an activity log beyond seventy two hours before now already exists for the user")
    public void anActivityLogBeyondSeventyHoursFromNowAlreadyExistsForTheUser() {
        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TM");
        HashMap<String, String> allActivitiesForEdit = myLogSteps.getAllActivitiesBeyondSeventyTwoHours();

        if(allActivitiesForEdit.size() == 0){
            myLogSteps.createACompleteActivityLogForTheDayBeyondSeventyTwoHours();
            navigationBarSteps.logOut();
            loginSteps.UserLogsIn("TL");
            navigationBarSteps.clickOnTickets();
            ticketsSteps.selectAValueFromTicketStatus("For Supervisor then VP Approval");
            ticketsSteps.clickFirstValueInList();
            ticketsSteps.approveTheTicket();
            ticketsSteps.clickYesOnApproval();
            ticketsSteps.clickOKOnTicketApprovedMessage();
            loginSteps.UserLogsIn("VP");
            navigationBarSteps.clickOnTickets();
            ticketsSteps.selectAValueFromTicketStatus("For Supervisor then VP Approval");
            ticketsSteps.clickFirstValueInList();
            ticketsSteps.approveTheTicket();
            ticketsSteps.clickYesOnApproval();
            ticketsSteps.clickOKOnTicketApprovedMessage();
       }
        navigationBarSteps.logOut();
    }

    @And("User hover on the activity that was updated")
    public void userHoverOnTheActivityThatWasUpdated() {
        myLogSteps.hoverOnTheUpdatedActivity();
    }

    @And("User hover on the first activity that is within seventy two hours from now")
    public void userHoverOnTheFirstActivityThatIsWithinSeventyTwoHoursFromNow() {
        myLogSteps.hoverOnTheFirstActivityThatIsWithinSeventyTwoHours();
    }

    @And("User gets the details of the pop up content")
    public void userGetsTheDetailsOfThePopUpContent() {
        myLogSteps.getActivityDetailsFromPopUp();
    }

    @Then("Verify the trash icon is present in the upper right corner of the form")
    public void verifyTheTrashIconIsPresentInTheUpperRightCornerOfTheForm() {
        modalSteps.checkThatTrashBtnIsDisplayed();
    }

    @Then("Get the value of the start time field")
    public void getTheValueOfTheStartTimeField() {
        originalStartTime = modalSteps.getTheOriginalStartTimeValue();
    }

    @And("Change the PM or AM value of start time field to a value with first letter on big cap and second on small cap")
    public void changeTheValueofStartTimeToFirstLetterCapAndTabOut() {
        modalSteps.changeTheValueOfAMOrPMOfStartTime(originalStartTime, "First letter uppercase");
        modalSteps.clickTabOutFromStartTimeField();
    }

    @And("Change the PM or AM value of start time field to a value with first letter on small cap and second on big cap")
    public void changeTheValueofStartTimeToSecondLetterCapAndTabOut() {
        modalSteps.changeTheValueOfAMOrPMOfStartTime(originalStartTime, "Last letter uppercase");
        modalSteps.clickTabOutFromStartTimeField();
    }

    @And("Change the PM or AM value of start time field to a value to both small cap")
    public void changeTheValueOfStartTimeToBothCapAndTabOut() {
        modalSteps.changeTheValueOfAMOrPMOfStartTime(originalStartTime, "Both small cap");
        modalSteps.clickTabOutFromStartTimeField();
    }

    @Then("Verify the value of the start time field is the same as original value")
    public void verifyTheValueOfTheStartTimeFieldIsTheSameAsOriginalValue() {
        modalSteps.checkThatValueOfStartTimeIsSameAsOriginal(originalStartTime);
    }

    @Then("Get the value of the end time field")
    public void getTheValueOfTheEndTimeField() {
        originalEndTime = modalSteps.getTheOriginalEndTimeValue();
    }

    @And("Change the PM or AM value of end time field to a value with first letter on big cap and second on small cap")
    public void changeTheValueofEndTimeToFirstLetterCapAndTabOut() {
        modalSteps.changeTheValueOfAMOrPMOfEndTime(originalEndTime, "First letter uppercase");
        modalSteps.clickTabOutFromEndTimeField();
    }

    @And("Change the PM or AM value of end time field to a value with first letter on small cap and second on big cap")
    public void changeTheValueofEndTimeToSecondLetterCapAndTabOut() {
        modalSteps.changeTheValueOfAMOrPMOfEndTime(originalEndTime, "Last letter uppercase");
        modalSteps.clickTabOutFromEndTimeField();
    }

    @And("Change the PM or AM value of end time field to a value to both small cap")
    public void changeTheValueOfEndTimeToBothCapAndTabOut() {
        modalSteps.changeTheValueOfAMOrPMOfEndTime(originalEndTime, "Both small cap");
        modalSteps.clickTabOutFromEndTimeField();
    }

    @Then("Verify the value of the end time field is the same as original value")
    public void verifyTheValueOfTheEndTimeFieldIsTheSameAsOriginalValue() {
        modalSteps.checkThatValueOfEndTimeIsSameAsOriginal(originalEndTime);
    }

    @Then("Verify ITSD reference number field is displayed")
    public void verifyITSDReferenceNumberFieldIsDisplayed() {
        modalSteps.checkThatITSDRefNumFieldIsDisplayed();
    }

    @Then("Verify that field error message {string} is displayed")
    public void verifyThatFieldErrorMessageIsDisplayed(String arg0) {
        modalSteps.checkThatErrorMessageDisplays(arg0);
    }

    @Then("Select <reason> on action reason dropdown")
    public void selectReasonAnActionReason(List<Map<String,String>> reasons) {
        modalSteps.selectActionReason(reasons.get(0).get(0));
    }

    @Then("Verify ITSD reference number field is not displayed")
    public void verifyITSDReferenceNumberFieldIsNotDisplayed() {
        modalSteps.checkThatITSDRefNumFieldIsNotDisplayed();
    }

    @Then("User logs out")
    public void userLogsOut() {
        navigationBarSteps.logOut();
    }

    @Then("User clicks on the first {string} activity")
    public void userClicksOnTheFirstActivityWithType(String arg0) {
        myLogSteps.clicksOnFirstActivityWithType(arg0);
    }

    @Then("Click on trash icon")
    public void clickOnTrashIcon() {
         activityToBeDeletedDetails = modalSteps.textToCompareToInvalidTaggingList();
         modalSteps.clickOnTrashIcon();
    }

    @And("Click Yes on Delete Activity Log modal")
    public void clickYesOnDeleteActivityLogModal() {
        modalSteps.clickYesBtnOnSaveOrUpdateActivityLogModal();
        modalSteps.clickOKOnWarningMessage();
    }

    @And("Click on the Invalid Tagging button on the My Logs page")
    public void clickOnTheInvalidTaggingButtonOnTheMyLogsPage() {
        myLogSteps.clickOnInvalidTaggingBtn();
    }

   @Then("Verify the details of the deleted activity appears on the invalid tagging list")
    public void verifyTheDetailsOfTheDeletedActivityAppearsOnTheInvalidTaggingList() {
       invalidTaggingModalSteps.checkThatDeletedActivityIsInInvalidTaggingList(activityToBeDeletedDetails);
    }

    @Then("Close the invalid tagging modal")
    public void closeTheInvalidTaggingModal() {
        invalidTaggingModalSteps.clickTheCloseBtn();
    }

    @And("Hover on the deleted activity log on the screen")
    public void clickOnTheDeletedActivityLogOnTheScreen() {
        myLogSteps.hoverOnTheDeletedActivityOnScreen();
    }

    @Then("Verify {string} displays on the popup")
    public void verifyDisplaysOnThePopup(String arg0) {
        assert myLogSteps.getTheContentsOfPopup().equalsIgnoreCase(arg0);
    }

    @And("User keep the count of the invalid tagging notification")
    public void userKeepTheCountOfTheInvalidTaggingNotification() {
        invalidTagCount = myLogSteps.getTheCountOfInvalidTaggingNotifications();
    }

    @Then("Verify the number of invalid tagging notification count was added by {string}")
    public void verifyTheNumberOfInvalidTaggingNotificationCountWasAddedBy(String arg0) {
        int newInvalidTagCnt = myLogSteps.getTheCountOfInvalidTaggingNotifications();
        assert newInvalidTagCnt == invalidTagCount + Integer.parseInt(arg0);
        invalidTagCount = newInvalidTagCnt;
    }

    @And("Verify the number of rows in the invalid tagging list is the same as those on notifications")
    public void verifyTheNumberOfRowsInTheInvalidTaggingListIsTheSameAsThoseOnNotifications() {
        assert invalidTagCount == myLogSteps.getTheNumberOfRowsInInvalidTaggingList();
    }

    @Then("User creates a new {string} activity")
    public void userCreatesANewActivity(String arg0) {
        String startTime = "";
        String endTime = "";

        if(arg0.equalsIgnoreCase("Start Shift")) {
           startTime = modalPage.getStartTime();
           endTime = TimeCalendar
                    .getTimeValueGivenTimeAndMinusOrAddHours(startTime, 5, "add",
                            "Minute", "yyyy-MM-dd HH:mm:ss a");
        }

        defaultDateDisplayedOnEndTimeFld = endTime;
        modalPage.enterValuesAndSaveModalAndClickPromptMessageAfter(arg0, startTime, endTime,
                "Forgot to log-in", "Sample remarks", "Insert");
    }

    @Then("User keeps the date displayed on Start Time field")
    public void userKeepsTheDateDisplayedOnStartTimeField() {
        defaultDateDisplayedOnStartTimeFld = modalSteps.getTheOriginalStartTimeValue();
    }

    @And("Verify {string} tag displays on the invalid tagging list")
    public void verifyTagDisplaysOnTheInvalidTaggingList(String arg0) {
        invalidTaggingModalPage.getTextToCompareWithInvalidTaggingListGivenActivityType(arg0,
                defaultDateDisplayedOnStartTimeFld, defaultDateDisplayedOnEndTimeFld);
    }

    @Then("User clicks on an area before the first {string} activity")
    public void userClicksOnAnAreaBeforeTheFirstActivity(String arg0) {
        myLogsPage.clickOnAnAreaBeforeFirstStartActivity();
    }

    @Then("Click on action button corresponding to the deleted activity")
    public void clickOnActionButtonCorrespondingToTheDeletedActivity() {
        invalidTaggingModalSteps.clickOnAnActionBtn();
    }

    @And("Verify the number of invalid tagging notification count was decreased by {string}")
    public void verifyTheNumberOfInvalidTaggingNotificationCountWasDecreasedBy(String arg0) {
        int newInvalidTagCnt = myLogSteps.getTheCountOfInvalidTaggingNotifications();
        assert newInvalidTagCnt == invalidTagCount - Integer.parseInt(arg0);
        invalidTagCount = newInvalidTagCnt;
    }

    @Then("Verify {string} is displayed on the dialog message")
    public void verifyIsDisplayedOnTheDialogMessage(String arg0) {
        modalSteps.checkThatDisplayDialogMessageIsSameAsSomeValue(arg0);
    }

    @And("Click No on Delete Activity Log modal")
    public void clickNoOnDeleteActivityLogModal() {
        modalSteps.clickNoOnConfirmationModal();
    }

    @And("User gets all the log entries displayed initially")
    public void userGetsAllTheLogEntriesDisplayedInitially() {
        initialActivityLogs = myLogSteps.getAllActivityLogEntriesFromTheScreen();
    }

    @And("Verify the log entries displayed on the screen is still the same")
    public void verifyTheLogEntriesDisplayedOnTheScreenIsStillTheSame() {
        List<String> newActivityLogsOnScreen  = myLogSteps.getAllActivityLogEntriesFromTheScreen();
        Collections.sort(initialActivityLogs);
        Collections.sort(newActivityLogsOnScreen);
        assert initialActivityLogs.equals(newActivityLogsOnScreen);
    }

    @And("User keep the count of the view deleted notification")
    public void userKeepTheCountOfTheViewDeletedNotification() {
        viewDeletedCount = myLogSteps.getTheCountOfViewDeletedNotifications();
    }

    @And("Verify the number of view deleted notification count was increased by {string}")
    public void verifyTheNumberOfViewDeletedNotificationCountWasIncreasedBy(String arg0) {
        int newviewDeletedCount = myLogSteps.getTheCountOfViewDeletedNotifications();
        assert newviewDeletedCount == viewDeletedCount + Integer.parseInt(arg0);
        viewDeletedCount = newviewDeletedCount;
    }

    @And("Click on the view deleted notification button")
    public void clickOnTheForDelNotificationButton() {
        myLogSteps.clickViewDeletedBtn();
    }

    @Then("Verify the view deleted modal screen does not have any data")
    public void verifyTheViewDeletedModalScreenDoesNotHaveAnyData() {
        assert myLogSteps.getTheCountOfViewDeletedNotifications() == 0;
    }

    @And("Verify the log entries displayed on the screen is not the same")
    public void verifyTheLogEntriesDisplayedOnTheScreenIsNotTheSame() {
        List<String> newActivityLogsOnScreen  = myLogSteps.getAllActivityLogEntriesFromTheScreen();
        Collections.sort(initialActivityLogs);
        Collections.sort(newActivityLogsOnScreen);
        assert !initialActivityLogs.equals(newActivityLogsOnScreen);
    }

    @Then("Verify the view deleted modal screen have data")
    public void verifyTheViewDeletedModalScreenHaveData() {
        assert myLogSteps.getTheCountOfViewDeletedNotifications() > 0;
    }

    @Then("Click on {string} on the calendar")
    public void clickOnOnTheCalendar(String arg0) {
        myLogsPage.clickOnFeb22();
    }

    @Then("User keeps the date displayed on End Time field")
    public void userKeepsTheDateDisplayedOnEndTimeField() {
        defaultDateDisplayedOnEndTimeFld = modalSteps.getTheOriginalEndTimeValue();
    }

    @Then("User finds the first {string} activity on the screen")
    public void userFindsTheFirstActivityOnTheScreen(String activityType) {

        switch (activityType) {
            case "Inserted":
                activityToFind =  myLogSteps.getTheFirstInsertedActivity();
                break;
            case "Deleted":
                activityToFind = myLogSteps.getTheFirstDeletedActivity();
                break;
            default:
                int indexOfActivityToFind = myLogSteps.findsFirstActivityWithType(activityType);
                indexOfActivityToEdiit = indexOfActivityToFind;
                if(indexOfActivityToFind != -1)
                    activityToFind = myLogsPage.getActivities().get(indexOfActivityToFind);
                break;
        }
    }

    @And("Verify that the activity color is {string}")
    public void verifyThatTheActivityColorIs(String arg0) {
        Assume.assumeTrue(activityToFind != null);
        myLogSteps.verifyActivityColorCode(activityToFind, arg0);
    }

    @Then("Add one hour to the existing end time field value")
    public void addOneHoourToTheExistingEndTimeFieldValue() {
        String endTime = modalPage.getEndTime();
        String newEndTime = TimeCalendar.getDateOneHourAfterGiveDateInyyyyMMddhhmmssaFormat(endTime);
        modalPage.enterEndTime(newEndTime);
    }

    @And("User finds the next activity from the edited activity")
    public void userFindsTheNextActivityFromTheEditedActivity() {
        indexOfActivityToEdiit++;
        if(indexOfActivityToEdiit != -1)
            activityToFind = myLogsPage.getActivities().get(indexOfActivityToEdiit);
   }

    @And("Verify confirmation message {string} displays")
    public void verifyConfirmationMessageDisplays(String arg0) {
       modalSteps.checkThatDisplayDialogMessageIsSameAsSomeValue(arg0);
    }

    @Then("Click OK on warning message if displayed")
    public void clickOKOnWarningMessageIfDisplayed() {
        modalSteps.clickOKOnWarningMessage();
    }

    @Given("an activity log within seventy two hours from now not yet approved already exists for the user")
    public void anActivityLogWithinSeventyHoursFromNowNotYetApprovedAlreadyExistsForTheUser() {
        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TL");
        navigationBarSteps.clickOnTickets();
        List<WebElement> editLogs = ticketsPage.getViewTickets();
        if(editLogs.size() == 0) {
            navigationBarSteps.logOut();
            loginSteps.UserLogsIn("TM");
            myLogSteps.createACompleteActivityLogForTheDayWithinSeventyTwoHours();
        }
        navigationBarSteps.logOut();
    }

    @Given("an activity log beyond seventy two hours before now not yet approved already exists for the user")
    public void anActivityLogBeyondSeventyHoursBeforeNowNotYetApprovedAlreadyExistsForTheUser() {
        loginSteps.theTimeWarpEditorHomePage();
        loginSteps.UserLogsIn("TL");
        navigationBarSteps.clickOnTickets();
        ticketsSteps.selectAValueFromTicketStatus("For Supervisor then VP Approval");
        List<WebElement> editLogs = ticketsPage.getViewTickets();
        if(editLogs.size() == 0) {
                navigationBarSteps.logOut();
                loginSteps.UserLogsIn("TM");
                myLogSteps.createACompleteActivityLogForTheDayBeyondSeventyTwoHours();
        }else{
            String updatedDate = ticketsPage.getUpdatedDateOfFirstTicketInTheList();
            if(updatedDate != null && !updatedDate.trim().isEmpty()) {
                navigationBarSteps.logOut();
                loginSteps.UserLogsIn("TM");
                myLogSteps.createACompleteActivityLogForTheDayBeyondSeventyTwoHours();
            }
      }

        navigationBarSteps.logOut();
    }
}
