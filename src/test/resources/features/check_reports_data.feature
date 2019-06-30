Feature: Check reports data after we add some data on Timesheet

  Background:
    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|r.chowaniak+selenium10@timecamp.com|
      |password|Selenium123|
    Then we are checking that user are on Timesheet

#                                       FIRST SCENARIO:                                                              #
#====================================================================================================================#
  @REPORT_PAGE
  Scenario: On today Timesheet we are adding manually entries, then going into summary report, checking that all tasks
    and hours we added is there

    Given user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
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
    And user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete

#                                       SECOND SCENARIO:                                                              #
#====================================================================================================================#
  @REPORT_PAGE
  Scenario: On today Timesheet we are adding manually entries, then going into detailed report, checking that all tasks
  and hours we added is there

    Given user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
    When add manually time entries with duration using main widget and chooses project
      |taskName|SweetTaskForDetailedReport|
      |durationTime|2h 31m|
    And add manually time entries with duration using main widget and chooses project
      |taskName|WierdTaskForDetailedReport|
      |durationTime|39m|
    And add manually time entries with duration using main widget and chooses project
      |taskName|FastTaskForDetailedReport|
      |durationTime|50m|
    And we are checking duration of time entries
      |totalEntryDuration|4h 00m|
    Then we are going on detailed report page
    And we are changing filter of data range to today and people to our user
    And we are checking data of detailed report
      |taskNameFirst|SweetTaskForDetailedReport|
      |durationTimeFirst|2h 31m|
      |taskNameSecond|WierdTaskForDetailedReport|
      |durationTimeSecond|39m|
      |taskNameThird|FastTaskForDetailedReport|
      |durationTimeThird|50m|
      |totalSummaryReport|4h 00m|
    And we are going back to timesheet page
    And user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete

#                                       THIRD SCENARIO:                                                              #
#====================================================================================================================#
  @REPORT_PAGE
  Scenario: On today Timesheet we are adding manually entries, then going into people by tasks report, checking that all
  tasks and hours we added is there

    Given user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
    When add manually time entries with duration using main widget and chooses project
      |taskName|CleverPeopleByTask|
      |durationTime|420s|
    And add manually time entries with duration using main widget and chooses project
      |taskName|FunnyPeopleByTask|
      |durationTime|113m|
    And add manually time entries with duration using main widget and chooses project
      |taskName|CrazyPeopleByTask|
      |durationTime|7240s|
    And we are checking duration of time entries
      |totalEntryDuration|4h 00m|
    Then we are going on people by tasks page
    And we are changing filter of data range to today and people to our user
    And we are checking data of people by tasks report
      |taskNameFirst|CleverPeopleByTask|
      |durationTimeFirst|07m|
      |taskNameSecond|FunnyPeopleByTask|
      |durationTimeSecond|1h 53m|
      |taskNameThird|CrazyPeopleByTask|
      |durationTimeThird|2h 00m|
      |totalSummaryReport|4h 00m|
    And we are going back to timesheet page
    And user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete