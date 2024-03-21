package com.zavadimka.softgamings.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.opencsv.exceptions.CsvValidationException;
import com.zavadimka.softgamings.drivers.WebDriverProvider;
import com.zavadimka.softgamings.helpers.Attach;
import com.zavadimka.softgamings.pages.HomePage;
import com.zavadimka.softgamings.pages.components.HeaderMenuBarItem;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {
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

    @BeforeAll
    static void beforeAll() {
        createWebDriverConfig();

        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 100_000;
        Configuration.timeout = 15_000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "geolocation", false
        ));

        Configuration.browserCapabilities = capabilities;

        System.out.println("The test is run with the following WebDriver parameters:");
        WebDriverProvider.printDriverConfig();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    private static void createWebDriverConfig() {
        String driver = System.getProperty("driver", "local");
        System.setProperty("driver", driver);

        WebDriverProvider.setDriverConfig();
    }
}
