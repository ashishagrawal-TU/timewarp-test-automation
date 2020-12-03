package pageobjects;

import common.TimeCalendar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testdataobjects.UserAuthenticate;

import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class ModalPage extends PageObject {

    @FindBy(xpath = "(//span[@class='ui-select-match-text pull-left'])[0]")
    private WebElementFacade campaignDrpDwn;

    @FindBy(xpath = "//span[@aria-label='Select Activity Type activate'][1]")
    private WebElementFacade activityTypeDrpDwn;

    @FindBy(css = "input#startTime")
    private WebElementFacade startTimeTxtBox;

    @FindBy(xpath = "//input[@id='startTime']/following-sibling::span")
    private WebElementFacade startTimeCalendar;

    @FindBy(css = "input#endTime")
    private WebElementFacade endTimeTxtBox;

    @FindBy(xpath = "//input[@id='endTime']/following-sibling::span")
    private WebElementFacade endTimeCalendar;

    @FindBy(xpath = "//span[@aria-label='Select Action Reason activate']")
    private WebElementFacade actionReasonDrpDwn;

    @FindBy(css = "textarea#remark")
    private WebElementFacade remarksTxtArea;

    @FindBy(css = "button#buttonSave")
    private WebElementFacade saveBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and text()='NO']")
    private WebElementFacade submitTicketModalNoBtn;

    @FindBy(css = "div.bootstrap-dialog-footer-buttons>button.btn.btn-success")
    private WebElementFacade confirmationYesBtn;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//span[@class='ui-select-match-text pull-left']"),})
    List<WebElement> dropDownFldValues;

    @FindBy(css = "activity-log-insert")
    private WebElementFacade insertActivityModal;

    @FindBy(css = "activity-log-update")
    private WebElementFacade updateActivityModal;

    @FindBy(css = "h4.modal-title")
    private WebElementFacade modalTitle;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElementFacade modal;

    @FindBy(css = "span.timepicker-hour")
    private WebElementFacade hourPicker;

    @FindBy(css = "span.timepicker-minute")
    private WebElementFacade minutePicker;

    @FindBy(css = "span.timepicker-second")
    private WebElementFacade secondPicker;

    @FindBy(xpath = "//button[@title='Toggle Period']")
    private WebElementFacade amPmPicker;

    @FindBy(xpath = "//a[@title='Decrement Second']")
    private WebElementFacade secondDecreaser;

    @FindBy(xpath = "//a[@title='Decrement Hour']")
    private WebElementFacade hourDecreaser;

    @FindBy(xpath = "//a[@title='Decrement Minute']")
    private WebElementFacade minuteDecreaser;

    @FindBy(xpath = "//a[@title='Increment Second']")
    private WebElementFacade secondIncreaser;

    @FindBy(xpath = "//a[@title='Increment Hour']")
    private WebElementFacade hourIncreaser;

    @FindBy(xpath = "//a[@title='Increment Minute']")
    private WebElementFacade minuteIncreaser;

    @FindBy(xpath = "//a[@title='Select Time']")
    private WebElementFacade selectTimeBtn;

    @FindBy(xpath = "//a[@title='Clear selection']")
    private WebElementFacade clearBtn;

    /*@FindBy(xpath = "//div[@class='bootstrap-dialog-message' and text()='Submit Ticket?']")
    private WebElementFacade submitTicketModal;*/

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg' and text()='YES']")
    private WebElementFacade confirmationModalYesBtn;

    @FindBy(xpath = "//div[@class='bootstrap-dialog-message' and text()='Ticket submitted!']")
    private WebElementFacade ticketSubmittedModal;

    @FindBy(css = "button.btn.btn-success.btn-lg")
    private WebElementFacade modalOKBtn;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//span[@class='ui-select-choices-row-inner']"),})
    List<WebElement> dropdownValues;

    @FindBy(xpath = "//div[@class='bootstrap-dialog-message']")
    private WebElementFacade dialogMessage;

    @FindBy(xpath = "//button[@class='close']")
    private WebElementFacade closeBtn;

    @FindBy(css = "button.close.delete-color")
    private WebElementFacade trashBtn;

    @FindBy(xpath = " //button[contains(@class, 'btn-warning')]")
    private WebElementFacade warningModalOKBtn;

    @FindBy(xpath = "//div[@name='activityType']")
    private WebElementFacade activityTypeFld;

    @FindBy(xpath = "//div[@name='actionReason']")
    private WebElementFacade actionReasonFld;

    @FindBy(xpath = "//button[contains(@class,'btn-danger') and text()='No']")
    private WebElementFacade noButtonOnModal;

    @FindBy(css = "input#ITSDRefNum")
    private WebElementFacade ITSDRefNumFld;

    @FindBy(xpath = "//p[@class='help-block']")
    private WebElementFacade fieldErrorMessage;

    public String getCampaign(){
        return dropDownFldValues.get(0).getText();
    }

    public String getActivity(){
        return dropDownFldValues.get(1).getText();
    }

    public String getSelectedActivityType(){
        String defaultVal = "--Select Activity Type--";
        String val = activityTypeFld.getText();
        int ind = val.indexOf(defaultVal);
        String selectedVal = null;
        String ret;
       if (ind != -1) {
            selectedVal = val.replace(defaultVal, "");
        }
        if (selectedVal != null && selectedVal.length() != 0) {
            ret = selectedVal.trim();
        } else {
            ret = defaultVal;
        }
        return ret;
    }

    public String getStartTime(){
        return startTimeTxtBox.getAttribute("value");
    }

    public String getEndTime(){
        return endTimeTxtBox.getAttribute("value");
    }

    public String getRemarks(){
        return remarksTxtArea.getText();
    }

    public void selectActivity(String activity){
        activityTypeDrpDwn.click();
        setValueInDropdown(activity);
        waitForAngularRequestsToFinish();
    }

    public void selectActionReason(String reason){
        actionReasonDrpDwn.click();
        setValueInDropdown(reason);
        waitForAngularRequestsToFinish();
    }

    public String getActionReason(){
        String reason =  dropDownFldValues.get(2).getAttribute("textContent");
        return reason.trim();
    }

    public String getSelectedActionReason(){
        String defaultVal = "--Select Action Reason--";
        String val = actionReasonFld.getText();
        int ind = val.indexOf(defaultVal);
        String selectedVal = null;
        String ret;
        if (ind != -1) {
            selectedVal = val.replace(defaultVal, "");
        }
        if (selectedVal!=null && selectedVal.trim().length() != 0) {
            ret = selectedVal.trim();
        } else {
            ret = defaultVal;
        }
        return ret;
    }

    public void clickSave(){
        saveBtn.click();
   }

    public void clickYesOnConfirmationModal(){
        if(!confirmationYesBtn.isClickable()){
            waitFor(ExpectedConditions.elementToBeClickable(confirmationYesBtn));
        }
        confirmationYesBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickNoOnSubmitTicketModal(){
        submitTicketModalNoBtn.click();
    }

    public void setRemarks(String remarks){
        remarksTxtArea.sendKeys(remarks);
    }

    private void setValueInDropdown(String value) {
        WebElementFacade dropdownValue =
                find(By.xpath("//span[@class='ui-select-choices-row-inner']/div[normalize-space(text())='" + value + "']"));
        dropdownValue.waitUntilVisible().then().click();
    }

    public void enterStartTime(String start){
        startTimeTxtBox.clear();
        startTimeTxtBox.sendKeys(start);
    }

    public void enterEndTime(String end){
        endTimeTxtBox.clear();
        endTimeTxtBox.sendKeys(end);
    }

    public String keepActivityDetails(String actiontype){

        String activityString;
        String action = null;
        String activityType = getActivity();
        UserAuthenticate userAuthenticate = Serenity.sessionVariableCalled("userAuthenticate");

        String timeZone = userAuthenticate.getTimeZone();
        String start_time = getStartTime();
        String end_time = getEndTime();
        String duration = "";
        if(end_time.trim().length()>0){
            duration = TimeCalendar.getDurationBetweenTwoDates(start_time, end_time);
        }

        if(dropDownFldValues.size() > 2) {
           action = getActionReason().trim();
        }
        activityString =  "Activity Type : " + activityType + "Start Time : " + start_time +
                "End Time : " + end_time + "Duration : " + duration
                + "TimeZone : " + timeZone + "Action Type : " + actiontype ;

        if(action!=null){
            activityString = activityString + "Action Reason : " + action;
        }

        return activityString;

    }



    public boolean isInsertActivityModalVisible(){
        return insertActivityModal.isVisible();
    }

    public boolean isUpdateActivityModalDisplayed(){
        return updateActivityModal.isDisplayed();
    }

    public boolean isInsertActivityModalDisplayed(){
        return insertActivityModal.isDisplayed();
    }

    public boolean isUpdateActivityModalPresent(){
        return updateActivityModal.isPresent();
    }

    public boolean isInsertActivityModalPresent(){
        return insertActivityModal.isPresent();
    }

    public String getActivityModalTitle(){
       return modalTitle.getText().trim();
    }

    public boolean isActivityTypeDrpDownEnabled(){
        return activityTypeDrpDwn.isEnabled();
    }

    public boolean isStartTimeTextBoxEnabled(){
        return startTimeTxtBox.isEnabled();
    }

    public boolean isEndTimeTextBoxEnabled(){
        return endTimeTxtBox.isEnabled();
    }

    public boolean isActionReasonDrpDownEnabled(){
        return actionReasonDrpDwn.isEnabled();
    }

    public boolean isRemarksTxAreaEnabled(){
        return remarksTxtArea.isEnabled();
    }

    public WebElement getModal(){return modal;}

    public boolean isModalDisplayed(){
        return modal.isPresent() && modal.isVisible();
    }

    public WebElementFacade selectHour(String hour){
        String path = "//td[@class='hour' and text()='" + hour + "']";
        return find("xpath = " + path);
    }

    public void selectMinute(String minute){
        String path = "//td[@class='minute' and text()='" + minute + "']";
        WebElementFacade elementFacade = find("xpath = " + path);
        elementFacade.click();
    }

    public void selectSecond(String second){
        String path = "//td[@class='second' and text()='" + second + "']";
        WebElementFacade elementFacade = find("xpath = " + path);
        elementFacade.click();
    }

    public void clickOnClockIcon(){
        selectTimeBtn.click();
    }

    public void clickIncreMentHourArrow(){
        hourIncreaser.click();
    }

    public void clickIncreMentMinuteArrow(){
        minuteIncreaser.click();
    }

    public void clickIncreMentSecondArrow(){
       secondIncreaser.click();
    }

    public void clickDecreMentHourArrow(){
        hourDecreaser.click();
    }

    public void clickDecreMentMinuteArrow(){
        minuteDecreaser.click();
    }

    public void clickDecreMentSecondArrow(){
        secondDecreaser.click();
    }

    public void clickStartTimeCalendar(){
        startTimeCalendar.click();
    }

    public void clickEndTimeCalendar(){
        endTimeCalendar.click();
    }

    public boolean isSaveBtnClickable(){
        return saveBtn.isClickable();
    }

    /*public boolean isSubmitTicketModalPresent(){
        return submitTicketModal.isPresent();
    }*/

    public void clickYesOnModal(){
          confirmationModalYesBtn.click();
    }

    public boolean isTicketSubmittedModalPresent(){
       return ticketSubmittedModal.isPresent();
    }

    public void clickOnModalOKBtn(){
        if ( !(modalOKBtn.isPresent() && modalOKBtn.isDisplayed())){
            withTimeoutOf(2, MINUTES)
                    .waitFor(ExpectedConditions.visibilityOf(modalOKBtn));
        }
        modalOKBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickActivityTypeDrpDwn(){
        activityTypeDrpDwn.click();
    }

    public List<String> getAllActivityTypeDropdownValues(){
        List<String> drpdownValues = new ArrayList<>();
        for (WebElement dropdownValue : dropdownValues) {
            drpdownValues.add(dropdownValue.getText().trim());
        }
        return drpdownValues;
    }

    public boolean isStartTimeCalendarDisplayed(){
        return startTimeCalendar.isDisplayed();
    }

    public boolean isHourPickerDisplayed(){
        return hourPicker.isDisplayed();
    }

    public boolean isHourIncreaserDisplayed(){
        return hourIncreaser.isDisplayed();
    }

    public boolean isHourDecreaserDisplayed(){
        return hourDecreaser.isDisplayed();
    }

    public boolean isMinutePickerDisplayed(){
        return minutePicker.isDisplayed();
    }

    public boolean isMinuteIncreaserDisplayed(){
        return minuteIncreaser.isDisplayed();
    }

    public boolean isMinuteDecreaserDisplayed(){
        return minuteDecreaser.isDisplayed();
    }

    public boolean isSecondPickerDisplayed(){
        return secondPicker.isDisplayed();
    }

    public boolean isSecondIncreaserDisplayed(){
        return secondIncreaser.isDisplayed();
    }

    public boolean isSecondDecreaserDisplayed(){
        return secondDecreaser.isDisplayed();
    }

    public void clickClearBtn(){
        clearBtn.click();
    }

    public String getHourPickerValue(){
        return hourPicker.getText();
    }

    public String getMinutePickerValue(){
        return minutePicker.getText();
    }

    public String getSecondPickerValue(){
        return secondPicker.getText();
    }

    public void clickAmOrPmPicker(){
        amPmPicker.click();
    }

    public String getAmOrPmPickerValue(){
        return amPmPicker.getText();
    }

    public void clickActionReasonDrpDwn(){
        actionReasonDrpDwn.click();
    }

    public String getDialogMessage(){
        if(dialogMessage.isPresent())
            return dialogMessage.getText();
        else
            return "";
    }

    public void clickCloseBtn(){ closeBtn.click();}

    public void clickOnWarningModalOKBtn(){
        warningModalOKBtn.click();
    }



    public String enterValuesAndSaveModal(String activity, String startTime, String endTime, String reason,
                                                                    String remarks, String actiontype){
        String activitydetails;
        selectActivity(activity);
        if(startTime.trim().length()>0){
            enterStartTime(startTime);
        }
        if(endTime.trim().length()>0){
            enterEndTime(endTime);
        }
        selectActionReason(reason);
        setRemarks(remarks);
        activitydetails = keepActivityDetails(actiontype);
        clickSave();

        return activitydetails;
    }

    public boolean isDialogMessagePresent(){
        return dialogMessage.isPresent();
    }

    public void clickNoOnSaveChangesModal(){
        noButtonOnModal.click();
    }

    public boolean isTrashBtnDisplayed(){
       if(trashBtn.isPresent()){
           return trashBtn.isDisplayed();
       }else{
           return false;
       }
    }

    public void tabOutFromStartTime(){
        startTimeTxtBox.sendKeys(Keys.TAB);
    }

    public void tabOutFromEndTime(){
        endTimeTxtBox.sendKeys(Keys.TAB);
    }

    public boolean isITSDRefNumDisplayed(){
        if(ITSDRefNumFld.isPresent()){
            return ITSDRefNumFld.isVisible();
        }else{
            return false;
        }
    }

    public String getFieldErrorMessage(){
        return fieldErrorMessage.getText();
    }

    public void clickOnTrashButton(){
        trashBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickNoOnConfirmationModal(){
        noButtonOnModal.click();
    }

    public String fillUpModalFieldValuesGivenActivityAndTimeValues
            (String activity, String startTime, String endTime){

        if(activity.equalsIgnoreCase("Start Shift")) {
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 10, "add",
                    "Minute", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Productive Time 1")) {
            activity = "Productive Time";
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 2, "add",
                    "Hour", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Break (15 minutes)")) {
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 15, "add",
                    "Minute", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Team Meetings")) {
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "add",
                    "Hour", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("TaskUs Training")) {
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "add",
                    "Hour", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Lunch (60 minutes)")) {
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "add",
                    "Hour", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Client Training")) {
            activity = "Productive Time";
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 2, "add",
                    "Hour", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Idle Time")) {
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 15, "add",
                    "Minute", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("Productive Time 2")) {
            activity = "Productive Time";
            startTime = endTime;
            endTime = TimeCalendar.getTimeValueGivenTimeAndMinusOrAddHours(startTime, 2, "add",
                    "Hour", "yyyy-MM-dd hh:mm:ss a");
        }

        if(activity.equalsIgnoreCase("End Shift")) {
            startTime = endTime;
            endTime = "";
        }

        enterValuesAndSaveModalAndClickPromptMessageAfter(activity, startTime, endTime,
                "Forgot to log-in", "Sample remarks", "Insert");

        return endTime;
    }

    @Step("Enter activity and click Save")
    public String enterValuesAndSaveModalAndClickPromptMessageAfter(String activity, String startTime, String endTime, String reason,
                                                                    String remarks, String actiontype){

        String activitydetails = enterValuesAndSaveModal(activity, startTime, endTime, reason, remarks, actiontype);
        if(isModalDisplayed() && getDialogMessage().equalsIgnoreCase("You’re trying to submit a request with edited logs beyond 72 hours approval period. This will require Immediate Superior and VP approval.")){
            clickOnWarningModalOKBtn();
        }
        clickYesOnConfirmationModal();
        return activitydetails;
    }

}
