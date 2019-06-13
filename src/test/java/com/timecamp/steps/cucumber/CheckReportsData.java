package com.timecamp.steps.cucumber;

import com.timecamp.pages.SummaryPage;
import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class CheckReportsData {


    @Steps
    private TimesheetPage timesheetPage;

    @Steps
    private SummaryPage summaryPage;

    @Then("^we are going on summary report page$")
    public void weAreGoingOnSummaryReportPage() {
        timesheetPage.changePageToSummaryReport();
    }

    @And("^we are changing filter of data range to today and people to our user$")
    public void weAreChangingFilterOfDataRangeToTodayAndPeopleToOurUser() {
        summaryPage.selectFilterTimeFrameToToday();
        summaryPage.selectFilterPeopleToYou();
    }
    @And("^we are checking data of summary report$")
    public void weAreCheckingDataOfSummaryReport(Map<String,String> data) {
        summaryPage.getNamesAndDurationOfEntriesInSummary(
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
        summaryPage.goIntoTimesheetPage();
    }


}
