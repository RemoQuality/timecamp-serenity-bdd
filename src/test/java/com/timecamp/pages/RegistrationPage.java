package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.LocalTime;


public class RegistrationPage extends PageObject {

    @FindBy(id = "email")
    private WebElementFacade emailInput;

    @FindBy(id = "pass_hash")
    private WebElementFacade passwordInput;

    @FindBy (id = "register2")
    private WebElementFacade loginButton;

    public void putEmailPasswordAndConfirm(String login, String domain, String password){
        int AccNumber = LocalTime.now().getNano();
        emailInput.waitUntilVisible().click();
        emailInput.type(login + AccNumber + domain);
        passwordInput.click();
        passwordInput.typeAndEnter(password);
    }
}
