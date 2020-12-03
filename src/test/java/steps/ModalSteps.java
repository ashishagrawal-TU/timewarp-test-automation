package steps;

import common.APIHelper;
import common.TimeCalendar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import org.assertj.core.api.SoftAssertions;
import pageobjects.ModalPage;
import testdataobjects.UserAuthenticate;

import java.util.Collections;
import java.util.List;

public class ModalSteps {

    String hourValueBefore;
    String minuteValueBefore;
    String secondValueBefore;
    String aMOrpMValueBefore;
    private EnvironmentVariables environmentVariables;

    @Steps
    ModalPage modalPage;

    @Step("Select the clock icon")
    public void clickSelectTimeBtn(){
        modalPage.clickOnClockIcon();
    }

    @Step("Click the up arrow on hour")
    public void incrementTheHour(){
        modalPage.clickIncreMentHourArrow();
    }

    @Step("Click the start time calendar")
    public void clickCalendarOnStartTime(){
        modalPage.clickStartTimeCalendar();
    }

    @Step("Click the end time calendar")
    public void clickCalendarOnEndTime(){
        modalPage.clickEndTimeCalendar();
    }

    @Step("Select action reason")
    public void selectActionReason(String reason){
        modalPage.selectActionReason(reason);
    }

    @Step("Add additional information on remarks field")
    public void addRemarks(String remarks){
        modalPage.setRemarks(remarks);
    }

    @Step("Click Save button")
    public void clickSaveOnModal(){
        modalPage.clickSave();
    }

    @Step("Click Yes on confirmation window")
    public void clickYesOnConfirmationWindow(){
        modalPage.clickYesOnConfirmationModal();
    }

    @Step("Click No on confirmation window")
    public void clickNoOnConfirmationWindow(){
        modalPage.clickNoOnSubmitTicketModal();
    }

    @Step("Select an activity from the dropdown")
    public void selectAnActivityFromDropdown(String activity) {
        modalPage.selectActivity(activity);
    }

    @Step("Verify the selected activity should be displayed on the activity type field")
    public void checkSelectedActivityIsWhatsDisplayed(String activity) {
        assert modalPage.getActivity().equalsIgnoreCase(activity);
    }

    @Step("Verify start time calendar is displayed")
    public void checkThatStartTimeCalendarIsDisplayed() {
        assert modalPage.isStartTimeCalendarDisplayed() == true;
    }

    @Step("Verify time selector elements are displayed")
    public void checkThatTimeSelectorElementsAreDisplayed() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(modalPage.isHourPickerDisplayed()).isEqualTo(true);
        softly.assertThat(modalPage.isHourIncreaserDisplayed()).isEqualTo(true);
        softly.assertThat(modalPage.isHourDecreaserDisplayed()).isEqualTo(true);

        softly.assertThat(modalPage.isMinutePickerDisplayed()).isEqualTo(true);
        softly.assertThat(modalPage.isMinuteIncreaserDisplayed()).isEqualTo(true);
        softly.assertThat(modalPage.isMinuteDecreaserDisplayed()).isEqualTo(true);

        softly.assertThat(modalPage.isSecondPickerDisplayed()).isEqualTo(true);
        softly.assertThat(modalPage.isSecondIncreaserDisplayed()).isEqualTo(true);
        softly.assertThat(modalPage.isSecondDecreaserDisplayed()).isEqualTo(true);

