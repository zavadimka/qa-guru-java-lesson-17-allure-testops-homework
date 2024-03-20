package com.zavadimka.softgamings.drivers;

import com.codeborne.selenide.Configuration;
import com.zavadimka.softgamings.config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;

public class WebDriverProvider {

    static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void setDriverConfig() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
//        Configuration.remote = config.getRemoteUrl();
    }
}
