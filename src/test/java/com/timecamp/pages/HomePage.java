package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


public class HomePage extends PageObject {

    @FindBy(xpath = "//a[contains(@href, '/auth/login')]")
    private WebElementFacade loginButton;

    @FindBy(xpath = "//a[contains(@href, '/auth/register')]")
    private WebElementFacade signUpButton;


    public void openLoginPage(){
        loginButton.click();
    }
    public void openRegistrationPage(){
        signUpButton.click();
    }
}
