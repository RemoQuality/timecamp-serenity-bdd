package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;


public class ProjectPage extends PageObject {

    @FindBy(id = "newTaskBtn")
    private WebElementFacade newProjectButton;

    @FindBy(id = "new-task-name")
    private WebElementFacade inputNewProjectName;

    @FindBy(id = "new-task-btn")
    private WebElementFacade confirmNewTask;

    @FindBy(xpath = "//div[@class='task-name'][contains(.,'project [LEVEL 1]')]")
    private WebElementFacade projectOnList;

    @FindBy(xpath = "(//div[@class='btn tc-btn btn-success task-btn-add'][contains(.,'Add task')])[1]") // bardzo słaby locator
    private WebElementFacade addTaskOnList;

    @FindBy(xpath = "//input[@name='title'][contains(.,'project [LEVEL 1]')]")
    private WebElementFacade inputNewTaskName;

    @FindBy(xpath = "//div[@class='task-name'][contains(.,'task [LEVEL 2]')]")
    private WebElementFacade taskOnList;

    @FindBy(xpath = "(//div[@class='btn tc-btn btn-success task-btn-add'][contains(.,'Add task')])[2]") // bardzo słaby locator
    private WebElementFacade addSubtaskOnList;

    @FindBy(xpath = "//input[@name='title'][contains(.,'task [LEVEL 2]')]")
    private WebElementFacade inputNewSubTask;

    @FindBy(id = "manageTreeTask_0")
    private WebElementFacade fullTaskTree;

    @FindBy(xpath = "(//button[contains(@class,'btn tc-btn btn-default dropdown-toggle taskMenuDropdown')])[1]")
    private WebElementFacade moreActionsProject;

    @FindBy(xpath = "(//a[@class='task-btn-delete'][contains(.,'Delete')])[1]")
    private WebElementFacade deleteFromMoreActions;

    @FindBy(xpath = "//button[@data-bb-handler='confirm'][contains(.,'OK')]")
    private WebElementFacade confirmDeleteOK;

    @FindBy(xpath = "//div[contains(@class,'bootbox modal fade bootbox-confirm in')]")
    private WebElementFacade fadeModal;

    @FindBy(id = "time_menu_link")
    private WebElementFacade timesheetTopButton;

    @FindBy(xpath = "//li[contains(.,'No projects')]")
    private WebElementFacade noProjects;

    private Logger log = LoggerFactory.getLogger(TimesheetPage.class);

    public void addNewProject(String projectName) {
        waitForAngularRequestsToFinish();
        if (noProjects.isCurrentlyVisible()){
            log.info("there is no project");
        }else{
            fullTaskTree.waitUntilVisible();
            log.info("there is project list");
        }
        newProjectButton.waitUntilVisible().click();
        inputNewProjectName.type(LocalTime.now().getNano() + projectName);
        confirmNewTask.waitUntilClickable().click();
    }
    public void addNewTask(String taskName){
        waitForAngularRequestsToFinish();
        projectOnList.click();
        withAction().moveToElement(addTaskOnList).build().perform();
        addTaskOnList.click();
        inputNewTaskName.click();
        inputNewTaskName.waitUntilVisible().typeAndEnter(LocalTime.now().getNano() + taskName);
    }

    public void addNewSubtask(String subtaskName){
        waitForAngularRequestsToFinish();
        taskOnList.click();
        withAction().moveToElement(addSubtaskOnList).build().perform();
        addSubtaskOnList.click();
        inputNewSubTask.click();
        inputNewSubTask.waitUntilVisible().typeAndEnter(LocalTime.now().getNano() + subtaskName);
    }

    public void verifyAddedProjectTaskSubtask(
            String projectName,
            String taskName,
            String subtaskName
    ){
        waitForAngularRequestsToFinish();
        fullTaskTree.waitUntilPresent();
        fullTaskTree.shouldContainText(projectName);
        fullTaskTree.shouldContainText(taskName);
        fullTaskTree.shouldContainText(subtaskName);
    }

    public void addNewSubtask(){
        waitForAngularRequestsToFinish();
        projectOnList.waitUntilVisible().click();
        moreActionsProject.waitUntilVisible().click();
        deleteFromMoreActions.waitUntilVisible().click();
        confirmDeleteOK.waitUntilClickable().click();
        fadeModal.waitUntilNotVisible();
    }
    public void goingBackToTimesheet(){
        timesheetTopButton.waitUntilClickable().click();
    }

}
