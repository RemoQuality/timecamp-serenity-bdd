Feature: Check project page functionality

  Background:
    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|r.chowaniak+selenium3@timecamp.com|
      |password|Selenium123|
    Then we are checking that user are on Timesheet

  Scenario: We are going after login to project page, then add project, add task, add subtask. After this delete added
    projects

    Given user is on daily timesheet, no time entries, no running timer, when there is entries clean up by bulk delete
    And user is going into project page
    When user on project page is adding a project using main widget
      |projectName|project [LEVEL 1]|
    And user add task using button located on project added before
      |taskName|task [LEVEL 2]|
    And user add subtask using button located on task added before
      |subtaskName|subtask [LEVEL 3]|
    Then we are checking that all added task are listed
      |projectName|project [LEVEL 1]|
      |taskName|task [LEVEL 2]|
      |subtaskName|subtask [LEVEL 3]|
    And user is deleting added project, task, subtask