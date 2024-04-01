package com.zavadimka.softgamings.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${driver}.properties"
})
public interface WebDriverConfig extends Config {

    @Key("base_url")
    @DefaultValue("https://www.softgamings.com/ru")
    String getBaseUrl();

    @Key("browser")
    String getBrowser();

    @Key("browser_version")
    String getBrowserVersion();

    @Key("browser_size")
    String getBrowserSize();

    @Key("remote_url")
    String getRemoteUrl();
}
