package com.timecamp.steps.cucumber;

import com.timecamp.pages.ProjectPage;
import com.timecamp.pages.TimesheetPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class CheckProjectPageFunctions {


    @Steps
    private TimesheetPage timesheetPage;

    @Steps
    private ProjectPage projectPage;


    @And("^user is going into project page$")
    public void userIsGoingIntoProjectPage() {
        timesheetPage.changePageToProjectsSection();
    }

    @When("^user on project page is adding a project using main widget$")
    public void userOnProjectPageIsAddingAProjectLEVELUsingMainWidget(Map<String,String> data) {
        projectPage.addNewProject(data.get("projectName"));
    }

    @And("^user add task using button located on project added before$")
    public void userAddTaskLEVELUsingButtonLocatedOnProjectAddedBefore(Map<String,String> data) {
        projectPage.addNewTask(data.get("taskName"));
    }

    @And("^user add subtask using button located on task added before$")
    public void userAddSubtaskLEVELUsingButtonLocatedOnTaskAddedBefore(Map<String,String> data) {
        projectPage.addNewSubtask(data.get("subtaskName"));
    }

    @Then("^we are checking that all added task are listed$")
    public void weAreCheckingThatAllAddedTaskAreListed(Map<String,String> data) {
        projectPage.verifyAddedProjectTask(
                data.get("projectName"),
                data.get("taskName"),
                data.get("subtaskName"));
    }
    @Then("^we are checking that cloned project is listed$")
    public void weAreCheckingThatClonedProjectIsListed(Map<String,String> data) {
        projectPage.verifyClonedProject(data.get("projectName"));
    }


    @And("^user is deleting added project, task, subtask$")
    public void userIsDeletingAddedProjectTaskSubtask(){
    projectPage.deleteProjectFromActions();
    }

    @And("^we are going back to timesheet$")
    public void weAreGoingBackToTimesheet() {
    projectPage.goingBackToTimesheet();
    }

    @And("^user is cloning created project$")
    public void userIsCloningCreatedProject() {
        projectPage.cloneProject();
    }
}
