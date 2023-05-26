package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

    //************ DON'T NEED EXCEL***********//
//    @DataProvider
//    public Object[][] incorrectLoginTestData() {
//        return new Object[][]{
//                {"Sel1234@gmail.com", "testingIncorrect"},
//                {"test1234", "Password1234"},
//                {"#&$*@&#$", "#@$&@#*$&"},
//        };
//    }
//
//    @Test(dataProvider = "incorrectLoginTestData")
//    public void loginWithWrongCredentialsTest(String userName, String password) {
//        Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName, password));
//
//    }

    //*************HOMEWORK*****************EXCEL TEST********************// POM_8 - 40:00

    @DataProvider(name = "incorrectLoginExcelData") //FOR ExcelUtil_POM8
    public Object[][] getIncorrectLoginExcelDataTest() {
        Object[][] regData = ExcelUtil.getTestData(AppConstants.INCORRECT_LOGIN_SHEET_NAME);
        return regData;
    }

    @Test(dataProvider = "incorrectLoginExcelData")
    public void loginWithWrongCredentialsTest(String userName, String password) {
        Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName, password));

    }


}

