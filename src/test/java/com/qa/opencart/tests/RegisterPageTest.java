package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

public class RegisterPageTest extends BaseTest { //POM_7_CommonDataProvider_With_POJO_RegisterPage_RandomEmai

    @BeforeClass
    public void registrationSetup(){
        registerPage = loginPage.navigateToRegisterPage();
    }

    public String getRandomEmailID() {
        return "testautomation"+System.currentTimeMillis()+"@gmail.com"; //=> You can use this, only one
        //return "testautomation"+ UUID.randomUUID()+"@gmail.com"; //=> You can use this, only one


    }

    @DataProvider(name = "regData")
    public Object[][] getUserRegTestData() {
        return new Object[][] {
                {"PhoneKo", "mo", "12312313", "papapasword","yes"},
                {"mama", "ko", "12323313", "HowManyWord","no"},
                {"Xiaomi", "automate", "1239913", "TheOneTwo","yes"}

        };
    }
//    @Test //=> YOU CAN USE THIS WOUT DATA PROVIDER from 1:14:37_POM_7
//    public void userRegistrationTest() {
//        String actRegistrationSuccessMessage =registerPage.registerUser("lolo", "mo", getRandomEmailID(), "12312313", "papapasword","yes");
//        Assert.assertEquals(actRegistrationSuccessMessage, AppConstants.USER_REG_SUCCESS_MSG);
//    }

    @Test(dataProvider = "regData")
    public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
        String actRegistrationSuccessMessage =
                registerPage.registerUser(firstName, lastName, getRandomEmailID(), telephone, password,subscribe);
        Assert.assertEquals(actRegistrationSuccessMessage, AppConstants.USER_REG_SUCCESS_MSG);
    }

}
