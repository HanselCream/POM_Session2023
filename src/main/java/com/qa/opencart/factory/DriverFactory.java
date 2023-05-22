package com.qa.opencart.factory;

import com.qa.opencart.frameworkexception.FrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    WebDriver driver;
    public WebDriver initDriver(Properties prop) {

        String browserName = prop.getProperty("browser"); //To call on config properties

        System.out.println("Browser Name is : " + browserName);

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("please pass the right browser");
                throw new FrameException("NOBROWSERFOUNDEXCEPTION");
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;
    }

    public Properties initProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/main/resources/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}
