package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOError;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;


public class TimesheetPage extends PageObject {

    @FindBy(id = "time_menu_link")
    private WebElementFacade timesheetTopButton;

    @FindBy(xpath = "//strong[contains(.,'TOTAL')]")
    private WebElementFacade totalDuration;

    @FindBy(xpath = "//strong[contains(.,'TOTAL')]")
    private List <WebElementFacade> durationList;

    @FindBy(xpath = "//i[@title='Bulk edit']")
    private WebElementFacade bulkEditIcon;

    @FindBy(xpath = "//input[contains(@ng-click,'addAllEntriesToBulkEdit(day)')]")
    private WebElementFacade checkboxBulkcheck;

    @FindBy(xpath = "//a[@ng-click='deleteEntriesInBulkMode()']")
    private WebElementFacade bulkDeleteLink;

    @FindBy(xpath = "//button[@data-bb-handler='confirm']")
    private WebElementFacade confirmBulkDeleteButton;

    @FindBy(xpath = "//div[contains(@class,'bootbox modal fade bootbox-confirm in')]")
    private WebElementFacade bootboxConfirm;

    @FindBy(id = "timer-start-button")
    private WebElementFacade timerStartButton;

    @FindBy(id = "js-add-task-btn-widget")
    private WebElementFacade jsAddTaskWidget;

    @FindBy(id = "js-add-box-name")
    private WebElementFacade jsTaskNameInput;

    @FindBy (id = "tcTTTaskPickerBase")
    private WebElementFacade jsBoxTask;

    @FindBy(xpath = "//div[@class='pull-left btn btn-link ng-binding ng-scope'][contains(.,'Add new time entry')]")
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

    @FindBy (id = "new-entry-task-picker")
    private WebElementFacade inputWhatDidYouWorkOn;

    @FindBy(xpath = "//a[@ng-if='canAddManually()']")
    private WebElementFacade mainWidgetAddManually;

    @FindBy(xpath = "//div[@class='panel panel-default pull-right entry-body']//div[@class='entry-title']")
    private List<WebElementFacade> listOfEntriesOnTimesheet;

    @FindBy (xpath = "//div[@class='panel panel-default pull-right entry-body']")
    private WebElementFacade getNamesOfEntries;

    @FindBy(xpath = "//div[@class='entry-summary-duration']//strong")
    private WebElementFacade summaryEntryDuration;

    @FindBy (id = "timer-task-picker")
    private WebElementFacade whatAreYouWorkingOn;


    private Logger log = LoggerFactory.getLogger(TimesheetPage.class);

    public void isTimesheetButtonDisplayed(boolean isTimesheet) {
        if (isTimesheet) {
            timesheetTopButton.shouldBeVisible();
        } else {
            timesheetTopButton.shouldNotBeVisible();
        }
    }

    public void isTotalDurationDisplayed() {
        if (durationList.size()!=1){
            log.info("Total duration is not present, so there is not time entries");
            totalDuration.shouldNotBeVisible();
        }else{
            bulkEditIcon.waitUntilClickable().click();
            checkboxBulkcheck.click();
            bulkDeleteLink.waitUntilClickable().click();
            confirmBulkDeleteButton.click();
            confirmBulkDeleteButton.waitUntilNotVisible();
            bootboxConfirm.waitUntilNotVisible();
        }
    }

    public void clickStartTimerButton(){
        bootboxConfirm.waitUntilNotVisible();
        timerStartButton.waitUntilClickable().click();
    }

    public void jsAddTaskFromTimesheet(String taskName){
        jsAddTaskWidget.waitUntilEnabled().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + taskName);
    }

    public void addTaskFromLinkUnderTimesheet(String durationTime){
        jsBoxTask.waitUntilNotVisible();
        jsTaskNameInput.waitUntilNotVisible();
        addTaskLink.waitUntilVisible().click();
        recentlyUsedTask.click();
        durationEntryInput.typeAndEnter(durationTime);
    }
    public void clickTimerStopButton(){
        timerStopButton.click();
    }

    public void addTimeEntryFromManualMode(String taskName, String durationTime){
        manualModeLink.click();
        inputWhatDidYouWorkOn.click();
        jsAddTaskWidget.waitUntilVisible().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + taskName);
        durationWidgetInput.typeAndEnter(durationTime);
        mainWidgetAddManually.click();
    }
    public void getTimeEntriesCount(){
        log.info("Size of time entries " + listOfEntriesOnTimesheet.size());
        Assert.assertEquals(3, listOfEntriesOnTimesheet.size());
    }

    public void getNamesAndDurationOfEntries(String totalEntryDuration){
        log.info("Names of time entries " + getNamesOfEntries.getText());
        log.info("Durations of three entries is " + summaryEntryDuration.getText());
        Assert.assertEquals(totalEntryDuration, summaryEntryDuration.getText());


    }
}
