package com.timecamp.steps.cucumber;

import com.timecamp.pages.HomePage;
import com.timecamp.pages.RegistrationPage;
import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.Map;

public class CheckRegistration {

    @Steps
    private HomePage homepage;

    @Steps
    private RegistrationPage registrationPage;

    @Steps
    private TimesheetPage timesheetPage;


    @When("^user is going into registration page$")
    public void userOpenLoginPage() {
        homepage.openRegistrationPage();
    }

    @And("^input email and password$")
    public void loginUsingBellowCredentials(Map<String,String> data) {
        registrationPage.putEmailPasswordAndConfirm(data.get("email"),data.get("password"));
    }

    @Then("^we are checking that account is properly created$")
    public void weAreCheckingThatAccountIsProperlyCreated() {
        timesheetPage.checkThatAccountCreated();
    }
}
