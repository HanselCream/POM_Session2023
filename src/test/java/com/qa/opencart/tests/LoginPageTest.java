package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageTitleTest() {
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
    }

    @Test
    public void loginLoginPageUrlTest() {
        String actUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
    }

    @Test
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

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
