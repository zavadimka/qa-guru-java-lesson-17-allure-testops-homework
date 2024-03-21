package com.zavadimka.softgamings.drivers;

import com.zavadimka.softgamings.config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Configuration.*;
import static java.lang.System.getProperty;

public class WebDriverProvider {

    static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());


    public static void setDriverConfig() {
        baseUrl = config.getBaseUrl();
        browser = config.getBrowser();
        browserVersion = config.getBrowserVersion();
        browserSize = config.getBrowserSize();
        remote = config.getRemoteUrl();
    }

    public static void printDriverConfig() {
        System.out.printf("WebDriver: %s\nBrowser: %s\nVersion: %s\nWindow size: %s\nRemote URL: %s%n",
                getProperty("driver"), browser, browserVersion, browserSize, remote);
    }
}
