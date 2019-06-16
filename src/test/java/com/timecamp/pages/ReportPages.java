package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ReportPages extends PageObject {

    @FindBy(xpath = "//button[contains(@id,'tool-bar-custom-date-btn')]")
    private WebElementFacade durationFilter;

    @FindBy(xpath = "//a[@ng-click='todayBtn()']")
    private WebElementFacade todayFilter;

    @FindBy (id = "filterState_people")
    private WebElementFacade peopleFilter;

    @FindBy (partialLinkText = " - YOU")
    private WebElementFacade peopleYouFilter;

    @FindBy(xpath = "//div[@class='range-date-picker-button-panel']")
    private WebElementFacade listDuration;

    @FindBy (id = "tcUserPickerBase")
    private WebElementFacade listUserPicker;

    @FindBy(xpath = "//div[contains(@ng-style,'body.styles()')]")
    private WebElementFacade reportPageData;

    @FindBy (id = "time_menu_link")
    private WebElementFacade timesheetPageLink;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void selectFilterTimeFrameToToday(){
        waitForAngularRequestsToFinish();
        durationFilter.waitUntilVisible();
        withAction().moveToElement(durationFilter).click(durationFilter).build().perform();
        todayFilter.waitUntilVisible().click();
        listDuration.waitUntilNotVisible();
    }
    public void selectFilterPeopleToYou(){
        withAction().moveToElement(peopleFilter).click(peopleFilter).build().perform();
        peopleYouFilter.waitUntilEnabled().click();
        listUserPicker.waitUntilNotVisible();
    }
    public void getNamesAndDurationOfEntriesInSummary(
            String summaryReport,
            String durationTimeFirst,
            String taskNameSecond,
            String durationTimeSecond,
            String taskNameThird,
            String durationTimeThird,
            String totalSummaryReport
    ){
        waitForAngularRequestsToFinish();
        reportPageData.waitUntilPresent();
        reportPageData.shouldContainText(summaryReport);
        reportPageData.shouldContainText(durationTimeFirst);
        reportPageData.shouldContainText(taskNameSecond);
        reportPageData.shouldContainText(durationTimeSecond);
        reportPageData.shouldContainText(taskNameThird);
        reportPageData.shouldContainText(durationTimeThird);
        reportPageData.shouldContainText(totalSummaryReport);
    }

    public void goIntoTimesheetPage(){
        timesheetPageLink.click();
    }

    public void getNamesAndDurationOfEntriesInDetailed(
            String summaryReport,
            String durationTimeFirst,
            String taskNameSecond,
            String durationTimeSecond,
            String taskNameThird,
            String durationTimeThird,
            String totalSummaryReport
    ){
        LocalDate localDate = LocalDate.now();
        waitForAngularRequestsToFinish();
        reportPageData.waitUntilPresent();
        reportPageData.shouldContainText(summaryReport);
        reportPageData.shouldContainText(durationTimeFirst);
        reportPageData.shouldContainText(taskNameSecond);
        reportPageData.shouldContainText(durationTimeSecond);
        reportPageData.shouldContainText(taskNameThird);
        reportPageData.shouldContainText(durationTimeThird);
        reportPageData.shouldContainText(totalSummaryReport);
        reportPageData.shouldContainText(dtf.format(localDate));

    }

}
