Feature: Check registering account

@REGISTRATION
  Scenario: We are going into registration page and registration new account

  Given user on TimeCamp home page
  When user is going into registration page
    And input email and password
      |login|r.chowaniak+selenium|
      |domain|@timecamp.com|
      |password|Selenium123|
    Then we are checking that account is properly created
