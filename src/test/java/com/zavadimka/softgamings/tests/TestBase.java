package com.zavadimka.softgamings.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.zavadimka.softgamings.drivers.WebDriverProvider;
import com.zavadimka.softgamings.helpers.Attach;
import com.zavadimka.softgamings.pages.HomePage;
import com.zavadimka.softgamings.pages.LambdaSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    LambdaSteps steps = new LambdaSteps();

    @BeforeAll
    static void beforeAll() {
        WebDriverProvider.setDriverConfig();

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
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        System.out.println("The test is run with the following parameters:");

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
