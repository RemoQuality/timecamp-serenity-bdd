package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;



public class ProjectPage extends PageObject {

    @FindBy(id = "newTaskBtn")
    private WebElementFacade newProjectButton;

    @FindBy(id = "new-task-name")
    private WebElementFacade inputNewProjectName;

    @FindBy(id = "new-task-btn")
    private WebElementFacade confirmNewTask;

    @FindBy(xpath = "//div[@class='task-name'][contains(.,'project [LEVEL 1]')]")
    private WebElementFacade projectOnList;

    @FindBy(xpath = "(//div[@class='task-name'])[1]", timeoutInSeconds="10")
    private WebElementFacade firstProjectOnList;

    @FindBy(xpath = "//div[@class='tc-ui-task-list']")
    private List<WebElementFacade> allProjectsList;

    @FindBy(xpath = "(//div[@class='btn tc-btn btn-success task-btn-add'][contains(.,'Add task')])[1]") // bardzo słaby locator
    private WebElementFacade addTaskOnList;

    @FindBy(xpath = "//input[@name='title'][contains(.,'project [LEVEL 1]')]")
    private WebElementFacade inputNewTaskName;

    @FindBy(xpath = "(//div[@class='task-name'][contains(.,'task [LEVEL 2]')])[1]")
    private WebElementFacade taskOnList;

    @FindBy(xpath = "(//div[@class='btn tc-btn btn-success task-btn-add'][contains(.,'Add task')])[2]") // bardzo słaby locator
    private WebElementFacade addSubtaskOnList;

    @FindBy(xpath = "//div[@id='editTaskBox']//input[@placeholder='Task name...']")
    private WebElementFacade inputNewSubTask;

    @FindBy(id = "taskTreeWrapper")
    private WebElementFacade fullTaskTree;

    @FindBy(xpath = "//div[@class='task-name'][contains(.,'[LEVEL 3]')]")
    private WebElementFacade levelThreeTasks;

    @FindBy(xpath = "(//button[contains(@class,'btn tc-btn btn-default dropdown-toggle taskMenuDropdown')])[1]")
    private WebElementFacade moreActionsProject;

    @FindBy(xpath = "(//a[@class='task-btn-delete'][contains(.,'Delete')])[1]")
    private WebElementFacade deleteFromMoreActions;

    @FindBy(xpath = "(//a[@class='task-btn-clone tooltipDescriptionBox tooltipDescriptionBox'])[1]")
    private WebElementFacade cloneFromMoreActions;

    @FindBy(xpath = "//button[@data-bb-handler='confirm'][contains(.,'OK')]")
    private WebElementFacade confirmDeleteOK;

    @FindBy(xpath = "//div[@class='modal-backdrop fade']")
    private WebElementFacade fadeModal;

    @FindBy(id = "time_menu_link")
    private WebElementFacade timesheetTopButton;

    @FindBy(xpath = "//li[contains(.,'No projects')]")
    private WebElementFacade noProjects;

    @FindBy(xpath = "//a[@class='btn tc-btn btn-default editTaskBox-cancelBtn']")
    private WebElementFacade cancelButtonBox;

    @FindBy(id = "manageTasksTreeCollapseAll")
    private WebElementFacade collapseAllProjects;

    private Logger log = LoggerFactory.getLogger(TimesheetPage.class);

    public void addNewProject(String projectName) {
        waitForAngularRequestsToFinish();
        System.out.println("Total projects on list is " + allProjectsList.size());
        if (noProjects.isCurrentlyVisible()) {
            log.info("there is no project");
        } else {
            log.info("We are cleaning all projects on this page");
            fullTaskTree.waitUntilVisible();
            int i = 0;
            while (allProjectsList.size() >= 1) {
                i++;
                System.out.println("Task number " + i + " is deleted");
                waitForAngularRequestsToFinish();
                firstProjectOnList.click();
                waitForAngularRequestsToFinish();
                moreActionsProject.waitUntilVisible().click();
                deleteFromMoreActions.waitUntilVisible().click();
                confirmDeleteOK.waitUntilClickable().click();
                confirmDeleteOK.waitUntilNotVisible();
                deleteFromMoreActions.waitUntilNotVisible();
                fadeModal.waitUntilNotVisible();
                waitForAngularRequestsToFinish();
            }
        }
        newProjectButton.waitUntilVisible().click();
        inputNewProjectName.type(LocalTime.now().getNano() + " " + projectName);
        confirmNewTask.waitUntilClickable().click();
    }
    public void addNewTask(String taskName){
        waitForAngularRequestsToFinish();
        projectOnList.click();
        withAction().moveToElement(addTaskOnList).build().perform();
        addTaskOnList.click();
        inputNewTaskName.click();
        inputNewTaskName.waitUntilVisible().typeAndEnter(LocalTime.now().getNano() + " " + taskName);
    }

    public void addNewSubtask(String subtaskName){
        waitForAngularRequestsToFinish();
        taskOnList.waitUntilVisible().click();
        withAction().moveToElement(addSubtaskOnList).build().perform();
        addSubtaskOnList.click();
        inputNewSubTask.typeAndEnter(LocalTime.now().getNano() + " " + subtaskName);
        withAction().moveToElement(cancelButtonBox).click().build().perform();
    }

    public void verifyAddedProjectTask(
            String projectName,
            String taskName,
            String subtaskName
    ){
        waitForAngularRequestsToFinish();
        fullTaskTree.waitUntilPresent();
        fullTaskTree.shouldContainText(projectName);
        fullTaskTree.shouldContainText(taskName);
        levelThreeTasks.shouldContainText(subtaskName);
    }

    public void verifyClonedProject(String projectName    ){
        waitForAngularRequestsToFinish();
        fullTaskTree.waitUntilPresent();
        fullTaskTree.shouldContainText(projectName);
    }

    public void deleteProjectFromActions(){
            int p = 0;
            while (allProjectsList.size() >= 1) {
                p++;
                System.out.println("Task number " + p + " is deleted");
                waitForAngularRequestsToFinish();
                firstProjectOnList.click();
                waitForAngularRequestsToFinish();
                moreActionsProject.waitUntilVisible().click();
                deleteFromMoreActions.waitUntilVisible().click();
                confirmDeleteOK.waitUntilClickable().click();
                confirmDeleteOK.waitUntilNotVisible();
                deleteFromMoreActions.waitUntilNotVisible();
                fadeModal.waitUntilNotVisible();
                waitForAngularRequestsToFinish();
            }
    }
    public void goingBackToTimesheet(){
        timesheetTopButton.waitUntilClickable().click();
    }
    public void cloneProject(){
        waitForAngularRequestsToFinish();
        projectOnList.waitUntilVisible().click();
        moreActionsProject.waitUntilVisible().click();
        cloneFromMoreActions.waitUntilVisible().click();
        confirmDeleteOK.waitUntilClickable().click();
        confirmDeleteOK.waitUntilNotVisible();
        deleteFromMoreActions.waitUntilNotVisible();
        fadeModal.waitUntilNotVisible();
    }

}
