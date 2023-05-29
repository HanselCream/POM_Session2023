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

        //POM_9_ParallelTest_CrossBrowserParameters_MultiEnvironmentSetup => 36:00
        //mvn clean install -Denv="qa"
        //mvn clean install

        Properties prop = new Properties();
        FileInputStream ip = null;

        String envName = System.getProperty("env");
        System.out.println("Running test cases on env: " + envName);

        try {
        if (envName == null) {
            System.out.println("No Env is given .. hence running it on QA environment .. ");
            ip = new FileInputStream("./src/main/resources/config/qa.config.properties");

        } else {
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/main/resources/config/config.properties");
                        break;

                    default:
                        System.out.println("Please print the right env name ... " + envName);
                        throw new FrameException("NOVALIDENVIRONEMENTGIVEN");
                }
            }
        }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
        try {
            prop.load(ip);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
        }
}

        //*******************//
//        Properties prop = new Properties();
//        try {
//            FileInputStream ip = new FileInputStream("./src/main/resources/config/config.properties");
//            prop.load(ip);

//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return prop;
//    }
//}
