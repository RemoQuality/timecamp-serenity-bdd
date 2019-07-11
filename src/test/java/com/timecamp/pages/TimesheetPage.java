package com.timecamp.pages;

import com.ibm.icu.util.Calendar;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalTime;
import java.util.List;



public class TimesheetPage extends PageObject {

    @FindBy(id = "time_menu_link")
    private WebElementFacade timesheetTopButton;

    @FindBy(xpath = "//div[@class='entry-summary-duration ng-binding']")
    private WebElementFacade totalDuration;

    @FindBy(xpath = "//div[@class='entry-summary-duration ng-binding']")
    private List<WebElementFacade> durationList;

    @FindBy(xpath = "//div[@class='btn btn-default btn-wide-padding ng-binding']")
    private WebElementFacade bulkEditIcon;

    @FindBy(xpath = "//input[contains(@ng-click,'addAllEntriesToBulkEdit(day)')]")
    private WebElementFacade checkboxBulkcheck;

    @FindBy(xpath = "//div[@class='ts-bulk-edit-row']//a[contains(text(),'Delete')]")
    private WebElementFacade bulkDeleteLink;

    @FindBy(xpath = "//button[@data-bb-handler='confirm']")
    private WebElementFacade confirmBulkDeleteButton;

    @FindBy(xpath = "//div[@class='bootbox modal fade bootbox-confirm in']")
    private WebElementFacade bootboxConfirm;

    @FindBy(css = "modal-backdrop fade")
    private WebElementFacade modalFade;

    @FindBy(id = "timer-start-button")
    private WebElementFacade timerStartButton;

    @FindBy(xpath = "//span[@title='Select task or project']")
    private WebElementFacade selectTaskHiperLink;

    @FindBy(id = "js-add-task-btn-widget")
    private WebElementFacade jsAddTaskWidget;

    @FindBy(id = "js-add-box-name")
    private WebElementFacade jsTaskNameInput;

    @FindBy(id = "tcTTTaskPickerBase")
    private WebElementFacade jsBoxTask;

    @FindBy(xpath = "//div[@data-original-title='Add new time entry']")
    private WebElementFacade addTaskLink;

    @FindBy(id = "js-add-box-add-btn")
    private WebElementFacade jsAddTaskButton;

    @FindBy(xpath = "//*[@id='lastUsedTasks']/li/a")
    private WebElementFacade recentlyUsedTask;

    @FindBy(xpath = "//input[contains(@tabindex,'109')]")
    private WebElementFacade durationEntryInput;

    @FindBy(xpath = "//span[contains(.,'Stop timer')]")
    private WebElementFacade timerStopButton;

    @FindBy(id = "upperBarManualButton")
    private WebElementFacade manualModeLink;

    @FindBy(xpath = "//input[@tabindex='2']")
    private WebElementFacade durationWidgetInput;

    @FindBy(id = "new-entry-task-picker")
    private WebElementFacade inputWhatDidYouWorkOn;

    @FindBy(xpath = "//a[@ng-if='canAddManually()']")
    private WebElementFacade mainWidgetAddManually;

    @FindBy(xpath = "//div[@class='panel panel-default pull-right entry-body']//div[@class='entry-title']")
    private List<WebElementFacade> listOfEntriesOnTimesheet;

    @FindBy(xpath = "//div[@class='panel panel-default pull-right entry-body']")
    private WebElementFacade getNamesOfEntries;

    @FindBy(xpath = "//div[contains(@title,'total time in all entries, excluding unassigned computer activities')]")
    private WebElementFacade summaryEntryDuration;

    @FindBy(id = "timer-task-picker")
    private WebElementFacade whatAreYouWorkingOn;

    @FindBy(xpath = "//button[@ng-click='onReload(true); synchronizeIntegrations()']")
    private WebElementFacade refreshButton;

    @FindBy(xpath = "//a[contains(.,'Reports')]")
    private WebElementFacade reportsSection;

    @FindBy(xpath = "//a[contains(.,'Summary')]")
    private WebElementFacade summaryReportPage;

    @FindBy(xpath = "//a[@title='Graphical timesheet']")
    private WebElementFacade weeklyTimesheetButton;

