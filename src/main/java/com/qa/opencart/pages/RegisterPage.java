package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;
    private final ElementUtility eleUtil;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtility(this.driver);
    }

    private final By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmPassword = By.id("input-confirm");

    private final By subscribeYes = By.xpath("(//label/input[@name='newsletter'])[1]");
    private final By subscribeNo = By.xpath("(//label/input[@name='newsletter'])[2]");

    private final By privacyPolicyCheckBox = By.name("agree");
    private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

    private final By userRegistrationSuccessMessage = By.cssSelector("div#content h1");
    private final By logoutLink = By.linkText("Logout");
    private final By registerLink = By.linkText("Register");



    public String registerUser(String firstName, String lastName,
                               String email, String telephone, String password, String subscribe) {

        eleUtil.waitForElementVisible(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT);
        eleUtil.doSendKeys(this.firstName, firstName);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.confirmPassword, password);

//        if (subscribe.equalsIgnoreCase("yes")) {  => Move to private void doSubscribe(String subscribe)
//            eleUtil.doClick(subscribeYes);
//        } else {
//            eleUtil.doClick(subscribeNo);
//        }

        doSubscribe(subscribe);


        eleUtil.doClick(privacyPolicyCheckBox);
        eleUtil.doClick(continueButton);

        //Message: Your Account Has Been Created!
        String userRegSuccessMessage = eleUtil.waitForElementVisible(userRegistrationSuccessMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
        System.out.println(userRegSuccessMessage);

        eleUtil.doClick(logoutLink);
        eleUtil.doClick(registerLink);

        return userRegSuccessMessage;
    }

    private void doSubscribe(String subscribe) {
        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYes);
        } else {
            eleUtil.doClick(subscribeNo);
        }
    }

}
