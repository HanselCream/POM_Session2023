package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup(){
       accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accPageTitleTest() {
        String actTitle = accPage.getAccPageTitle();
        Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
    }

    @Test
    public void isLogoutLinkExistTest() {
        Assert.assertTrue(accPage.isLogoutLinkExist());
    }

    @Test
    public void myAccountLinkExistTest() {
        Assert.assertTrue(accPage.isMyAccountLinkExist());
    }

    @Test
    public void accPageHeaderCountTest() {
        List<String> actAccHeaderList = accPage.getAccountPageHeadersList();
        Assert.assertEquals(actAccHeaderList.size(), 4);
    }

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