package com.timecamp.steps.cucumber;

import com.timecamp.pages.HomePage;
import com.timecamp.pages.LoginPage;
import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class CheckLoginIntoAccount {

    @Steps
    private HomePage homepage;

    @Steps
    private LoginPage loginPage;

    @Steps
    private TimesheetPage timesheetPage;


    @Given("^user on TimeCamp home page$")
    public void userOnTimeCampHomePage() {
        homepage.open();
    }

    @When("^user open login page$")
    public void userOpenLoginPage() {
        homepage.openLoginPage();
    }

    @And("^login using bellow email and password$")
    public void loginUsingBellowCredentials(Map<String,String> data) {
        loginPage.putEmailPasswordAndConfirm(data.get("email"),data.get("password"));
    }

    @Then("^we are checking that user are on Timesheet$")
    public void weAreCheckingThatUserAreOnTimesheet() {
        timesheetPage.isTimesheetButtonDisplayed(true);
    }
}