    @FindBy(xpath = "//button[contains(@class,'btn btn-xs btn-success ng-binding')]")
    private WebElementFacade startTimerWeeklyButton;

    @FindBy(xpath = "//div[@ng-if='hasTimer()']")
    private WebElementFacade activeTimerWeeklyButton;

    @FindBy(xpath = "//span[@ng-click='selectedDay == dayno && $event.stopPropagation()']")
    private WebElementFacade selectTaskRunningTimer;

    @FindBy(xpath = "//div[@class='graph-day-container']")
    private WebElementFacade allWeeklyCalendar;

    @FindBy(xpath = "//a[@event='tt_manage'][contains(.,'Projects')]")
    private WebElementFacade projectTopButton;

    @FindBy(xpath = "//div[@ng-click='toggleTimer(data)']")
    private WebElementFacade stopTimerWeekly;

    @FindBy (xpath = "//p[@class='duration-time ng-binding ng-scope']")
    private WebElementFacade durationOfDayWeeklyTimesheet;

    @FindBy(xpath = "//a[@ng-href='#/timesheets/timer']")
    private WebElementFacade dailyTimesheetButton;

    @FindBy(xpath = "//a[contains(.,'Detailed')]")
    private WebElementFacade detaliledReportPage;

    @FindBy(xpath = "//a[contains(.,'People by tasks')]")
    private WebElementFacade peopleByTasksReport;

    @FindBy(xpath = "//a[@class='btn btn-default active ng-binding'][contains(.,'Day')]")
    private WebElementFacade activeDailyButton;

    @FindBy(xpath = "//a[@event='tt_manage'][contains(.,'Projects')]")
    private WebElementFacade projectsSection;

    private Logger log = LoggerFactory.getLogger(TimesheetPage.class);

    @FindBy(id = "dots")
    private WebElementFacade preparingAccountMessage;

    @FindBy(xpath = "//div[contains(.,'Draw entry here')]")
    private WebElementFacade drawEntryHere;


    public void isTimesheetButtonDisplayed(boolean isTimesheet) {
        if (isTimesheet) {
            timesheetTopButton.shouldBeVisible();
        } else {
            timesheetTopButton.shouldNotBeVisible();
        }
    }

    public void isTimerRunning() {
        if (timerStopButton.isCurrentlyVisible()) {
            log.info("Timer is running so we are stopping it");
            timerStopButton.click();
        }
    }

    public void isTotalDurationDisplayed() {
        if (durationList.size() != 1) {
            log.info("Total duration is not present, so there is not time entries");
            totalDuration.shouldNotBeVisible();
        } else {
            waitForAngularRequestsToFinish();
            bulkEditIcon.waitUntilPresent().waitUntilVisible().click();
            checkboxBulkcheck.waitUntilClickable().click();
            bulkDeleteLink.waitUntilClickable().click();
            confirmBulkDeleteButton.click();
            confirmBulkDeleteButton.waitUntilNotVisible();
            bootboxConfirm.waitUntilNotVisible();
        }
    }

    public void isTimesheetDailyIfNotSwitchToDaily() {
        if (activeDailyButton.isVisible()) {
            log.info("We are on daily timesheet");
            activeDailyButton.shouldBeVisible();
        } else {
            waitForAngularRequestsToFinish();
            dailyTimesheetButton.waitUntilVisible().click();
        }
    }
    public void clickStartTimerButton() {
        bootboxConfirm.waitUntilNotVisible();
        modalFade.waitUntilNotVisible();
        waitForAngularRequestsToFinish();
        timerStartButton.waitUntilVisible().waitUntilClickable().click();
    }

