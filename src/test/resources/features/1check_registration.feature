Feature: Check registering account

@REGISTRATION
  Scenario: We are going into registration page and registration new account

  Given user on TimeCamp home page
  When user is going into registration page
    And input email and password
      |login|xxxxxxxxx|
      |domain|@xxxxxxxxxxxx.com|
      |password|xxxxxxxxxxxxxxxx|
    Then we are checking that account is properly created
