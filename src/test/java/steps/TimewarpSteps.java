package steps;

import net.thucydides.core.annotations.Step;
import pageobjects.TimewarpPage;

public class TimewarpSteps {

    TimewarpPage timewarpPage;
    String PopupMessageText = "All activities are being monitored and are being logged. Any suspicious or malicious activity is subject to disciplinary action.";

    @Step("Timewarp Home Page")
    public void TimewarpHomePage(){
        System.out.println("Checkpoint#1");
        assert timewarpPage.verifyTimewarpPopupMessageButton().equalsIgnoreCase(PopupMessageText);
        System.out.println("Checkpoint#2");
        System.out.println("Checkpoint#3");
    }

    @Step("Verify Activity Page")
    public void verifyActivityPage(){
        timewarpPage.verifyActivityPageHeader();
    }

    @Step("Verify Activity Page")
    public void verifyStartShiftHasBeenRecorded(){
        timewarpPage.verifyCurrentActivityTable();
    }

    @Step("Verify Start Shift is recorded and show")
    public void verifyStartShiftIsRecordedAndShow(){
        timewarpPage.verifyStartShiftAndCurrentActivityTable();
    }

    @Step("Verify Yes button is clicked")
    public void verifyYesButtonIsClicked() {
        timewarpPage.verifyActivityPageHeader();
    }


}