        softly.assertAll();
    }

    @Step("Click the garbage icon")
    public void clickTheClearBtn() {
        modalPage.clickClearBtn();
    }

    @Step("Check that value displayed on start time field is Invalid date")
    public void checkTheValueOfStartTimeFieldIsNotADate() {
         assert modalPage.getStartTime().equalsIgnoreCase("Invalid date");
    }

    @Step("Click the up icon on hour")
    public void clickOnUpIconOnHour() {
        modalPage.clickIncreMentHourArrow();
    }

    @Step("Click the up icon on minute")
    public void clickOnUpIconOMinute() {
        modalPage.clickIncreMentMinuteArrow();
    }

    @Step("Click the up icon on second")
    public void clickOnUpIconOnSecond() {
        modalPage.clickIncreMentSecondArrow();
    }

    @Step("Click the down icon on hour")
    public void clickOnDownIconOnHour() {
        modalPage.clickDecreMentHourArrow();
    }

    @Step("Click the down icon on minute")
    public void clickOnDownIconOMinute() {
        modalPage.clickDecreMentMinuteArrow();
    }

    @Step("Click the down icon on second")
    public void clickOnDownIconOnSecond() {
        modalPage.clickDecreMentSecondArrow();
    }

    @Step("Verify hour value increased by 1")
    public void checkHourValueWasIncreasedByOne() {
        String hourValueNow = modalPage.getHourPickerValue();
        if(hourValueBefore.equalsIgnoreCase("12")){
            assert hourValueNow.equalsIgnoreCase("01");
        }else{
            assert Integer.parseInt(hourValueNow) == Integer.parseInt(hourValueBefore) + 1;
        }
   }

    @Step("Get the hour value")
    public void getHourValue() {
       hourValueBefore = modalPage.getHourPickerValue();
    }

    @Step("Get the minute value")
    public void getMinuteValue() {
        minuteValueBefore = modalPage.getMinutePickerValue();
    }

    @Step("Get the second value")
    public void getSecondValue() {
        secondValueBefore = modalPage.getSecondPickerValue();
    }

    @Step("Verify minute value increased by 1")
    public void checkMinuteValueWasIncreasedByOne() {
        String minuteValueNow = modalPage.getMinutePickerValue();
        if(minuteValueBefore.equalsIgnoreCase("59")){
            assert minuteValueNow.equalsIgnoreCase("00");
        }else{
            assert Integer.parseInt(minuteValueNow) == Integer.parseInt(minuteValueBefore) + 1;
        }
    }

    @Step("Verify second value increased by 1")
    public void checkSecondValueWasIncreasedByOne() {
        String secondValueNow = modalPage.getSecondPickerValue();
        if(secondValueBefore.equalsIgnoreCase("59")){
            assert secondValueNow.equalsIgnoreCase("00");
        }else{
            assert Integer.parseInt(secondValueNow) == Integer.parseInt(secondValueBefore) + 1;
        }

    }

    @Step("Verify hour value decreased by 1")
    public void checkHourValueWasDecreasedByOne() {
        String hourValueNow = modalPage.getHourPickerValue();
        if(hourValueBefore.equalsIgnoreCase("01")){
            assert hourValueNow.equalsIgnoreCase("12");
        }else{
            assert Integer.parseInt(hourValueNow) == Integer.parseInt(hourValueBefore) - 1;
        }

    }

    @Step("Verify minute value decreased by 1")
    public void checkMinuteValueWasDecreasedByOne() {
        String minuteValueNow = modalPage.getMinutePickerValue();
        if(minuteValueBefore.equalsIgnoreCase("00")){
            assert minuteValueNow.equalsIgnoreCase("59");
        }else{
            assert Integer.parseInt(minuteValueNow) == Integer.parseInt(minuteValueBefore) - 1;
        }
    }

    @Step("Verify second value decreased by 1")
    public void checkSecondValueWasDecreasedByOne() {
        String secondValueNow = modalPage.getSecondPickerValue();
        if(secondValueBefore.equalsIgnoreCase("00")){
            assert secondValueNow.equalsIgnoreCase("59");
        }else{
            assert Integer.parseInt(secondValueNow) == Integer.parseInt(secondValueBefore) - 1;
        }
    }

    @Step("Click the AM or PM button")
    public void clickOnAmOrPmButton() {
        modalPage.clickAmOrPmPicker();
    }

    @Step("Get the AM or PM button value")
    public void getAMorPMValue() {
        aMOrpMValueBefore = modalPage.getAmOrPmPickerValue();
    }

    @Step("Verify AM or PM button value changes")
    public void checkAmOrPmValueChanges() {
        String aMOrpMValueNow = modalPage.getAmOrPmPickerValue();
        assert !aMOrpMValueBefore.equalsIgnoreCase(aMOrpMValueNow);
    }

    @Step("Check that value displayed on end time field is Invalid date")
    public void checkTheValueOfEndTimeFieldIsNotADate() {
        assert modalPage.getEndTime().equalsIgnoreCase("Invalid date");
    }

    @Step("Click on Action Reason dropdown")
    public void clickOnActionReasonDropDown(){
        modalPage.clickActionReasonDrpDwn();
    }

    @Step("Check that the displayed activities are complete")
    public void checkActivitiesDisplayedAreForTheCampaign(){
        APIHelper apiHelper = new APIHelper();
        UserAuthenticate userAuthenticate = Serenity.sessionVariableCalled("userAuthenticate");
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        List<String> drpDwnValuesFromAPI = apiHelper.getActivitiesByCampaign(webserviceEndpoint,
                userAuthenticate.getCampaignName(), userAuthenticate.getAuthenticationToken());
        //add Start Shift
        drpDwnValuesFromAPI.add("Start Shift");
        List<String> drpDwnValuesFromScreen = modalPage.getAllActivityTypeDropdownValues();

        Collections.sort(drpDwnValuesFromAPI);
        Collections.sort(drpDwnValuesFromScreen);

        assert drpDwnValuesFromAPI.equals(drpDwnValuesFromScreen);

    }

    @Step("Check that the displayed action reasons are complete")
    public void checkActionReasonDropDownAreValuesComplete(){
        APIHelper apiHelper = new APIHelper();
        UserAuthenticate userAuthenticate = Serenity.sessionVariableCalled("userAuthenticate");
        String webserviceEndpoint =  EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webservices.base.url");
        List<String> drpDwnValuesFromAPI = apiHelper.getActionReasonSelect(webserviceEndpoint,
                userAuthenticate.getAuthenticationToken());
        List<String> drpDwnValuesFromScreen = modalPage.getAllActivityTypeDropdownValues();

        Collections.sort(drpDwnValuesFromAPI);
        Collections.sort(drpDwnValuesFromScreen);

        assert drpDwnValuesFromAPI.equals(drpDwnValuesFromScreen);

    }

    @Step("Set an end time that is before the start time")
    public void setAnEndTimeBeforeStartTime(){
        String startTime = modalPage.getStartTime();
        String endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "minus",
                        "Hour", "yyyy-MM-dd hh:mm:ss a");
        modalPage.enterEndTime(endTime);
    }

    @Step("Check that error message displays")
    public void checkThatErrorMessageDisplays(String errorMsg) {
         assert modalPage.getFieldErrorMessage().equalsIgnoreCase(errorMsg);
    }

    @Step("Check that the selection action reason gets displayed on the field")
    public void checkThatTheSelectedActionReasonIsDisplayed(String reason) {
        System.out.println("reason: [" +  reason + "]");
        System.out.println("modalPage.getActionReason: [" +  modalPage.getActionReason() + "]");
        assert modalPage.getActionReason().equalsIgnoreCase(reason);
    }

    @Step("Click on the activity log form Close button")
    public void clickTheActivityLogFormCloseBtn() {
          modalPage.clickCloseBtn();
    }

    @Step("Check that the Update Activity log form is gone")
    public void checkThatUpdateActivityLogFormIsNotPresent() {
        assert !modalPage.isUpdateActivityModalPresent();
    }

    @Step("Check that the Insert Activity log form is gone")
    public void checkThatInsertActivityLogFormIsNotPresent() {
        assert !modalPage.isInsertActivityModalPresent();
    }

    @Step("Check all fields in the modal are editable ")
    public void checkAllFieldsInTheModalAreEditable(){
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(modalPage.isActivityTypeDrpDownEnabled()).isEqualTo(true);
        softly.assertThat(modalPage.isStartTimeTextBoxEnabled()).isEqualTo(true);
        softly.assertThat(modalPage.isEndTimeTextBoxEnabled()).isEqualTo(true);
        softly.assertThat(modalPage.isActionReasonDrpDownEnabled()).isEqualTo(true);
        softly.assertThat(modalPage.isRemarksTxAreaEnabled()).isEqualTo(true);
        softly.assertAll();
    }

    @Step("Click the OK button on warning message")
    public void clickTheOKButtonOnWarningMessage(){
        modalPage.clickOnWarningModalOKBtn();
    }

    @Step("Click Yes button on Activity Log modal")
    public void clickYesBtnOnSaveOrUpdateActivityLogModal(){
        modalPage.clickYesOnConfirmationModal();
    }

    @Step("Check that no error message displays")
    public void checkThatNoErrorMessageModalIsPresent() {
        assert !modalPage.isDialogMessagePresent();
    }

    @Step("Click No on save changes window")
    public void clickNoOnSaveChangesWindow(){
        modalPage.clickNoOnSaveChangesModal();
    }

    @Step("Check that trash button is displayed")
    public void checkThatTrashBtnIsDisplayed(){
        assert modalPage.isTrashBtnDisplayed();
    }

    @Step ("Get the original value of the start time field")
    public String getTheOriginalStartTimeValue(){
         return modalPage.getStartTime();
    }

    @Step ("Change the value of PM on start time field")
    public void changeTheValueOfAMOrPMOfStartTime(String timefield, String newvalue){
        modalPage.enterStartTime(getValueToEnterToTimeField(timefield, newvalue));
   }

    @Step ("Tab Out from the start time field")
    public void clickTabOutFromStartTimeField(){
        modalPage.tabOutFromStartTime();
    }

    public void checkThatValueOfStartTimeIsSameAsOriginal(String originalStartTime){
        String startTimeVal = modalPage.getStartTime();
        assert originalStartTime.equals(startTimeVal);
    }

    @Step ("Get the original value of the end time field")
    public String getTheOriginalEndTimeValue(){
        return modalPage.getEndTime();
    }

    private String getValueToEnterToTimeField(String timefieldvalue, String newvalue){
        String stripPMVal;
        String amOrPM = "AM";
        String toAdd = "";
        String valueToEnter;

        if(timefieldvalue.indexOf("PM")!= -1){
            amOrPM = "PM";
            stripPMVal = timefieldvalue.replace("PM", "");
        }else{
            stripPMVal = timefieldvalue.replace("AM", "");
        }

        if(newvalue.equals("Both small cap")){
            toAdd = amOrPM.toLowerCase();
        }

        if(newvalue.equals("First letter uppercase")){
            String a = amOrPM.substring(0,1).toUpperCase();
            String b = amOrPM.substring(1).toLowerCase();
            toAdd = a + b;
        }

        if(newvalue.equals("Last letter uppercase")){
            String a = amOrPM.substring(0,1).toLowerCase();
            String b = amOrPM.substring(1).toUpperCase();
            toAdd = a + b;
        }

        return stripPMVal.trim() + " " + toAdd;

    }

    @Step ("Change the value of AM or PM on end time field")
    public void changeTheValueOfAMOrPMOfEndTime(String timefield, String newvalue){
        modalPage.enterEndTime(getValueToEnterToTimeField(timefield, newvalue));
    }

    @Step ("Tab Out from the end time field")
    public void clickTabOutFromEndTimeField(){
        modalPage.tabOutFromEndTime();
    }

    @Step ("Check that the value of end time is the same as old value")
    public void checkThatValueOfEndTimeIsSameAsOriginal(String originalEndTime){
        String endTimeVal = modalPage.getEndTime();
        assert originalEndTime.equals(endTimeVal);
    }

    @Step("Verify ITSD reference number field is displayed")
    public void checkThatITSDRefNumFieldIsDisplayed(){
        assert modalPage.isITSDRefNumDisplayed();
    }

    @Step("Check that a field error message is displayed")
    public void checkThatErrorMessageIsDisplayed(String message){
        assert modalPage.getFieldErrorMessage().equalsIgnoreCase(message);
    }

    @Step("Verify ITSD reference number field is not displayed")
    public void checkThatITSDRefNumFieldIsNotDisplayed(){
        assert !modalPage.isITSDRefNumDisplayed();
    }

    @Step("Click on trash icon")
    public void clickOnTrashIcon(){
        modalPage.clickOnTrashButton();
        if(modalPage.isModalDisplayed() && modalPage.getDialogMessage()
                .equalsIgnoreCase(
            "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval.")){
            modalPage.clickOnWarningModalOKBtn();
        }
    }

    @Step("Keep the activity details to be deleted")
    public String textToCompareToInvalidTaggingList(){
        String ret = null;
        String activity = modalPage.getActivity();
        String startTime = modalPage.getStartTime();
        String[] splitText = startTime.split(" ");
        String date = splitText[0];

        if(activity.equalsIgnoreCase("Start Shift"))
            ret = "No" + " " + activity + " " + date;

        if(activity.equalsIgnoreCase("Productive Time"))
            ret = "Gap Between Activity Logs " + date;

        if(activity.equalsIgnoreCase("End Shift"))
            ret = "No" + " " + activity + " " + date;

        return ret;
    }

    @Step("Check the displayed message")
    public void checkThatDisplayDialogMessageIsSameAsSomeValue(String expectedMessage){
        modalPage.getDialogMessage().equalsIgnoreCase(expectedMessage);
    }

    @Step("Click No on confirmation modal")
    public void clickNoOnConfirmationModal(){
        modalPage.clickNoOnConfirmationModal();
    }

    @Step("Click OK button on warning message")
    public void clickOKOnWarningMessage(){
       if(modalPage.isModalDisplayed() && modalPage.getDialogMessage()
        .equalsIgnoreCase(
        "You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval.")) {
                modalPage.clickOnWarningModalOKBtn();
       }
    }





}
