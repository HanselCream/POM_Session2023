package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

@Epic("EPIC 100: Account Page Test Design")
@Story("US 102: Account Page Test for OpenCart - AccPageSetUp, PageTitle, LogoutExist, My Account Login Exist, HeaderCount")
public class AccountPageTest extends BaseTest {

    @Severity(SeverityLevel.BLOCKER)
    @Description("This is setting up AccountPage")
    @Feature("Account Page Setup Test")
    @BeforeClass
    public void accPageSetup(){
       accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Checking Page Title")
    @Feature("Account Page Title Test")
    @Test
    public void accPageTitleTest() {
        String actTitle = accPage.getAccPageTitle();
        Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Checking Logout Exist")
    @Feature("Account Page Logout Exist Test")
    @Test
    public void isLogoutLinkExistTest() {
        Assert.assertTrue(accPage.isLogoutLinkExist());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Checking Account Link Exist")
    @Feature("Account Page Account Link Exist Test")
    @Test
    public void myAccountLinkExistTest() {
        Assert.assertTrue(accPage.isMyAccountLinkExist());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Checking header count")
    @Feature("Account Page Header Count Test")
    @Test
    public void accPageHeaderCountTest() {
        List<String> actAccHeaderList = accPage.getAccountPageHeadersList();
        Assert.assertEquals(actAccHeaderList.size(), 4);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Checking Header")
    @Feature("HeaderTest Page Test")
    @Test
    public void accPageHeaderTest() {
        List<String> actAccHeaderList = accPage.getAccountPageHeadersList();
        //Assert.assertEquals(actAccHeaderList, AppConstants.EXP_ACCOUNTS_HEADER_LIST); You can use this then delete below

        // Sort the header lists before comparing them
        Collections.sort(actAccHeaderList);
        Collections.sort(AppConstants.EXP_ACCOUNTS_HEADER_LIST);

        // Compare the sorted lists using assertEquals
        Assert.assertEquals(actAccHeaderList, AppConstants.EXP_ACCOUNTS_HEADER_LIST);
    }



}