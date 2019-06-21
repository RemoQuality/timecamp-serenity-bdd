package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;



public class LoginPage extends PageObject {

    @FindBy(id = "email")
    private WebElementFacade emailInput;

    @FindBy(id = "pass_hash")
    private WebElementFacade passwordInput;

    @FindBy (xpath = "//*[@id='go']")
    private WebElementFacade loginButton;

    public void putEmailPasswordAndConfirm(String email, String password){
        emailInput.waitUntilVisible().click();
        emailInput.type(email);
        passwordInput.click();
        passwordInput.typeAndEnter(password);
    }
}
