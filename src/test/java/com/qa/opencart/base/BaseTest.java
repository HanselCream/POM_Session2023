package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
    @BeforeTest
    public void setup() {
        //Setup on DriverFactory
        df = new DriverFactory();
        prop = df.initProperties();
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