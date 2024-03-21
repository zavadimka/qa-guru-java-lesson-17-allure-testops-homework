package com.zavadimka.softgamings.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Owner("Dmitry Zavada")
@Feature("Home Page Simple Tests")
@Tags({@Tag("ui"), @Tag("home_page")})
public class HomePageTests extends TestBase {

    @Test
    @DisplayName("Testing of Home Page opening")
    @Story("Testing the opening of the Home Page and verifying Home Page title")
    @Severity(SeverityLevel.CRITICAL)
    void homePageOpenTest() {

        homePage.openPage()
                .checkTitle(homePage.homePageTitle);
    }

    @Test
    @DisplayName("Testing of Home Page sections headers")
    @Story("Verifying Home Page sections headers")
    @Severity(SeverityLevel.CRITICAL)
    void homePageSectionsHeadersTest() {

        homePage.openPage()
                .checkPageSectionHeaders();
    }

    @Test
    @DisplayName("Testing of Home Page header menubar")
    @Story("Verifying header menubar titles and links opening test")
    @Severity(SeverityLevel.CRITICAL)
    void homePageHeaderMenuBarTest() {
        homePage.openPage();

        headerMenuBarItem.headerMenuBarVerification(headerMenuBar)
                .headerMenuBarLinksOpeningTest(headerMenuBar);
    }
}
