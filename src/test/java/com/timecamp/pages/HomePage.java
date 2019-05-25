package com.timecamp.pages;

import cucumber.api.java.Before;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.DefaultUrl;


public class HomePage extends PageObject {

    @FindBy(xpath = "//a[@href='https://timecamp.com/auth/login']")
    private WebElementFacade loginButton;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    public void openLoginPage(){
        loginButton.click();
    }
}
