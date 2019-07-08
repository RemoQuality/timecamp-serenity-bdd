package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;


public class ImportPage extends PageObject {


    @FindBy(xpath = "//h1[@class='tc-title'][contains(.,'Import Projects')]")
    private WebElementFacade importPageTitle;

    @FindBy(id="js-csv-data")
    private WebElementFacade inputForCsv;

    @FindBy(id="js-import-users")
    private WebElementFacade buttonImport;

    public void checkThatIsImportPage(){
        importPageTitle.shouldBeVisible();
    }

    public void importDataUsingCsv(String csvImportData){
        inputForCsv.type(csvImportData);
        buttonImport.waitUntilVisible().click();
    }

}
