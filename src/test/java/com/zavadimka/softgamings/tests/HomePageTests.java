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
    @Story("Testing the opening of the Home Page and and verifying header menubar and Home Page sections headers")
    @Severity(SeverityLevel.CRITICAL)
    void homePageOpenTest() {

        steps.openHomePage();
    }

    @Test
    @DisplayName("Testing of Home Page sections headers")
    @Story("Verifying Home Page sections headers")
    @Severity(SeverityLevel.CRITICAL)
    void homePageSectionsHeadersTest() {
        steps.openHomePage();
        steps.checkPageSectionHeaders();
    }

    @Test
    @DisplayName("Testing of Home Page header menubar")
    @Story("Verifying header menubar titles and links opening")
    @Severity(SeverityLevel.CRITICAL)
    void homePageHeaderMenuBarTest() {
        steps.openHomePage();
        steps.checkHeaderMenuBarFields();
        steps.checkHeaderMenuBarLinksOpening();
    }
}
