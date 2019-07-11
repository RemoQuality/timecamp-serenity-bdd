Feature: Check timesheet basic functions on weekly view

  Background:
    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|r.chowaniak+selenium@timecamp.com|
      |password|Selenium123|
    Then we are checking that user are on Timesheet

@WEEKLY_TIMESHEET
  Scenario: On Timesheet user goes into weekly view, start timer, then draw manually time entry, stop
  timer that actually has running and checking all added data that is equal that expected.

    Given user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
    And user is going into weekly view timesheet
    When user start timer from weekly view with clicking button to create new task
      |taskName|Testing task added from weekly view|
    And click stop timer and draw manually time entry on weekly view and choose created before project
      |taskName|Testing task drawn from weekly view|
    Then we are checking size of time entries on weekly timesheet
      |taskName|Testing task drawn from weekly view|
      |durationDaily|2h 15m|
    And user back to daily view
    And user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete