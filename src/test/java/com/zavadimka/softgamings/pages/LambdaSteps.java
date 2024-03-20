package com.zavadimka.softgamings.pages;

import com.opencsv.exceptions.CsvValidationException;
import com.zavadimka.softgamings.pages.components.HeaderMenuBarItem;
import com.zavadimka.softgamings.pages.components.PageSection;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

public class LambdaSteps {

    HomePage homePage = new HomePage();
    HeaderMenuBarItem headerMenuBarItem = new HeaderMenuBarItem();
    ArrayList<HeaderMenuBarItem>[] headerMenuBar;

    {
        try {
            headerMenuBar = headerMenuBarItem.getHeaderMenuBarFromCsv();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openHomePage() {
        step("### Open Home Page and verify Home Page Title", () -> {
            homePage.openPage();
            homePage.checkTitle();
        });
    }

    public void checkPageSectionHeaders() {
        step("### Check Page section headers: " + homePage.pageLabel, () -> {
            homePage.checkPageSectionHeaders();
        });
    }

    public void checkHeaderMenuBarFields(){
        step("### Check the header MenuBar field titles and links", () -> {
            headerMenuBarItem.headerMenuBarVerification(headerMenuBar);
        });
    }

    public void checkHeaderMenuBarLinksOpening(){
        step("### Check the header MenuBar links opening", () -> {
            headerMenuBarItem.headerMenuBarLinksOpeningTest(headerMenuBar);
        });
    }

}
