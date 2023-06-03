package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;
//POM_10_HeadLess_Incognito_OptionsManager_HighlightElement_ThreadLocal
public class OptionsManager {

    private final Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

//Boolean.valueOf(stringValue);
//"true".equals(stringValue);
//"true".equalsIgnoreCase(stringValue);
//Boolean.parseBoolean(stringValue);


    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        return co;

//        co.addArguments("--headless");
//        co.addArguments("--disable-gpu");
//        co.addArguments("--no-sandbox");
//        co.addArguments("--disable-infobars");
//        co.addArguments("--disable-extensions");
//        co.addArguments("--start-maximized");
//        co.addArguments("--window-size=1280,800");
//        co.addArguments("--incognito");
//        co.addArguments("--disable-notifications");
//        co.addArguments("--disable-popup-blocking");
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
        }
        return fo;
    }

    public EdgeOptions getEdgeOptions() {
        eo = new EdgeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            eo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            eo.addArguments("--incognito");
        }
        return eo;

    }



}
