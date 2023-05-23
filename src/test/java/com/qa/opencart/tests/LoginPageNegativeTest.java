package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

    @DataProvider
    public Object[][] incorrectLoginTestData() {
        return new Object[][]{
                {"Sel1234@gmail.com", "testingIncorrect"},
                {"test1234", "Password1234"},
                {"#&$*@&#$", "#@$&@#*$&"},
        };
    }

    @Test(dataProvider = "incorrectLoginTestData")
    public void loginWithWrongCredentialsTest(String userName, String password) {
        Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName, password));

    }


}