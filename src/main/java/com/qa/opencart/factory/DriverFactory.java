package com.qa.opencart.factory;

import com.qa.opencart.frameworkexception.FrameException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    WebDriver driver;
    OptionsManager optionsManager;
    public static String highlightElement; //POM_10 32:13

    /**
     A ThreadLocal variable in Java allows each thread to have its own copy of a variable,
     such as a driver instance, ensuring thread safety and isolation.
     */
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); //POM_10 51:00

    public WebDriver initDriver(Properties prop) {
//        String browserName = prop.getProperty("browser"); //To call on config properties
//        System.out.println("Browser Name is : " + browserName);

        optionsManager = new OptionsManager(prop); //from OptionManager => POM_10

        highlightElement = prop.getProperty("highlight"); //POM_10 32:13

        //mvn clean install -Denv="qa" -Dbrowser="chrome"   =>POM_11 : 26:50
        String browserName = System.getProperty("browser");  //I have Commented out 23 & 24 so that I can call this on my terminal
        System.out.println("browser name is : " + browserName);

        switch (browserName.toLowerCase()) {
            case "chrome":
                //driver = new ChromeDriver(optionsManager.getChromeOptions()); //Comment: Because we will be using ThreadLocalDriver => POM_10 53:40
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "firefox":
                //driver = new FirefoxDriver(optionsManager.getFirefoxOptions()); //Comment: Because we will be using ThreadLocalDriver => POM_10 53:40
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "edge":
                //driver = new EdgeDriver(optionsManager.getEdgeOptions()); //Comment: Because we will be using ThreadLocalDriver => POM_10 53:40
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;

            default:
                System.out.println("please pass the right browser");
                throw new FrameException("NOBROWSERFOUNDEXCEPTION");
        }

        //This is a change from driver.manage(): POM_10: 59:20
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();


        //Comment 4 Syntax, making it into ThreadLocal driver: POM_10: 59:20
//        driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//        driver.get(prop.getProperty("url"));
//        return driver;
    }


    //return the thread local copy of the driver
    public synchronized static WebDriver getDriver() { //POM_10: synchronized, is not mandatory you can use or not, just for synchronization
        return tlDriver.get(); //POM_10: Adding his so the driver knows where to getMethod
    }


    public Properties initProperties() {

        //POM_9_ParallelTest_CrossBrowserParameters_MultiEnvironmentSetup => 36:00
        //C:\java-intellij\POM_Session2023
        //mvn clean install -Denv="qa"
        //mvn clean install -Denv="qa" -Dbrowser="chrome"
        //mvn clean install -Denv="qa" -Dbrowser="chrome" -Dpassword="password" =>LoginPageTest


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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(ip);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


//********initProperties********//
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

    /**
     * take screenshot
     */
    public static String getScreenshot() { //POM_11 : 52:21 =>ExtentReport
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs((OutputType.FILE));

        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination); // FileUtils should be coming from "org.apache.commons.io.FileUtils"
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


}



