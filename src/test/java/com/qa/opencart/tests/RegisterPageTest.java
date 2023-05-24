package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {

    @BeforeClass
    public void registrationSetup(){
        registerPage = loginPage.navigateToRegisterPage();
    }

    @Test
    public void userRegistrationTest() {
        registerPage.registerUser("lolo", "mo", "lolomo@gmail.com", "12312313", "papapasword","yes");
    }

}
