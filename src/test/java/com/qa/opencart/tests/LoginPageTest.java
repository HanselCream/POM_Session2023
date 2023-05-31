package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Epic 100: Login Page Design")
@Story("US 101: Design Login Page for OpenCart App with Title, URL, Forgot PWD Links, User is able to Login")
public class LoginPageTest extends BaseTest {

    @Severity(SeverityLevel.MINOR) //POM_12 31:00
    @Description("Checking LoginPage Title Test ...") //POM_12 31:00
    @Feature("Title Test") //POM_12 31:00
    @Test
    public void loginPageTitleTest() {
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
    }

    @Severity(SeverityLevel.NORMAL) //POM_12 31:00
    @Description("Checking LoginPage URL Test ...")
    @Feature("URL Test")
    @Test
    public void loginLoginPageUrlTest() {
        String actUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
    }

    @Severity(SeverityLevel.BLOCKER) //POM_12 31:00
    @Description("Checking Forgot PWD Link Test ...")
    @Feature("Forgot PWD Link Test")
    @Test
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Severity(SeverityLevel.BLOCKER) //POM_12 31:00
    @Description("Checking User is able to login with correct username/password Test ...")
    @Feature("Login Test")
    @Test
    public void loginTest() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogoutLinkExist());
        Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
    }

//    @Test //mvn clean install -Denv="qa" -Dbrowser="chrome" -Dpassword="password"
//    public void loginTest() {
//        accPage = loginPage.doLogin(prop.getProperty("username"), System.getProperty("password")); //Using this for mvn clean install -Denv="qa" -Dbrowser="chrome" -Dpassword="password"
//        Assert.assertTrue(accPage.isLogoutLinkExist());
//        Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
//    }


}
