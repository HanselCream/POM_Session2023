package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    protected LoginPage loginPage;
    protected AccountsPage accPage;
    protected ResultsPage resultsPage;
    protected ProductInformationPage productInfoPage;
    protected SoftAssert softAssert;
    protected RegisterPage registerPage;

    //-------------------------//

    protected DriverFactory df;
    protected Properties prop;

    @Parameters({"browser"}) //ADDED POM_9
    @BeforeTest
    public void setup(String browserName) { //Added "String browserName" POM_9
        //Setup on DriverFactory
        df = new DriverFactory();
        prop = df.initProperties();

        if(browserName!=null) {
            prop.setProperty("browser", browserName);
        }

        driver = df.initDriver(prop);


        //Setup on LoginPage
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }
        @AfterTest
        public void tearDown(){
            driver.quit();
        }

}