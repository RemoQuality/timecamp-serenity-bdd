package com.timecamp.pages;

import cucumber.api.java.it.Ma;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.MoveMouseToTarget;
import net.serenitybdd.screenplay.actions.MoveMouseToWebElement;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.webdriver.WebdriverManager;
import org.graalvm.compiler.lir.sparc.SPARCMove;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOError;
import java.io.IOException;
import java.sql.Driver;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


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

    @FindBy(xpath = "//div[@class='bootbox modal fade bootbox-confirm in']")
    private WebElementFacade bootboxConfirm;

    @FindBy (css = "modal-backdrop fade")
    private WebElementFacade modalFade;

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

    @FindBy(xpath = "//i[@class='fa fa-repeat']")
    private WebElementFacade refreshButton;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-default ng-binding'][contains(.,'Week')]")
    private WebElementFacade weeklyButton;

    @FindBy(xpath = "//button[@ng-click='startNewEntryTimer(0)']")
    private WebElementFacade startTimerWeekly;

    @FindBy(xpath = "//span[@ng-if='isToday()']")
    private WebElementFacade todaySectionWeekly;

    @FindBy(xpath = "//p[@ng-if='selectedDay == dayno && getTotal()']")
    private WebElementFacade todayTotalPoint;

    @FindBy(xpath = "//div[@ng-if='hasTimer()']")
    private WebElementFacade stopTimerWeekly;

    @Managed
    private WebDriver driver;

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
        modalFade.waitUntilNotVisible();
        timerStartButton.waitUntilVisible().waitUntilClickable().click();
    }

    public void jsAddTaskFromTimesheet(String taskName){
        jsAddTaskWidget.waitUntilEnabled().click();
        jsTaskNameInput.typeAndEnter(LocalTime.now().getNano() + taskName);
        jsTaskNameInput.waitUntilNotVisible();
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
        jsTaskNameInput.waitUntilNotVisible();
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
        refreshButton.click();
        summaryEntryDuration.waitUntilVisible();
        Assert.assertEquals(totalEntryDuration, summaryEntryDuration.getText());
    }
    public void goIntoWeeklyView(){
        log.info("User changing view from daily to weekly");
        weeklyButton.waitUntilClickable();
        weeklyButton.click();
    }
    public void startTimerWeekly() {
        todaySectionWeekly.waitUntilClickable().click();
        startTimerWeekly.isDisplayed();
        startTimerWeekly.waitUntilClickable().click();
    }
    public void drawManuallyTimeEntry() {
        todayTotalPoint.waitUntilVisible();
        Actor tcActor = Actor.named("tcActor");
        tcActor.can(BrowseTheWeb.with(driver));



    }
    public void stopTimerWeekly() throws InterruptedException {
        stopTimerWeekly.waitUntilClickable().click();
        Thread.sleep(4000);
    }
}
