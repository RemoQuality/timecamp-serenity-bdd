Feature: Check timesheet basic functions on weekly view

  Background:
    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|r.chowaniak+selenium1@timecamp.com|
      |password|Selenium123|
    Then we are checking that user are on Timesheet

  Scenario: On Timesheet user goes into weekly view, start timer, then draw manually time entry, stop
  timer that actually has running and checking all added data that is equal that expected.

    Given user is on timesheet where is no time entries, when there is entries clean up by bulk delete
    And user is going into weekly view timesheet
    When user start timer from weekly view with clicking button to create new task
      |taskName|Testing task added from weekly view|
    And click stop timer and draw manually time entry on weekly view and choose created before project
    Then we are checking size of time entries on weekly timesheet
    And user back to daily view
    And user is on timesheet where is no time entries, when there is entries clean up by bulk delete