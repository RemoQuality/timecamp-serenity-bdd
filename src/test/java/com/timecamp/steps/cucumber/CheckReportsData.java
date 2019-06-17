package com.timecamp.steps.cucumber;

import com.timecamp.pages.ReportPages;
import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class CheckReportsData {


    @Steps
    private TimesheetPage timesheetPage;

    @Steps
    private ReportPages reportPages;


    @Then("^we are going on summary report page$")
    public void weAreGoingOnSummaryReportPage() {
        timesheetPage.changePageToSummaryReport();
    }

    @And("^we are changing filter of data range to today and people to our user$")
    public void weAreChangingFilterOfDataRangeToTodayAndPeopleToOurUser() {
        reportPages.selectFilterTimeFrameToToday();
        reportPages.selectFilterPeopleToYou();
    }
    @And("^we are checking data of summary report$")
    public void weAreCheckingDataOfSummaryReport(Map<String,String> data) {
        reportPages.getNamesAndDurationOfEntriesInSummary(
                data.get("totalSummaryReport"),
                data.get("taskNameFirst"),
                data.get("durationTimeFirst"),
                data.get("taskNameSecond"),
                data.get("durationTimeSecond"),
                data.get("taskNameThird"),
                data.get("durationTimeThird")

        );
    }

    @And("^we are checking data of detailed report$")
    public void weAreCheckingDataOfDetailedReport(Map<String,String> data) {
        reportPages.getNamesAndDurationOfEntriesInDetailed(
                data.get("totalSummaryReport"),
                data.get("taskNameFirst"),
                data.get("durationTimeFirst"),
                data.get("taskNameSecond"),
                data.get("durationTimeSecond"),
                data.get("taskNameThird"),
                data.get("durationTimeThird")
        );
    }

    @And("^we are going back to timesheet page$")
    public void weAreGoingBackToTimesheetPage() {
        reportPages.goIntoTimesheetPage();
    }


    @Then("^we are going on detailed report page$")
    public void weAreGoingOnDetailedReportPage() {
    timesheetPage.changePageToDetailedReport();
    }

    @Then("^we are going on people by tasks page$")
    public void weAreGoingOnPeopleByTasksPage() {
        timesheetPage.changePageToPeopleByTasksReport();
    }

    @And("^we are checking data of people by tasks report$")
    public void weAreCheckingDataOfPeopleByTasksReport(Map<String,String> data) {
        reportPages.getNamesAndDurationOfEntriesInPeopleByDay(
                data.get("totalSummaryReport"),
                data.get("taskNameFirst"),
                data.get("durationTimeFirst"),
                data.get("taskNameSecond"),
                data.get("durationTimeSecond"),
                data.get("taskNameThird"),
                data.get("durationTimeThird")
        );
    }
}
