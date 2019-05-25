Feature: Login successful on TimeCamp account

  Scenario: Login into TimeCamp using correctly credentials

    Given user on TimeCamp home page
    When user open login page
    And login using bellow email and password
      |email|r.chowaniak+selenium1@timecamp.com|
      |password|Selenium123|
    Then we are checking that user are on Timesheet