    public void jsAddTaskFromTimesheet(String taskName) {
        waitForAngularRequestsToFinish();
        jsAddTaskWidget.waitUntilVisible().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + " " + taskName);
        jsTaskNameInput.waitUntilNotVisible();
        jsAddTaskWidget.waitUntilNotVisible();
        waitForAngularRequestsToFinish();
    }

    public void addTaskFromLinkUnderTimesheet(String durationTime) {
        jsBoxTask.waitUntilNotVisible();
        jsTaskNameInput.waitUntilNotVisible();
        addTaskLink.waitUntilVisible().click();
        recentlyUsedTask.click();
        durationEntryInput.typeAndEnter(durationTime);
        waitForAngularRequestsToFinish();
    }

    public void clickTimerStopButton() {
        timerStopButton.click();
    }

    public void addTimeEntryFromManualMode(String taskName, String durationTime) {
        if (manualModeLink.isVisible()) {
            withAction().moveToElement(manualModeLink).perform();
            manualModeLink.waitUntilClickable().click();
        }
        inputWhatDidYouWorkOn.click();
        jsAddTaskWidget.waitUntilVisible().waitUntilClickable().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + " " + taskName);
        jsTaskNameInput.waitUntilNotVisible();
        durationWidgetInput.typeAndEnter(durationTime);
        mainWidgetAddManually.click();
    }

    public void getTimeEntriesCount() {
        waitForAngularRequestsToFinish();
        log.info("Size of time entries " + listOfEntriesOnTimesheet.size());
        Assert.assertEquals(3, listOfEntriesOnTimesheet.size());
    }

    public void getNamesAndDurationOfEntries(String totalEntryDuration) {
        waitForAngularRequestsToFinish();
        log.info("Names of time entries " + getNamesOfEntries.getText());
        log.info("Durations of three entries is " + summaryEntryDuration.getText());
        summaryEntryDuration.waitUntilVisible();
        Assert.assertEquals(totalEntryDuration, summaryEntryDuration.getText());
    }

    public void changePageToSummaryReport() {
        withAction().moveToElement(reportsSection).perform();
        summaryReportPage.waitUntilClickable().click();
    }

    public void changeViewToWeekly() {
        waitForAngularRequestsToFinish();
        weeklyTimesheetButton.waitUntilPresent().waitUntilClickable().click();
    }

    public void clickStartButtonOnWeekly() {
        waitForAngularRequestsToFinish();
        jsBoxTask.waitUntilNotVisible();
        Calendar cal = Calendar.getInstance();
        final int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        withAction().moveToElement(
                find(By.xpath("//span[@class='day-num ng-binding'][contains(.,'" + dayOfMonth + "')]")))
                .click()
                .build()
                .perform(); //locator cannot be as annotation because we need dayOfMonth in xpath
        startTimerWeeklyButton.waitUntilClickable().click();
        startTimerWeeklyButton.waitUntilNotVisible();
    }

    public void drawEntryInWeekly() {
        waitForAngularRequestsToFinish();
        Calendar cal = Calendar.getInstance();
        final int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        withAction().moveToElement(
                find(By.xpath("//span[@class='day-num ng-binding'][contains(.,'" + dayOfMonth + "')]")))
                .moveByOffset(0, 100)
                .clickAndHold()
                .moveByOffset(0, 204)
                .release()
                .build()
                .perform(); //locator cannot be as annotation because we need dayOfMonth in xpath
    }

    public void clickStopTimerWeekly() {
        waitForAngularRequestsToFinish();
        stopTimerWeekly.waitUntilVisible().click();
    }

    public void checkTaskNameAndDuration(String taskName, String durationDaily) {
        waitForAngularRequestsToFinish();
        durationOfDayWeeklyTimesheet.shouldBeVisible();
        allWeeklyCalendar.shouldContainText(taskName);
        durationOfDayWeeklyTimesheet.shouldContainText(durationDaily);
    }
    public void changeViewToDaily() {
        waitForAngularRequestsToFinish();
        dailyTimesheetButton.waitUntilPresent().waitUntilClickable().click();
    }

    public void changePageToDetailedReport() {
        withAction().moveToElement(reportsSection).perform();
        detaliledReportPage.waitUntilClickable().click();
    }

    public void changePageToPeopleByTasksReport() {
        withAction().moveToElement(reportsSection).perform();
        peopleByTasksReport.waitUntilClickable().click();
    }

    public void changePageToProjectsSection() {
       projectsSection.waitUntilClickable().click();
    }

    public void checkThatAccountCreated() {
        preparingAccountMessage.shouldBeVisible();
        preparingAccountMessage.waitUntilNotVisible();
        timesheetTopButton.click();
        bulkEditIcon.shouldBeVisible();
        waitForAngularRequestsToFinish();
        waitForAnyTextToAppear(timesheetTopButton, "Timesheet");
    }
}
