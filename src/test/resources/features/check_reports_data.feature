Feature: Check reports data after we add some data on Timesheet

  Background:
    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|r.chowaniak+selenium1@timecamp.com|
      |password|Selenium123|
    Then we are checking that user are on Timesheet

  Scenario: On today Timesheet we are adding manually entries, then going into summary report, checking that all tasks
    and hours we added is there

    Given user is on timesheet where is no time entries, when there is entries clean up by bulk delete
    When add manually time entries with duration using main widget and chooses project
      |taskName|FirstTaskForReport|
      |durationTime|10h 35m|
    And add manually time entries with duration using main widget and chooses project
      |taskName|SecondTaskForReport|
      |durationTime|13m|
    And add manually time entries with duration using main widget and chooses project
      |taskName|ThirdTaskForReport|
      |durationTime|12m|
    And we are checking duration of time entries
      |totalEntryDuration|11h 00m|
    Then we are going on summary report page
    And we are changing filter of data range to today and people to our user
    And we are checking data of summary report
      |taskNameFirst|FirstTaskForReport|
      |durationTimeFirst|10h 35m|
      |taskNameSecond|SecondTaskForReport|
      |durationTimeSecond|13m|
      |taskNameThird|ThirdTaskForReport|
      |durationTimeThird|12m|
      |totalSummaryReport|11h 00m|
    And we are going back to timesheet page
    And user is on timesheet where is no time entries, when there is entries clean up by bulk delete