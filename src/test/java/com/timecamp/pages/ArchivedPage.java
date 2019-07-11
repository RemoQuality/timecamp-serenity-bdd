package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ArchivedPage extends PageObject {


    @FindBy(xpath = "//h1[@class='tc-title'][contains(.,'Archived Tasks')]")
    private WebElementFacade archivedPageTitle;

    @FindBy (id = "tree")
    private WebElementFacade archivedTree;

    @FindBy (xpath = "(//a[contains(.,'Reactivate')])")
    private List<WebElementFacade> listOfArchivedProjects;

    @FindBy(xpath = "(//a[contains(.,'Reactivate')])[1]")
    private WebElementFacade firstReactivateButton;

    @FindBy(xpath = "//a[contains(@href,'tracking/manage')]")
    private WebElementFacade topProjectPage;


    private Logger log = LoggerFactory.getLogger(TimesheetPage.class);

    public void checkThatIsArchivedPage(){
        archivedPageTitle.shouldBeVisible();
    }

    public void checkThatProjectIsOnList(String projectName) {
        archivedTree.shouldContainText(projectName);
    }
    public void reactivateProjectsAndBackProjectPage() {
        while(listOfArchivedProjects.size() > 1){
            withAction().moveToElement(firstReactivateButton)
                        .click()
                        .build()
                        .perform();
                waitForAngularRequestsToFinish();
        }
        topProjectPage.waitUntilVisible().click();
    }
}
