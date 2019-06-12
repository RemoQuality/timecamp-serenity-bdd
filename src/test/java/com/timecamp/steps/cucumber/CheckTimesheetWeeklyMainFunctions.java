package com.timecamp.steps.cucumber;

import com.timecamp.pages.HomePage;
import com.timecamp.pages.LoginPage;
import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.And;

import java.util.Map;


public class CheckTimesheetWeeklyMainFunctions {

    @Steps
    private HomePage homepage;

    @Steps
    private LoginPage loginPage;

    @Steps
    private TimesheetPage timesheetPage;

    @And("^user is going into weekly view timesheet$")
    public void userIsGoingIntoWeeklyViewTimesheet() {
        timesheetPage.changeViewToWeekly();
    }

    @When("^user start timer from weekly view with clicking button to create new task$")
    public void userStartTimerFromWeeklyViewWithClickingButtonToCreateNewTask(Map<String,String> data) {
        timesheetPage.clickStartButtonOnWeekly();
        timesheetPage.jsAddTaskFromTimesheet(data.get("taskName"));
    }


    @And("^click stop timer and draw manually time entry on weekly view and choose created before project$")
    public void clickStopTimerAndDrawManuallyTimeEntryOnWeeklyViewAndChooseCreatedBeforeProject() {
        timesheetPage.drawEntryInWeekly();
        timesheetPage.clickStopTimerWeekly();
    }

    @Then("^we are checking size of time entries on weekly timesheet$")
    public void weAreCheckingSizeOfTimeEntriesOnWeeklyTimesheet() {
        timesheetPage.checkTaskNameAndDuration();
    }

    @And("^user back to daily view$")
    public void userBackToDailyView() {
        timesheetPage.changeViewToDaily();
    }
}
